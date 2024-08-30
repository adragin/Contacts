package com.demo.contacts.repository.contacts;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;

import java.util.List;

public interface ContactRepository {
    Contact getContact(int id);

    List<Contact> getAllContacts();

    Contact createContact(ContactDTO contact, int ownerId);

    Contact updateContact(int id, ContactDTO contact);

    boolean deleteContact(int id);
}
