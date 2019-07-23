package com.hello2pal.socialMediaApp.exception;

public class SocialAppException extends RuntimeException {

    private int errorCode;
    private String errorMsg;

    public SocialAppException(ExceptionCodes code, String message) {
        super(message);
        this.errorMsg = code.getMsg();
        this.errorCode = code.getId();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}

