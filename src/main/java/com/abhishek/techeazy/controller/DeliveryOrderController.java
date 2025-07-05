package com.abhishek.techeazy.controller;

import java.time.LocalDate;
import java.util.List;

import com.abhishek.techeazy.dto.DeliveryOrderDTO;
import com.abhishek.techeazy.entity.DeliveryOrder;
import com.abhishek.techeazy.service.DeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/delivery-orders")
public class DeliveryOrderController {

    @Autowired
    private DeliveryOrderService deliveryOrderService;

    @PostMapping("/upload")
    public DeliveryOrderDTO uploadOrder(
            @RequestParam String vendorName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate orderDate,
            @RequestParam MultipartFile file
            ) throws Exception {
        return deliveryOrderService.uploadOrder(vendorName, orderDate, file);
    }

    @GetMapping("/today")
    public List<DeliveryOrderDTO> getTodayOrders() {
        return deliveryOrderService.getTodayOrders();
    }

    @GetMapping
    public List<DeliveryOrder> getByVendorAndDate(
            @RequestParam String vendor,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
      return deliveryOrderService.getOrdersByVendorAndDate(vendor, date);
    }

}
