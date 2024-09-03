package com.demo.contacts.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ContactDTO {
    private String name;
    private String email;
    private int ownerId;
    private List<String> phones;
}
