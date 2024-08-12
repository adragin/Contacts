package com.demo.contacts.repository;

import com.demo.contacts.models.Contact;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ContactRepository {
    private final List<Contact> contacts;

    public ContactRepository() {
        contacts = new ArrayList<>();
        contacts.add(new Contact(1,"John", "john@email.com"));
        contacts.add(new Contact(2,"Amily", "amily@email.com"));
        contacts.add(new Contact(3,"Greg", "greg@email.com"));
        contacts.add(new Contact(4,"Steve", "steve@email.com"));
        contacts.add(new Contact(5,"Lusy", "lusy@email.com"));
    }
}
