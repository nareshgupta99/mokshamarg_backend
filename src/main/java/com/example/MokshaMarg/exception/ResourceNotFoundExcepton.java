package com.example.MokshaMarg.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResourceNotFoundExcepton extends RuntimeException {
	
	String id;
	String message;

}
