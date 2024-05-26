package com.mobile.petkuy;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BatalJanji extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bataljanji);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentbatal, new BatalJanjiFragment())
                .commit();
    }

    public void proceedToKonfirmasi(String alasan) {
        Intent intent = new Intent(this, konfirmasi.class);
        intent.putExtra("alasan", alasan);
        startActivity(intent);
    }
}
