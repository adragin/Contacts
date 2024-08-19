package com.demo.contacts.repository;

import com.demo.contacts.dto.ContactDTO;
import com.demo.contacts.models.Contact;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryContactRepository implements ContactRepository {

    private final List<Contact> contacts = init();

    public List<Contact> init() {
        List<Contact> contacts = new ArrayList<>();

        contacts.add(Contact.builder().id(UUID.randomUUID()).email("test@tes.ru").name("Petya").build());
        contacts.add(Contact.builder().id(UUID.randomUUID()).email("test@tes1.ru").name("Natasha").build());
        contacts.add(Contact.builder().id(UUID.randomUUID()).email("test@tes2.ru").name("Masha").build());
        contacts.add(Contact.builder().id(UUID.randomUUID()).email("test@tes3.ru").name("Olya").build());
        contacts.add(Contact.builder().id(UUID.randomUUID()).email("test@tes4.ru").name("Katya").build());
        contacts.add(Contact.builder().id(UUID.randomUUID()).email("test@te5.ru").name("Sery").build());
        contacts.add(Contact.builder().id(UUID.randomUUID()).email("test@tes6.ru").name("Laura").build());
        return contacts;
    }

    @Override
    public Contact getContact(UUID id) {
        try {
            return contacts.stream()
                    .filter(contact -> contact.getId().equals(id))
                    .findFirst()
                    .orElseGet(null);
        } catch (NullPointerException | NoSuchElementException e) {
            return null;
        }


    }

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public Contact createContact(ContactDTO contact) {
        Contact contactCreated = Contact.builder()
                .name(contact.getName())
                .email(contact.getEmail())
                .id(UUID.randomUUID())
                .build();
        contacts.add(contactCreated);
        // TODO: creat check if there contact with such name or email
        return contactCreated;
    }

    @Override
    public Contact updateContact(UUID id, ContactDTO contact) {
        try {
            Contact existedContact = contacts.stream()
                    .filter(contact1 -> contact1.getId().equals(id))
                    .findFirst().get();
            existedContact.setEmail(contact.getEmail());
            existedContact.setName(contact.getName());

            return existedContact;

        } catch (NullPointerException | NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public boolean deleteContact(UUID id) {
        try {
            Contact existedContact = contacts.stream()
                    .filter(contact1 -> contact1.getId().equals(id))
                    .findFirst().get();

            contacts.remove(existedContact);
            return true;
        } catch (NullPointerException | NoSuchElementException e) {
            return false;
        }
    }
}
