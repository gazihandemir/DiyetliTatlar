package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.girisekran.Fragments.CalculationsActivityBesinlerCaloriesFragment;
import com.example.girisekran.R;
import com.google.android.material.navigation.NavigationView;

public class CalculationsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculations);
        navigationView = findViewById(R.id.CalculationsActivityNavView);
        toolbar = findViewById(R.id.toolbarCalculations);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.CalculationsActivityDraverLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_conteiner_cal, new CalculationsActivityBesinlerCaloriesFragment()).commit();
            navigationView.setCheckedItem(R.id.calculationsActivitiyBesinKalorileri);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.calculationsActivitiyBesinKalorileri:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner_cal,
                        new CalculationsActivityBesinlerCaloriesFragment()).commit();
                break;
            case R.id.calculationsActivitiyAnasayfa:
                Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
                startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
