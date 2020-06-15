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
import com.google.firebase.firestore.FirebaseFirestore;
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
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        firebaseFirestore = FirebaseFirestore.getInstance();
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
//                            users1.setUserEmail(firebaseUser.getEmail());
                            System.out.println("user email : " + firebaseUser.getEmail());
                            profilActivityFragmentBosKaydet();
                            profileActivityKisiselBilgilerFragmentBosKaydet();
                            profileActivityDiyetisyenBilgilerimFragmentFireStoreBosKaydet();
                            profileActivityBasariHikayemFragmentFireStoreBosKaydet();
                            diyetActivityDiyetEkleBilgileriFireStoreBosKaydet();
                            kullaniciEkle();
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
        profileMap.put("TelefonNo", "");
        profileMap.put("Email", firebaseUser.getEmail().toString());
        profileMap.put("DogumTarihi", "");
        String userDefaultPhoto = "https://firebasestorage.googleapis.com/v0/b/diyetlitatlar.appspot.com/o/images%2FuserDefaultPhoto.jpg?alt=media&token=12c9d6d2-208b-4bbc-b9ba-47376f36ae8e";
        profileMap.put("ProfilResmi", userDefaultPhoto);

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

    public void profileActivityDiyetisyenBilgilerimFragmentFireStoreBosKaydet() {
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("Email", firebaseUser.getEmail().toString());
        hashmap.put("Olcüm", "");
        hashmap.put("Hastalik", "");
        hashmap.put("Tahlil", "");
        hashmap.put("Hikaye", "");
        hashmap.put("id", firebaseUser.getUid().toString());
        firebaseFirestore.collection("ProfileDiyetisyenBilgileri").document(firebaseUser.getEmail().toString())
                .set(hashmap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SignUpActivity.this, "FireStore diyetisyen bilgilerim Basarili", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(SignUpActivity.this, "FireStore Basarisiz", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void profileActivityBasariHikayemFragmentFireStoreBosKaydet() {
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("Email", firebaseUser.getEmail().toString());
        hashmap.put("haft1a", "0");
        hashmap.put("haft2a", "0");
        hashmap.put("haft3a", "0");
        hashmap.put("haft4a", "0");
        hashmap.put("basariHikayesi", "");
        firebaseFirestore.collection("ProfileBasariHikayesi").document(firebaseUser.getEmail().toString())
                .set(hashmap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SignUpActivity.this, "FireStore basari hikayem Basarili", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(SignUpActivity.this, "FireStore Basarisiz", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void diyetActivityDiyetEkleBilgileriFireStoreBosKaydet() {
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("Email", firebaseUser.getEmail().toString());
        hashmap.put("diyetSabah", "-");
        hashmap.put("diyetAr1a", "-");
        hashmap.put("diyetOglen", "-");
        hashmap.put("diyetAr2a", "-");
        hashmap.put("diyetAksam", "-");
        hashmap.put("diyetGece", "-");
        firebaseFirestore.collection("DiyetListeleri").document(firebaseUser.getEmail().toString())
                .set(hashmap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(SignUpActivity.this, "FireStore diyet ekle bos basarili", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(SignUpActivity.this, "FireStore Basarisiz", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void kullaniciEkle() {
        Map<String, String> kullanicilarMap = new HashMap();
        String userEmail = firebaseUser.getEmail();
        String userID = firebaseUser.getUid();
        stName = name.getText().toString();
        //  kullanicilarMap.put("userMail", userEmail);
        kullanicilarMap.put("user", stName);
        databaseReference.child("Kullanicilar").child(userID).setValue(kullanicilarMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Kullanici ekleme başarılı", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}