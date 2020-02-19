package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.girisekran.R;
import com.example.girisekran.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email, password, name;
    CheckBox checkBox;
    ImageButton signup;
    Button signin;
    static Users users1 = new Users();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.edSignUpActEmail);
        password = (EditText) findViewById(R.id.edSignUpActSifre);
        name = (EditText) findViewById(R.id.edSignUpActIsim);

        checkBox = (CheckBox) findViewById(R.id.cbSignUpKosullar);

        signup = (ImageButton) findViewById(R.id.btnSignUpActKayıtOl);

        signin = (Button) findViewById(R.id.btnSignUpActSignInActGecis);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(i);
            }
        });


    }

    public void kayıtOl(View view) {

        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isComplete()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            users1.setUserEmail(firebaseUser.getEmail());
                            System.out.println("user email : " + users1.getUserEmail());

                        } else {
                            Toast.makeText(SignUpActivity.this, "Kayıt oluşturulamadı complateListener", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e != null) {
                    Toast.makeText(SignUpActivity.this, "Kayıt esnasında bir sorun oluştu failureListener", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SignUpActivity.this, "Hoşgeldiniz \n " + users1.getUserEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
                startActivity(intent);
            }
        });

    }
}