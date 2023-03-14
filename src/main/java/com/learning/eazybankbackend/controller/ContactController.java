package com.learning.eazybankbackend.controller;

import com.learning.eazybankbackend.model.Contact;
import com.learning.eazybankbackend.repository.ContactRepository;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/contact")
    @PreFilter("filterObject.contactName != Test")
    @PostFilter("filterObject.contactName != Test")
   /*authorization criteria on the input only if it is of type collection interface.*/
    public List<Contact> saveContactInquiryDetails(@RequestBody List<Contact> contacts){
        Contact contact = contacts.get(0);
        contact.setContactId(getServiceRequestNumber());
        return List.of(contactRepository.save(contact));
    }

    public String getServiceRequestNumber(){
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
