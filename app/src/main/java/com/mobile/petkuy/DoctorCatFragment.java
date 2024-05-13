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

public class DoctorCatFragment extends Fragment {

    private List<Doctor> doctorList;
    private RecyclerView rvDoctorCat;
    private DoctorListAdapter doctorListAdapter;
    private TextView tvNoResultDoctorCat;

    public DoctorCatFragment(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public static DoctorCatFragment newInstance(List<Doctor> doctorList) {
        DoctorCatFragment fragment = new DoctorCatFragment(doctorList);
        fragment.doctorList = doctorList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doctor_cat, container, false);

        rvDoctorCat = rootView.findViewById(R.id.rvDoctorCat);
        tvNoResultDoctorCat = rootView.findViewById(R.id.tvNoResultDoctorCat);

        doctorListAdapter = new DoctorListAdapter(doctorList);
        rvDoctorCat.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDoctorCat.setAdapter(doctorListAdapter);

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
            tvNoResultDoctorCat.setVisibility(View.VISIBLE);
        } else {
            tvNoResultDoctorCat.setVisibility(View.GONE);
        }
    }
}