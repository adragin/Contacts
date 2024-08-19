package com.demo.contacts.repository;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public class DatabaseContactRepository implements ContactRepository{
    @Override
    public Contact getContact(UUID id) {
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        return null;
    }

    @Override
    public Contact createContact(ContactDTO contact) {
        return null;
    }

    @Override
    public Contact updateContact(UUID id, ContactDTO contact) {
        return null;
    }

    @Override
    public boolean deleteContact(UUID id) {
        return false;
    }
}
