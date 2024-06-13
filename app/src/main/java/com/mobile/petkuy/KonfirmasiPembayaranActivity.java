package com.mobile.petkuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KonfirmasiPembayaranActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);

        // Inisialisasi Firebase Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://petkuy-89899-default-rtdb.asia-southeast1.firebasedatabase.app");
        databaseReference = firebaseDatabase.getReference();

        // Mendapatkan referensi TextView untuk menampilkan data
        TextView textViewNama = findViewById(R.id.nama);
        TextView textViewNoHp = findViewById(R.id.no_hp);
        TextView textViewKonsultasi = findViewById(R.id.konsultasi);
        TextView textViewDokter = findViewById(R.id.dokter);
        TextView textViewPrice = findViewById(R.id.bayar);

        Button KonfirmasiPembayaranButton = findViewById(R.id.konfirmasi_pembayaran_button);

        String appointmentId = "2";

        DatabaseReference appointmentsRef = firebaseDatabase.getReference().child("appointments").child(appointmentId);
        appointmentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String userId = String.valueOf(snapshot.child("user_id").getValue(Long.class));
                String doctorId = String.valueOf(snapshot.child("doctor_id").getValue(Long.class));

                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {



                        if (userId != null) {
                            DatabaseReference usersRef = firebaseDatabase.getReference().child("users").child(userId);

                            usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        String username = snapshot.child("username").getValue(String.class);
                                        String phoneNumber = snapshot.child("phone_number").getValue(String.class);
                                        textViewNama.setText("Nama           : " + username);
                                        textViewNoHp.setText("No. Hp         : " + phoneNumber);
                                    } else {
                                        Log.e("Firebase Error", "User data not found");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e("Firebase Error", "Error fetching user data", error.toException());
                                }
                            });
                        } else {
                            Log.e("Firebase Error", "User ID is null");
                        }
                        break;

                    }

                }
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        if (doctorId != null) {
                            DatabaseReference doctorsRef = firebaseDatabase.getReference().child("doctors").child(doctorId);
                            doctorsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        String doctorName = snapshot.child("name").getValue(String.class);
                                        String speciality = snapshot.child("specialities").getValue(String.class);
                                        String price = String.valueOf(snapshot.child("price").getValue(Long.class));
                                        textViewKonsultasi.setText("Konsultasi   : " + speciality);
                                        textViewDokter.setText("Dokter          : " + doctorName);
                                        textViewPrice.setText("Rp " + price);

                                    } else {
                                        Log.e("Firebase Error", "Doctor data not found");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e("Firebase Error", "Error fetching doctor data", error.toException());
                                }
                            });
                        } else {
                            Log.e("Firebase Error", "Doctor ID is null");
                        }
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase Error", "Error fetching appointments data", error.toException());
            }

        });

        final Context context = this;
        KonfirmasiPembayaranButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PembayaranBerhasil.class);
                startActivity(intent);
            }
        });

    }
}