package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.girisekran.R;


public class SignInActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText email, password;
    CheckBox checkBox;
    ImageButton signin;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        checkBox = (CheckBox) findViewById(R.id.checkbox);

        signin = (ImageButton) findViewById(R.id.signin);

        signup = (Button) findViewById(R.id.signup);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(SignInActivity.this, "Sign In Button Clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }



}