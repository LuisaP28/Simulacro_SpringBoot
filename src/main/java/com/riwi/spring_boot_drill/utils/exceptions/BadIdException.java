package com.riwi.spring_boot_drill.utils.exceptions;

public class BadIdException extends RuntimeException {
    public BadIdException(String name) {
        super(name);
    }
}