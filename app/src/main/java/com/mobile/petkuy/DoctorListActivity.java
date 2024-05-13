package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
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
    private DoctorRepository doctorRepository;
    private MutableLiveData<String> searchQuery = new MutableLiveData<>();

    interface DoctorRequest {
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

        filteredDoctorList = new ArrayList<>();
        doctorListAdapter = new DoctorListAdapter();
        doctorRepository = new DoctorRepository(getApplication());
        doctorRepository.getAllDoctors().observe(this, new Observer<List<Doctor>>() {
            @Override
            public void onChanged(List<Doctor> doctors) {
                doctorListAdapter.setData(doctors);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacingItemDecoder(16));
        recyclerView.setAdapter(doctorListAdapter);

        filteredDoctorList = new ArrayList<>();
        svSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchQuery.setValue(newText);
                return true;
            }
        });
        searchQuery.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String query) {
                searchDoctor(query);
            }
        });

        fetchAndInsertData();
    }

    public void fetchAndInsertData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DoctorRequest apiCall = retrofit.create(DoctorRequest.class);
        apiCall.getDoctor().enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    doctorList = response.body();
                    doctorRepository.deleteAllDoctors();
                    for (Doctor doctor : doctorList) {
                        doctorRepository.insert(doctor);
                    }
                } else {
                    Toast.makeText(DoctorListActivity.this, "Response kosong atau terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {
                Toast.makeText(DoctorListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void searchDoctor(String query) {
        filteredDoctorList.clear();
        if (doctorList != null && !TextUtils.isEmpty(query)) {
            for (Doctor doctor : doctorList) {
                if (doctor.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredDoctorList.add(doctor);
                }
            }
        } else if (doctorList != null) {
            filteredDoctorList.addAll(doctorList);
        }

        if (filteredDoctorList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            noResultsText.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            noResultsText.setVisibility(View.GONE);
            doctorListAdapter.setData(filteredDoctorList);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btBack) {
            finish();
            return;
        }
    }
}