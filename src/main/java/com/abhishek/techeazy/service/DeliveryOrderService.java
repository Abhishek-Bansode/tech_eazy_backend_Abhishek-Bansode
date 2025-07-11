package com.abhishek.techeazy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.abhishek.techeazy.dto.DeliveryOrderDTO;
import com.abhishek.techeazy.dto.ParcelDTO;
import com.abhishek.techeazy.entity.DeliveryOrder;
import com.abhishek.techeazy.entity.Parcel;
import com.abhishek.techeazy.entity.Vendor;
import com.abhishek.techeazy.repo.DeliveryOrderRepo;
import com.abhishek.techeazy.repo.ParcelRepo;
import com.abhishek.techeazy.repo.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DeliveryOrderService {

    @Autowired
    private DeliveryOrderRepo deliveryOrderRepo;

    @Autowired
    private ParcelRepo parcelRepo;

    @Autowired
    private VendorRepo vendorRepo;

    public DeliveryOrderDTO uploadOrder(LocalDate date, MultipartFile file) throws Exception {
        // Extract the logged-in vendor using SecurityContextHolder
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }

        Vendor loggedInVendor = vendorRepo.findByName(username);
        if (loggedInVendor == null) {
            throw new RuntimeException("Vendor not found for username: " + username);
        }

        List<Parcel> parcels = getParcelList(file);

        DeliveryOrder order = new DeliveryOrder();
        order.setVendor(loggedInVendor);
        order.setOrderDate(date);
        order.setFileName(file.getOriginalFilename());
        order.setParcels(parcels);
        parcels.forEach(p -> p.setDeliveryOrder(order));

        DeliveryOrder saved = deliveryOrderRepo.save(order);

        DeliveryOrderDTO dto = new DeliveryOrderDTO();
        dto.setVendorName(loggedInVendor.getName());
        dto.setOrderDate(saved.getOrderDate());
        dto.setTotalOrders(parcels.size());
        dto.setFileDownloadLink("api/v1/delivery-orders/download/" + saved.getId());
        dto.setParcels(saved.getParcels().stream().map(this::toParcelDTO).toList());

        return dto;
    }

    private static List<Parcel> getParcelList(MultipartFile file) throws IOException {
        List<Parcel> parcels = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 4) {
                Parcel p = new Parcel();
                p.setCustomerName(parts[0].trim());
                p.setDeliveryAddress(parts[1].trim());
                p.setSize(parts[2].trim());
                p.setWeight(Double.parseDouble(parts[3].trim()));
                parcels.add(p);
            }
        }
        return parcels;
    }

    public List<DeliveryOrderDTO> getTodayOrders() {
        List<DeliveryOrder> orders = deliveryOrderRepo.findByOrderDate(LocalDate.now());

        return orders.stream()
                .map(this::toDeliveryOrderDTO)
                .toList();
    }

    public List<DeliveryOrder> getOrdersByVendorAndDate(String vendorName, LocalDate date) {
        return deliveryOrderRepo.findByVendor_NameAndOrderDate(vendorName, date);
    }

    private ParcelDTO toParcelDTO(Parcel p) {
        ParcelDTO dto = new ParcelDTO();
        dto.id = p.getTrackingId();
        dto.customerName = p.getCustomerName();
        dto.deliveryAddress = p.getDeliveryAddress();
        dto.contactNumber = p.getContactNumber();
        dto.size = p.getSize();
        dto.weight = p.getWeight();
        return dto;
    }

    private DeliveryOrderDTO toDeliveryOrderDTO(DeliveryOrder order) {
        DeliveryOrderDTO dto = new DeliveryOrderDTO();
        dto.setOrderDate(order.getOrderDate());
        dto.setVendorName(order.getVendor().getName());
        dto.setTotalOrders(order.getParcels().size());
        dto.setFileDownloadLink("/api/delivery-orders/download/" + order.getId());

        // map parcels
        dto.setParcels(
                order.getParcels()
                        .stream()
                        .map(this::toParcelDTO)
                        .toList()
        );
        return dto;
    }

}
