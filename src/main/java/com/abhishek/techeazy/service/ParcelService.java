package com.abhishek.techeazy.service;

import java.util.List;

import com.abhishek.techeazy.dto.ParcelDTO;
import com.abhishek.techeazy.entity.Parcel;
import com.abhishek.techeazy.repo.ParcelRepo;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {
    private final ParcelRepo parcelRepo;

    public ParcelService(ParcelRepo parcelRepo) {
        this.parcelRepo = parcelRepo;
    }

    public List<ParcelDTO> getAllParcels() {
        return parcelRepo.findAll().stream().map(this::toParcelDTO).toList();
    }

    public ParcelDTO getParcelById(String id) {
        Parcel parcel =  parcelRepo.findById(id).orElseThrow(() -> new RuntimeException("Parcel not found"));
        return toParcelDTO(parcel);
    }

    public Parcel createParcel(ParcelDTO dto) {
        Parcel p = new Parcel();
        p.setCustomerName(dto.customerName);
        p.setDeliveryAddress(dto.deliveryAddress);
        p.setContactNumber(dto.contactNumber);
        p.setSize(dto.size);
        p.setWeight(dto.weight);
        return parcelRepo.save(p);
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

    public ParcelDTO getParcelByTrackingId(String trackingId) {
        Parcel parcel = parcelRepo.findByTrackingId(trackingId)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));
        return toParcelDTO(parcel);
    }

}
