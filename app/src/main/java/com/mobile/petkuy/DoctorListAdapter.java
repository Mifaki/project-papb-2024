package com.mobile.petkuy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mobile.petkuy.model.DoctorDetails;

import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder> {

    private List<DoctorDetails> doctorList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(DoctorDetails doctor);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public DoctorListAdapter(List<DoctorDetails> doctorList) {
        this.doctorList = doctorList;
    }

    public void setData(List<DoctorDetails> doctorList) {
        this.doctorList = doctorList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_card, parent, false);
        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorDetails doctor = doctorList.get(position);
        holder.bind(doctor);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView doctorNameTextView, petCategoryTextView, hospitalTextView, addressTextView;
        ImageView doctorImageView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            doctorImageView = itemView.findViewById(R.id.ivDoctorPicture);
            doctorNameTextView = itemView.findViewById(R.id.tvDoctorName);
            petCategoryTextView = itemView.findViewById(R.id.tvPetCategory);
            hospitalTextView = itemView.findViewById(R.id.tvHospital);
            addressTextView = itemView.findViewById(R.id.tvAddress);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick((DoctorDetails) v.getTag());
                        }
                    }
                }
            });
        }

        public void bind(DoctorDetails doctor) {
            doctorNameTextView.setText(doctor.getName());
            petCategoryTextView.setText(doctor.getSpecialities());
            hospitalTextView.setText(doctor.getHospitalDetails().getName());
            addressTextView.setText(doctor.getHospitalDetails().getAddress());

            Glide.with(doctorImageView.getContext())
                    .load(doctor.getPicture())
                    .placeholder(R.drawable.doctor_4)
                    .into(doctorImageView);

            itemView.setTag(doctor);
        }
    }
}
