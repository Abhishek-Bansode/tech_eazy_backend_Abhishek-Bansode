package com.abhishek.techeazy.service;

import com.abhishek.techeazy.dto.VendorDTO;
import com.abhishek.techeazy.entity.Vendor;
import com.abhishek.techeazy.repo.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    @Autowired
    private VendorRepo vendorRepo;

    public Vendor saveVendor(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setName(dto.getName());
        vendor.setSubscriptionType(dto.getSubscriptionType());
        return vendorRepo.save(vendor);
    }

    public Page<Vendor> getAllVendors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return vendorRepo.findAll(pageable);
    }

    public Vendor getVendorById(Long id) {
        return vendorRepo.findById(id).orElse(null);
    }
}
