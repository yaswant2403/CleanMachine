package com.example.cleanmachine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;


import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements ScanFragment.ScanFragmentListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new LoginFragment()).commit();
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

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
                Fragment selectedFragment = null;
                HomeFragment homeFragment = HomeFragment.newInstance(3);
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        //selectedFragment = new HomeFragment();
                        //selectedFragment.newInstance(2);
                        selectedFragment = homeFragment;
                        break;
                    case R.id.nav_scan:
                        selectedFragment = new ScanFragment();
                        break;
                    case R.id.nav_map:
                        selectedFragment = new MapFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            }

        };
    }


    @Override
    public void countTotal(int total) {
        HomeFragment.newInstance(total);
    }

}