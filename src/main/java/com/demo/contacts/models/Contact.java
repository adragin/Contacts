package com.demo.contacts.models;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class Contact {
    private UUID id;
    private String name;
    private String email;
}
