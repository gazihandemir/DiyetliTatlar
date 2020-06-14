package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.girisekran.R;

public class DiyetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyet);
    }

    public void btnDiyetAnaSayfa(View view) {
        Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
        startActivity(intent);
    }
}
