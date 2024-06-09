package com.mobile.petkuy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class editAppointmentFragment extends Fragment {

    private OnAppointmentItemSelectedListener mListener;
    private TextView selectedButton; // To store the previously selected button

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        // Find TextViews
        TextView tvTgl1 = view.findViewById(R.id.tvTgl1);
        TextView tvTgl2 = view.findViewById(R.id.tvTgl2);
        TextView tvTgl3 = view.findViewById(R.id.tvTgl3);
        TextView tvTgl4 = view.findViewById(R.id.tvTgl4);

        // Set click listeners for TextViews
        setClickListener(tvTgl1, "Senin", "7");
        setClickListener(tvTgl2, "Selasa", "8");
        setClickListener(tvTgl3, "Rabu", "9");
        setClickListener(tvTgl4, "Kamis", "10");

        return view;
    }

    private void setClickListener(TextView textView, final String selectedDate, final String selectedTime) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedButton != null) {
                    selectedButton.setBackgroundColor(getResources().getColor(android.R.color.white)); // Reset background color
                }
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light)); // Set selected button color
                selectedButton = (TextView) v;
                mListener.onAppointmentItemSelected(selectedDate, selectedTime);
            }
        });
    }

    // Interface for communication with hosting activity
    public interface OnAppointmentItemSelectedListener {
        void onAppointmentItemSelected(String selectedDate, String selectedTime);
    }

    // Method to set the listener
    public void setOnAppointmentItemSelectedListener(OnAppointmentItemSelectedListener listener) {
        mListener = listener;
    }
}