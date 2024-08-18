package com.demo.contacts.services;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import com.demo.contacts.models.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseContactService  implements ContactService{
    @Override
    public ResponseModel getContact(String id) {
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        return null;
    }

    @Override
    public ResponseModel createContact(ContactDTO contact) {
        return null;
    }

    @Override
    public ResponseModel updateContact(String id, ContactDTO contact) {
        return null;
    }

    @Override
    public ResponseModel deleteContact(String id) {
        return null;
    }
}
