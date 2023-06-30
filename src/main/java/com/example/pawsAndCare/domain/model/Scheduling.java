package com.example.pawsAndCare.domain.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Scheduling {
    @Id
    private String id;
    @NotBlank
    private String customerId;
    @NotBlank
    private String petId;
    @NotBlank
    private String date;

    public Scheduling() {
    }

    public Scheduling(String id, String customerId, String petId, String date) {
        this.id = id;
        this.customerId = customerId;
        this.petId = petId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
