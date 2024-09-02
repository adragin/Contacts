package com.demo.contacts.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactDTO {
    private String name;
    private String email;
    private int ownerId;
}
