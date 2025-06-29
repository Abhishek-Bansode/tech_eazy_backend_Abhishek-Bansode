package com.abhishek.parcel.service;

import java.util.List;

import com.abhishek.parcel.dto.ParcelDTO;
import com.abhishek.parcel.entity.Parcel;
import com.abhishek.parcel.repo.ParcelRepo;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {
    private final ParcelRepo parcelRepo;

    public ParcelService(ParcelRepo parcelRepo) {
        this.parcelRepo = parcelRepo;
    }

    public List<Parcel> getAllParcels() {
        return parcelRepo.findAll();
    }

    public Parcel getParcelById(String id) {
        return parcelRepo.findById(id).orElseThrow(() -> new RuntimeException("Parcel not found"));
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
}
