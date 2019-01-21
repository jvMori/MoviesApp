package com.example.jvmori.moviesapp.view.activities;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.jvmori.moviesapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        setupBottomNav(navController);
    }

    private void setupBottomNav(NavController navController){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        if (bottomNavigationView != null)
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}
