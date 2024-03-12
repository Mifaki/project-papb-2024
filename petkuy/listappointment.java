package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class listappointment extends AppCompatActivity {

    private TextView tvSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listappointment);

        // Initialize TextViews to display selected date and time
        tvSelectedDate = findViewById(R.id.tvJanji);

        // Retrieve the intent from Checklist activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            // Extract the selected date and time from the intent
            String selectedDate = extras.getString("selectedDate");
            String selectedTime = extras.getString("selectedTime");

            // Display the selected date and time in the TextViews
            tvSelectedDate.setText(selectedDate + " " + selectedTime);

        }

        findViewById(R.id.btUbahJadwal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listappointment.this, Checklist.class);
                startActivity(intent);
            }
        });
    }
}
