package com.mobile.petkuy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobile.petkuy.models.Doctor;
import com.mobile.petkuy.models.appointmentModel;
import com.mobile.petkuy.models.hospitalModel;

import java.util.ArrayList;
import java.util.List;

public class listsAppointment extends AppCompatActivity {

    private List<appointmentModel> data;
    private List<Doctor> doctorsData;
    private List<hospitalModel> hospitalsData;
    private RecyclerView rvAppointment;

    private TextView tvSelectedDate;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private appAdapter AA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listappointment);

        data = new ArrayList<>();
        doctorsData = new ArrayList<>();
        hospitalsData = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance("https://petkuy-89899-default-rtdb.asia-southeast1.firebasedatabase.app");

        rvAppointment = findViewById(R.id.rvAppointment);
        AA = new appAdapter(this, data, doctorsData, hospitalsData);
        rvAppointment.setAdapter(AA);
        rvAppointment.setLayoutManager(new LinearLayoutManager(this));

        loadDoctorsData();
        loadHospitalsData();
        loadData();
        AA.setOnItemClickListener((position, v) -> {
            data.remove(position);
            AA.notifyDataSetChanged();
        });

        View appointmentCardView = getLayoutInflater().inflate(R.layout.appointmentcard, null);
        tvSelectedDate = appointmentCardView.findViewById(R.id.tvJanji);
    }

    private void loadData() {
        DatabaseReference appointmentsRef = firebaseDatabase.getReference("appointments");
        appointmentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    appointmentModel appointment = snapshot1.getValue(appointmentModel.class);
                    data.add(appointment);
                    Log.d("Appointment", appointment.toString());
                }
                AA.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadData:onCancelled", error.toException());
            }
        });
    }

    private void loadDoctorsData() {
        DatabaseReference doctorsRef = firebaseDatabase.getReference("doctors");
        doctorsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                doctorsData.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Doctor doctor = snapshot1.getValue(Doctor.class);
                    doctorsData.add(doctor);
                    Log.d("Doctor", doctor.toString());
                }
                AA.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadDoctorsData:onCancelled", error.toException());
            }
        });
    }

    private void loadHospitalsData() {
        DatabaseReference hospitalsRef = firebaseDatabase.getReference("hospitals");
        hospitalsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hospitalsData.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    hospitalModel hospital = snapshot1.getValue(hospitalModel.class);
                    hospitalsData.add(hospital);
                    Log.d("Hospital", hospital.toString());
                }
                AA.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("loadHospitalsData:onCancelled", error.toException());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String selectedDate = extras.getString("selectedDate");
            String selectedTime = extras.getString("selectedTime");
            tvSelectedDate.setText(String.format("%s %s", selectedDate, selectedTime));
        }
    }
}
