package com.example.girisekran.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.girisekran.Adapter.UserAdapter;
import com.example.girisekran.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatAdminActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    List<String> list;
    RecyclerView.LayoutManager layoutManager;
    UserAdapter userAdapter;
    RecyclerView userRecyclerView;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_admin);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        kullaniciİsimVeMailCek();
        System.out.println("gaziuser-> " + userName);

        listele();
    }

    public void kullaniciİsimVeMailCek() {
        String userID = firebaseUser.getUid();
        databaseReference.child("Kullanicilar").child(userID).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                        userName = hashMap.get("user");
                        list = new ArrayList<>();
                        userRecyclerView = findViewById(R.id.userRecyclerView);
                        layoutManager = new GridLayoutManager(ChatAdminActivity.this, 1);
                        userAdapter = new UserAdapter(ChatAdminActivity.this, list, ChatAdminActivity.this, userName);
                        userRecyclerView.setLayoutManager(layoutManager);
                        userRecyclerView.setAdapter(userAdapter);
                        System.out.println("gaziuser-> " + userName);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    public void listele() {
        databaseReference.child("Kullanicilar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                HashMap<String, String> kullanicilarMap = (HashMap<String, String>) dataSnapshot.getValue();
                String userName = kullanicilarMap.get("user");
                System.out.println("gazival->" + dataSnapshot.getValue());
                list.add(userName);
                userAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
