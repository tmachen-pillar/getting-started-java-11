package com.revolvingbunnies;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    private final CustomerService customerService;

    public HelloController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from MY bunny, Wolfie!";
    }

    @RequestMapping("/health")
    public String getHealth() {
        return "Service is up!";
    }

    @RequestMapping("/customers")
    public Customer customers() {
//        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer("Anthony", "Elliott");
//        customers.add(customer);
        return customer;
//        return customers;
//        return customerService.lookup();
    }
}
