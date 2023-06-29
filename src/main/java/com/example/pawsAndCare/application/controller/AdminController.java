package com.example.pawsAndCare.application.controller;

import com.example.pawsAndCare.domain.exception.DuplicatedDocumentException;
import com.example.pawsAndCare.domain.model.Admin;
import com.example.pawsAndCare.domain.model.dto.AdminDTO;
import com.example.pawsAndCare.domain.utils.PasswordUtils;
import com.example.pawsAndCare.infrastructure.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok().body(
                adminService.getAllAdmins().stream().map(admin -> {
                    AdminDTO adminDTO = new AdminDTO();
                    BeanUtils.copyProperties(admin, adminDTO);
                    return adminDTO;
                }).collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AdminDTO> getById(@PathVariable String id) {
        Optional<Admin> admin = adminService.getById(id);
        if (admin.isPresent()) {
            AdminDTO adminDTO = new AdminDTO();
            BeanUtils.copyProperties(admin.get(), adminDTO);
            return ResponseEntity.ok(adminDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@Valid @RequestBody Admin admin) throws DuplicatedDocumentException {
        admin.setPassword(PasswordUtils.encrypt(admin.getPassword()));
        admin = adminService.createAdmin(admin);
        AdminDTO adminDTO = new AdminDTO();
        BeanUtils.copyProperties(admin, adminDTO);

        return ResponseEntity.ok().body(adminDTO);
    }
}
