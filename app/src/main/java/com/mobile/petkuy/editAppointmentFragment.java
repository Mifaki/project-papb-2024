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
    private TextView selectedButton; // To store the previously selected date button
    private TextView selectedTimeButton; // To store the previously selected time button

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);

        TextView tvTgl1 = view.findViewById(R.id.tvTgl1);
        TextView tvTgl2 = view.findViewById(R.id.tvTgl2);
        TextView tvTgl3 = view.findViewById(R.id.tvTgl3);
        TextView tvTgl4 = view.findViewById(R.id.tvTgl4);

        setDateClickListener(tvTgl1, "Senin", "7");
        setDateClickListener(tvTgl2, "Selasa", "8");
        setDateClickListener(tvTgl3, "Rabu", "9");
        setDateClickListener(tvTgl4, "Kamis", "10");

        TextView tvJadwal1 = view.findViewById(R.id.tvJadwal);
        TextView tvJadwal2 = view.findViewById(R.id.tvJadwal2);

        setTimeClickListener(tvJadwal1, "15:00");
        setTimeClickListener(tvJadwal2, "15:30");

        return view;
    }

    private void setDateClickListener(TextView textView, final String selectedDate, final String selectedTime) {
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

    private void setTimeClickListener(TextView textView, final String selectedTime) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTimeButton != null) {
                    selectedTimeButton.setBackgroundColor(getResources().getColor(android.R.color.white)); // Reset background color
                }
                v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light)); // Set selected button color
                selectedTimeButton = (TextView) v;
                mListener.onAppointmentTimeSelected(selectedTime);
            }
        });
    }

    public interface OnAppointmentItemSelectedListener {
        void onAppointmentItemSelected(String selectedDate, String selectedTime);
        void onAppointmentTimeSelected(String selectedTime);
    }

    public void setOnAppointmentItemSelectedListener(OnAppointmentItemSelectedListener listener) {
        mListener = listener;
    }
}