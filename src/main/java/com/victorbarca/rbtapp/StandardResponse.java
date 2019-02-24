package com.victorbarca.rbtapp;

import com.google.gson.JsonElement;

public class StandardResponse {

    private String status;
    private String message;
    private JsonElement data;

    public StandardResponse(String status, JsonElement data) {
        this.status = status;
        this.data = data;
    }
}
