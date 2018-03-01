package com.example.manep.android_bassprocommerceapp.services;

import com.example.manep.android_bassprocommerceapp.services.datatypes.PasswordResetData;
import com.example.manep.android_bassprocommerceapp.services.datatypes.ServiceDataHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jgreve on 2/23/18.
 */

public class ServicePasswordReset {

    public void makeCall(String logonId, String challengeAnswer, ServiceDataHandler sdh) {

        JSONObject parameters = new JSONObject();
        // START LINE -------------------------------------------------------------------------------------------------------------------------------

        //establish the URL, including retreival of "storeid" if necessary.
        // add HttpMethod as a parameter (get vs Post)
        final String url = "https://uatliveng.basspro.net/wcs/resources/store/715838534/bpsmember/resetPassword";
        final String verb = "POST";
        //final String successStatus = "201"; // will possibly be needed in the handler if you have to connect to commerce8 directly for somereason.
        //final String jsonParams = "{\"logonId\": \"FISHMONGER@ABC.COM\",\"logonPassword\": \"passw0rd\"}";
        try {
            parameters.put("logonId", logonId.toUpperCase());
            parameters.put("challengeAnswer", challengeAnswer);
        } catch (JSONException e) {
            // this exception block can be avoided by 1) not using null as a key(duh!) and 2) NOT posting an "non-finite" number as a "value" which should be avoidable.
            // you may want to write a catch/log anyway.
        }
        // END LINE -------------------------------------------------------------------------------------------------------------------------------

        new MyTask(sdh).execute(url, parameters.toString(), verb);
    }

    private static class MyTask extends GenericServiceTask {
        private ServiceDataHandler handler;

        public MyTask(ServiceDataHandler hand) {
            super();
            handler = hand;
        }

        protected void onPostExecute(JSONObject jsonData) {

            // START LINE -------------------------------------------------------------------------------------------------------------------------------

            PasswordResetData data = new PasswordResetData();  //change to actual data type returned.

            if (ErrorUtil.isCommerenceEightError(jsonData)) {
                data.applyError(ErrorUtil.handleCommerce8Error(jsonData));
            } else {
                try {
                    JSONArray jArray = jsonData.getJSONArray("userId");
                    if (jArray.length() == 1) {
                        data.setUserId(jArray.getString(0));
                    } else if (jArray.length() <= 0) {
                        data.applyError("No account for this email.");
                    } else {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; jArray.length() > i; i++) {
                            builder.append(", " + jArray.getString(i));
                        }
                        data.setUserId(builder.substring(2));
                    }
                } catch (JSONException e) {
                    data.applyError("Could not parse JSON.");
                }
            }


            //1) establish if this is a success or error.

            //2) establish how this service handles errors


            //3) cast this json to an appropriate Data Object

            //4) pass that object to the handler.


            // END LINE -------------------------------------------------------------------------------------------------------------------------------

            handler.handleResponse(data);

        }
    }
}
