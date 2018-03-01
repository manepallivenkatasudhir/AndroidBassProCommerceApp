package com.example.manep.android_bassprocommerceapp.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jgreve on 2/23/18.
 */

public class ErrorUtil {

    public static JSONObject makeJSONError(String message) {

        JSONObject retval = new JSONObject();

        try {
            retval.put("errorMessage", message);
        } catch (JSONException e) {
            //e.printStackTrace();
            //TODO should be pretty safe but fix this catch clause anyway
        }

        return retval;

    }

    public static String handleCommerce8Error(JSONObject serviceErrorResponseJson) {

        JSONArray errors = null;
        try {
            errors = serviceErrorResponseJson.getJSONArray("errors");

            JSONObject firstError = new JSONObject();// ))errors.getJSONObject(0);

            String errorCode = firstError.getString("errorCode");
            String errorMsg = firstError.getString("errorMessage");
            String errorKey = firstError.getString("errorKey");

            StringBuilder message = new StringBuilder();

            if (errorCode.equalsIgnoreCase("2010") && errorKey.equalsIgnoreCase("_ERR_BAD_PARMS")) {
                message.append("; Bad Parameter, also forgot password/email not registered");
                errorCode = "WCS_2032";//Forgot password Email Not registered
            } else if (errorCode.equalsIgnoreCase("2030") && errorKey.equalsIgnoreCase("_ERR_LOGONID_ALREDY_EXIST")) {
                message.append("; Cannot create, User already exists");
                errorCode = "WCS_2031";//Create Account Email already Used
            } else if ("CWXBB1010E".equalsIgnoreCase(errorKey)) {//the token is expired
                message.append("; Token is Expired, need to log in again");
                errorCode = "WCS_CWXBB1010E";
            } else if ("CMN1039E".equalsIgnoreCase(errorKey)) {//same login is used by different user
                message.append("; user already logged in, you can't do it twice.");
                errorCode = "WCS_CMN1039E";

            } else {
                errorCode = "WCS_" + errorCode;
            }
            return message.substring(2);

        } catch (JSONException e) {
            return "JSON Conversion Failue: " + serviceErrorResponseJson.toString();
        }
    }

    public static boolean isCommerenceEightError(JSONObject responseJSon) {

        return responseJSon.has("errorKey");

    }

}
