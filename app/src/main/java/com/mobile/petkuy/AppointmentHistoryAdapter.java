package com.mobile.petkuy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobile.petkuy.model.AppointmentHistory;

import java.util.ArrayList;
import java.util.List;

public class AppointmentHistoryAdapter extends RecyclerView.Adapter<AppointmentHistoryAdapter.ViewHolder> {

    private List<AppointmentHistory> riwayatJanjiList;

    public AppointmentHistoryAdapter() {
        this.riwayatJanjiList = new ArrayList<>();
    }

    public void setData(List<AppointmentHistory> appointmentHistories) {
        this.riwayatJanjiList = appointmentHistories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_history_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppointmentHistory riwayatJanji = riwayatJanjiList.get(position);
        holder.doctorImageView.setImageResource(R.drawable.doctor_1);
        holder.doctorNameTextView.setText(riwayatJanji.getDoctorDetails().getName());
        holder.petTextView.setText(riwayatJanji.getDoctorDetails().getSpecialities());
        holder.hospitalTextView.setText(riwayatJanji.getHospitalDetails().getName());
    }

    @Override
    public int getItemCount() {
        return riwayatJanjiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        TextView doctorNameTextView, petTextView, hospitalTextView;
        Button buttonTextView;
        ImageView doctorImageView;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);

            doctorImageView = itemView.findViewById(R.id.ivDoctorPicture);
            doctorNameTextView = itemView.findViewById(R.id.tvDoctorName);
            petTextView = itemView.findViewById(R.id.tvPetCategory);
            hospitalTextView = itemView.findViewById(R.id.tvHospitalAdress);
            buttonTextView = itemView.findViewById(R.id.btDoctorAppoinment);
        }

        @Override
        public void onClick(View view) {

        }
    }
}