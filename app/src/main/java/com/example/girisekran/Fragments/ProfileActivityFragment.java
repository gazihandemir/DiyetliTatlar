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


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileActivityFragment extends Fragment {
    ImageView imgProfilResmi;
    TextView tvProfilIsmi, tvProfilMail;
    Button btnUpload;
    EditText edIsimSoyisim, edTelefonNo, edEmail, edDogumTarihi;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;

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

        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        Map<String,String> profileMap = new HashMap();
        profileMap.put("İsimSoyisim", stIisimSoyisim);
        profileMap.put("TelefonNo", stTelefonNo);
        profileMap.put("Email", user.getEmail().toString());
        profileMap.put("DogumTarihi", stDogumTarihi);
        databaseReference.child("Profile1").child(userID).setValue(profileMap)
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

}
