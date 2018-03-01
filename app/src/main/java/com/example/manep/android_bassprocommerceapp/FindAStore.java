package com.example.manep.android_bassprocommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FindAStore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_astore);
    }

    public void goToStoreDetails(View view) {
        Intent intent = new Intent(this, StoreInformation.class);
        startActivity(intent);
    }

}
