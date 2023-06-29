package com.example.pawsAndCare.domain.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pet {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String customerId;
    @NotBlank
    private String type;
    @NotBlank
    private String breed;

    public Pet() {
    }

    public Pet(String id, String name, String customerId, String type, String breed) {
        this.id = id;
        this.name = name;
        this.customerId = customerId;
        this.type = type;
        this.breed = breed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
