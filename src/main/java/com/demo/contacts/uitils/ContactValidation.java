package com.demo.contacts.uitils;

import com.demo.contacts.dto.ContactDTO;

public class ContactValidation {
    public static Integer isStringUUID(String id) {
        try {
            return Integer.parseInt(id);
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
