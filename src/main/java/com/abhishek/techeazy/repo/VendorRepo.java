package com.abhishek.techeazy.repo;

import com.abhishek.techeazy.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<Vendor, Long> {
    Vendor findByName(String vendorName);
}
