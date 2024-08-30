package com.demo.contacts.services.contact;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import com.demo.contacts.models.ResponseModel;
import com.demo.contacts.repository.contacts.ContactRepository;
import com.demo.contacts.uitils.ContactValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryContactService implements ContactService {
    @Qualifier("databaseContactRepository")
    @Autowired
    ContactRepository repository;

    private ResponseModel getWrongIdResponse() {
        return new ResponseModel(400, null, "The id is not correct");
    }

    private ResponseModel getWrongContactDTOResponse(String message) {
        return new ResponseModel(401, null, message);
    }

    public ResponseModel getContact(String id) {
        Integer uuid = ContactValidation.isStringUUID(id);
        if (uuid == null) {
            return getWrongIdResponse();
        }
        Contact contact = repository.getContact(uuid);

        if (contact == null) {
            return new ResponseModel(404, null, "The contact not found");
        }

        return new ResponseModel(200, contact, null);
    }

    @Override
    public List<Contact> getAllContacts() {
        return repository.getAllContacts();
    }

    @Override
    public ResponseModel createContact(ContactDTO contact, int ownerId) {
        String message = ContactValidation.contactDTOValidate(contact);
        System.out.println(message);
        if (!message.equals("success")) {
           return getWrongContactDTOResponse(message);
        }

        Contact contactCreated = repository.createContact(contact, ownerId);

        if (contactCreated == null) {
            return new ResponseModel(400, null, "The contact exist with such name!");
        }
        return new ResponseModel(200, contactCreated, null);
    }

    @Override
    public ResponseModel updateContact(String id, ContactDTO contact) {
        Integer uuid = ContactValidation.isStringUUID(id);
        if (uuid == null) {
            return getWrongIdResponse();
        }

        String message = ContactValidation.contactDTOValidate(contact);
        if (!message.equals("success")) {
            return getWrongContactDTOResponse(message);
        }

        Contact contactUpdated = repository.updateContact(uuid, contact);

        if (contactUpdated == null) {
            return new ResponseModel(404, null, "The contact not found");
        }

        return new ResponseModel(200, contactUpdated, null);
    }

    @Override
    public ResponseModel deleteContact(String id) {
        Integer uuid = ContactValidation.isStringUUID(id);
        if (uuid == null) {
            return getWrongIdResponse();
        }

        boolean isDeleted = repository.deleteContact(uuid);

        if (isDeleted) {
            return new ResponseModel(200, null, "success");
        }

        return new ResponseModel(404, null, "The contact was not found");
    }
}
