package com.demo.contacts.uitils;

import com.demo.contacts.dto.ContactDTO;

import java.util.UUID;

public class ContactValidation {
    public static UUID isStringUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException exc) {
            return null;
        }
    }

   public static String contactDTOValidate(ContactDTO contactDTO) {
        if (contactDTO == null) {
            return "The contact cannot be null!";
        } else if (contactDTO.getName() == null || contactDTO.getName().isEmpty()) {
            return "The name cannot be empty!";
        } else if (contactDTO.getEmail() == null || contactDTO.getEmail().isEmpty()) {
            return "The email cannot be empty!";
        }

        return "success";
    }
}
