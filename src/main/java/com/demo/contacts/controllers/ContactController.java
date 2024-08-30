package com.demo.contacts.controllers;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import com.demo.contacts.models.ResponseModel;
import com.demo.contacts.services.auth.AuthService;
import com.demo.contacts.services.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
    @Qualifier("inMemoryContactService")
    @Autowired
    private ContactService contactService;

    @Autowired
    private AuthService authService;

    private ResponseEntity<ResponseModel> getResponse(int status, ResponseModel response) {
        if (response.getStatusCode() != status) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseModel> getContactById(@PathVariable String id) {
        ResponseModel response = contactService.getContact(id);

        return getResponse(200, response);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseModel> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();

        return ResponseEntity.ok(new ResponseModel(200, contacts, null));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ResponseModel> createContact(@RequestHeader("token") String token,@RequestBody ContactDTO contact) {

        int ownerId = authService.getUserIdFromToken(token);
        ResponseModel response = contactService.createContact(contact, ownerId);
        return getResponse(201, response);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<ResponseModel> updateContact(@PathVariable String id, @RequestBody ContactDTO contact) {
        ResponseModel response = contactService.updateContact(id, contact);

        return getResponse(200, response);
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<ResponseModel> deleteContact(@PathVariable String id) {
        ResponseModel response = contactService.deleteContact(id);

        return getResponse(200, response);
    }
}
