package com.abhishek.techeazy.repo;

import java.util.List;
import java.util.Optional;

import com.abhishek.techeazy.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepo extends JpaRepository<Parcel, String> {
    List<Parcel> findByDeliveryStatus(String status);

    Optional<Parcel> findByTrackingId(String trackingId);
}
