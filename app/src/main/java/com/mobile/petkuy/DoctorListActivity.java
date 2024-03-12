package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DoctorListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btBack;
    private SearchView svSearchBar;
    private LinearLayout doctorCardContainer;
    private TextView noResultsText;
    private List<Doctor> doctorList;
    private List<Doctor> filteredDoctorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        btBack = findViewById(R.id.btBack);
        svSearchBar = findViewById(R.id.svSearchBar);
        doctorCardContainer = findViewById(R.id.llCardContainer);
        noResultsText = findViewById(R.id.tvNoResult);

        btBack.setOnClickListener(this);


        doctorList = new ArrayList<>();
        doctorList.add(new Doctor("drh. Ava Tar", R.drawable.doctor_3, "Kucing", "RSUB", "Jl. Soekarno Hatta, Lowokwaru, Kec. Lowokwaru, Kota Malang"));
        doctorList.add(new Doctor("drh. Hanni", R.drawable.doctor_4, "Kucing", "RS Depok", "JL. Margonda, Depok, Kec. Pancoran Mas, Kota Depok, Jawa Barat 16424"));
        doctorList.add(new Doctor("drh. M. Zuko", R.drawable.doctor_5, "Hewan Ternak", "RS Depok", "JL. Margonda, Depok, Kec. Pancoran Mas, Kota Depok, Jawa Barat 16424"));
        doctorList.add(new Doctor("drh. Sana", R.drawable.doctor_6, "Ayam", "RS Depok", "JL. Margonda, Depok, Kec. Pancoran Mas, Kota Depok, Jawa Barat 16424"));
        doctorList.add(new Doctor("drh. Gen", R.drawable.doctor_7, "Hewan Ternak", "RS Depok", "JL. Margonda, Depok, Kec. Pancoran Mas, Kota Depok, Jawa Barat 16424"));
        doctorList.add(new Doctor("drh. Ava Tar", R.drawable.doctor_3, "Kucing", "RSUB", "Jl. Soekarno Hatta, Lowokwaru, Kec. Lowokwaru, Kota Malang"));
        doctorList.add(new Doctor("drh. Hanni", R.drawable.doctor_4, "Kucing", "RS Depok", "JL. Margonda, Depok, Kec. Pancoran Mas, Kota Depok, Jawa Barat 16424"));
        doctorList.add(new Doctor("drh. M. Zuko", R.drawable.doctor_5, "Hewan Ternak", "RS Depok", "JL. Margonda, Depok, Kec. Pancoran Mas, Kota Depok, Jawa Barat 16424"));

        filteredDoctorList = new ArrayList<>(doctorList);
        populateDoctorCards(filteredDoctorList);

        svSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredDoctorList.clear();
                if (TextUtils.isEmpty(newText)) {
                    filteredDoctorList.addAll(doctorList);
                } else {
                    for (Doctor doctor : doctorList) {
                        if (doctor.getName().toLowerCase().contains(newText.toLowerCase())) {
                            filteredDoctorList.add(doctor);
                        }
                    }
                }

                populateDoctorCards(filteredDoctorList);

                return true;
            }
        });
    }

    private void populateDoctorCards(List<Doctor> doctorList) {
        doctorCardContainer.removeAllViews();

        if (doctorList.isEmpty()) {
            noResultsText.setVisibility(TextView.VISIBLE);
        } else {
            noResultsText.setVisibility(TextView.GONE);

            for (Doctor doctor : doctorList) {
                CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.doctor_card, doctorCardContainer, false);

                TextView doctorNameTextView = cardView.findViewById(R.id.tvDoctorName);
                doctorNameTextView.setText(doctor.getName());

                ImageView doctorImageView = cardView.findViewById(R.id.ivDoctorPicture);
                doctorImageView.setImageResource(doctor.getProfilePicture());

                TextView petCategoryTextView = cardView.findViewById(R.id.tvPetCategory);
                petCategoryTextView.setText(doctor.getPetCategory());

                TextView hospitalTextView = cardView.findViewById(R.id.tvHospital);
                hospitalTextView.setText(doctor.getHospital());

                TextView addressTextView = cardView.findViewById(R.id.tvAddress);
                addressTextView.setText(doctor.getAdresss());

                doctorCardContainer.addView(cardView);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btBack) {
            finish();
            return;
        }
    }
}