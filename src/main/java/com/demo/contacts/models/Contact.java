package com.demo.contacts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Contact {
    private int id;
    private String name;
    private String email;
}
