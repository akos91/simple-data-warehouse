package com.interview.simpledatawarehouse.controller.response;

public class AdvertisementStatErrorResponse implements AdvertisementStatApi {

    private String errorMessage;

    public AdvertisementStatErrorResponse() {
    }

    public AdvertisementStatErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
