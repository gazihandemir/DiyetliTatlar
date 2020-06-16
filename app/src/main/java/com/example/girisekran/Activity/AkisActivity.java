package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.girisekran.Adapter.AkisAdapter;
import com.example.girisekran.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class AkisActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    ArrayList<String> userEmailFromFB;
    ArrayList<String> userCommentFromFB;
    ArrayList<String> userImageFromFB;
    AkisAdapter akisAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akis);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        userCommentFromFB = new ArrayList<>();
        userEmailFromFB = new ArrayList<>();
        userImageFromFB = new ArrayList<>();
        verileriCek();
        recyclerView = findViewById(R.id.recAkisAct);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        akisAdapter = new AkisAdapter(userEmailFromFB, userCommentFromFB, userImageFromFB);
        recyclerView.setAdapter(akisAdapter);

    }

    public void chatActivityGecis(View view) {
      /*  FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DenemeFragment1 firstFragment = new DenemeFragment1();
        fragmentTransaction.replace(R.id.linearLayout2, firstFragment).commit();*/


        Intent intent = new Intent(getApplicationContext(), ChatAdminActivity.class);
        startActivity(intent);


    }

    public void calculationsActivityGecis(View view) {
        Intent intent = new Intent(getApplicationContext(), CalculationsActivity.class);
        startActivity(intent);
      /*  FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DenemeFragment2 firstFragment = new DenemeFragment2();
        fragmentTransaction.replace(R.id.linearLayout2, firstFragment).commit();*/
    }

    public void uploadPhotoActivityGecis(View view) {
        Intent intent = new Intent(getApplicationContext(), UploadPhotoActivity.class);
        startActivity(intent);
    }

    public void diyetActivityGecis(View view) {
        Intent intent = new Intent(getApplicationContext(), DiyetActivity.class);
        startActivity(intent);
    }

    public void profileActivityGecis(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        String IsimSoyisim = getIntent().getStringExtra("IsimSoyisim");
        intent.putExtra("IsimSoyisim", IsimSoyisim);
        startActivity(intent);
    }

    private void verileriCek() {
        String userID = firebaseUser.getUid();
        databaseReference.child("AkisPhoto").child("FotografBilgileri").child("1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                        String userEmail = hashMap.get("Email");
                        String userComment = hashMap.get("Comment");
                        String userImage = hashMap.get("UploadPhoto");
                        userCommentFromFB.add(userComment);
                        userEmailFromFB.add(userEmail);
                        userImageFromFB.add(userImage);
                        akisAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
        databaseReference.child("AkisPhoto").child("FotografBilgileri").child("2")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                        String userEmail = hashMap.get("Email");
                        String userComment = hashMap.get("Comment");
                        String userImage = hashMap.get("UploadPhoto");
                        userCommentFromFB.add(userComment);
                        userEmailFromFB.add(userEmail);
                        userImageFromFB.add(userImage);
                        akisAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

    }


 /*   private void mailKaydet() {
        String userID = firebaseUser.getUid();
        //String IsimSoyisim = getIntent().getStringExtra("IsimSoyisim");
        String userEmail = firebaseUser.getEmail().toString();
        Map<String, String> profileMap = new HashMap<>();
        // profileMap.put("IsimSoyisim", IsimSoyisim);
        profileMap.put("Email", userEmail);
        databaseReference.child("Profile").child(userID).child("ProfileHesapBilgileri").setValue(profileMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Krall", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Baslarken hata oluştu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/


}
