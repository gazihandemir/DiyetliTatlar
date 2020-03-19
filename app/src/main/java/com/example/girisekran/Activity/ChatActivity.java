package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    String userName, otherName, userName1;
    ImageView imgBackButton, imgSendButton;
    EditText edMesaj;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    RecyclerView chatRecyclerView;
    ChatAdapter chatAdapter;
    List<ChatModel> list;

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
        chatAdapter = new ChatAdapter(ChatActivity.this, list, ChatActivity.this, userID.toString());
        chatRecyclerView.setAdapter(chatAdapter);
        //   bilgileriCek1();
    }


    public void mesajGönder(String text) {
        final Map messageMap = new HashMap();
        messageMap.put("text", text);

        String adminMail = "diyetisyenadmin@gmail.com";
        String adminMailFireBase = firebaseUser.getEmail();
        if (adminMail.equals(adminMailFireBase)) {
            messageMap.put("from", userName1);
            final String key = databaseReference.child("Chat").child(userName1).child(otherName).push().getKey();
            databaseReference.child("Chat").child(userName1).child(otherName).child(key).setValue(messageMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("Chat").child(otherName).child(userName1).child(key).setValue(messageMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(ChatActivity.this, "mesaj gönderildi", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    });
        } else {
            final String userID = firebaseUser.getUid();
            messageMap.put("from", userID);
            final String key1 = databaseReference.child("Chat").child(userID).child("Admin").push().getKey();
            databaseReference.child("Chat").child(userID).child("Admin").child(key1).setValue(messageMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("Chat").child("Admin").child(userID).child(key1).setValue(messageMap)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(ChatActivity.this, "mesaj gönderildi", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                    });
        }

    }

    public void mesajGönder1(String text) {
        final String userEmail = firebaseUser.getEmail();
        final String userID = firebaseUser.getUid();
        final Map messageMap = new HashMap();
        final String diyetiysyenAdmin = "diyetisyenadmin@gmail.com";
        messageMap.put("text", text);
        messageMap.put("from", userEmail.toString());
        final String key1 = databaseReference.child("Mesajlar").child(userID).child("Admin").push().getKey();
        final String key2 = databaseReference.child("Mesajlar").child("Admin").child(userID).push().getKey();

        if (diyetiysyenAdmin.equals(userEmail)) {
            databaseReference.child("Chat").child("Admin").child(userID).child(key2).setValue(messageMap).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("Chat").child(userID).child("Admin").child(key1).setValue(messageMap).
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
            databaseReference.child("Chat").child(userID).child("Admin").child(key2).setValue(messageMap).
                    addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("Chat").child("Admin").child(userID).child(key1).setValue(messageMap).
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
        if (firebaseUser.getEmail().equals("diyetisyenadmin@gmail.com")) {
            userName1 = getIntent().getExtras().getString("userName1");
        }

        // System.out.println("gazigeliyor->"+userName1+"->"+otherName);
    }

    public void bilgileriCek() {
        final String userEmail = firebaseUser.getEmail();
        final String userID = firebaseUser.getUid();
        final String diyetiysyenAdmin = "diyetisyenadmin@gmail.com";
        if (diyetiysyenAdmin.equals(userEmail)) {
            databaseReference.child("Chat").child("Admin").child(userID).child(userID).
                    addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                            String user = hashMap.get("from");
                            String text = hashMap.get("text");
                            ChatModel chatModel = new ChatModel(user, text);
                            list.add(chatModel);
                            chatAdapter.notifyDataSetChanged();
                            chatRecyclerView.scrollToPosition(list.size() - 1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        } else {
            databaseReference.child("Chat").child(userID).child("Admin").child(userID).
                    addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                            String user = hashMap.get("from");
                            String text = hashMap.get("text");
                            ChatModel chatModel = new ChatModel(user, text);
                            list.add(chatModel);
                            chatAdapter.notifyDataSetChanged();
                            chatRecyclerView.scrollToPosition(list.size() - 1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
        }

    }

    public void bilgileriCek1() {
        final String userEmail = firebaseUser.getEmail();
        final String userID = firebaseUser.getUid();
        final String diyetiysyenAdmin = "diyetisyenadmin@gmail.com";
        // if (diyetiysyenAdmin.equals(userEmail)) {
        databaseReference.child("Chat").child(userID).child("Admin").
                addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                        String user = hashMap.get("from");
                        String text = hashMap.get("text");
                        ChatModel chatModel = new ChatModel(user, text);
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
        // }
         /*else {
            databaseReference.child("Chat").child(userID).child("Admin").
                    addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            ChatModel chatModel = dataSnapshot.getValue(ChatModel.class);
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
        }*/

    }
}
