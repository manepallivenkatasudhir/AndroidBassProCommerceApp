package com.example.manep.android_bassprocommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ShopBySubDepartment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_by_sub_department);
    }

    public void goToProductListing(View view) {
        Intent intent = new Intent(this, ProductListing.class);
        startActivity(intent);
    }
}
