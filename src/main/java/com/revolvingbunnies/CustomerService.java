package com.revolvingbunnies;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        Iterable<Customer> iterator = customerRepository.findAll();
        List<Customer> customers = new ArrayList<>();
        iterator.forEach(customers::add);
        return customers;
    }

    public List<Customer> findByLastName(String name) {
        return customerRepository.findByLastName(name);
    }
}
