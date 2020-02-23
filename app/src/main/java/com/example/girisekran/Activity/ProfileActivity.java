package com.example.girisekran.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.girisekran.Fragments.ProfileActivityBasariHikayemFragment;
import com.example.girisekran.Fragments.ProfileActivityDiyetisyenBilgilerimFragment;
import com.example.girisekran.Fragments.ProfileActivityFragment;
import com.example.girisekran.Fragments.ProfileActivityKafamKarisiyorFragment;
import com.example.girisekran.Fragments.ProfileActivityKisiselBilgilerimFragment;
import com.example.girisekran.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    Uri selected;
    ImageView imgProfilResmi;
    TextView tvProfilIsmi, tvProfilMail;
    Button btnUpload;
    EditText edIsimSoyisim, edTelefonNo, edEmail, edDogumTarihi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        NavigationView navigationView = findViewById(R.id.profileActivityNavView);
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.profileActivityDraverLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner, new ProfileActivityFragment()).commit();
            navigationView.setCheckedItem(R.id.profileActivityNavHesapBilgilerim);
        }
        bilgileriAl();
        //  veriKaydet();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.profileActivityNavHesapBilgilerim:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                        new ProfileActivityFragment()).commit();
                break;
            case R.id.profileActivityNavKisiselBilgilerim:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                        new ProfileActivityKisiselBilgilerimFragment()).commit();
                break;
            case R.id.profileActivityNavDiyetisyenBilgilerim:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                        new ProfileActivityDiyetisyenBilgilerimFragment()).commit();
                break;
            case R.id.profileActivityNavBasariHikayem:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner,
                        new ProfileActivityBasariHikayemFragment()).commit();
                break;
            case R.id.profileActivityNavKafamKaristi:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_conteiner, new ProfileActivityKafamKarisiyorFragment()).commit();
                break;
            case R.id.profileActivityNavSikcaSorulanSorular:
                Toast.makeText(this, "S.S.S", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profileActivityNavIletisim:
                Toast.makeText(this, "Gazihan DEMİR -> Yazılımcı iletişim : gazihand@gmail.com \n " +
                        "Gülten manav -> diyetisyen iletişim : gültenmnv@gmail.com", Toast.LENGTH_LONG).show();
                break;
            case R.id.profileActivityNavCikis:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public void bilgileriAl() {


        String tvProfilİsmi = getIntent().getStringExtra("tvProfilİsmi");
        String tvProfilMail = getIntent().getStringExtra("tvProfilMail");
        String edIisimSoyisim = getIntent().getStringExtra("edIisimSoyisim");
        String edTelefonNo = getIntent().getStringExtra("edTelefonNo");
        String edEmail = getIntent().getStringExtra("edEmail");
        String edDogumTarihi = getIntent().getStringExtra("edDogumTarihi");
        System.out.println("gazi -> " + tvProfilİsmi);
        System.out.println("gazi -> " + tvProfilMail);
        System.out.println("gazi -> " + edIisimSoyisim);
        System.out.println("gazi -> " + edTelefonNo);
        System.out.println("gazi -> " + edEmail);
        System.out.println("gazi -> " + edDogumTarihi);


    }


    public void veriKaydet() {
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        Map profileMap = new HashMap();
        profileMap.put("İsimSoyisim", edIsimSoyisim);
        profileMap.put("edTelefonNo", edTelefonNo);
        profileMap.put("edEmail", edEmail);
        profileMap.put("edDogumTarihi", edDogumTarihi);
        databaseReference.child("Profile").child(userID).child("İsimSoyisim").setValue(profileMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "Veri Kaydedildi", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Basarisiz", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("gazi -> " + e.getMessage());
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }


}
