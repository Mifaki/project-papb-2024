package com.mobile.petkuy;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class konfirmasi extends AppCompatActivity {
    private TextView tvKonfirmasiAlasan;
    private Button btLihatDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konfirmasilayout);
        btLihatDaftar = findViewById(R.id.btLihatDaftar);

        btLihatDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(konfirmasi.this, MainActivity.class);

                startActivity(intent);
            }
        });

        tvKonfirmasiAlasan = findViewById(R.id.tvKonfirmasiAlasan);

        // Menerima data dari class BatalJanji
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String alasan = extras.getString("alasan");
            // Menampilkan konfirmasi pembatalan dengan alasan yang diterima
            tvKonfirmasiAlasan.setText("Janji Temu Telah Dibatalkan\nAlasan: " + alasan);
        }
    }
}
