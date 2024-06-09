package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KonfirmasiPembayaranActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_pembayaran);

        final Context context = this;

        Button konfirmasiButton = findViewById(R.id.konfirmasi_pembayaran_button);
        konfirmasiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PembayaranBerhasil.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
