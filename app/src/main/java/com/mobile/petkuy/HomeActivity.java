package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btWelcome = findViewById(R.id.button);
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

        LinearLayout linearLayout = findViewById(R.id.llHistoryCardContainer);

        for (AppointmentHistory appointment : riwayatJanjiList) {
            CardView cardView = (CardView) LayoutInflater.from(this).inflate(R.layout.appointment_history_card, null);

            TextView doctorNameTextView = cardView.findViewById(R.id.tv_doctor_name);
            ImageView doctorImageView = cardView.findViewById(R.id.imageView2);
            TextView petTextView = cardView.findViewById(R.id.tv_pet);
            TextView hospitalTextView = cardView.findViewById(R.id.tv_hospital);

            doctorNameTextView.setText(appointment.getDoctorName());
            doctorImageView.setImageResource(appointment.getDoctorPicture());
            petTextView.setText(appointment.getPetCategory());
            hospitalTextView.setText(appointment.getHospital());

            linearLayout.addView(cardView);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            Intent intent = new Intent(HomeActivity.this, DoctorListActivity.class);
            startActivity(intent);
        }
    }
}
