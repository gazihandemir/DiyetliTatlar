package com.example.girisekran.Fragments.Profile;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.girisekran.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileActivityBasariHikayemFragment extends Fragment {
    BarChart barChart;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    DocumentReference docRef;
    private FirebaseFirestore firebaseFirestore;
    String birinciHafta = "0", ikinciHafta = "0", ücüncüHafta = "0", dördüncüHafta = "0";
    TextView tvBasariHikayem;


    /*
        ProgressBar progressBar1;
        ProgressBar progressBar2;
        ProgressBar progressBar3;
        ProgressBar progressBar4;
        ProgressBar progressBar5;
        static String progressBar1Deger = "30";
        static String progressBar2Deger = "40";
        static String progressBar3Deger = "10";
        static String progressBar4Deger = "65";
        static String progressBar5Deger = "100";
        TextView tv1, tv2, tv3, tv4, tv5;
    */


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("gaziilkönce : onCreate");


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("gaziilkönce : onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_profile_activity_basari_hikayem, container, false);

       /* progressBar1 = rootView.findViewById(R.id.progresbar1);
        progressBar2 = rootView.findViewById(R.id.progresbar2);
        progressBar3 = rootView.findViewById(R.id.progresbar3);
        progressBar4 = rootView.findViewById(R.id.progresbar4);
        progressBar5 = rootView.findViewById(R.id.progresbar5);

        tv1 = rootView.findViewById(R.id.tvProgresbar1);
        tv2 = rootView.findViewById(R.id.tvProgresbar2);
        tv3 = rootView.findViewById(R.id.tvProgresbar3);
        tv4 = rootView.findViewById(R.id.tvProgresbar4);
        tv5 = rootView.findViewById(R.id.tvProgresbar5);


        int degerInt1 = Integer.parseInt(progressBar1Deger);
        int degerInt2 = Integer.parseInt(progressBar2Deger);
        int degerInt3 = Integer.parseInt(progressBar3Deger);
        int degerInt4 = Integer.parseInt(progressBar4Deger);
        int degerInt5 = Integer.parseInt(progressBar5Deger);
        progressBar1.setProgress(10);
        progressBar2.setProgress(degerInt2);
        progressBar3.setProgress(degerInt3);
        progressBar4.setProgress(degerInt4);
        progressBar5.setProgress(degerInt5);
        tv1.setText(progressBar1Deger + "kg");
        tv2.setText(progressBar2Deger + "kg");
        tv3.setText(progressBar3Deger + "kg");
        tv4.setText(progressBar4Deger + "kg");
        tv5.setText(progressBar5Deger + "kg");*/
        barChart = rootView.findViewById(R.id.barChart);
        tvBasariHikayem = rootView.findViewById(R.id.tvProfileActivityBasariHikayem);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        bilgileriCek();
        return rootView;
    }

    private ArrayList<BarEntry> dataValues1() {
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        int birinciHaftaInt = Integer.parseInt(birinciHafta);
        int ikinciHaftaInt = Integer.parseInt(ikinciHafta);
        int ücüncüHaftaInt = Integer.parseInt(ücüncüHafta);
        int dördüncüHaftaInt = Integer.parseInt(dördüncüHafta);
        //   System.out.println("gazidatavalues icin : " + birinciHafta + ikinciHafta + ücüncüHafta + dördüncüHafta);
        // System.out.println("gazidatavalues icin int : " + birinciHaftaInt + ikinciHaftaInt + ücüncüHaftaInt + dördüncüHaftaInt);
        dataVals.add(new BarEntry(0, birinciHaftaInt));
        dataVals.add(new BarEntry(7, ikinciHaftaInt));
        dataVals.add(new BarEntry(14, ücüncüHaftaInt));
        dataVals.add(new BarEntry(21, dördüncüHaftaInt));
        return dataVals;

    }


    private void bilgileriCek() {
        docRef = firebaseFirestore.collection("ProfileBasariHikayesi").document(firebaseUser.getEmail().toString());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> hashMap = documentSnapshot.getData();
                System.out.println("gazi2" + documentSnapshot.getData());
                birinciHafta = (String) hashMap.get("haft1a");
                ikinciHafta = (String) hashMap.get("haft2a");
                ücüncüHafta = (String) hashMap.get("haft3a");
                dördüncüHafta = (String) hashMap.get("haft4a");
                System.out.println("gazi Api Çıktı");

                System.out.println("gazilistener icin : " + birinciHafta + ikinciHafta + ücüncüHafta + dördüncüHafta);
                String basariHilkayesi = (String) hashMap.get("basariHikayesi");
                tvBasariHikayem.setText(basariHilkayesi);
                grafikOlustur();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "Veriler Cekilemedi ! ", Toast.LENGTH_SHORT).show();

            }

        });

    }

    public void grafikOlustur() {
        BarDataSet barDataSet1 = new BarDataSet(dataValues1(), "Kilo");
        barDataSet1.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);
        barChart.setData(barData);
        barChart.invalidate();
        barData.setValueTextSize(10);
    }

}
