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

    private SearchView searchView;
    NavController navController;
    private OnSearchCallback onSearchCallback;
    public Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.searchView);
        context = this;

        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        setupBottomNav(navController);

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    //Toast.makeText(getContext(), "Focused", Toast.LENGTH_SHORT).show();
                     navController.navigate(R.id.action_home_to_searchResultsFragment2);
                }else{
                    navController.navigateUp();
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 1 && onSearchCallback != null){
                    onSearchCallback.onSearch(query);
                }
                return true;
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

    public void setOnSearchCallback(OnSearchCallback onSearchCallback){
        this.onSearchCallback = onSearchCallback;
    }


    private void setupBottomNav(NavController navController){
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        if (bottomNavigationView != null)
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

}
