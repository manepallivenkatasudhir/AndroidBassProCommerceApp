package com.example.manep.android_bassprocommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.manep.android_bassprocommerceapp.https.HttpsUtil;

/**
 * Created by dpaunovi on 2/12/18.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HttpsUtil.InitializeKeyStore(getApplicationContext());

        Intent intent;
        //TODO Get owners data and login if found, otherwise intent to log on
        //if(login worked){
        //    intent = new Intent(this, Home.class);
        //else
        intent = new Intent(this, Login.class);

        startActivity(intent);
        finish();

    }
}
