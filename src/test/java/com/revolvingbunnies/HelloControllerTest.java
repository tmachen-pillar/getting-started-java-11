package com.revolvingbunnies;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class HelloControllerTest {
    @Test
    public void customersCallsFindByLastName() {
        CustomerService service = mock(CustomerService.class);
        HelloController controller = new HelloController(service);

        controller.customers();
        verify(service).findByLastName(any());
    }

    @Test
    public void customersReturnsAListOfCustomersByLastName() {
        CustomerService service = mock(CustomerService.class);
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customers.add(customer);
        when(service.findByLastName(any())).thenReturn(customers);

        HelloController controller = new HelloController(service);

        List<Customer> actualCustomers = controller.customers();
        Assert.assertEquals(1, actualCustomers.size());
    }
}
