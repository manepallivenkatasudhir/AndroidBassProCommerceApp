package com.example.manep.android_bassprocommerceapp.services;

import android.os.AsyncTask;

import com.example.manep.android_bassprocommerceapp.https.HttpsUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.net.ssl.HttpsURLConnection;

import static com.example.manep.android_bassprocommerceapp.https.HttpsUtil.Supported_HTTP_METHODS.GET;
import static com.example.manep.android_bassprocommerceapp.https.HttpsUtil.Supported_HTTP_METHODS.POST;

/**
 * Created by jgreve on 2/23/18.
 */

public class GenericServiceTask extends AsyncTask<String, Void, JSONObject> {


    @Override
    protected JSONObject doInBackground(String... params) {
        HttpsURLConnection conn = null;

        String param1 = params[1];
        final String url = params[0];
        final String verb = params[2];


        try {

            conn = HttpsUtil.getConnection(url);

            switch (HttpsUtil.Supported_HTTP_METHODS.valueOf(verb)) {
                case POST:
                    HttpsUtil.applyGenericPostHeaders(conn);
                    break;
                case GET:
                    HttpsUtil.applyGenericGetHeaders(conn);
                    break;
                default:
                    break;
            }

            if (param1 != null && !param1.isEmpty()) {
                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(param1);
                wr.flush();
                wr.close();
            }

            StringBuilder stb = new StringBuilder(), errstb = new StringBuilder();
            BufferedReader in, err;
            InputStream tmpStream;
            String inputLine;
            tmpStream = conn.getInputStream();
            if (tmpStream != null) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((inputLine = in.readLine()) != null) {
                    stb.append(inputLine); //raw complete
                }
                in.close();
            }

            tmpStream = conn.getErrorStream();
            if (tmpStream != null) {
                err = new BufferedReader(new InputStreamReader(
                        conn.getErrorStream()));
                while ((inputLine = err.readLine()) != null) {
                    errstb.append(inputLine);
                }
                err.close();
            }


            if (errstb.length() > 0)
                return new JSONObject(errstb.toString());
            else
                return new JSONObject(stb.toString());
        } catch (MalformedURLException ex) {
            return ErrorUtil.makeJSONError("Bad Endpoint (" + url + ") : " + ex.getMessage());
        } catch (IOException ex) {
            return ErrorUtil.makeJSONError("A Communication Errro has occured: " + ex.getMessage());
        } catch (JSONException e) {
            return ErrorUtil.makeJSONError("Poor JSON conversion");
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }

}