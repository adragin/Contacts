package com.demo.contacts.repository.contacts;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import com.demo.contacts.models.Phone;
import com.demo.contacts.models.User;
import com.demo.contacts.repository.user.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Primary
@Repository
public class ContactAdapterRepository implements ContactRepository {
    @Autowired
    private JPAContactRepository jpa;
    @Autowired
    private UserRepositoryJPA userJpa;
    @Autowired
    private JPAPhoneRepository phoneRepository;

    @Override
    public Contact getContact(int id) {
        return jpa.findById(id).orElseGet(null);
    }

    @Override
    public List<Contact> getAllContacts(int ownerId) {
        return jpa.findAllByUser(ownerId);
    }

    @Override
    public Contact createContact(ContactDTO contact, int ownerId) {
        Contact newContact = new Contact();
        newContact.setEmail(contact.getEmail());
        newContact.setName(contact.getName());

        contact.getPhones().forEach((p) -> {
            if (phoneRepository.findByPhoneNumber(p) == null) {
                Phone ph = new Phone();
                ph.setPhoneNumber(p);
                ph.setContact(newContact);
                phoneRepository.save(ph);
            }
        });


        User user = userJpa.findById(ownerId).orElseGet(null);
        newContact.setUser(user);
        return jpa.save(newContact);
    }

    @Override
    public Contact updateContact(int id, ContactDTO contact) {
        Contact contactDB = getContact(id);

        contactDB.setName(contact.getName());
        contactDB.setEmail(contact.getEmail());

        contact.getPhones().forEach((p) -> {
            if (phoneRepository.findByContactIdAndPhoneNumber(id, p) == null) {
                Phone ph = new Phone();
                ph.setPhoneNumber(p);
                ph.setContact(contactDB);
                phoneRepository.save(ph);
            }
        });

        return jpa.save(contactDB);
    }

    @Override
    public boolean deleteContact(int id) {
        jpa.deleteById(id);
        return true;
    }
}
