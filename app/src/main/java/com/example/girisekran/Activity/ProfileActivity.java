package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.girisekran.Fragments.ProfileActivityBasariHikayemFragment;
import com.example.girisekran.Fragments.ProfileActivityDiyetisyenBilgilerimFragment;
import com.example.girisekran.Fragments.ProfileActivityFragment;
import com.example.girisekran.Fragments.ProfileActivityKafamKarisiyorFragment;
import com.example.girisekran.Fragments.ProfileActivityKisiselBilgilerimFragment;
import com.example.girisekran.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.profileActivityNavView);

        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.profileActivityDraverLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner, new ProfileActivityFragment()).commit();
            navigationView.setCheckedItem(R.id.profileActivityNavHesapBilgilerim);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.profileActivityNavHesapBilgilerim:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                        new ProfileActivityFragment()).commit();
                break;
            case R.id.profileActivityNavKisiselBilgilerim:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                        new ProfileActivityKisiselBilgilerimFragment()).commit();
                break;
            case R.id.profileActivityNavDiyetisyenBilgilerim:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                        new ProfileActivityDiyetisyenBilgilerimFragment()).commit();
                break;
            case R.id.profileActivityNavBasariHikayem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                        new ProfileActivityBasariHikayemFragment()).commit();
                break;
            case R.id.profileActivityNavKafamKaristi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner, new ProfileActivityKafamKarisiyorFragment()).commit();
                break;
            case R.id.profileActivityNavSikcaSorulanSorular:
                Toast.makeText(this, "S.S.S", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profileActivityNavIletisim:
                Toast.makeText(this, "Gazihan DEMİR -> Yazılımcı iletişim : gazihand@gmail.com \n " +
                        "Gülten manav -> diyetisyen iletişim : gültenmnv@gmail.com", Toast.LENGTH_LONG).show();
                break;
            case R.id.profileActivityNavCikis:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
