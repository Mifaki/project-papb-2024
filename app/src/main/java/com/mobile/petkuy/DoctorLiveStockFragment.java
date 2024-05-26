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

public class DoctorLiveStockFragment extends Fragment {

    private List<Doctor> doctorList;
    private RecyclerView rvDoctorLiveStock;
    private DoctorListAdapter doctorListAdapter;
    private TextView tvNoResultDoctorLiveStock;

    public DoctorLiveStockFragment(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public static DoctorLiveStockFragment newInstance(List<Doctor> doctorList) {
        DoctorLiveStockFragment fragment = new DoctorLiveStockFragment(doctorList);
        fragment.doctorList = doctorList;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doctor_live_stock, container, false);

        rvDoctorLiveStock = rootView.findViewById(R.id.rvDoctorLiveStock);
        tvNoResultDoctorLiveStock = rootView.findViewById(R.id.tvNoResultDoctorLiveStock);

        doctorListAdapter = new DoctorListAdapter(doctorList);
        rvDoctorLiveStock.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDoctorLiveStock.setAdapter(doctorListAdapter);

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
            tvNoResultDoctorLiveStock.setVisibility(View.VISIBLE);
        } else {
            tvNoResultDoctorLiveStock.setVisibility(View.GONE);
        }
    }
}