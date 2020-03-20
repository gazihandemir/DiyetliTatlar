package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.girisekran.Adapter.ChatAdapter;
import com.example.girisekran.Class.ChatModel;
import com.example.girisekran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    String userName, otherName;
    ImageView imgBackButton, imgSendButton;
    EditText edMesaj;
    TextView tvName;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    RecyclerView chatRecyclerView;
    ChatAdapter chatAdapter;
    List<ChatModel> list;
    String userKellesi;
    String kelleName;
    String gercekKelle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        list = new ArrayList<>();
        tanimla();
        backButton();
        sendButton();
        String userID = firebaseUser.getUid();
        chatRecyclerView = findViewById(R.id.chatAcitivityRecyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ChatActivity.this, 1);
        chatRecyclerView.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(ChatActivity.this, list, ChatActivity.this, userName);
        chatRecyclerView.setAdapter(chatAdapter);
        bilgileriCek();
    }


    public void mesajGönder(String text) {
        final Map messageMap = new HashMap();
        messageMap.put("text", text);
        messageMap.put("from", userName);
        System.out.println("gazigeliyor2->" + userName + "->" + otherName);

        final String key = databaseReference.child("Chat").child(userName).child(otherName).push().getKey();
        databaseReference.child("Chat").child(userName).child(otherName).child(key).setValue(messageMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            databaseReference.child("Chat").child(otherName).child(userName).child(key).setValue(messageMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(ChatActivity.this, "karsilikli mesaj veri tabanına gönderme başarılı", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ChatActivity.this, "karsilikli hata oluştu içeri", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ChatActivity.this, "karsilikli hata olustu disari", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void backButton() {
        imgBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AkisActivity.class);
                startActivity(intent);
            }
        });
    }

    public void sendButton() {
        imgSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mesaj = edMesaj.getText().toString();
                edMesaj.setText("");
                mesajGönder(mesaj);
            }
        });
    }

    public void tanimla() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        imgBackButton = findViewById(R.id.btnChatActivityBackButton);
        imgSendButton = findViewById(R.id.btnChatActivitySendButton);
        edMesaj = findViewById(R.id.edChatAcitivityMesaj);
        tvName = findViewById(R.id.tvChatAdminActivityName);
        userName = getIntent().getExtras().getString("userName");
        otherName = getIntent().getExtras().getString("otherName");
        System.out.println("gazigeliyor->" + userName + "->" + otherName);
        tvName.setText(otherName);

        // System.out.println("gazigeliyor->"+userName1+"->"+otherName);
    }

    public void bilgileriCek() {
        databaseReference.child("Chat").child(userName).child(otherName)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                        String from = hashMap.get("from");
                        String text = hashMap.get("text");
                        ChatModel chatModel = new ChatModel(from, text);
                        list.add(chatModel);
                        chatAdapter.notifyDataSetChanged();
                        chatRecyclerView.scrollToPosition(list.size() - 1);
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
