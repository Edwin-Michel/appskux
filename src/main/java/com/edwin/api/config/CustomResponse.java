package com.edwin.api.config;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomResponse<T> {
    private Boolean ok;
    private String message;
    private T data;

    public CustomResponse(Boolean ok, String message){
        this.ok = ok;
        this.message = message;
    }

    public CustomResponse(T data){
        this.ok = true;
        this.message = "Successful";
        this.data = data;
    }
}
