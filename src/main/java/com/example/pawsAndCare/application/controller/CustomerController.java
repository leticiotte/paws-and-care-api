package com.example.pawsAndCare.application.controller;

import com.example.pawsAndCare.domain.exception.CustomerDependencyException;
import com.example.pawsAndCare.domain.exception.DuplicatedDocumentException;
import com.example.pawsAndCare.domain.model.Customer;
import com.example.pawsAndCare.domain.model.Pet;
import com.example.pawsAndCare.domain.model.dto.PetDTO;
import com.example.pawsAndCare.infrastructure.service.CustomerService;
import com.example.pawsAndCare.infrastructure.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(@RequestParam(name = "q" , required = false) String q) {
        if (q != null && !q.isBlank()) {
            return ResponseEntity.ok().body(
                    customerService.getCustomersBySearch(q)
            );
        }
        return ResponseEntity.ok().body(
                customerService.getAllCustomers()
        );
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws DuplicatedDocumentException {
        customer = customerService.createCustomer(customer);

        return ResponseEntity.ok().body(customer);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> getById(@PathVariable String id) {
        Optional<Customer> customer = customerService.getById(id);
        return customer.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @Valid @RequestBody Customer customer) {
        customer.setId(id);
        customer = customerService.updateCustomer(customer);

        return ResponseEntity.ok().body(customer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable String id) throws CustomerDependencyException {
        List<PetDTO> pets = petService.getPetsByCustomerId(id);
        if (pets.size() != 0) {
            throw new CustomerDependencyException("Cliente possui pets cadastrados.");
        }
        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/pets")
    public ResponseEntity<List<PetDTO>> getPets(@PathVariable String id) {
        List<PetDTO> pets = petService.getPetsByCustomerId(id);

        return ResponseEntity.ok().body(pets);
    }
}
