package com.example.manep.android_bassprocommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Home extends BaseActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


    }

    public void goToReviewCart(View view) {
        Intent intent = new Intent(this, ReviewCart.class);

        startActivity(intent);
    }

    public void goToFindAStore(View view) {
        Intent intent = new Intent(this, FindAStore.class);

        startActivity(intent);
    }

    //
//    public void goToCamera(View view) {
//        Intent intent = new Intent(this, ReviewCart.class);
//        //TODO Will probably be deleted
//        startActivity(intent);
//    }
//
    public void goToShopByDepartment(View view) {
        Intent intent = new Intent(this, ShopByDepartment.class);

        startActivity(intent);
    }
//
//    public void goToMyRewards(View view) {
//        Intent intent = new Intent(this, RewardsHome.class);
//
//        startActivity(intent);
//    }
//
//    public void goToLocalEvents(View view) {
//        Intent intent = new Intent(this, LocalEvents.class);
//
//        startActivity(intent);
//    }
//
//    public void goToGiftCardPLP(View view) {
//        Intent intent = new Intent(this, GiftCardPLP.class);
//
//        startActivity(intent);
//    }
//
//    public void goToApplicationSettings(View view) {
//        Intent intent = new Intent(this, ApplicationSettings.class);
//
//        startActivity(intent);
//    }
//

    public void goToMyProfile(View view) {
        Intent intent = new Intent(this, Profile.class);

        startActivity(intent);
    }

    public void goToOffers(View view) {
        Intent intent = new Intent(this, Offers.class);

        startActivity(intent);
    }


    public void goToMore(View view) {
        Intent intent = new Intent(this, More.class);

        startActivity(intent);
    }




}
