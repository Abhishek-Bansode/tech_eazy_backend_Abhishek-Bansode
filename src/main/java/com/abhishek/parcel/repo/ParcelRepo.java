package com.abhishek.parcel.repo;

import java.util.List;

import com.abhishek.parcel.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepo extends JpaRepository<Parcel, String> {
    List<Parcel> findByDeliveryStatus(String status);
}
