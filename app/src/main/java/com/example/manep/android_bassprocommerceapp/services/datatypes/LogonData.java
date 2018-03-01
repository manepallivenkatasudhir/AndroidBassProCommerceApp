package com.example.manep.android_bassprocommerceapp.services.datatypes;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jgreve on 2/23/18.
 */

public class LogonData extends BasicData {

    /*{
       "WCToken": "999901000163004%2CGyIy2XOcUpEBDo7SowF%2Bp3dAr265wnYeBA4uI68MegYO1Gs5h5st8%2BIb55U5IoeNkNKpGOD9xvwtVZVuom6YmY%2FIRtrYaoNi2w0EWBYE6CAy15aMUGSINdOkqtD4fnfoLBAu63CZbV9qE3JuCBQ%2F7U2XBLRZSxvJ3opUY35wi31iLVEQ2HdXRRohln%2Fu20UlB5XoatwFfMZdGHOgwZ08SRKD5E8MUbeLLW9SUpKuctty%2F9WDNQLWDXk4EvusR2Ma",
       "userId": "999901000163004",
       "WCTrustedToken": "999901000163004%2CwuBfgvPYIS8%2BTcv%2BxUJu10DZCN1%2FBpYpIRqhbiWgr%2Fc%3D",
       "personalizationID": "1519233592103-4"
    }*/

    String WCToken;
    String WCTrustedToken;
    String personalizationID;
    String userId;

    public String getWCToken() {
        return WCToken;
    }

    public void setWCToken(String WCToken) {
        this.WCToken = WCToken;
    }

    public String getWCTrustedToken() {
        return WCTrustedToken;
    }

    public void setWCTrustedToken(String WCTrustedToken) {
        this.WCTrustedToken = WCTrustedToken;
    }

    public String getPersonalizationID() {
        return personalizationID;
    }

    public void setPersonalizationID(String personaliztionID) {
        this.personalizationID = personaliztionID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void loadFromJson(JSONObject jo) {
        try {
            WCToken = jo.getString("WCToken");
            WCTrustedToken = jo.getString("WCTrustedToken");
            personalizationID = jo.getString("personalizationID");
            userId = jo.getString("userId");
        } catch (JSONException e) {
            applyError("Failure loading LogonData from JSON Object");
        }


    }

}
