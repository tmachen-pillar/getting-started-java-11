package com.revolvingbunnies;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Resource
    private CustomerRepository customerRepository;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("Greetings from MY bunny, Wolfie!")));
    }

    @Test
    public void getHealth() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/health").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(equalTo("Service is up!")));
    }

    @Test
    public void getCustomersReturnsACustomer() throws Exception {
        Customer customer = new Customer("Anthony", "Elliott");
        customerRepository.save(customer);

        mvc.perform(MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON))
            .andExpect((status().isOk()))
            .andExpect(content().string(Matchers.containsString("Anthony")));
    }

    @Test
    public void getCustomersReturnsCustomersThatMatchGivenLastName() throws Exception {
        Customer customer = new Customer("Tori", "Machen");
        customerRepository.save(customer);

        Customer anthony = new Customer("Anthony", "Elliott");
        customerRepository.save(anthony);

        mvc.perform(MockMvcRequestBuilders.get("/customers").accept(MediaType.APPLICATION_JSON))
            .andExpect((status().isOk()))
            .andExpect(content().string(Matchers.containsString(customer.getLastName())));
    }
}
