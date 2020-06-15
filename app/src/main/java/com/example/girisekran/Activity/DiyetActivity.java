package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.girisekran.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class DiyetActivity extends AppCompatActivity {
    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    DocumentReference docRef;
    TextView tvSabah, tvAra1, tvOglen, tvAra2, tvAksam, tvGece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diyet);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        tvSabah = findViewById(R.id.tvDiyetActSabah);
        tvAra1 = findViewById(R.id.tvDiyetActAra1);
        tvOglen = findViewById(R.id.tvDiyetActOglen);
        tvAra2 = findViewById(R.id.tvDiyetActAra2);
        tvAksam = findViewById(R.id.tvDiyetActAksam);
        tvGece = findViewById(R.id.tvDiyetActGece);
        String yazi = tvSabah.getText().toString();
        System.out.println("yazi -> " + yazi);
        bilgileriCekDiyet();
    }

    public void btnDiyetAnaSayfa(View view) {
        Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
        startActivity(intent);
    }

    private void bilgileriCekDiyet() {
        docRef = firebaseFirestore.collection("DiyetListeleri").document(firebaseUser.getEmail().toString());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> hashMap = documentSnapshot.getData();
                String userEmail = (String) hashMap.get("Email");
                String userDiyetSabah = (String) hashMap.get("diyetSabah");
                String userDiyetAra1 = (String) hashMap.get("diyetAr1a");
                String userDiyetOglen = (String) hashMap.get("diyetOglen");
                String userDiyetAra2 = (String) hashMap.get("diyetAr2a");
                String userDiyetAksam = (String) hashMap.get("diyetAksam");
                String userDiyetGece = (String) hashMap.get("diyetGece");
                tvSabah.setText(userDiyetSabah);
                tvAra1.setText(userDiyetAra1);
                tvOglen.setText(userDiyetOglen);
                tvAra2.setText(userDiyetAra2);
                tvAksam.setText(userDiyetAksam);
                tvGece.setText(userDiyetGece);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "Veriler Cekilemedi ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

