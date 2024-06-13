package com.mobile.petkuy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.mobile.petkuy.model.Doctor;
import com.mobile.petkuy.model.appointmentModel;
import com.mobile.petkuy.model.hospitalModel;

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
    private ImageView doctorImageView;
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
        AA.setOnItemClickListener(new appAdapter.SelectListener() {
            @Override
            public void onItemClicked(int position, View v) {
                // tidak ada yang terjadi
            }

            @Override
            public void onBayarClicked(int position) {
                int appointmentId = data.get(position).getId();
                String doktor = doctorsData.get(position).getName();
                String speciality = doctorsData.get(position).getSpecialities();

                Intent intent = new Intent(listsAppointment.this, PembayaranActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("APPOINTMENT_ID", appointmentId);
                bundle.putString("DOCTOR_NAME", doktor);
                bundle.putString("DOCTOR_SPECIALITY", speciality);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onBatalClicked(int position) {
                // Handle batal button click
                data.remove(position);
                AA.notifyItemRemoved(position);
                AA.notifyItemRangeChanged(position, data.size());
            }

            @Override
            public void onUbahClicked(int position) {
                String doktor = doctorsData.get(position).getName();
                String spesialis = doctorsData.get(position).getSpecialities();
                String lokasi = hospitalsData.get(position).getAddress();
                String janji = data.get(position).getAppointment_date();
                String imageResourceIdStr = doctorsData.get(position).getPicture();

                Intent intent = new Intent(listsAppointment.this, doctorsAppointment.class);
                Bundle bundle = new Bundle();
                bundle.putString("DOCTOR", doktor);
                bundle.putString("SPESIALIS", spesialis);
                bundle.putString("LOKASI", lokasi);
                bundle.putString("JANJI", janji);
                bundle.putString("IV_DOKTOR", imageResourceIdStr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
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