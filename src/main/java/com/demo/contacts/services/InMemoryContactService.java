package com.demo.contacts.services;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import com.demo.contacts.models.ResponseModel;
import com.demo.contacts.repository.ContactRepository;
import com.demo.contacts.repository.InMemoryContactRepository;
import com.demo.contacts.uitils.ContactValidation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryContactService implements ContactService {
    ContactRepository repository = new InMemoryContactRepository();

    private ResponseModel getWrongIdResponse() {
        return new ResponseModel(400, null, "The id is not correct");
    }

    private ResponseModel getWrongContactDTOResponse(String message) {
        return new ResponseModel(401, null, message);
    }

    public ResponseModel getContact(String id) {
        UUID uuid = ContactValidation.isStringUUID(id);
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
    public ResponseModel createContact(ContactDTO contact) {
        String message = ContactValidation.contactDTOValidate(contact);
        System.out.println(message);
        if (!message.equals("success")) {
           return getWrongContactDTOResponse(message);
        }

        Contact contactCreated = repository.createContact(contact);

        if (contactCreated == null) {
            return new ResponseModel(40, null, "Something went wrong!");
        }
        return new ResponseModel(200, contactCreated, null);
    }

    @Override
    public ResponseModel updateContact(String id, ContactDTO contact) {
        UUID uuid = ContactValidation.isStringUUID(id);
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
        UUID uuid = ContactValidation.isStringUUID(id);
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
