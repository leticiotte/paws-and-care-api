package com.example.pawsAndCare.application.controller;

import com.example.pawsAndCare.domain.exception.CustomerNotFoundException;
import com.example.pawsAndCare.domain.exception.PetNotFoundException;
import com.example.pawsAndCare.domain.exception.SchedulingConflictException;
import com.example.pawsAndCare.domain.model.Customer;
import com.example.pawsAndCare.domain.model.Scheduling;
import com.example.pawsAndCare.domain.model.dto.PetDTO;
import com.example.pawsAndCare.domain.model.dto.SchedulingDTO;
import com.example.pawsAndCare.infrastructure.service.CustomerService;
import com.example.pawsAndCare.infrastructure.service.PetService;
import com.example.pawsAndCare.infrastructure.service.SchedulingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/schedules")
public class SchedulingController {
    @Autowired
    private SchedulingService schedulingService;
    @Autowired
    private PetService petService;
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<SchedulingDTO>> getAllSchedules(@RequestParam(name = "q", required = false) String q, @RequestParam(name = "date", required = false) String date) {
        if (q != null && !q.isBlank()) {
            return ResponseEntity.ok().body(
                    schedulingService.getSchedulesBySearch(q)
            );
        }
        if (q != null && !q.isBlank()) {
            return ResponseEntity.ok().body(
                    schedulingService.getSchedulesByDate(date)
            );
        }

        return ResponseEntity.ok().body(
                schedulingService.getAllSchedules()
        );
    }

    @PostMapping
    public ResponseEntity<SchedulingDTO> createScheduling(@Valid @RequestBody Scheduling scheduling)
            throws CustomerNotFoundException, PetNotFoundException, SchedulingConflictException {
        Optional<Customer> customer = customerService.getById(scheduling.getCustomerId());
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Cliente não encontrado.");
        }
        Optional<PetDTO> pet = petService.getById(scheduling.getPetId());
        if (pet.isEmpty()) {
            throw new PetNotFoundException("Pet não encontrado.");
        }

        Optional<Scheduling> schedulingSearch = schedulingService.findScheduleConflict(
                scheduling.getCustomerId(),
                scheduling.getPetId(),
                scheduling.getDate()
        );
        if (schedulingSearch.isPresent()) {
            throw new SchedulingConflictException("Já possui agendamento nessa data e hora.");
        }

        scheduling = schedulingService.createScheduling(scheduling);
        SchedulingDTO schedulingDTO = new SchedulingDTO();
        schedulingDTO.setId(scheduling.getId());
        schedulingDTO.setCustomerId(scheduling.getCustomerId());
        schedulingDTO.setPetId(scheduling.getPetId());
        schedulingDTO.setDate(scheduling.getDate());
        schedulingDTO.setCustomerName(customer.get().getName());
        schedulingDTO.setPetName(pet.get().getName());

        return ResponseEntity.ok().body(schedulingDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SchedulingDTO> getById(@PathVariable String id) {
        Optional<SchedulingDTO> schedulingDTO = schedulingService.getById(id);
        return schedulingDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SchedulingDTO> updateScheduling(@PathVariable String id, @Valid @RequestBody Scheduling scheduling)
            throws CustomerNotFoundException, PetNotFoundException, SchedulingConflictException {
        Optional<Customer> customer = customerService.getById(scheduling.getCustomerId());
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Cliente não encontrado.");
        }
        Optional<PetDTO> pet = petService.getById(scheduling.getPetId());
        if (pet.isEmpty()) {
            throw new PetNotFoundException("Pet não encontrado.");
        }

        scheduling = schedulingService.updateScheduling(scheduling);
        SchedulingDTO schedulingDTO = new SchedulingDTO();
        schedulingDTO.setId(scheduling.getId());
        schedulingDTO.setCustomerId(scheduling.getCustomerId());
        schedulingDTO.setPetId(scheduling.getPetId());
        schedulingDTO.setDate(scheduling.getDate());
        schedulingDTO.setCustomerName(customer.get().getName());
        schedulingDTO.setPetName(pet.get().getName());

        return ResponseEntity.ok().body(schedulingDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable String id) {
        schedulingService.deleteSchedule(id);

        return ResponseEntity.noContent().build();
    }
}
