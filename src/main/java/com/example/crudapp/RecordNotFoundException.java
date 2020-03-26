package com.example.crudapp;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class RecordNotFoundException extends Exception {
    public RecordNotFoundException(String s) {
        super(s);
    }
}
