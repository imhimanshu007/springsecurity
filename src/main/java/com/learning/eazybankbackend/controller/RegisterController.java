package com.learning.eazybankbackend.controller;

import com.learning.eazybankbackend.model.Customer;
import com.learning.eazybankbackend.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    public RegisterController(CustomerRepository customerRepository,
                              PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){

        ResponseEntity<String> responseEntity= null;
        String hashPwd = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashPwd);
        customerRepository.save(customer);
        responseEntity = ResponseEntity.status(HttpStatus.CREATED)
                .body("Given user details are successfully registered");

        return responseEntity;
    }
}
