package com.example.girisekran.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.girisekran.Fragments.DenemeFragment1;
import com.example.girisekran.Hesaplamalar.AGEActivity;
import com.example.girisekran.Hesaplamalar.BKİActivity;
import com.example.girisekran.Hesaplamalar.BMHActivity;
import com.example.girisekran.Hesaplamalar.GunlukSuMiktariHesaplama;
import com.example.girisekran.Hesaplamalar.IdealKiloHesaplama;
import com.example.girisekran.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AkisActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akis);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = firebaseDatabase.getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        //  ilkGiris();

        // ilkGirisiCek();

    }

    public void deneme1(View view) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DenemeFragment1 firstFragment = new DenemeFragment1();
        fragmentTransaction.replace(R.id.linearLayout2, firstFragment).commit();
    }

    public void deneme2(View view) {
        Intent intent = new Intent(getApplicationContext(), CalculationsActivity.class);
        startActivity(intent);
      /*  FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DenemeFragment2 firstFragment = new DenemeFragment2();
        fragmentTransaction.replace(R.id.linearLayout2, firstFragment).commit();*/
    }

    public void deneme5(View view) {
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        String IsimSoyisim = getIntent().getStringExtra("IsimSoyisim");
        intent.putExtra("IsimSoyisim", IsimSoyisim);
        startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.bki_menu) {
            Intent intent = new Intent(getApplicationContext(), BKİActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.bmh_menu) {
            Intent intent = new Intent(getApplicationContext(), BMHActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.age_menu) {
            Intent intent = new Intent(getApplicationContext(), AGEActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.ideal_menu) {
            Intent intent = new Intent(getApplicationContext(), IdealKiloHesaplama.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.su_menu) {
            Intent intent = new Intent(getApplicationContext(), GunlukSuMiktariHesaplama.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
