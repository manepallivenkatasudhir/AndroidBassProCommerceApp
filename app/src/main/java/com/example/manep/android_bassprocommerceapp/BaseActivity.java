package com.example.manep.android_bassprocommerceapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;

/**
 * Created by manep on 2/28/2018.
 */

public class BaseActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mImageViewBarcode;
    SearchView mSearchView;
    ImageView mImageViewLogo;
    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_toolbar);
        initializeToolbar();
        initializeBottomToolbar();
    }

    private void initializeBottomToolbar() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = ItemOneFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemThreeFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = ItemFourFragment.newInstance();
                                break;
                            case R.id.action_item5:
                                selectedFragment = ItemFiveFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }


    private void initializeToolbar()
    {
       mToolbar = (Toolbar) findViewById(R.id.activityMainToolbar);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar==null||mToolbar ==null) return;
        actionBar.setDisplayShowTitleEnabled(true);
        setToolbarListners();
    }


    private void setToolbarListners()
    {

       mImageViewBarcode = (ImageView)findViewById(R.id.barcode);
       mImageViewLogo = (ImageView) findViewById(R.id.logo);
    }
}
