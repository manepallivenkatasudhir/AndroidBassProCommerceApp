package com.example.manep.android_bassprocommerceapp.services;

import com.example.manep.android_bassprocommerceapp.services.datatypes.BasicData;
import com.example.manep.android_bassprocommerceapp.services.datatypes.ServiceDataHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jgreve on 2/23/18.
 */

public class ServiceTemplate {

    public void makeCall(String param1, String param2, ServiceDataHandler sdh) {

        JSONObject parameters = new JSONObject();
        // START LINE -------------------------------------------------------------------------------------------------------------------------------

        //establish the URL, including retreival of "storeid" if necessary.
        // add HttpMethod as a parameter (get vs Post)
        final String url = "https://uatliveng.basspro.net/wcs/resources/store/715838534/loginidentity";
        final String verb = "POST";
        final String successStatus = "201"; // will possibly be needed in the handler if you have to connect to commerce8 directly for somereason.
        //final String jsonParams = "{\"logonId\": \"FISHMONGER@ABC.COM\",\"logonPassword\": \"passw0rd\"}";
        try {
            parameters.put("logonId", param1);
            parameters.put("logonPassword", param2);
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

            BasicData data = new BasicData();  //change to actual data type returned.


            //1) establish if this is a success or error.

            //2) establish how this service handles errors


            //3) cast this json to an appropriate Data Object

            //4) pass that object to the handler.


            // END LINE -------------------------------------------------------------------------------------------------------------------------------

            handler.handleResponse(data);

        }
    }
}
