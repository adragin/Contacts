package com.demo.contacts.dto;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private String email;
    private String token;
}
