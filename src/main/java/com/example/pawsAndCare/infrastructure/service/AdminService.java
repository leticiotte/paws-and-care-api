package com.example.pawsAndCare.infrastructure.service;

import com.example.pawsAndCare.domain.exception.DuplicatedDocumentException;
import com.example.pawsAndCare.domain.model.Admin;
import com.example.pawsAndCare.domain.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin) throws DuplicatedDocumentException {
        try{
            adminRepository.insert(admin);
            return admin;
        }catch (DuplicateKeyException e) {
            throw new DuplicatedDocumentException("Já existe um administrador com esse documento.");
        }
    }

    public Optional<Admin> getById(String id) {
        return adminRepository.findById(id);
    }


}
