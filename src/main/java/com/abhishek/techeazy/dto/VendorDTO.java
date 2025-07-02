package com.abhishek.techeazy.dto;

import com.abhishek.techeazy.entity.VendorSubscriptionType;

public class VendorDTO {
    private String name;
    private VendorSubscriptionType subscriptionType;

    // Getter and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VendorSubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(VendorSubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }
}
