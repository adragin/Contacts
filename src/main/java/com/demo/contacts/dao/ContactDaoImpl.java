package com.demo.contacts.dao;

import com.demo.contacts.models.Contact;
import com.demo.contacts.repository.ContactRepository;

import java.util.List;
import java.util.Random;

public class ContactDaoImpl implements ContactDao {
    ContactRepository repository;

    @Override
    public Contact getContactRandom() {
        List<Contact> contacts = repository.getContacts();
        int idx = new Random().nextInt(0, contacts.size());
        return contacts.get(idx);
    }

    public ContactDaoImpl() {
        this.repository = new ContactRepository();
    }
}
