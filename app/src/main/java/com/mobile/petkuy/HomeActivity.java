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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.petkuy.utils.SpacingItemDecoder;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btWelcome;
    private RecyclerView rvRiwayatJanji;
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

        List<AppointmentHistory> riwayatJanjiList = new ArrayList<>();
        riwayatJanjiList.add(new AppointmentHistory("drh. Anitamax", R.drawable.doctor_1, "Kucing", "RS Keluarga Kita Tangerang, Banten"));
        riwayatJanjiList.add(new AppointmentHistory("drh. Kim", R.drawable.doctor_2, "Kucing", "RS Keluarga Kita Tangerang, Banten"));
        riwayatJanjiList.add(new AppointmentHistory("drh. Anitamax", R.drawable.doctor_1, "Kucing", "RS Keluarga Kita Tangerang, Banten"));
        riwayatJanjiList.add(new AppointmentHistory("drh. Kim", R.drawable.doctor_2, "Kucing", "RS Keluarga Kita Tangerang, Banten"));
        riwayatJanjiList.add(new AppointmentHistory("drh. Anitamax", R.drawable.doctor_1, "Kucing", "RS Keluarga Kita Tangerang, Banten"));

        rvRiwayatJanji = findViewById(R.id.rvRiwayatJanji);
        rvRiwayatJanji.setLayoutManager(new LinearLayoutManager(this));
        SpacingItemDecoder itemDecorator = new SpacingItemDecoder(16);
        rvRiwayatJanji.addItemDecoration(itemDecorator);
        AppointmentHistoryAdapter adapter = new AppointmentHistoryAdapter(riwayatJanjiList);
        rvRiwayatJanji.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btWelcomeCard) {
            Intent intent = new Intent(HomeActivity.this, DoctorListActivity.class);
            startActivity(intent);
        }
    }
}
