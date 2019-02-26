package com.revolvingbunnies;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class HelloControllerTest {
    @Resource
    private CustomerRepository customerRepository;

    @Test
    public void customersCallsFindByLastName() {
        CustomerService service = mock(CustomerService.class);
        HelloController controller = new HelloController(service);

        controller.customers("");
        verify(service).findByLastName(any());
    }

    @Test
    public void customersReturnsAHardcodedListOfCustomersByLastName() {
        CustomerService service = mock(CustomerService.class);
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customers.add(customer);
        when(service.findByLastName(any())).thenReturn(customers);

        HelloController controller = new HelloController(service);

        List<Customer> actualCustomers = controller.customers("");
        Assert.assertEquals(1, actualCustomers.size());
    }

    @Test
    public void customersReturnsAListOfCustomersByCorrectLastName() {
//        Customer customer = new Customer("Tori", "Machen");

//        customerRepository.save(customer);

        Customer anthony = new Customer("Anthony", "Elliott");
        List<Customer> listOfCustomers = new ArrayList<>();
        listOfCustomers.add(anthony);
//        customerRepository.save(anthony);
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findByLastName("Elliott")).thenReturn(listOfCustomers);

        CustomerService service = new CustomerService(customerRepository);
        HelloController controller = new HelloController(service);

        List<Customer> actualCustomers = controller.customers("Elliott");
        Assert.assertEquals(1, actualCustomers.size());
    }
}
