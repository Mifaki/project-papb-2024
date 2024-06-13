package com.mobile.petkuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Reason extends AppCompatActivity {
    private EditText etAlasanR;
    private EditText etDeskripsiR;
    private Button btTambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reason);
        etAlasanR = findViewById(R.id.etAlasanR);
        etDeskripsiR = findViewById(R.id.etDeskripsiR);

        btTambah = findViewById(R.id.btTambah);
        btTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Alasan = etAlasanR.getText().toString();
                String Deskripsi = etDeskripsiR.getText().toString();

                if(Alasan.isEmpty()){
                    etAlasanR.setError("Alasan tidak boleh kosong");
                    return;
                }
                if(Deskripsi.isEmpty()){
                    etDeskripsiR.setError("Deskripsi tidak boleh kosong");
                    return;
                }
                TambahKeDatabase(Alasan,Deskripsi);
            }
        });
    }

    private void TambahKeDatabase(String alasan, String deskripsi) {
        HashMap<String, Object> AlasanHashMap = new HashMap<>();
        AlasanHashMap.put("Alasan", alasan);
        AlasanHashMap.put("Deskripsi", deskripsi);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://petkuy-89899-default-rtdb.asia-southeast1.firebasedatabase.app/");

        DatabaseReference AlasanRef = database.getReference("Alasan");

        String key = AlasanRef.push().getKey();
        AlasanHashMap.put("key", key);

        AlasanRef.child(key).setValue(AlasanHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Reason.this, "Berhasil Membatalkan Janji", Toast.LENGTH_SHORT).show();
                etAlasanR.getText().clear();
                etDeskripsiR.getText().clear();

                Intent intent = new Intent(Reason.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}