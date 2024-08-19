package com.demo.contacts.services;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import com.demo.contacts.models.ResponseModel;

import java.util.List;

public interface ContactService {
    ResponseModel getContact(String id);

    List<Contact> getAllContacts();

    ResponseModel createContact(ContactDTO contact);

    ResponseModel updateContact(String id, ContactDTO contact);

    ResponseModel deleteContact(String id);
}
