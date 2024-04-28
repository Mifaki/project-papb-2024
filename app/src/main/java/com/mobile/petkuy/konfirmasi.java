package com.mobile.petkuy;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class konfirmasi extends AppCompatActivity {
    private TextView tvKonfirmasiAlasan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konfirmasilayout);

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
