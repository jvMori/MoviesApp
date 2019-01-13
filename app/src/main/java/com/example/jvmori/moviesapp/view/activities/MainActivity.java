package com.example.jvmori.moviesapp.view.activities;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.jvmori.moviesapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private OnSearchCallback onSearchCallback;
    public Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchView);
        context = this;

        NavController navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        setupBottomNav(navController);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    //Toast.makeText(getContext(), "Focused", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(context, R.id.my_nav_host_fragment).navigate(R.id.action_home_to_searchResultsFragment2);
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1 && onSearchCallback != null){
                    onSearchCallback.onSearch(newText);
                }
                return true;
            }
        });

    }

    public interface OnSearchCallback{
        void onSearch(String query);
    }

    private void setupBottomNav(NavController navController){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        if (bottomNavigationView != null)
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}
