package com.example.girisekran.Fragments.Profile;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.girisekran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
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
    private static String ilkGiris;
    Uri selected;
    Context context;

    public ProfileActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ilkGiris = "1";
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        //  System.out.println("gazi -> onCreateView başladı");
        View rootView = inflater.inflate(R.layout.fragment_profile_activity, container, false);
        imgProfilResmi = rootView.findViewById(R.id.imgProfileFragmentActProfilResmi);
        tvProfilIsmi = rootView.findViewById(R.id.tvProfileFragmentActProfilIsmi);
        //  tvProfilMail = rootView.findViewById(R.id.tvProfileFragmentActProfilEmail);
        btnUpload = rootView.findViewById(R.id.btnProfileFragmentActUpload);
        edIsimSoyisim = rootView.findViewById(R.id.edProfileFragmentActİsimSoyisim);
        edTelefonNo = rootView.findViewById(R.id.edProfileFragmentActTelefonNumarası);
        edEmail = rootView.findViewById(R.id.edProfileFragmentActEmail);
        edDogumTarihi = rootView.findViewById(R.id.edProfileFragmentActDogumTarihi);
        btnUpload.setVisibility(View.INVISIBLE);
        // KayitOlanKullanicilarinBilgileriniHemenKaydet();
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
                // bilgileriCek();
                Toast.makeText(getActivity(), "kisa tıklandı", Toast.LENGTH_SHORT).show();


            }
        });
        imgProfilResmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
            }
        });
        btnUpload.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getActivity(), "Uuzun tıklandı", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
       //bilgileriCek();

        return rootView;

    }


    private void veriKaydet() {
        //  UUID uuıdImage = UUID.randomUUID();
        //final String imageName = "images/" + uuıdImage + ".jpg";
        String userID = firebaseUser.getUid();
        final String imageName = "images/" + userID + ".jpg";
        StorageReference newReference = storageReference.child(imageName);
        newReference.putFile(selected).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageReference profileImageRef = FirebaseStorage.getInstance().getReference(imageName);
                profileImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String downloadUrl = uri.toString();
                        String userID = firebaseUser.getUid();
                        stIisimSoyisim = edIsimSoyisim.getText().toString();
                        stTelefonNo = edTelefonNo.getText().toString();
                        stEmail = edEmail.getText().toString();
                        stDogumTarihi = edDogumTarihi.getText().toString();
                        Map<String, String> profileMap = new HashMap();
                        profileMap.put("IsimSoyisim", stIisimSoyisim);
                        profileMap.put("TelefonNo", stTelefonNo);
                        profileMap.put("Email", firebaseUser.getEmail().toString());
                        profileMap.put("DogumTarihi", stDogumTarihi);
                        profileMap.put("ProfilResmi", downloadUrl);
                        databaseReference.child("Profile").child(userID).child("ProfileHesapBilgileri").setValue(profileMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getActivity(), "fotoflı tamam", Toast.LENGTH_SHORT).show();
                                        btnUpload.setVisibility(View.INVISIBLE);

                                    }
                                });
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "fotoğraf fail ", Toast.LENGTH_SHORT).show();
            }
        });

    /*    stIisimSoyisim = edIsimSoyisim.getText().toString();
        stTelefonNo = edTelefonNo.getText().toString();
        stEmail = edEmail.getText().toString();
        stDogumTarihi = edDogumTarihi.getText().toString();

        String userID = firebaseUser.getUid();
        Map<String, String> profileMap = new HashMap();
        profileMap.put("IsimSoyisim", stIisimSoyisim);
        profileMap.put("TelefonNo", stTelefonNo);
        profileMap.put("Email", firebaseUser.getEmail().toString());
        profileMap.put("DogumTarihi", stDogumTarihi);
        databaseReference.child("Profile").child(userID).child("ProfileHesapBilgileri").setValue(profileMap)
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

*/
    }


    private void bilgileriCek() {
        final String userDefaultPhoto = "https://firebasestorage.googleapis.com/v0/b/diyetlitatlar.appspot.com/o/images%2FuserDefaultPhoto.jpg?alt=media&token=12c9d6d2-208b-4bbc-b9ba-47376f36ae8e";

        String userID = firebaseUser.getUid();
        // DatabaseReference newReference = firebaseDatabase.getReference("Profile");
        databaseReference.child("Profile").child(userID).child("ProfileHesapBilgileri").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                String userIsimSoyisim = hashMap.get("IsimSoyisim");
                String userEmail = hashMap.get("Email");
                String userTelefonNo = hashMap.get("TelefonNo");
                String userDogumTarihi = hashMap.get("DogumTarihi");
                String userProfileResmiUrl = hashMap.get("ProfilResmi");
                edEmail.setText(userEmail);
                edIsimSoyisim.setText(userIsimSoyisim);
                edTelefonNo.setText(userTelefonNo);
                edDogumTarihi.setText(userDogumTarihi);
                tvProfilIsmi.setText(userIsimSoyisim);
                // tvProfilMail.setText(userEmail);
                edEmail.setFocusable(false);
                System.out.println("gazi -> kaydedilen id " + userProfileResmiUrl);
                if (!userProfileResmiUrl.equals(userDefaultPhoto)) {
                    Picasso.get().load(userProfileResmiUrl).into(imgProfilResmi);
                } else {
                    Picasso.get().load(userDefaultPhoto).into(imgProfilResmi);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



/*        final String userID = firebaseUser.getUid();

        databaseReference.child("Profile").child(userID).child("ProfileHesapBilgileri").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              *//*  String getkey = dataSnapshot.getKey();
                String getchildren = dataSnapshot.getChildren().toString();
                String getvalue = dataSnapshot.getValue().toString();
                System.out.println("gazi -> getkey" + getkey);
                System.out.println("gazi -> getchildren" + getchildren);
                System.out.println("gazi -> getvalue" + getvalue);
                System.out.println("gazi -> value1 " + userEmail);*//*
                HashMap<String, String> hashMap = (HashMap<String, String>) dataSnapshot.getValue();
                String userIsimSoyisim = hashMap.get("IsimSoyisim");
                String userEmail = hashMap.get("Email");
                String userTelefonNo = hashMap.get("TelefonNo");
                String userDogumTarihi = hashMap.get("DogumTarihi");
                edEmail.setText(userEmail);
                edIsimSoyisim.setText(userIsimSoyisim);
                edTelefonNo.setText(userTelefonNo);
                edDogumTarihi.setText(userDogumTarihi);
                tvProfilIsmi.setText(userIsimSoyisim);
               // tvProfilMail.setText(userEmail);
                edEmail.setFocusable(false);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2 && resultCode == -1 && data != null) {
            selected = data.getData();
            try {

                btnUpload.setVisibility(View.VISIBLE);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selected);
                imgProfilResmi.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

}
