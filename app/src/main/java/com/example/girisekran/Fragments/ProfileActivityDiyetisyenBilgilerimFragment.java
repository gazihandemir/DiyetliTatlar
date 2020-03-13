package com.example.girisekran.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.girisekran.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileActivityDiyetisyenBilgilerimFragment extends Fragment {
    private FirebaseFirestore firebaseFirestore;
    TextView tvOlcüm, tvHastalikAdi, tvTahlil, tvHikaye;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    DocumentReference docRef;

    public ProfileActivityDiyetisyenBilgilerimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        View rootView = inflater.inflate(R.layout.fragment_profile_activity_diyetisyen_bilgilerim, container, false);
        tvOlcüm = rootView.findViewById(R.id.tvProfileActDiyetisyenBilgilerimÖlcüm);
        tvHastalikAdi = rootView.findViewById(R.id.tvProfileActDiyetisyenBilgilerimHastalikAdi);
        tvTahlil = rootView.findViewById(R.id.tvProfileActDiyetisyenBilgilerimTahlil);
        tvHikaye = rootView.findViewById(R.id.tvProfileActDiyetisyenBilgilerimHikaye);
        bilgileriCek();
        return rootView;
    }

    private void bilgileriCek() {
        docRef = firebaseFirestore.collection("ProfileDiyetisyenBilgileri").document(firebaseUser.getEmail().toString());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> hashMap = documentSnapshot.getData();
                System.out.println("gazi2" + documentSnapshot.getData());
                String userEmail = (String) hashMap.get("Email");
                String userOlcüm = (String) hashMap.get("Olcüm");
                String userHastalikAdi = (String) hashMap.get("Hastalik");
                String userTahlil = (String) hashMap.get("Tahlil");
                String userHikaye = (String) hashMap.get("Hikaye");
                /*System.out.println("gazi2" + userEmail);
                System.out.println("gazi2" + userOlcüm);*/
                tvOlcüm.setText(userOlcüm);
                tvHastalikAdi.setText(userHastalikAdi);
                tvTahlil.setText(userTahlil);
                tvHikaye.setText(userHikaye);

            }
        });

    }

}
