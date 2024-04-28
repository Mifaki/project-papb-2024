package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btWelcome;
    private RecyclerView rvRiwayatJanji;
    private AppointmentHistoryAdapter riwayatJanjiAdapter;
    interface historyRequest {
        @GET("/AppoinmentHistory/read.php")
        Call<List<AppointmentHistory>> getAppoinmentHistory();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btWelcome = findViewById(R.id.btWelcomeCard);
        btWelcome.setOnClickListener(this);

        TextView textView = findViewById(R.id.tvName);
        String fullText = "Selamat datang, momo";

        SpannableString spannableString = new SpannableString(fullText);

        int momoColor = getResources().getColor(R.color.primary_700);

        int momoStartIndex = fullText.indexOf("momo");
        int momoEndIndex = momoStartIndex + "momo".length();

        spannableString.setSpan(new ForegroundColorSpan(momoColor), momoStartIndex, momoEndIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        historyRequest apicall = retrofit.create(historyRequest.class);

        List<AppointmentHistory> riwayatJanjiList = new ArrayList<>();
        riwayatJanjiAdapter = new AppointmentHistoryAdapter(riwayatJanjiList);

        apicall.getAppoinmentHistory().enqueue(new Callback<List<AppointmentHistory>>() {
            @Override
            public void onResponse(Call<List<AppointmentHistory>> call, Response<List<AppointmentHistory>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    riwayatJanjiList.addAll(response.body());
                    riwayatJanjiAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(HomeActivity.this, "Response kosong atau terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AppointmentHistory>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        rvRiwayatJanji = findViewById(R.id.rvRiwayatJanji);
        rvRiwayatJanji.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecoder itemDecorator = new SpacingItemDecoder(16);
        rvRiwayatJanji.addItemDecoration(itemDecorator);
        rvRiwayatJanji.setAdapter(riwayatJanjiAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btWelcomeCard) {
            Intent intent = new Intent(HomeActivity.this, DoctorListActivity.class);
            startActivity(intent);
        }
    }
}
