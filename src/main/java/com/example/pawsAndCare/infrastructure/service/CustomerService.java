package com.example.pawsAndCare.infrastructure.service;

import com.example.pawsAndCare.domain.exception.DuplicatedDocumentException;
import com.example.pawsAndCare.domain.model.Customer;
import com.example.pawsAndCare.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Customer> getCustomersBySearch(String q) {
        return customerRepository.getCustomerBySearch(q);
    }

    public Customer createCustomer(Customer customer) throws DuplicatedDocumentException  {
        try{
            customerRepository.insert(customer);
            return customer;
        }catch (DuplicateKeyException e) {
            throw new DuplicatedDocumentException("Já existe um cliente com esse documento.");
        }
    }

    public Optional<Customer> getById(String id) {
        return customerRepository.findById(id);
    }


    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
