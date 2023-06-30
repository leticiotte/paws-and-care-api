package com.example.pawsAndCare.infrastructure.service;

import com.example.pawsAndCare.domain.model.Customer;
import com.example.pawsAndCare.domain.model.Pet;
import com.example.pawsAndCare.domain.model.dto.PetDTO;
import com.example.pawsAndCare.domain.repository.CustomerRepository;
import com.example.pawsAndCare.domain.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<PetDTO> getPetsByCustomerId(String id) {
        List<Pet> pets = petRepository.findByCustomerId(id);
        return pets.stream().map(item -> {
            PetDTO petDTO = new PetDTO();
            petDTO.setId(item.getId());
            petDTO.setName(item.getName());
            petDTO.setCustomerId(item.getCustomerId());
            petDTO.setType(item.getType());
            petDTO.setBreed(item.getBreed());
            Optional<Customer> customer = customerRepository.findById(item.getCustomerId());
            petDTO.setCustomerName(customer.get().getName());
            return petDTO;
        }).collect(Collectors.toList());
    }

    public List<PetDTO> getPetsBySearch(String q) {
        List<Pet> pets = petRepository.findPetsBySearch(q);
        return pets.stream().map(item -> {
            PetDTO petDTO = new PetDTO();
            petDTO.setId(item.getId());
            petDTO.setName(item.getName());
            petDTO.setCustomerId(item.getCustomerId());
            petDTO.setType(item.getType());
            petDTO.setBreed(item.getBreed());
            Optional<Customer> customer = customerRepository.findById(item.getCustomerId());
            petDTO.setCustomerName(customer.get().getName());
            return petDTO;
        }).collect(Collectors.toList());
    }

    public List<PetDTO> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return pets.stream().map(item -> {
            PetDTO petDTO = new PetDTO();
            petDTO.setId(item.getId());
            petDTO.setName(item.getName());
            petDTO.setCustomerId(item.getCustomerId());
            petDTO.setType(item.getType());
            petDTO.setBreed(item.getBreed());
            Optional<Customer> customer = customerRepository.findById(item.getCustomerId());
            petDTO.setCustomerName(customer.get().getName());
            return petDTO;
        }).collect(Collectors.toList());
    }

    public Pet createPet(Pet pet) {
        petRepository.insert(pet);
        return pet;
    }

    public Optional<PetDTO> getById(String id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()){
            Optional<Customer> customer = customerRepository.findById(pet.get().getCustomerId());
            PetDTO petDTO = new PetDTO();
            petDTO.setId(pet.get().getId());
            petDTO.setName(pet.get().getName());
            petDTO.setCustomerId(pet.get().getCustomerId());
            petDTO.setType(pet.get().getType());
            petDTO.setBreed(pet.get().getBreed());
            petDTO.setCustomerName(customer.get().getName());
            return Optional.of(petDTO);
        }else{
            return Optional.empty();
        }
    }

    public PetDTO updatePet(Pet pet) {
        petRepository.save(pet);
        return getById(pet.getId()).get();
    }

    public void deletePet(String id) {
        petRepository.deleteById(id);
    }
}
