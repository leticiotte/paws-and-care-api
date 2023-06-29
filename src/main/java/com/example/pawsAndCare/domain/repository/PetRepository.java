package com.example.pawsAndCare.domain.repository;

import com.example.pawsAndCare.domain.model.Pet;
import com.example.pawsAndCare.domain.model.dto.PetDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends MongoRepository<Pet, String> {
    List<Pet> findByCustomerId(String id);

    @Query("{$or: [{'id': {$regex: ?0, $options: 'i'}}, {'name': {$regex: ?0, $options: 'i'}}]}")
    List<PetDTO> getPetBySearch(String search);
}
