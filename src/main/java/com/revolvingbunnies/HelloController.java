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
    public List<Customer> customers(String lastName) {
//        return customerService.findAll();
        return customerService.findByLastName("Elliott");
    }
}
