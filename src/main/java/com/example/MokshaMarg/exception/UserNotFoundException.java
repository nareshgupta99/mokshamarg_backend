package com.example.MokshaMarg.exception;

public class UserNotFoundException extends RuntimeException {
	boolean status;
	String message;
	
    public UserNotFoundException(boolean status,String message) {
    	this.message=message;
    	this.status=status;
    }
}
