package com.example.cleanmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    final Fragment homeFrag = new HomeFragment();
    final Fragment scanFrag = new ScanFragment();
    final Fragment mapFrag = new MapFragment();
    final Fragment loginFrag = new LoginFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = loginFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                new LoginFragment()).commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        fm.beginTransaction().add(R.id.fragment_container, scanFrag, "scan").hide(scanFrag).commit();
        fm.beginTransaction().add(R.id.fragment_container, mapFrag, "map").hide(mapFrag).commit();
        fm.beginTransaction().add(R.id.fragment_container, loginFrag, "home").commit();


    }

    public static void hideBottomNav(BottomNavigationView navBar){
        navBar.setVisibility(View.GONE);
    }

    public static void showBottomNav(BottomNavigationView navBar){
        navBar.setVisibility(View.VISIBLE);
    }

    private final BottomNavigationView.OnItemSelectedListener navListener;
    {
        navListener = new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fm.beginTransaction().hide(active).show(homeFrag).commit();
                        active = homeFrag;
                        break;
                    case R.id.nav_scan:
                        fm.beginTransaction().hide(active).show(scanFrag).commit();
                        active = scanFrag;
                        break;
                    case R.id.nav_map:
                        fm.beginTransaction().hide(active).show(mapFrag).commit();
                        active = mapFrag;
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,active).commit();
                return true;
            }

        };
    }


}