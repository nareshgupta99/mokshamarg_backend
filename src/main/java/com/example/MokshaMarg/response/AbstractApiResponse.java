package com.example.MokshaMarg.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AbstractApiResponse<T> {


	private boolean status;
    private String message;
    private T data;

    public AbstractApiResponse(boolean status, String message, T dishResponse) {
        this.status = status;
        this.message = message;
        this.data = dishResponse;
    }

  
}
