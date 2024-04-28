package com.mobile.petkuy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class appAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final List<appointmentModel> appointmentModels;
    protected static SelectListener listener;

    public appAdapter(Context context, List<appointmentModel> appointmentModels) {
        this.context = context;
        this.appointmentModels = appointmentModels;
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvDokter;
        private final TextView tvSpesialis;
        private final TextView tvLokasi;
        private final TextView tvJanji;
        private final Button btUbah;
        private TextView tvSelectedDate;
        private final ImageView DoctorApp;
        private final Button btBayar;
        private final Button btBatal;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.DoctorApp = itemView.findViewById(R.id.ivDoctorApp);
            this.tvDokter = itemView.findViewById(R.id.tvDokter);
            this.btUbah = itemView.findViewById(R.id.btUbahJadwal);
            this.tvSpesialis = itemView.findViewById(R.id.tvSpesialis);
            this.tvLokasi = itemView.findViewById(R.id.tvLokasi);
            this.tvJanji = itemView.findViewById(R.id.tvJanji);
            this.btBayar = itemView.findViewById(R.id.btBayar);
            this.btBatal = itemView.findViewById(R.id.btBatal);
            this.tvSelectedDate = itemView.findViewById(R.id.tvJanji);
            itemView.setOnClickListener(this);
            btUbah.setOnClickListener(this);
            btBayar.setOnClickListener(this);
            btBatal.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClicked(getAdapterPosition(), v);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointmentcard, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        appointmentModel appointmentModel = appointmentModels.get(position);
        VH vh = (VH) holder;
        vh.DoctorApp.setImageResource(appointmentModel.getImage());
        vh.tvDokter.setText(appointmentModel.getNamaDok());
        vh.tvSpesialis.setText(appointmentModel.getSpesialis() + "|" + appointmentModel.getNamaRS());
        vh.tvLokasi.setText(appointmentModel.getJalanRS());
        vh.tvJanji.setText(appointmentModel.getJadwal());

        vh.btUbah.setOnClickListener(v -> {
            int ivDoktor = appointmentModel.getImage();
            String doktor = vh.tvDokter.getText().toString();
            String spesialis = appointmentModel.getSpesialis().toString();
            String lokasi = vh.tvLokasi.getText().toString();
            String janji = vh.tvJanji.getText().toString();

            // Create an intent to start the Checklist activity
            Intent intent = new Intent(context, doctorsAppointment.class);
            // Pass the extracted data to the Checklist activity using a Bundle
            Bundle bundle = new Bundle();
            bundle.putInt("IV_DOKTOR", ivDoktor);
            bundle.putString("DOCTOR", doktor);
            bundle.putString("SPESIALIS", spesialis);
            bundle.putString("LOKASI", lokasi);
            bundle.putString("JANJI", janji);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });

        // Check if there is an intent and extras available
        if (context instanceof Activity) {
            Intent intent = ((Activity) context).getIntent();
            Bundle extras = intent.getExtras();

            if (extras != null) {
                String selectedDate = intent.getStringExtra("selectedDate");
                String selectedTime = intent.getStringExtra("selectedTime");
                vh.tvSelectedDate.setText(selectedDate + " " + selectedTime);
            } else {
                // Clear text if no intent extras available
                vh.tvSelectedDate.setText("");
            }
        }

        vh.btBayar.setOnClickListener((v -> {
            Intent intent = new Intent(context, PembayaranActivity.class);
            context.startActivity(intent);
        }));

        vh.btBatal.setOnClickListener((v -> {
            Intent intent = new Intent(context, BatalJanji.class);
            context.startActivity(intent);

            // Perform action on button click
        }));

    }


    @Override
    public int getItemCount() {
        return appointmentModels.size();
    }

    public void setOnItemClickListener(SelectListener listener) {
        appAdapter.listener = listener;
    }

    public interface SelectListener {
        void onItemClicked(int position, View v);
    }

}
