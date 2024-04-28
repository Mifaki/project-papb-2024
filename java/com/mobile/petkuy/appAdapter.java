package com.mobile.petkuy;

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
        private final ImageView DoctorApp;
        private final Button btBayar;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.DoctorApp = itemView.findViewById(R.id.ivDoctorApp);
            this.tvDokter = itemView.findViewById(R.id.tvDokter);
            this.btUbah = itemView.findViewById(R.id.btUbahJadwal);
            this.tvSpesialis = itemView.findViewById(R.id.tvSpesialis);
            this.tvLokasi = itemView.findViewById(R.id.tvLokasi);
            this.tvJanji = itemView.findViewById(R.id.tvJanji);
            this.btBayar = itemView.findViewById(R.id.btBayar);
            itemView.setOnClickListener(this);
            btUbah.setOnClickListener(this);
            btBayar.setOnClickListener(this);
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



        vh.btBayar.setOnClickListener((v -> {

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
