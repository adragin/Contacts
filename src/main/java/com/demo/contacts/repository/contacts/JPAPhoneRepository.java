package com.demo.contacts.repository.contacts;

import com.demo.contacts.models.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAPhoneRepository extends JpaRepository<Phone, Integer> {
    Phone findByContactIdAndPhoneNumber(int contact_id, String number);
    Phone findByPhoneNumber(String number);
}
