package com.demo.contacts.repository.contacts;

import com.demo.contacts.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAContactRepository extends JpaRepository<Contact, Integer> {

        @Query("SELECT c FROM Contact c WHERE c.user.id = ?1")
        List<Contact> findAllByUser(int ownerId);
}
