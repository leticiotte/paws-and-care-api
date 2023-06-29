package com.example.pawsAndCare.domain.repository;

import com.example.pawsAndCare.domain.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
}
