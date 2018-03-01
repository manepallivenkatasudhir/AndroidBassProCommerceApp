package com.example.manep.android_bassprocommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ShopByDepartment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_by_department);
    }

    public void goToShopBySubDepartment(View view) {
        Intent intent = new Intent(this, ShopBySubDepartment.class);
        startActivity(intent);
    }

}
