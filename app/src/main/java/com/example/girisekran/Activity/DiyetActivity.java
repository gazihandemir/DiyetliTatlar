package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.girisekran.R;

public class DiyetActivity extends AppCompatActivity {
    TextView tvSabah, tvAra1, tvOglen, tvAra2, tvAksam, tvGece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tvSabah = findViewById(R.id.tvDiyetActSabah);
        tvAra1 = findViewById(R.id.tvDiyetActAra1);
        tvOglen = findViewById(R.id.tvDiyetActOglen);
        tvAra2 = findViewById(R.id.tvDiyetActAra2);
        tvAksam = findViewById(R.id.tvDiyetAksam);
        tvGece = findViewById(R.id.tvDiyetActGece);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyet);
    }

    public void btnDiyetAnaSayfa(View view) {
        Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
        startActivity(intent);
    }
}
