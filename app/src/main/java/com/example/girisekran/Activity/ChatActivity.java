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
import java.util.UUID;

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
        chatAdapter = new ChatAdapter(ChatActivity.this, list, ChatActivity.this, userID.toString());
        chatRecyclerView.setAdapter(chatAdapter);
        // bilgileriCek();
        kullaniciCek();
    }


    public void mesajGönder(String text) {
        final Map messageMap = new HashMap();
        messageMap.put("text", text);
        String adminMail = "diyetisyenadmin@gmail.com";
        String adminMailFireBase = firebaseUser.getEmail();
        if (adminMail.equals(adminMailFireBase)) {
            messageMap.put("from", userName1);
            final UUID uuid = UUID.randomUUID();
            final String strRandom = uuid.toString();
            databaseReference.child("Chat").child(userName1).child(otherName).child(strRandom).setValue(messageMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("Chat").child(otherName).child(userName1).child(strRandom).setValue(messageMap)
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
            final UUID uuid = UUID.randomUUID();
            final String strRandom1 = uuid.toString();
            databaseReference.child("Chat").child(userID).child("Admin").child(strRandom1).setValue(messageMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                databaseReference.child("Chat").child("Admin").child(userID).child(strRandom1).setValue(messageMap)
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
            otherName = getIntent().getExtras().getString("otherName");
            System.out.println("gazigeliyor->" + userName1 + "->" + otherName);
        }

        // System.out.println("gazigeliyor->"+userName1+"->"+otherName);
    }

    public void bilgileriCek2() {
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

    public void bilgileriCek() {
        String adminMail = "diyetisyenadmin@gmail.com";
        String adminMailFireBase = firebaseUser.getEmail();
        if (adminMail.equals(adminMailFireBase)) {
            System.out.println("gazi->pikacu" + otherName);

            System.out.println("gazinull->" + userName1 + "-" + userName + "" + otherName);
            databaseReference.child("Chat").child(userName1).child("oGeB49XXUsRAlCypndQoUBygPNI3").
                    addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            // System.out.println("kelle->" + userKellesi + "kelle name " + kelleName + "--");
                            kullaniciCek();
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

    public void kullaniciCek() {
        databaseReference.child("Kullanicilar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot1, @Nullable String s) {
                HashMap<String, String> kelleMap = (HashMap<String, String>) dataSnapshot1.getValue();
                kelleName = kelleMap.get("user");

                userKellesi = dataSnapshot1.getKey();
                System.out.println("kelle->" + userKellesi + "kelle name " + kelleName + " other name " + otherName);
                System.out.println("gazi->userKellesi " + userKellesi);
                System.out.println("gazi->kelleName" + kelleName);
                System.out.println("gazi->otherName" + otherName);


                System.out.println("gazi->gercekKelle" + gercekKelle);
                String adminMail = "diyetisyenadmin@gmail.com";
                String adminMailFireBase = firebaseUser.getEmail();
                if (kelleName.equals(otherName)) {
                    gercekKelle = userKellesi;

                    if (adminMail.equals(adminMailFireBase)) {
                        System.out.println("gazi->pikacu" + otherName);

                        System.out.println("gazinull->" + userName1 + "-" + userName + "" + otherName);
                        databaseReference.child("Chat").child(userName1).child(gercekKelle).
                                addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                        // System.out.println("kelle->" + userKellesi + "kelle name " + kelleName + "--");
                                        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                                        System.out.println("gazi->userKellesi1" + userKellesi);
                                        System.out.println("gazi->kelleName1" + kelleName);
                                        System.out.println("gazi->otherName1" + otherName);
                                        System.out.println("gazi->gercekKelle1" + gercekKelle);
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
                    } else {
                        String userID = firebaseUser.getUid();
                        System.out.println("gaziler->userKellesi1" + userKellesi);
                        System.out.println("gaziler->kelleName1" + kelleName);
                        System.out.println("gaziler->otherName1" + otherName);
                        System.out.println("gaziler->gercekKelle1" + gercekKelle);
                    }
                }
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
