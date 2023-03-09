package com.learning.eazybankbackend.repository;

import com.learning.eazybankbackend.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
