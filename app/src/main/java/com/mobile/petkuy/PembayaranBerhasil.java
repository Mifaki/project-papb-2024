package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class PembayaranBerhasil extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_berhasil);

        final Context context = this;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intentListsAppointment = new Intent(context, HomeActivity.class);
                startActivity(intentListsAppointment);
                finish();
            }
        }, 3000);
    }
}
