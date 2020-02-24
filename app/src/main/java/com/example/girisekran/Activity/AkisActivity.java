package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.girisekran.Fragments.DenemeFragment1;
import com.example.girisekran.Fragments.DenemeFragment2;
import com.example.girisekran.Hesaplamalar.AGEActivity;
import com.example.girisekran.Hesaplamalar.BKİActivity;
import com.example.girisekran.Hesaplamalar.BMHActivity;
import com.example.girisekran.Hesaplamalar.GunlukSuMiktariHesaplama;
import com.example.girisekran.Hesaplamalar.IdealKiloHesaplama;
import com.example.girisekran.R;

public class AkisActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.bki_menu) {
            Intent intent = new Intent(getApplicationContext(), BKİActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.bmh_menu) {
            Intent intent = new Intent(getApplicationContext(), BMHActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.age_menu) {
            Intent intent = new Intent(getApplicationContext(), AGEActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.ideal_menu) {
            Intent intent = new Intent(getApplicationContext(), IdealKiloHesaplama.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.su_menu) {
            Intent intent = new Intent(getApplicationContext(), GunlukSuMiktariHesaplama.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akis);
    }

    public void deneme1(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DenemeFragment1 firstFragment = new DenemeFragment1();
        fragmentTransaction.replace(R.id.linearLayout2, firstFragment).commit();
    }

    public void deneme2(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DenemeFragment2 firstFragment = new DenemeFragment2();
        fragmentTransaction.replace(R.id.linearLayout2, firstFragment).commit();
    }

    public void deneme5(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        String IsimSoyisim = getIntent().getStringExtra("IsimSoyisim");
        intent.putExtra("IsimSoyisim",IsimSoyisim);
        startActivity(intent);
    }
}
