package com.mobile.petkuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mobile.petkuy.model.AppointmentDetails;
import com.mobile.petkuy.model.AppointmentHistory;
import com.mobile.petkuy.model.DoctorDetails;
import com.mobile.petkuy.model.HospitalDetails;
import com.mobile.petkuy.utils.SpacingItemDecoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btWelcome;
    private RecyclerView rvRiwayatJanji;
    private AppointmentHistoryAdapter riwayatJanjiAdapter;
    private List<AppointmentHistory> riwayatJanjiList;
    private FirebaseDatabase database;
    private DatabaseReference appointmentHistoryRef;
    private DatabaseReference appointmentsRef;
    private DatabaseReference doctorsRef;
    private DatabaseReference hospitalsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btWelcome = findViewById(R.id.btWelcomeCard);
        btWelcome.setOnClickListener(this);

        TextView textView = findViewById(R.id.tvName);
        String fullText = "Selamat datang, momo";

        SpannableString spannableString = new SpannableString(fullText);
        int momoColor = getResources().getColor(R.color.primary_700);
        int momoStartIndex = fullText.indexOf("momo");
        int momoEndIndex = momoStartIndex + "momo".length();
        spannableString.setSpan(new ForegroundColorSpan(momoColor), momoStartIndex, momoEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

        riwayatJanjiList = new ArrayList<>();
        riwayatJanjiAdapter = new AppointmentHistoryAdapter();

        rvRiwayatJanji = findViewById(R.id.rvRiwayatJanji);
        rvRiwayatJanji.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecoder itemDecorator = new SpacingItemDecoder(16);
        rvRiwayatJanji.addItemDecoration(itemDecorator);
        rvRiwayatJanji.setAdapter(riwayatJanjiAdapter);

        database = FirebaseDatabase.getInstance("https://petkuy-89899-default-rtdb.asia-southeast1.firebasedatabase.app/");
        appointmentHistoryRef = database.getReference("appointment_history");
        appointmentsRef = database.getReference("appointments");
        doctorsRef = database.getReference("doctors");
        hospitalsRef = database.getReference("hospitals");

        fetchAndLogDetailedAppointmentHistories();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btWelcomeCard) {
            Intent intent = new Intent(HomeActivity.this, DoctorListActivity.class);
            startActivity(intent);
        }
    }

    private void fetchAndLogDetailedAppointmentHistories() {
        appointmentHistoryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AppointmentHistory appointmentHistory = snapshot.getValue(AppointmentHistory.class);
                    if (appointmentHistory != null) {
                        fetchAppointmentDetails(appointmentHistory);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseData", "Error fetching data", databaseError.toException());
            }
        });
    }

    private void fetchAppointmentDetails(AppointmentHistory appointmentHistory) {
        int appointmentId = appointmentHistory.getAppointment_id();
        appointmentsRef.child(String.valueOf(appointmentId)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    AppointmentDetails details = snapshot.getValue(AppointmentDetails.class);
                    if (details != null) {
                        fetchDoctorDetails(appointmentHistory, details);
                    } else {
                        Log.e("FirebaseData", "Appointment details are null for appointment ID: " + appointmentId);
                    }
                } else {
                    Log.e("FirebaseData", "Snapshot does not exist for appointment ID: " + appointmentId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseData", "Error fetching appointment details", databaseError.toException());
            }
        });
    }

    private void fetchDoctorDetails(AppointmentHistory appointmentHistory, AppointmentDetails appointmentDetails) {
        int doctorId = appointmentDetails.getDoctors_id();
        doctorsRef.child(String.valueOf(doctorId)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    DoctorDetails doctorDetails = snapshot.getValue(DoctorDetails.class);
                    if (doctorDetails != null) {
                        fetchHospitalDetails(appointmentHistory, appointmentDetails, doctorDetails);
                    } else {
                        Log.e("FirebaseData", "Doctor details are null for doctor ID: " + doctorId);
                    }
                } else {
                    Log.e("FirebaseData", "Snapshot does not exist for doctor ID: " + doctorId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseData", "Error fetching doctor details", databaseError.toException());
            }
        });
    }

    private void fetchHospitalDetails(AppointmentHistory appointmentHistory, AppointmentDetails appointmentDetails, DoctorDetails doctorDetails) {
        int hospitalId = doctorDetails.getHospital_id();
        hospitalsRef.child(String.valueOf(hospitalId)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    HospitalDetails hospitalDetails = snapshot.getValue(HospitalDetails.class);
                    if (hospitalDetails != null) {
                        insertAppointmentHistory(appointmentHistory, appointmentDetails, doctorDetails, hospitalDetails);
                    } else {
                        Log.e("FirebaseData", "Hospital details are null for hospital ID: " + hospitalId);
                    }
                } else {
                    Log.e("FirebaseData", "Snapshot does not exist for hospital ID: " + hospitalId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseData", "Error fetching hospital details", databaseError.toException());
            }
        });
    }

    private void insertAppointmentHistory(AppointmentHistory appointmentHistory, AppointmentDetails appointmentDetails, DoctorDetails doctorDetails, HospitalDetails hospitalDetails) {
        appointmentHistory.setAppointmentDetails(appointmentDetails);
        appointmentHistory.setDoctorDetails(doctorDetails);
        appointmentHistory.setHospitalDetails(hospitalDetails);

        if (appointmentHistory.getAppointmentDetails().isPayment_status()) {
            riwayatJanjiList.add(appointmentHistory);
            riwayatJanjiAdapter.setData(riwayatJanjiList);
        }
    }
}
