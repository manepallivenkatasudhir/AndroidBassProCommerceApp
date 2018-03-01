package com.example.manep.android_bassprocommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RewardsHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards_home);
    }

    public void goToCheckCardsBalance(View view) {
        Intent intent = new Intent(this, CreditCardBalance.class);
        startActivity(intent);
    }

    public void goToRequestCertificate(View view) {
        Intent intent = new Intent(this, RewardsNewCertificate.class);
        startActivity(intent);
    }

    public void goToRequestNewCard(View view) {
        Intent intent = new Intent(this, RewardsNewCard.class);
        startActivity(intent);
    }

    public void goToEnterReceipt(View view) {
        Intent intent = new Intent(this, RewardsEnterReceipt.class);
        startActivity(intent);
    }


}
