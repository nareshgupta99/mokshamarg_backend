package com.example.MokshaMarg.response;

import lombok.Data;

@Data
public class AbstractApiResponse<T> {


	private boolean status;
    private String message;
    private T data;

    public AbstractApiResponse(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

  
}
