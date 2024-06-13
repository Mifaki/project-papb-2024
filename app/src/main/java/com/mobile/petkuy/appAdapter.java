package com.mobile.petkuy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.petkuy.model.Doctor;
import com.mobile.petkuy.model.appointmentModel;
import com.mobile.petkuy.model.hospitalModel;

import java.util.List;

public class appAdapter extends RecyclerView.Adapter<appAdapter.VH> {

    private final Context context;
    private final List<appointmentModel> appointmentModels;
    private final List<Doctor> doctorsModel;
    private final List<hospitalModel> hospitalsModel;
    private SelectListener listener;

    public appAdapter(Context context, List<appointmentModel> appointmentModels, List<Doctor> doctorsModel, List<hospitalModel> hospitalsModel) {
        this.context = context;
        this.appointmentModels = appointmentModels;
        this.doctorsModel = doctorsModel;
        this.hospitalsModel = hospitalsModel;
    }

    public class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvDokter;
        private final TextView tvSpesialis;
        private final TextView tvLokasi;
        private final TextView tvJanji;
        private final Button btUbah;
        private final ImageView DoctorApp;
        private final Button btBayar;
        private final Button btBatal;
        private final TextView tvSelectedDate;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.DoctorApp = itemView.findViewById(R.id.ivDoctorApp);
            this.tvDokter = itemView.findViewById(R.id.tvDokter);
            this.btUbah = itemView.findViewById(R.id.btUbahJadwal);
            this.tvSpesialis = itemView.findViewById(R.id.tvSpesialis);
            this.tvLokasi = itemView.findViewById(R.id.tvLokasi);
            this.tvJanji = itemView.findViewById(R.id.tvJanji);
            this.btBayar = itemView.findViewById(R.id.btBayar);
            this.btBatal = itemView.findViewById(R.id.btLihatDaftar);
            this.tvSelectedDate = itemView.findViewById(R.id.tvJanji);

            btBayar.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBayarClicked(getAdapterPosition());
                }
            });

            btBatal.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onBatalClicked(getAdapterPosition());
                }
            });

            btUbah.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onUbahClicked(getAdapterPosition());
                }
            });

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClicked(getAdapterPosition(), v);
                }
            });
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClicked(getAdapterPosition(), v);
            }
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointmentcard, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        appointmentModel appointment = appointmentModels.get(position);
        Doctor doctor = doctorsModel.get(position);

        hospitalModel hospital = null;
        for (hospitalModel h : hospitalsModel) {
            if (h.getId() == doctor.getHospital_id()) {
                hospital = h;
                break;
            }
        }

        if (hospital != null) {
            holder.tvLokasi.setText(hospital.getAddress());
        }

        holder.tvDokter.setText(doctor.getName());
        holder.tvSpesialis.setText(doctor.getSpecialities());
        holder.tvJanji.setText(appointment.getAppointment_date());

        Glide.with(holder.DoctorApp.getContext())
                .load(doctor.getPicture())
                .placeholder(R.drawable.doctor_4)
                .into(holder.DoctorApp);


        int imageResourceId = context.getResources().getIdentifier(doctor.getPicture(), "drawable", context.getPackageName());
        if (imageResourceId != 0) {
            holder.DoctorApp.setImageResource(imageResourceId);
        }

        holder.btUbah.setOnClickListener(v -> {
            String doktor = holder.tvDokter.getText().toString();
            String spesialis = doctor.getSpecialities();
            String lokasi = holder.tvLokasi.getText().toString();
            String janji = holder.tvJanji.getText().toString();
            String imageResourceIdStr = doctor.getPicture();

            Intent intent = new Intent(context, doctorsAppointment.class);
            Bundle bundle = new Bundle();
            bundle.putString("DOCTOR", doktor);
            bundle.putString("SPESIALIS", spesialis);
            bundle.putString("LOKASI", lokasi);
            bundle.putString("JANJI", janji);
            bundle.putString("IV_DOKTOR", imageResourceIdStr);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return appointmentModels.size();
    }

    public void setOnItemClickListener(SelectListener listener) {
        this.listener = listener;
    }

    public interface SelectListener {
        void onItemClicked(int position, View v);
        void onBayarClicked(int position);
        void onBatalClicked(int position);
        void onUbahClicked(int position);
    }

}
