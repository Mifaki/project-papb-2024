package com.mobile.petkuy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class doctorsAppointment extends AppCompatActivity implements editAppointmentFragment.OnAppointmentItemSelectedListener {
    protected static String state = null;
    private TextView tvDokter, tvSpesialis, tvLokasi;
    private ImageView ivDoktor;
    private FloatingActionButton btBack;
    private Button btSend;
    private String selectedDate = "";
    private String selectedTime = "";
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorsdetail);

        tvDokter = findViewById(R.id.tvDoc);
        tvSpesialis = findViewById(R.id.tvSpesialis);
        tvLokasi = findViewById(R.id.tvLokasi);
        ivDoktor = findViewById(R.id.ivDoktor);
        btBack = findViewById(R.id.btBack);
        btSend = findViewById(R.id.btSend);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        mImageLoader = new ImageLoader(requestQueue);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorsAppointment.this, listsAppointment.class);
                state = "1";
                startActivity(intent);
            }
        });

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String formattedDate = selectedDate.replace("\n", "");
                state = "1";
                sendDataToAppointment(formattedDate, selectedTime);
            }
        });

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            editAppointmentFragment fragment = new editAppointmentFragment();
            fragment.setOnAppointmentItemSelectedListener(this);
            fragmentTransaction.replace(R.id.flTanggal, fragment);
            fragmentTransaction.commit();
        }
    }

    private void sendDataToAppointment(String formattedDate, String selectedTime) {
        Intent intent = new Intent(doctorsAppointment.this, listsAppointment.class);
        intent.putExtra("selectedDate", formattedDate);
        intent.putExtra("selectedTime", selectedTime);

        int doctorImageResourceId = R.drawable.doctor_4;
        intent.putExtra("IV_DOKTOR", doctorImageResourceId);

        startActivity(intent);
    }

    @Override
    public void onAppointmentItemSelected(String selectedDate, String selectedTime) {
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;
    }

    @Override
    public void onAppointmentTimeSelected(String selectedTime) {
        this.selectedTime = selectedTime;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        if (intent != null) {
            String doktor = intent.getStringExtra("DOCTOR");
            String spesialis = intent.getStringExtra("SPESIALIS");
            String lokasi = intent.getStringExtra("LOKASI");
            String ivDoktorUrl = intent.getStringExtra("IV_DOKTOR_URL");

            tvDokter.setText(doktor);
            tvSpesialis.setText(spesialis);
            tvLokasi.setText(lokasi);

            if (ivDoktorUrl != null) {
                Glide.with(this)
                        .load(ivDoktorUrl)
                        .into(ivDoktor);
            }

            String type = intent.getStringExtra("TYPE");
            if (Objects.equals(type, "EDIT")) {
                btSend.setText("Ubah Jadwal");
            }
        }
    }
}
