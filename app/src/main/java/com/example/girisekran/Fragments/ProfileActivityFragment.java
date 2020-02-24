package com.example.girisekran.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.girisekran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;


public class ProfileActivityFragment extends Fragment {
    ImageView imgProfilResmi;
    TextView tvProfilIsmi, tvProfilMail;
    Button btnUpload;
    EditText edIsimSoyisim, edTelefonNo, edEmail, edDogumTarihi;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    String stIisimSoyisim;
    String stTelefonNo;
    String stEmail;
    String stDogumTarihi;

    public ProfileActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        //  System.out.println("gazi -> onCreateView başladı");
        View rootView = inflater.inflate(R.layout.fragment_profile_activity, container, false);
        imgProfilResmi = rootView.findViewById(R.id.imgProfileFragmentActProfilResmi);
        tvProfilIsmi = rootView.findViewById(R.id.tvProfileFragmentActProfilIsmi);
        tvProfilMail = rootView.findViewById(R.id.tvProfileFragmentActProfilEmail);
        btnUpload = rootView.findViewById(R.id.btnProfileFragmentActUpload);
        edIsimSoyisim = rootView.findViewById(R.id.edProfileFragmentActİsimSoyisim);
        edTelefonNo = rootView.findViewById(R.id.edProfileFragmentActTelefonNumarası);
        edEmail = rootView.findViewById(R.id.edProfileFragmentActEmail);
        edDogumTarihi = rootView.findViewById(R.id.edProfileFragmentActDogumTarihi);
        KayitOlanKullanicilarinBilgileriniHemenKaydet();
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("tvProfilİsmi", tvProfilIsmi.getText().toString());
                intent.putExtra("tvProfilMail", tvProfilMail.getText().toString());
                intent.putExtra("edIisimSoyisim", edIsimSoyisim.getText().toString());
                intent.putExtra("edTelefonNo", edTelefonNo.getText().toString());
                intent.putExtra("edEmail", edEmail.getText().toString());
                intent.putExtra("edDogumTarihi", edDogumTarihi.getText().toString());
                startActivity(intent);
                */
                veriKaydet();
            }
        });
        return rootView;

    }


    private void veriKaydet() {
        stIisimSoyisim = edIsimSoyisim.getText().toString();
        stTelefonNo = edTelefonNo.getText().toString();
        stEmail = edEmail.getText().toString();
        stDogumTarihi = edDogumTarihi.getText().toString();
        String userID = firebaseUser.getUid();
        Map<String, String> profileMap = new HashMap();
        profileMap.put("IsimSoyisim", stIisimSoyisim);
        profileMap.put("TelefonNo", stTelefonNo);
        profileMap.put("Email", firebaseUser.getEmail().toString());
        profileMap.put("DogumTarihi", stDogumTarihi);
        databaseReference.child("Profile").child("ProfileHesapBilgileri").child(userID).setValue(profileMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Kayıt Basarili", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Basarisiz", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "FAil", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void KayitOlanKullanicilarinBilgileriniHemenKaydet() {
        String userID = firebaseUser.getUid();
        String IsimSoyisim = getActivity().getIntent().getStringExtra("IsimSoyisim");
        String userEmail = firebaseUser.getEmail().toString();
        Map<String, String> profileMap = new HashMap<>();
        profileMap.put("IsimSoyisim", IsimSoyisim);
        profileMap.put("Email", userEmail);
        databaseReference.child("Profile").child("ProfileHesapBilgileri").child(userID).setValue(profileMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Krall", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Baslarken hata oluştu", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
