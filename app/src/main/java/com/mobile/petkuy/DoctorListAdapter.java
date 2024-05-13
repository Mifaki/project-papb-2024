package com.mobile.petkuy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {

    private List<Doctor> doctorList;

    public DoctorListAdapter(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public void setData(List<Doctor> doctorList) {
        this.doctorList = doctorList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.bind(doctor);
    }
    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctorNameTextView, petCategoryTextView, hospitalTextView, addressTextView;
        ImageView doctorImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorImageView = itemView.findViewById(R.id.ivDoctorPicture);
            doctorNameTextView = itemView.findViewById(R.id.tvDoctorName);
            petCategoryTextView = itemView.findViewById(R.id.tvPetCategory);
            hospitalTextView = itemView.findViewById(R.id.tvHospital);
            addressTextView = itemView.findViewById(R.id.tvAddress);
        }

        public void bind(Doctor doctor) {
            doctorNameTextView.setText(doctor.getName());
            doctorImageView.setImageResource(R.drawable.doctor_4);
            petCategoryTextView.setText(doctor.getCategory());
            hospitalTextView.setText(doctor.getHospital_name());
            addressTextView.setText(doctor.getHospital_address());
        }
    }
}
