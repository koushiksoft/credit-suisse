package com.hello2pal.socialMediaApp.exception;

public enum ExceptionCodes {

    NO_DATA_FOUND(404, "No Data Found"),
    USER_ID_NOT_FOUND(403, "User Id Not Found"),
    VALIDATION_FAILED(406, "Validation Failed");

    private final int id;
    private final String msg;

    ExceptionCodes(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int getId() {
        return this.id;
    }

    public String getMsg() {
        return this.msg;
    }
}
