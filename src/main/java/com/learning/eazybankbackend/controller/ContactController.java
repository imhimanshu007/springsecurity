package com.learning.eazybankbackend.controller;

import com.learning.eazybankbackend.model.Contact;
import com.learning.eazybankbackend.repository.ContactRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class ContactController {

    private final ContactController contactController;
    private final ContactRepository contactRepository;

    public ContactController(ContactController contactController,
                             ContactRepository contactRepository) {
        this.contactController = contactController;
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact){
        contact.setContactId(getServiceRequestNumber());
        return contactRepository.save(contact);
    }

    public String getServiceRequestNumber(){
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
