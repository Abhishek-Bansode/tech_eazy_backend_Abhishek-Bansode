package com.abhishek.techeazy.controller;

import java.util.List;

import com.abhishek.techeazy.dto.ParcelDTO;
import com.abhishek.techeazy.entity.Parcel;
import com.abhishek.techeazy.service.ParcelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ParcelDTO>> getAllParcels() {
        return ResponseEntity.ok(parcelService.getAllParcels());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ParcelDTO> getParcelById(@PathVariable String id) {
        return ResponseEntity.ok(parcelService.getParcelById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Parcel> createParcel(@RequestBody ParcelDTO dto) {
        Parcel created = parcelService.createParcel(dto);
        return ResponseEntity.ok(created);
    }

}
