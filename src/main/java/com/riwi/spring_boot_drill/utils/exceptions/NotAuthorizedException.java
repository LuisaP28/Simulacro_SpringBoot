package com.riwi.spring_boot_drill.utils.exceptions;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String id) {
        super(id);
    }
}
