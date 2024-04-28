package com.mobile.petkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class BatalJanji extends AppCompatActivity {


    private EditText etAlasan;
    private Button btBatal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bataljanji);


        etAlasan = findViewById(R.id.etAlasan);
        btBatal = findViewById(R.id.btBatal);

        btBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String alasan = etAlasan.getText().toString();
                Intent intent = new Intent(BatalJanji.this,konfirmasi.class);
                intent.putExtra("alasan",alasan);
                startActivity(intent);

            }
        });

    }


}