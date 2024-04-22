package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.petkuy.utils.SpacingItemDecoder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class DoctorListActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btBack;
    private SearchView svSearchBar;
    private RecyclerView recyclerView;
    private TextView noResultsText;
    private List<Doctor> filteredDoctorList;
    private List<Doctor> doctorList;
    private DoctorListAdapter doctorListAdapter;

    interface doctorRequest {
        @GET("/Doctor/read.php")
        Call<List<Doctor>> getDoctor();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        btBack = findViewById(R.id.btBack);
        svSearchBar = findViewById(R.id.svSearchBar);
        noResultsText = findViewById(R.id.tvNoResult);
        recyclerView  = findViewById(R.id.rvDoctorContainer);

        btBack.setOnClickListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DoctorListActivity.doctorRequest apicall = retrofit.create(DoctorListActivity.doctorRequest.class);

        filteredDoctorList = new ArrayList<>();
        doctorListAdapter = new DoctorListAdapter(filteredDoctorList);

        apicall.getDoctor().enqueue(new Callback<List<Doctor>>(){
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    doctorList = response.body();
                    filteredDoctorList.addAll(doctorList);
                    doctorListAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(DoctorListActivity.this, "Response kosong atau terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                Toast.makeText(DoctorListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacingItemDecoder(16));
        recyclerView.setAdapter(doctorListAdapter);

        svSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredDoctorList.clear();
                if (TextUtils.isEmpty(newText)) {
                    filteredDoctorList.addAll(doctorList);
                } else {
                    for (Doctor doctor : doctorList) {
                        if (doctor.getName().toLowerCase().contains(newText.toLowerCase())) {
                            filteredDoctorList.add(doctor);
                        }
                    }
                }

                doctorListAdapter.notifyDataSetChanged();

                if (filteredDoctorList.isEmpty()) {
                    noResultsText.setVisibility(View.VISIBLE);
                } else {
                    noResultsText.setVisibility(View.GONE);
                }

                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btBack) {
            finish();
            return;
        }
    }
}