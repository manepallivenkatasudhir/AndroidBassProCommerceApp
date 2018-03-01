package com.example.manep.android_bassprocommerceapp.services.datatypes;

/**
 * Created by jgreve on 2/23/18.
 */

public class BasicData {

    private boolean isError = false;
    private String errorMessage;


    public void applyError(String message) {
        isError = true;
        errorMessage = message;
    }

    public boolean isError() {
        return isError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}