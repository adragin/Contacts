package com.demo.contacts.services.contact;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import com.demo.contacts.models.ResponseModel;
import com.demo.contacts.repository.contacts.ContactRepository;
import com.demo.contacts.repository.user.UserRepositoryJPA;
import com.demo.contacts.uitils.ContactValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryContactService implements ContactService {
    @Autowired
    private ContactRepository repository;

    @Autowired
    private  UserRepositoryJPA userRepository;

    private ResponseModel getWrongIdResponse() {
        return new ResponseModel(400, null, "The id is not correct");
    }

    private ResponseModel getWrongContactDTOResponse(String message) {
        return new ResponseModel(401, null, message);
    }

    public ResponseModel getContact(int id) {

        Contact contact = repository.getContact(id);

        if (contact == null) {
            return new ResponseModel(404, null, "The contact not found");
        }

        return new ResponseModel(200, contact, null);
    }

    @Override
    public List<Contact> getAllContacts(int ownerId) {
        return repository.getAllContacts(ownerId);
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
    public ResponseModel updateContact(int id, ContactDTO contact) {
        Contact oldContact = repository.getContact(id);
        if (oldContact == null) {
            return new ResponseModel(404, null, "The contact not found");
        }

        Contact contactUpdated = repository.updateContact(id, contact);

        return new ResponseModel(200, contactUpdated, null);
    }

    @Override
    public ResponseModel deleteContact(int id) {


        boolean isDeleted = repository.deleteContact(id);

        if (isDeleted) {
            return new ResponseModel(200, null, "success");
        }

        return new ResponseModel(404, null, "The contact was not found");
    }
}
