package com.riwi.spring_boot_drill.utils.exceptions;

public class BadRoleException extends RuntimeException {
    public BadRoleException (String message){
        super(message);
    }
}
