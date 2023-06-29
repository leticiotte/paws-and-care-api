package com.example.pawsAndCare.application.controller;

import com.example.pawsAndCare.domain.exception.CustomerNotFoundException;
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
@RequestMapping(value = "/api/v1/pets")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllPets(@RequestParam(name = "q" , required = false) String q) {
        if (q != null && !q.isBlank()) {
            return ResponseEntity.ok().body(
                    petService.getPetsBySearch(q)
            );
        }
        return ResponseEntity.ok().body(
                petService.getAllPets()
        );
    }

    @PostMapping
    public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet) throws CustomerNotFoundException {
        Optional<Customer> customer = customerService.getById(pet.getCustomerId());
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Cliente não encontrado.");
        }

        pet = petService.createPet(pet);

        return ResponseEntity.ok().body(pet);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PetDTO> getById(@PathVariable String id) {
        Optional<PetDTO> petDTO = petService.getById(id);
        return petDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable String id, @Valid @RequestBody Pet pet) throws CustomerNotFoundException {
        Optional<Customer> customer = customerService.getById(pet.getCustomerId());
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Cliente não encontrado.");
        }

        pet.setId(id);
        pet = petService.updatePet(pet);

        return ResponseEntity.ok().body(pet);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletePet(@PathVariable String id) {
        petService.deletePet(id);

        return ResponseEntity.noContent().build();
    }
}
