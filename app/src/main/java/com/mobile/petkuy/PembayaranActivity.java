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

    private String doctorName, doctorSpeciality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);

        Button bayarButton = findViewById(R.id.bayar_2);
        appointmentId = getIntent().getIntExtra("APPOINTMENT_ID", -1);
        doctorName = getIntent().getStringExtra("DOCTOR_NAME");
        doctorSpeciality = getIntent().getStringExtra("DOCTOR_SPECIALITY");

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
                intent.putExtra("APPOINTMENT_ID", appointmentId);
                intent.putExtra("DOCTOR_NAME", doctorName);
                intent.putExtra("DOCTOR_SPECIALITY", doctorSpeciality);
                intent.putExtra("PAYMENT_METHOD_ID", paymentMethodId);
                startActivity(intent);
            }
        });
    }
}
