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

import com.example.girisekran.Class.Users;
import com.example.girisekran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email, password, name;
    CheckBox checkBox;
    ImageButton signup;
    Button signin;
    String stName;
    private StorageReference storageReference;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    static Users users1 = new Users();
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        email = (EditText) findViewById(R.id.edSignUpActEmail);
        password = (EditText) findViewById(R.id.edSignUpActSifre);
        name = (EditText) findViewById(R.id.edSignUpActIsim);

        checkBox = (CheckBox) findViewById(R.id.cbSignUpKosullar);

        signup = (ImageButton) findViewById(R.id.btnSignUpActKayıtOl);

        signin = (Button) findViewById(R.id.btnSignUpActSignInActGecis);
        firebaseUser = mAuth.getCurrentUser();


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
                            firebaseUser = mAuth.getCurrentUser();
                            users1.setUserEmail(firebaseUser.getEmail());
                            System.out.println("user email : " + users1.getUserEmail());
                            profilActivityFragmentBosKaydet();
                            profileActivityKisiselBilgilerFragmentBosKaydet();
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

                kayitYapilanlariGönder();
            }
        });


    }

    public void kayitYapilanlariGönder() {
        Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
        String IsimSoyisim = name.getText().toString();
        intent.putExtra("IsimSoyisim", IsimSoyisim);
        intent.putExtra("ilkgiris", "1");
        startActivity(intent);
    }


    /*    public void ismideKayitYap() {
            String userID = firebaseUser.getUid();
            String userName = name.getText().toString();
            Map profileMap = new HashMap();
            profileMap.put("İsimSoyisim", userName);
            databaseReference.child("Profile").child(userID).setValue(profileMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "isminide ekledik", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUpActivity.this, "isminide ekleyemedik yani fail", Toast.LENGTH_SHORT).show();
                }
            });
        }*/
    private void profilActivityFragmentBosKaydet() {

        stName = name.getText().toString();
        String userID = firebaseUser.getUid();
        Map<String, String> profileMap = new HashMap();
        profileMap.put("IsimSoyisim", stName);
        profileMap.put("TelefonNo", " ");
        profileMap.put("Email", firebaseUser.getEmail().toString());
        profileMap.put("DogumTarihi", " ");
        profileMap.put("ProfilResmi", " ");
        databaseReference.child("Profile").child(userID).child("ProfileHesapBilgileri").setValue(profileMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Kayıt Basarili", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Basarisiz", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "FAil", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void profileActivityKisiselBilgilerFragmentBosKaydet() {

        String userID = firebaseUser.getUid();
        Map<String, String> kisiselBilgilerMap = new HashMap<>();
        kisiselBilgilerMap.put("Meslek", "");
        kisiselBilgilerMap.put("Sehir", "");
        kisiselBilgilerMap.put("Boy", "");
        kisiselBilgilerMap.put("Yas", "");
        kisiselBilgilerMap.put("Kilo", "");
        databaseReference.child("Profile").child(userID).child("ProfileKisiselBilgiler").setValue(kisiselBilgilerMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Kayıt Basarili", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "Kisisel bilgiler else", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Kisisel bilgiler fail", Toast.LENGTH_SHORT).show();

            }
        });
    }
}