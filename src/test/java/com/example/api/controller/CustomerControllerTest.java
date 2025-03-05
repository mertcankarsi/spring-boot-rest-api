package com.example.api.controller;

import com.example.api.entity.Customer;
import com.example.api.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerController customerController;

    private Customer testCustomer;
    private List<Customer> testCustomers;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setName("Test Customer");
        testCustomer.setEmail("test@example.com");
        testCustomer.setPhone("1234567890");
        testCustomer.setAddress("Test Address");

        testCustomers = Arrays.asList(testCustomer);
    }

    @Test
    void getAllCustomers_ShouldReturnListOfCustomers() {
        when(customerRepository.findAll()).thenReturn(testCustomers);

        List<Customer> result = customerController.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testCustomer, result.get(0));
        verify(customerRepository).findAll();
    }

    @Test
    void getCustomerById_WhenCustomerExists_ShouldReturnCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        ResponseEntity<Customer> response = customerController.getCustomerById(1L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(testCustomer, response.getBody());
        verify(customerRepository).findById(1L);
    }

    @Test
    void getCustomerById_WhenCustomerDoesNotExist_ShouldReturnNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Customer> response = customerController.getCustomerById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(customerRepository).findById(1L);
    }

    @Test
    void createCustomer_ShouldReturnCreatedCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        Customer result = customerController.createCustomer(testCustomer);

        assertNotNull(result);
        assertEquals(testCustomer, result);
        verify(customerRepository).save(testCustomer);
    }

    @Test
    void updateCustomer_WhenCustomerExists_ShouldReturnUpdatedCustomer() {
        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("Updated Name");
        updatedCustomer.setEmail("updated@example.com");
        updatedCustomer.setPhone("9876543210");
        updatedCustomer.setAddress("Updated Address");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        ResponseEntity<Customer> response = customerController.updateCustomer(1L, updatedCustomer);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(testCustomer, response.getBody());
        verify(customerRepository).findById(1L);
        verify(customerRepository).save(any(Customer.class));
    }

    @Test
    void updateCustomer_WhenCustomerDoesNotExist_ShouldReturnNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Customer> response = customerController.updateCustomer(1L, testCustomer);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(customerRepository).findById(1L);
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void deleteCustomer_WhenCustomerExists_ShouldReturnOk() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        ResponseEntity<Void> response = customerController.deleteCustomer(1L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        verify(customerRepository).findById(1L);
        verify(customerRepository).delete(testCustomer);
    }

    @Test
    void deleteCustomer_WhenCustomerDoesNotExist_ShouldReturnNotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = customerController.deleteCustomer(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(customerRepository).findById(1L);
        verify(customerRepository, never()).delete(any(Customer.class));
    }
} 