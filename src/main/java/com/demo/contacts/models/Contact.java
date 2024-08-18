package com.demo.contacts.models;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
public class Contact {
    private UUID id;

    @Setter
    private String name;

    @Setter
    private String email;
}
