package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.girisekran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    String userName;
    ImageView imgBackButton, imgSendButton;
    EditText edMesaj;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        userName = getIntent().getExtras().getString("userName");
        tanimla();
        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
                startActivity(intent);
            }
        });
        imgSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mesaj = edMesaj.getText().toString();
                edMesaj.setText("");
                mesajGönder(mesaj);
            }
        });
    }


    public void mesajGönder(String text) {
        final String userEmail = firebaseUser.getEmail();
        final String userID = firebaseUser.getUid();
        final Map messageMap = new HashMap();
        final String diyetiysyenAdmin = "diyetisyenadmin@gmail.com";
        messageMap.put("text", text);
        messageMap.put("from", userEmail.toString());
        if (diyetiysyenAdmin.equals(userEmail)) {
            databaseReference.child("Chat").child("Admin").child(userID).child(userID).setValue(messageMap).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("Chat").child(userID).child("Admin").child(userID).setValue(messageMap).
                                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ChatActivity.this, "Mesaj başarı ile Kaydedildi1", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ChatActivity.this, "Mesaj gönderme Başarısız1" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            databaseReference.child("Chat").child(userID).child("Admin").child(userID).setValue(messageMap).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("Chat").child("Admin").child(userID).child(userID).setValue(messageMap).
                                        addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ChatActivity.this, "Mesaj başarı ile Kaydedildi2", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ChatActivity.this, "Mesaj gönderme Başarısız2" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }


    }

    public void tanimla() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        imgBackButton = findViewById(R.id.btnChatActivityBackButton);
        imgSendButton = findViewById(R.id.btnChatActivitySendButton);
        edMesaj = findViewById(R.id.edChatAcitivityMesaj);
    }
}
