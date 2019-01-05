package com.example.jvmori.moviesapp.view.activities;
import android.os.Bundle;
import com.example.jvmori.moviesapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        setupBottomNav(navController);
    }

    private void setupBottomNav(NavController navController){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        if (bottomNavigationView != null)
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}
