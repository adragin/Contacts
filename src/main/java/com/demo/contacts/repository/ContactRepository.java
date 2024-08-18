package com.demo.contacts.repository;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactRepository {
    Contact getContact(UUID id);

    List<Contact> getAllContacts();

    Contact createContact(ContactDTO contact);

    Contact updateContact(UUID id, ContactDTO contact);

    boolean deleteContact(UUID id);
}
