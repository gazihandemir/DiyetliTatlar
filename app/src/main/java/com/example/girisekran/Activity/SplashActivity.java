package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.girisekran.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {
    ImageView imgAnimasyon;
    private FirebaseUser user;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        Animation animasyonDondur = AnimationUtils.loadAnimation(this, R.anim.animasyondondur);
        imgAnimasyon = findViewById(R.id.imgAnimasyon);
        imgAnimasyon.setAnimation(animasyonDondur);



    }

    @Override
    protected void onResume() {
        new CountDownTimer(5000, 1000) {
            @Override
            public void onFinish() {
                user = mAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onTick(long l) {

            }


        }.start();
        super.onResume();

    }
}
