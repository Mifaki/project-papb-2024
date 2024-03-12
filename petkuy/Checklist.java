package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Checklist extends AppCompatActivity {

    private TextView tvTgl1, tvTgl2, tvTgl3, tvTgl4;
    private TextView tvJadwal1, tvJadwal2;

    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        // Inisialisasi TextView
        tvTgl1 = findViewById(R.id.tvTgl1);
        tvTgl2 = findViewById(R.id.tvTgl2);
        tvTgl3 = findViewById(R.id.tvTgl3);
        tvTgl4 = findViewById(R.id.tvTgl4);

        tvJadwal1 = findViewById(R.id.tvJadwal);
        tvJadwal2 = findViewById(R.id.tvJadwal2);

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

        // Set listener klik untuk TextView Jadwal
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
                // Remove '\n' character from selectedDate
                String formattedDate = selectedDate.replace("\n", "");

                // Proceed with sending data to appointment
                sendDataToAppointment(formattedDate, selectedTime);
            }
        });

    }

// Method to send data to appointment
private void sendDataToAppointment(String formattedDate, String selectedTime) {
    Intent intent = new Intent(Checklist.this, listappointment.class);
    intent.putExtra("selectedDate", formattedDate);
    intent.putExtra("selectedTime", selectedTime);
    startActivity(intent);
}


    // Method to set shadow background for TextView
    private void setShadowBackground(TextView textView) {
        textView.setBackgroundResource(R.drawable.selected_background);
    }

    // Method to clear shadow background for all TextViews except the provided one
    private void clearShadowBackground(TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setBackgroundResource(R.drawable.default_background);
        }
    }
}
