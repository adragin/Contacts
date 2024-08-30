package com.demo.contacts.repository.user;

import com.demo.contacts.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
