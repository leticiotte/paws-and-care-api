package com.example.pawsAndCare.domain.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin {
    @Id
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String document;
    @Indexed(unique = true)
    @NotBlank
    private String email;

    public Admin() {
    }

    public Admin(String id, String name, String password, String email, String document) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.document = document;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
