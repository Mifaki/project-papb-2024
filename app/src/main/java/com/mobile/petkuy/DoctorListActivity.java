package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobile.petkuy.model.DoctorDetails;
import com.mobile.petkuy.model.HospitalDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btBack;
    private SearchView svSearchBar;
    private RecyclerView recyclerView;
    private TextView noResultsText;
    private List<DoctorDetails> doctorList, filteredDoctorList;
    private FirebaseDatabase database;
    private MutableLiveData<String> searchQuery = new MutableLiveData<>();
    private DatabaseReference doctorsRef;
    private DatabaseReference hospitalsRef;
    private DoctorListAdapter doctorListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        btBack = findViewById(R.id.btBack);
        svSearchBar = findViewById(R.id.svSearchBar);
        noResultsText = findViewById(R.id.tvNoResult);
        recyclerView = findViewById(R.id.rvDoctorList);

        btBack.setOnClickListener(this);

        filteredDoctorList = new ArrayList<>();
        doctorList = new ArrayList<>();
        doctorListAdapter = new DoctorListAdapter(filteredDoctorList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(doctorListAdapter);


        database = FirebaseDatabase.getInstance("https://petkuy-89899-default-rtdb.asia-southeast1.firebasedatabase.app/");
        doctorsRef = database.getReference("doctors");
        hospitalsRef = database.getReference("hospitals");

        // Setting up SearchView listener
        svSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery.setValue(newText);
                return true;
            }
        });

        // Observing changes in search query
        searchQuery.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String query) {
                searchDoctor(query);
            }
        });

        // Fetch data from Firebase
        fetchDataFromFirebase();
    }

    // Method to fetch data from Firebase
    private void fetchDataFromFirebase() {
        // Fetch hospitals first
        hospitalsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<HospitalDetails> hospitalList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HospitalDetails hospital = snapshot.getValue(HospitalDetails.class);
                    if (hospital != null) {
                        hospitalList.add(hospital);
                    }
                }

                // Now, fetch doctors and associate them with their hospital details
                doctorsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            DoctorDetails doctor = snapshot.getValue(DoctorDetails.class);
                            if (doctor != null) {
                                // Find the hospital details for this doctor
                                for (HospitalDetails hospital : hospitalList) {
                                    if (hospital.getId() == doctor.getHospital_id()) {
                                        doctor.setHospitalDetails(hospital);
                                        doctorList.add(doctor);
                                        break;
                                    }
                                }
                            }
                        }
                        searchDoctor(searchQuery.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("Firebase", "Failed to fetch doctors: " + databaseError.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Failed to fetch hospitals: " + databaseError.getMessage());
            }
        });
    }

    private void searchDoctor(String query) {
        filteredDoctorList.clear();

        if (doctorList != null) {
            if (TextUtils.isEmpty(query)) {
                filteredDoctorList.addAll(doctorList);
            } else {
                for (DoctorDetails doctor : doctorList) {
                    if (doctor.getName().toLowerCase().contains(query.toLowerCase())) {
                        filteredDoctorList.add(doctor);
                    }
                }
            }
        }
        doctorListAdapter.notifyDataSetChanged();
        checkEmptyList();
    }

    // Method to check if the filtered list is empty
    private void checkEmptyList() {
        if (filteredDoctorList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            noResultsText.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noResultsText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btBack) {
            finish();
        }
    }
}