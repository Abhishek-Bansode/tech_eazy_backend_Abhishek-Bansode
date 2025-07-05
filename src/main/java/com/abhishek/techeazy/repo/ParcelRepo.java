package com.abhishek.techeazy.repo;

import java.util.List;

import com.abhishek.techeazy.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelRepo extends JpaRepository<Parcel, String> {
    List<Parcel> findByDeliveryStatus(String status);
}
