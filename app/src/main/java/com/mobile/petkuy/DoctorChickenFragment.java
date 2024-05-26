package com.mobile.petkuy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DoctorChickenFragment extends Fragment {

    private List<Doctor> doctorList;
    private RecyclerView rvDoctorChicken;
    private DoctorListAdapter doctorListAdapter;
    private TextView tvNoResultDoctorChicken;

    public DoctorChickenFragment(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public static DoctorChickenFragment newInstance(List<Doctor> doctorList) {
        DoctorChickenFragment fragment = new DoctorChickenFragment(doctorList);
        fragment.doctorList = doctorList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doctor_chicken, container, false);

        rvDoctorChicken = rootView.findViewById(R.id.rvDoctorChicken);
        tvNoResultDoctorChicken = rootView.findViewById(R.id.tvNoResultDoctorChicken);

        doctorListAdapter = new DoctorListAdapter(doctorList);
        rvDoctorChicken.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDoctorChicken.setAdapter(doctorListAdapter);

        return rootView;
    }

    public void updateDoctors(List<Doctor> doctors) {
        doctorList.clear();
        doctorList.addAll(doctors);
        if (doctorListAdapter != null) {
            doctorListAdapter.notifyDataSetChanged();
        }
        updateNoResultTextViewVisibility();
    }

    private void updateNoResultTextViewVisibility() {
        if (doctorList.isEmpty()) {
            tvNoResultDoctorChicken.setVisibility(View.VISIBLE);
        } else {
            tvNoResultDoctorChicken.setVisibility(View.GONE);
        }
    }
}