package com.demo.contacts.controllers;

import com.demo.contacts.dao.ContactDao;
import com.demo.contacts.dao.ContactDaoImpl;
import com.demo.contacts.models.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact")
public class ContactController {
   private final ContactDao contacts = new ContactDaoImpl();

    @GetMapping()
    public ResponseEntity<String> getRandomContact() {
        Contact contact = contacts.getContactRandom();
        String response = String.format("Контакт имя: %s, имейл: %s", contact.getName(), contact.getEmail());
        return ResponseEntity.ok(response);
    }
}
