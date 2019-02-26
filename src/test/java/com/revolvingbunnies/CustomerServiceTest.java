package com.revolvingbunnies;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Test
    void shouldCallFindByLastName() {
        CustomerRepository repo = mock(CustomerRepository.class);
        CustomerService service = new CustomerService(repo);
        service.findByLastName("");
        verify(repo).findByLastName(any());
    }

    @Test
    public void shouldReturnEmptyListOfCustomersWhenFindByLastNameCalled() {
        CustomerRepository repo = mock(CustomerRepository.class);
        CustomerService service = new CustomerService(repo);
        List<Customer> customers = service.findByLastName("");
        Assert.assertEquals(new ArrayList<Customer>(), customers);
    }

    @Test
    public void shouldReturnListOfCustomersWhenFindByLastNameCalled() {
        CustomerRepository repo = mock(CustomerRepository.class);
        Customer customer = new Customer();
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(customer);
        when(repo.findByLastName(any())).thenReturn(expectedCustomers);

        CustomerService service = new CustomerService(repo);
        List<Customer> customers = service.findByLastName("");
        Assert.assertEquals(1, customers.size());
    }
}
