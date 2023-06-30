package com.example.pawsAndCare.domain.model.dto;

import com.example.pawsAndCare.domain.model.Scheduling;

public class SchedulingDTO extends Scheduling {
    private String customerName;
    private String petName;

    public SchedulingDTO() {

    }

    public SchedulingDTO(String customerName, String petName) {
        this.customerName = customerName;
        this.petName = petName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}
