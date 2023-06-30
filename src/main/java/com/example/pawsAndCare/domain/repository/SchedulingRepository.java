package com.example.pawsAndCare.domain.repository;

import com.example.pawsAndCare.domain.model.Pet;
import com.example.pawsAndCare.domain.model.Scheduling;
import com.example.pawsAndCare.domain.model.dto.PetDTO;
import com.example.pawsAndCare.domain.model.dto.SchedulingDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulingRepository extends MongoRepository<Scheduling, String> {

    @Query("{'id': {$regex: ?0}}")
    List<Scheduling> findSchedulesBySearch(String search);
    List<Scheduling> findByDate(String date);

    @Query("{'customerId': ?0, 'petId': ?1, 'date': ?2}")
    List<Scheduling> findConflict(String customerId, String petId, String date);
}
