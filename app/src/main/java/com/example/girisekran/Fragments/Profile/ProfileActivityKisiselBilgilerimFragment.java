package com.example.girisekran.Fragments.Profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.girisekran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileActivityKisiselBilgilerimFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    EditText edMeslek, edSehir, edBoy, edYas, edKilo;
    Button btnUpload;
    String stMeslek, stSehir, stBoy, stYas, stKilo;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ProfileActivityKisiselBilgilerimFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        View rootView = inflater.inflate(R.layout.fragment_profile_activity_kisisel_bilgilerim, container, false);
        edMeslek = rootView.findViewById(R.id.edProfilActivityKisiselBilgilerFragmentMeslek);
        edSehir = rootView.findViewById(R.id.edProfilActivityKisiselBilgilerFragmentSehir);
        edBoy = rootView.findViewById(R.id.edProfilActivityKisiselBilgilerFragmentBoy);
        edYas = rootView.findViewById(R.id.edProfilActivityKisiselBilgilerFragmentYas);
        edKilo = rootView.findViewById(R.id.edProfilActivityKisiselBilgilerFragmentKilo);
        btnUpload = rootView.findViewById(R.id.btnProfilActivityKisiselBilgilerFragmentUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veriKaydet();
            }
        });
        bilgileriCek();
        return rootView;
    }

    private void veriKaydet() {
        stMeslek = edMeslek.getText().toString();
        stSehir = edSehir.getText().toString();
        stBoy = edBoy.getText().toString();
        stYas = edYas.getText().toString();
        stKilo = edKilo.getText().toString();
        String userID = firebaseUser.getUid();
        Map<String, String> kisiselBilgilerMap = new HashMap<>();
        kisiselBilgilerMap.put("Meslek", stMeslek);
        kisiselBilgilerMap.put("Sehir", stSehir);
        kisiselBilgilerMap.put("Boy", stBoy);
        kisiselBilgilerMap.put("Yas", stYas);
        kisiselBilgilerMap.put("Kilo", stKilo);
        databaseReference.child("Profile").child(userID).child("ProfileKisiselBilgiler").setValue(kisiselBilgilerMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Kayıt Basarili", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getActivity(), "Kisisel bilgiler else", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Kisisel bilgiler fail", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void bilgileriCek() {


       String userID = firebaseUser.getUid();
        databaseReference.child("Profile").child(userID).child("ProfileKisiselBilgiler")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                        String userMeslek = hashMap.get("Meslek");
                        String userSehir = hashMap.get("Sehir");
                        String userBoy = hashMap.get("Boy");
                        String userYas = hashMap.get("Yas");
                        String userKilo = hashMap.get("Kilo");
                        edMeslek.setText(userMeslek);
                        edSehir.setText(userSehir);
                        edBoy.setText(userBoy);
                        edYas.setText(userYas);
                        edKilo.setText(userKilo);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

}
