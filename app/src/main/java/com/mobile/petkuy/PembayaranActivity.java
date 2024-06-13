package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PembayaranActivity extends AppCompatActivity {
    private int paymentMethodId;
    private int appointmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        Button bayarButton = findViewById(R.id.bayar_2);
        appointmentId = getIntent().getIntExtra("appointment_id", -1);

        findViewById(R.id.gopay_rb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethodId = 8;
            }
        });

        findViewById(R.id.ovo_rb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethodId = 7;
            }
        });

        final Context context = this;
        bayarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KonfirmasiPembayaranActivity.class);
                intent.putExtra("appointment_id", appointmentId);
                intent.putExtra("payment_method_id", paymentMethodId);
                startActivity(intent);
            }
        });
    }
}
