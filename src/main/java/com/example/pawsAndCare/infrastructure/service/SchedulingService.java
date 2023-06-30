package com.example.pawsAndCare.infrastructure.service;

import com.example.pawsAndCare.domain.model.Customer;
import com.example.pawsAndCare.domain.model.Pet;
import com.example.pawsAndCare.domain.model.Scheduling;
import com.example.pawsAndCare.domain.model.dto.SchedulingDTO;
import com.example.pawsAndCare.domain.repository.CustomerRepository;
import com.example.pawsAndCare.domain.repository.PetRepository;
import com.example.pawsAndCare.domain.repository.SchedulingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchedulingService {
    @Autowired
    private SchedulingRepository schedulingRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;
    
    public List<SchedulingDTO> getSchedulesBySearch(String q) {
        List<Scheduling> schedules = schedulingRepository.findSchedulesBySearch(q);
        return schedules.stream().map(item -> {
            Optional<Customer> customer = customerRepository.findById(item.getCustomerId());
            Optional<Pet> pet = petRepository.findById(item.getPetId());

            SchedulingDTO schedulingDTO = new SchedulingDTO();
            schedulingDTO.setId(item.getId());
            schedulingDTO.setCustomerId(item.getCustomerId());
            schedulingDTO.setPetId(item.getPetId());
            schedulingDTO.setDate(item.getDate());
            schedulingDTO.setCustomerName(customer.get().getName());
            schedulingDTO.setPetName(pet.get().getName());

            return schedulingDTO;
        }).collect(Collectors.toList());
    }

    public List<SchedulingDTO> getSchedulesByDate(String date) {
        List<Scheduling> schedules = schedulingRepository.findByDate(date);
        return schedules.stream().map(item -> {
            Optional<Customer> customer = customerRepository.findById(item.getCustomerId());
            Optional<Pet> pet = petRepository.findById(item.getPetId());

            SchedulingDTO schedulingDTO = new SchedulingDTO();
            schedulingDTO.setId(item.getId());
            schedulingDTO.setCustomerId(item.getCustomerId());
            schedulingDTO.setPetId(item.getPetId());
            schedulingDTO.setDate(item.getDate());
            schedulingDTO.setCustomerName(customer.get().getName());
            schedulingDTO.setPetName(pet.get().getName());

            return schedulingDTO;
        }).collect(Collectors.toList());
    }

    public List<SchedulingDTO> getAllSchedules() {
        List<Scheduling> schedules = schedulingRepository.findAll();
        return schedules.stream().map(item -> {
            Optional<Customer> customer = customerRepository.findById(item.getCustomerId());
            Optional<Pet> pet = petRepository.findById(item.getPetId());

            SchedulingDTO schedulingDTO = new SchedulingDTO();
            schedulingDTO.setId(item.getId());
            schedulingDTO.setCustomerId(item.getCustomerId());
            schedulingDTO.setPetId(item.getPetId());
            schedulingDTO.setDate(item.getDate());
            schedulingDTO.setCustomerName(customer.get().getName());
            schedulingDTO.setPetName(pet.get().getName());

            return schedulingDTO;
        }).collect(Collectors.toList());
    }

    public Optional<Scheduling> findScheduleConflict(String customerId, String petId, String date) {
        List<Scheduling> schedules = schedulingRepository.findConflict(customerId, petId, date);
        if (schedules.size() != 0){
            return Optional.of(schedules.get(0));
        }
        return Optional.empty();
    }

    public Scheduling createScheduling(Scheduling scheduling) {
        return schedulingRepository.insert(scheduling);
    }

    public Optional<SchedulingDTO> getById(String id) {
        Optional<Scheduling> scheduling = schedulingRepository.findById(id);
        if(scheduling.isEmpty()){
            return Optional.empty();
        }
        Optional<Customer> customer = customerRepository.findById(scheduling.get().getCustomerId());
        Optional<Pet> pet = petRepository.findById(scheduling.get().getPetId());

        SchedulingDTO schedulingDTO = new SchedulingDTO();
        schedulingDTO.setId(scheduling.get().getId());
        schedulingDTO.setCustomerId(scheduling.get().getCustomerId());
        schedulingDTO.setPetId(scheduling.get().getPetId());
        schedulingDTO.setDate(scheduling.get().getDate());
        schedulingDTO.setCustomerName(customer.get().getName());
        schedulingDTO.setPetName(pet.get().getName());

        return Optional.of(schedulingDTO);
    }

    public Scheduling updateScheduling(Scheduling scheduling) {
        return schedulingRepository.save(scheduling);
    }

    public void deleteSchedule(String id) {
        schedulingRepository.deleteById(id);
    }
}
