package com.abhishek.parcel.controller;

import java.util.List;

import com.abhishek.parcel.dto.ParcelDTO;
import com.abhishek.parcel.entity.Parcel;
import com.abhishek.parcel.service.ParcelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parcels")
public class ParcelController {
    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    // ✅ GET all parcels
    @GetMapping
    public ResponseEntity<List<Parcel>> getAllParcels() {
        return ResponseEntity.ok(parcelService.getAllParcels());
    }

    // ✅ GET parcel by ID
    @GetMapping("/{id}")
    public ResponseEntity<Parcel> getParcelById(@PathVariable String id) {
        return ResponseEntity.ok(parcelService.getParcelById(id));
    }

    // ✅ POST create parcel
    @PostMapping
    public ResponseEntity<Parcel> createParcel(@RequestBody ParcelDTO dto) {
        Parcel created = parcelService.createParcel(dto);
        return ResponseEntity.ok(created);
    }

}
