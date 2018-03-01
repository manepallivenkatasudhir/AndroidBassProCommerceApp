package com.example.manep.android_bassprocommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ReviewCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_cart);
    }


    public void goToCheckOut(View view) {
        Intent intent = new Intent(this, Checkout.class);
        startActivity(intent);
    }

    public void goToProductDetail(View view) {
        Intent intent = new Intent(this, ProductDetail.class);
        startActivity(intent);
    }

    public void goToSaveForLater(View view) {
        Intent intent = new Intent(this, SaveForLater.class);
        startActivity(intent);
    }


}
