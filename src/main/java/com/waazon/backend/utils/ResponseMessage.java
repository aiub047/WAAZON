package com.waazon.backend.utils;

public enum ResponseMessage {
    SUCCESS_LOGIN("Logged in successfully"),
    WRONG_CREDENTIALS("Wrong credentials"),
    FORBIDDEN("That is Forbidden"),
    ERROR("Unexpected error occurred"),
    NO_CONTENT("Content is empty"),
    SUCCESS("Successfully created"),
    SUCCESS_GET("GET Successful"),
    SUCCESS_UPDATE("Successfully updated"),
    MODIFIED("Successfully modified"),
    FOUND("User is already registered"),
    PRODUCT_NOT_FOUND("Order is not found"),
    ORDER_ITEM_NOT_FOUND("Order does not have items"),
    ITEM_NOT_FOUND("Item was not found");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
