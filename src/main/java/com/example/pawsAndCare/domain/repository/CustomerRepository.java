package com.example.pawsAndCare.domain.repository;

import com.example.pawsAndCare.domain.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    @Query("{$or: [{'id': {$regex: ?0, $options: 'i'}}, {'name': {$regex: ?0, $options: 'i'}}, {'document': {$regex: ?0, $options: 'i'}}]}")
    List<Customer> getCustomerBySearch(String search);

}
