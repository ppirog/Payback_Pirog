package com.payback.payback_pirog.contrtoller;


public class RequestDto {
    private String type;

    public RequestDto(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
