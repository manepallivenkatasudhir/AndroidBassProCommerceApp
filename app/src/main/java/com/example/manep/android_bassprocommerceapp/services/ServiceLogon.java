package com.example.manep.android_bassprocommerceapp.services;

import com.example.manep.android_bassprocommerceapp.services.datatypes.LogonData;
import com.example.manep.android_bassprocommerceapp.services.datatypes.ServiceDataHandler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jgreve on 2/23/18.
 */

public class ServiceLogon { // might not need the Abstract class, all value add might be service specific.

    public void makeCall(String logonId, String logonPassword, ServiceDataHandler sdh) {

        JSONObject parameters = new JSONObject();
        final String url = "https://uatliveng.basspro.net/wcs/resources/store/715838534/loginidentity";
        //final String param1 = jsonParams; //"{\"logonId\": \"FISHMONGER@ABC.COM\",\"logonPassword\": \"passw0rd\"}";
        final String verb = "POST";

        try {
            parameters.put("logonId", logonId.toUpperCase());
            parameters.put("logonPassword", logonPassword);
        } catch (JSONException e) {
            // this exception block can be avoided by NOT posting an obnoxiously long hard coded number as a "value"
            // you may want to write a catch/log anyway.

            // Log.d("title", "detail");
        }
        new MyTask(sdh).execute(url, parameters.toString(), verb);
    }

    private static class MyTask extends GenericServiceTask {
        private ServiceDataHandler handler;

        public MyTask(ServiceDataHandler hand) {
            super();
            handler = hand;
        }

        protected void onPostExecute(JSONObject jsonData) {


            LogonData data = new LogonData();

            if (jsonData.has("errorKey")) {
                data.applyError(ErrorUtil.handleCommerce8Error(jsonData));
            } else {
                data.loadFromJson(jsonData);
            }


            handler.handleResponse(data);

        }
    }
}
