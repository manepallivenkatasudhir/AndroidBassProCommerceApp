package com.example.manep.android_bassprocommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void goToRewardsHome(View view) {
        Intent intent = new Intent(this, RewardsHome.class);
        startActivity(intent);
    }

    public void goToSaveForLater(View view) {
        Intent intent = new Intent(this, SaveForLater.class);
        startActivity(intent);
    }

    public void goToUpdatePersonalDetails(View view) {
        Intent intent = new Intent(this, PersonalInformation.class);
        startActivity(intent);
    }

    public void goToPasswordManagement(View view) {
        Intent intent = new Intent(this, PasswordManagement.class);
        startActivity(intent);
    }


}
