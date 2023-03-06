package com.learning.eazybankbackend.controller;

import com.learning.eazybankbackend.model.Customer;
import com.learning.eazybankbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private CustomerRepository customerRepository;

    public RegisterController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){

        ResponseEntity<String> responseEntity= null;
        customerRepository.save(customer);
        responseEntity = ResponseEntity.status(HttpStatus.CREATED)
                .body("Given user details are successfully registered");

        return responseEntity;
    }
}
