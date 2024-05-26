package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
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
    private List<Doctor> doctorList, filteredDoctorList, catDoctors, chickenDoctors, livestockDoctors;
    private DoctorListAdapter doctorListAdapter;
    private DoctorRepository doctorRepository;
    private MutableLiveData<String> searchQuery = new MutableLiveData<>();
    private FrameLayout flDoctorCategory;
    private TabLayout tlDoctorCatgoryTab;

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
        flDoctorCategory = findViewById(R.id.flDoctorCategory);
        tlDoctorCatgoryTab = findViewById(R.id.tlDoctorCategoryTab);

        btBack.setOnClickListener(this);

        filteredDoctorList = new ArrayList<>();
        catDoctors = new ArrayList<>();
        chickenDoctors = new ArrayList<>();
        livestockDoctors = new ArrayList<>();


        getSupportFragmentManager().beginTransaction().replace(R.id.flDoctorCategory, new DoctorCatFragment(catDoctors))
                .addToBackStack(null)
                .commit();

        doctorRepository = new DoctorRepository(getApplication());
        doctorRepository.getAllDoctors().observe(this, new Observer<List<Doctor>>() {
            @Override
            public void onChanged(List<Doctor> doctors) {
                catDoctors.clear();
                chickenDoctors.clear();
                livestockDoctors.clear();
                for (Doctor doctor : doctors) {
                    switch (doctor.getCategory()) {
                        case "Kucing":
                            catDoctors.add(doctor);
                            break;
                        case "Ayam":
                            chickenDoctors.add(doctor);
                            break;
                        case "Hewan Ternak":
                            livestockDoctors.add(doctor);
                            break;
                    }
                }
            }
        });

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

        tlDoctorCatgoryTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new DoctorCatFragment(catDoctors);
                        break;
                    case 1:
                        fragment = new DoctorLiveStockFragment(livestockDoctors);
                        break;
                    case 2:
                        fragment = new DoctorChickenFragment(chickenDoctors);
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.flDoctorCategory, fragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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
                    searchDoctor(searchQuery.getValue());
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
        List<Doctor> filteredCatDoctors = new ArrayList<>();
        List<Doctor> filteredChickenDoctors = new ArrayList<>();
        List<Doctor> filteredLivestockDoctors = new ArrayList<>();

        if (doctorList != null) {
            if (TextUtils.isEmpty(query)) {
                filteredCatDoctors.addAll(catDoctors);
                filteredChickenDoctors.addAll(chickenDoctors);
                filteredLivestockDoctors.addAll(livestockDoctors);
            } else {
                for (Doctor doctor : doctorList) {
                    if (doctor.getName().toLowerCase().contains(query.toLowerCase())) {
                        switch (doctor.getCategory()) {
                            case "Kucing":
                                filteredCatDoctors.add(doctor);
                                break;
                            case "Ayam":
                                filteredChickenDoctors.add(doctor);
                                break;
                            case "Hewan Ternak":
                                filteredLivestockDoctors.add(doctor);
                                break;
                        }
                    }
                }
            }
        }
        notifyFragments(filteredCatDoctors, filteredChickenDoctors, filteredLivestockDoctors);
    }

    private void notifyFragments(List<Doctor> catDoctors, List<Doctor> chickenDoctors, List<Doctor> livestockDoctors) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.flDoctorCategory);
        if (fragment instanceof DoctorCatFragment) {
            ((DoctorCatFragment) fragment).updateDoctors(catDoctors);
        } else if (fragment instanceof DoctorChickenFragment) {
            ((DoctorChickenFragment) fragment).updateDoctors(chickenDoctors);
        } else if (fragment instanceof DoctorLiveStockFragment) {
            ((DoctorLiveStockFragment) fragment).updateDoctors(livestockDoctors);
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