package com.mobile.petkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class listsAppointment extends AppCompatActivity{


    private List<appointmentModel> data;
    private RecyclerView rvAppointment;

    private TextView tvSelectedDate;
    private appAdapter AA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listappointment);

        this.data = new ArrayList<appointmentModel>();

        appointmentModel a = new appointmentModel(R.drawable.doc1,"drh. Hanni", "Ikan", "RS Depok", "Jl. Margonda, Depok, Kec. Pancoran Mas, Jawa Barat 16424", "Senin 7 Februari 2045");
        appointmentModel b = new appointmentModel(R.drawable.doc4,"drh. Imron", "Hewan Kecil", "RS Depok", "Jl. Kedung Mulya, Depok, Jawa Barat 16423", "Kamis, 10 Februari 2045");
        appointmentModel c = new appointmentModel(R.drawable.doc5,"drh. Ampri", "Burung", "RS Depok", "Jl. Margonda, Depok, Kec. Pancoran Mas, Jawa Barat 16424", "Sabtu 10 Maret 2045");

        this.data.add(a);this.data.add(b);this.data.add(c);
        this.data.add(a);this.data.add(b);this.data.add(c);

        this.rvAppointment = this.findViewById(R.id.rvAppointment);

        AA = new appAdapter(this, this.data);
        rvAppointment.setAdapter(AA);
        AA.setOnItemClickListener((position, v) -> {
            data.remove(position);
            AA.notifyDataSetChanged();
        });

        RecyclerView.LayoutManager la = new LinearLayoutManager(listsAppointment.this, LinearLayoutManager.VERTICAL, false);
        this.rvAppointment.setLayoutManager(la);
        this.rvAppointment.setAdapter(AA);

        View appointmentCardView = getLayoutInflater().inflate(R.layout.appointmentcard, null);
        tvSelectedDate = appointmentCardView.findViewById(R.id.tvJanji);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            this.data.indexOf(6);
            String selectedDate = intent.getStringExtra("selectedDate");
            String selectedTime = intent.getStringExtra("selectedTime");
            this.tvSelectedDate.setText(selectedDate + " " + selectedTime);
        }

    }



}
