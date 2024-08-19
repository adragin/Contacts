package com.demo.contacts.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseModel {
    private int statusCode;
    private Object value;
    private String errorMessage;
}
