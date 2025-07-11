package com.abhishek.techeazy.repo;

import java.time.LocalDate;
import java.util.List;

import com.abhishek.techeazy.entity.DeliveryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOrderRepo extends JpaRepository<DeliveryOrder, Long> {
    List<DeliveryOrder> findByOrderDate(LocalDate orderDate);

    List<DeliveryOrder> findByVendor_NameAndOrderDate(String vendorName, LocalDate orderDate);
}
