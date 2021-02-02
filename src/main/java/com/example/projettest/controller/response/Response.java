package com.example.projettest.controller.response;

import java.util.Map;

public class Response {
    private boolean status;
    private String message;
    private Map<String, Object> data;



    public Response() {
        super();

    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }
    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
