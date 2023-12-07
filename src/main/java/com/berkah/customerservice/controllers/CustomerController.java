package com.berkah.customerservice.controllers;

import com.berkah.customerservice.models.Customer;
import com.berkah.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerRepository customerServices;

    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer created =  customerServices.save(customer);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/getCustomerById={customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Optional<Customer> optionalCustomer = customerServices.findById(customerId);
        Customer customer = optionalCustomer.orElse(null);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerServices.findAll();
        if(customers != null){
            return ResponseEntity.ok(customers);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteCustomer={customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId) {
        customerServices.deleteById(customerId);
        return ResponseEntity.noContent().build();
    }
}
