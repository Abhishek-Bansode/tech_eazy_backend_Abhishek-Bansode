package com.abhishek.techeazy.controller;

import com.abhishek.techeazy.dto.ParcelDTO;
import com.abhishek.techeazy.service.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class PublicController {

    @Autowired
    private ParcelService parcelService;

    @GetMapping("/parcels/{trackingId}")
    public ResponseEntity<ParcelDTO> getParcelByTrackingId(@PathVariable String trackingId) {
        ParcelDTO dto = parcelService.getParcelByTrackingId(trackingId);
        return ResponseEntity.ok(dto);
    }
}
