package com.mobile.petkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class doctorsAppointment extends AppCompatActivity {

    private TextView tvTgl1, tvTgl2, tvTgl3, tvTgl4;
    private TextView tvJadwal1, tvJadwal2, tvDokter, tvSpesialis, tvJanji, tvLokasi;
    private ImageView ivDoktor;
    private String selectedDate = "";
    private String selectedTime = "";
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        // Inisialisasi TextViews
        tvTgl1 = findViewById(R.id.tvTgl1);
        tvTgl2 = findViewById(R.id.tvTgl2);
        tvTgl3 = findViewById(R.id.tvTgl3);
        tvTgl4 = findViewById(R.id.tvTgl4);
        tvDokter = findViewById(R.id.tvDoc);
        tvSpesialis = findViewById(R.id.tvSpesialis);
        tvLokasi = findViewById(R.id.tvLokasi);
        tvJadwal1 = findViewById(R.id.tvJadwal);
        tvJadwal2 = findViewById(R.id.tvJadwal2);
        ivDoktor = findViewById(R.id.ivDoktor);

        // Set up Volley request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        mImageLoader = new ImageLoader(requestQueue);

        // Load image from URL
        String imageUrl = "https://news.unair.ac.id/wp-content/uploads/2022/01/dokter-hewan.jpg";
        mImageLoader.loadImage(imageUrl, ivDoktor);

        // Set listener klik untuk TextView Tgl
        tvTgl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = tvTgl1.getText().toString();
                setShadowBackground(tvTgl1);
                clearShadowBackground(tvTgl2, tvTgl3, tvTgl4);
            }
        });

        tvTgl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = tvTgl2.getText().toString();
                setShadowBackground(tvTgl2);
                clearShadowBackground(tvTgl1, tvTgl3, tvTgl4);
            }
        });

        tvTgl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = tvTgl3.getText().toString();
                setShadowBackground(tvTgl3);
                clearShadowBackground(tvTgl1, tvTgl2, tvTgl4);
            }
        });

        tvTgl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDate = tvTgl4.getText().toString();
                setShadowBackground(tvTgl4);
                clearShadowBackground(tvTgl1, tvTgl2, tvTgl3);
            }
        });

// listener klik
        tvJadwal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTime = tvJadwal1.getText().toString();
                setShadowBackground(tvJadwal1);
                clearShadowBackground(tvJadwal2);
            }
        });

        tvJadwal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTime = tvJadwal2.getText().toString();
                setShadowBackground(tvJadwal2);
                clearShadowBackground(tvJadwal1);
            }
        });

        findViewById(R.id.btSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove '\n'
                String formattedDate = selectedDate.replace("\n", "");

                // Sending data
                sendDataToAppointment(formattedDate, selectedTime);
            }
        });

    }



// send data to appointment
private void sendDataToAppointment(String formattedDate, String selectedTime) {
    Intent intent = new Intent(doctorsAppointment.this, listsAppointment.class);
    intent.putExtra("selectedDate", formattedDate);
    intent.putExtra("selectedTime", selectedTime);
    startActivity(intent);
}


    //set shadow background for TextView
    private void setShadowBackground(TextView textView) {
        // Set background for the provided textView
        textView.setBackgroundResource(R.drawable.selected_background);
    }

    //clear shadow background for all TextViews except the provided one
    private void clearShadowBackground(TextView... textViews) {
        for (TextView textView : textViews) {
            // Check if the textView is not the provided one, then clear its background
            if (textView != null) {
                textView.setBackgroundResource(R.drawable.default_background);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Mendapatkan data yang dikirim dari activity sebelumnya melalui intent
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {

                // Menetapkan gambar dokter ke ImageView


                // Mendapatkan nama dokter, spesialis, lokasi, dan janji
                String doktor = bundle.getString("DOCTOR", "");
                String spesialis = bundle.getString("SPESIALIS", "");
                String lokasi = bundle.getString("LOKASI", "");
                String janji = bundle.getString("JANJI", "");
                int ivDoktorResource = bundle.getInt("IV_DOKTOR");

                // Menetapkan nilai-nilai tersebut ke TextViews
                tvDokter.setText(doktor);
                tvSpesialis.setText(spesialis);
                tvLokasi.setText(lokasi);
                // Set image resource to ImageView
                ivDoktor.setImageResource(ivDoktorResource);
    //                tvJanji.setText(janji);
            }
        }
    }

}
