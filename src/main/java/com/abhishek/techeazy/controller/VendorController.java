package com.abhishek.techeazy.controller;

import com.abhishek.techeazy.dto.VendorDTO;
import com.abhishek.techeazy.entity.Vendor;
import com.abhishek.techeazy.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping
    public Vendor createVendor(@RequestBody VendorDTO dto) {
        return vendorService.saveVendor(dto);
    }

    @GetMapping
    public Page<Vendor> getAllVendors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return vendorService.getAllVendors(page, size);
    }

    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

}
