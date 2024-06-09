package com.mobile.petkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        // Inisialisasi TextViews
        tvDokter = findViewById(R.id.tvDoc);
        tvSpesialis = findViewById(R.id.tvSpesialis);
        tvLokasi = findViewById(R.id.tvLokasi);
        ivDoktor = findViewById(R.id.ivDoktor);
        btBack = findViewById(R.id.btBack);
        btSend = findViewById(R.id.btSend);

        // Set up Volley request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        mImageLoader = new ImageLoader(requestQueue);

        // Load image from URL
        String imageUrl = "https://news.unair.ac.id/wp-content/uploads/2022/01/dokter-hewan.jpg";
        mImageLoader.loadImage(imageUrl, ivDoktor);

        // Check state to decide whether to show editAppointmentFragment
        if (state != null) {
            editAppointmentFragment editAppointmentFragment = new editAppointmentFragment();
            editAppointmentFragment.setOnAppointmentItemSelectedListener(this); // Set this activity as the listener
            getSupportFragmentManager().beginTransaction().add(R.id.flTanggal, editAppointmentFragment).commit();
        }else {
            btSend.setText("Buat Jadwal");
        }

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
                // Remove '\n'
                String formattedDate = selectedDate.replace("\n", "");
                state = "1";
                // Sending data
                sendDataToAppointment(formattedDate, selectedTime);
            }
        });
    }

    // Send data to appointment
    private void sendDataToAppointment(String formattedDate, String selectedTime) {
        Intent intent = new Intent(doctorsAppointment.this, listsAppointment.class);
        intent.putExtra("selectedDate", formattedDate);
        intent.putExtra("selectedTime", selectedTime);
        startActivity(intent);
    }

    @Override
    public void onAppointmentItemSelected(String selectedDate, String selectedTime) {
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Mendapatkan data yang dikirim dari activity sebelumnya melalui intent
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // Mendapatkan nama dokter, spesialis, lokasi, dan janji
                String doktor = bundle.getString("DOCTOR", "");
                String spesialis = bundle.getString("SPESIALIS", "");
                String lokasi = bundle.getString("LOKASI", "");
                int ivDoktorResource = bundle.getInt("IV_DOKTOR");

                // Menetapkan nilai-nilai tersebut ke TextViews
                tvDokter.setText(doktor);
                tvSpesialis.setText(spesialis);
                tvLokasi.setText(lokasi);
                // Set image resource to ImageView
                ivDoktor.setImageResource(ivDoktorResource);
            }
        }
    }
}