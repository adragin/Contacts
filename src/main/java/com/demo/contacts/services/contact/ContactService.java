package com.demo.contacts.services.contact;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import com.demo.contacts.models.ResponseModel;

import java.util.List;

public interface ContactService {
    ResponseModel getContact(int id);

    List<Contact> getAllContacts(int ownerId);

    ResponseModel createContact(ContactDTO contact, int ownerId);

    ResponseModel updateContact(int id, ContactDTO contact);

    ResponseModel deleteContact(int id);
}
