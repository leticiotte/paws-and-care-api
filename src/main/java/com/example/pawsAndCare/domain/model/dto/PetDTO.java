package com.example.pawsAndCare.domain.model.dto;

import com.example.pawsAndCare.domain.model.Pet;

public class PetDTO extends Pet {
    private String customerName;

    public PetDTO() {
    }

    public PetDTO(String customerName) {
        this.customerName = customerName;
    }

    public PetDTO(String id, String name, String customerId, String type, String breed, String customerName) {
        super(id, name, customerId, type, breed);
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
