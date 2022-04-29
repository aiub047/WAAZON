package com.waazon.backend.utils;

public enum ResponseCode {
    SUCCESS(200),
    ERROR(500),
    FORBIDDEN(403),
    UNAUTHORIZED(401),
    FOUND(302),
    NOT_FOUND(404),
    NO_CONTENT(204);

    private final int responseCode;

    ResponseCode(int code) {
        this.responseCode = code;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

}
