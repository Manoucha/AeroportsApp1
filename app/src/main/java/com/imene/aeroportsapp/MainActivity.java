package com.imene.aeroportsapp;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {


                Fragment selectedFragment = null ;
                switch (tabId)
                {
                    case R.id.search:
                        selectedFragment = new SearchFragment() ;
                        break;
                    case R.id.map:
                        selectedFragment = new MapFragment() ;
                        break;
                    case R.id.his:
                        selectedFragment = new SearchFragment();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer , selectedFragment).commit();


            }
        });


        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                Fragment selectedFragment = null ;
                switch (tabId)
                {
                    case R.id.search:
                        selectedFragment = new SearchFragment() ;
                        break;
                    case R.id.map:
                        selectedFragment = new MapFragment() ;
                        break;
                    case R.id.his:
                        selectedFragment = new SearchFragment() ;
                        break;


                }
                getSupportFragmentManager().beginTransaction().replace(R.id.contentContainer , selectedFragment).commit();
            }
        });
    }
    }
