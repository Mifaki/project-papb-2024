package com.mobile.petkuy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DokterHewan> data;
    private RecyclerView rvDokterHewan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.data = new ArrayList<DokterHewan>();

        DokterHewan a = new DokterHewan("drh. Haerin","Terlalu jauh",R.drawable.drhhaerin,"Rumah Sakit Depok","7 Februari 2023");
        DokterHewan b = new DokterHewan("drh. Minji","hewan membaik",R.drawable.drhminji,"Rumah Sakit Universitas Brawijaya","23 Januari 2023");
        DokterHewan c = new DokterHewan("drh. Ozai","Kondisi keuangan",R.drawable.ozai,"Rumah Sakit Malang","6 Maret 2023");
        DokterHewan d = new DokterHewan("drh. Zuko","Telalu mahal",R.drawable.zuko,"Rumah Sakit Surabaya","13 April 2023");
        DokterHewan e = new DokterHewan("drh. Soka","Alasan pribadi",R.drawable.soka,"Rumah Sakit Jakarta","6 Mei 2023");
        DokterHewan f = new DokterHewan("drh. Aang","Tidak ada akses jalan",R.drawable.aang,"Rumah Sakit Batu","30 Juni 2023");

        this.data.add(a);
        this.data.add(b);
        this.data.add(c);
        this.data.add(d);
        this.data.add(e);
        this.data.add(f);

        this.rvDokterHewan = this.findViewById(R.id.rvDokterHewan);

        DokterHewanAdapter dokterHewanAdapter=
                new DokterHewanAdapter(MainActivity.this,this.data);

        RecyclerView.LayoutManager lm =
                new LinearLayoutManager(MainActivity.this
                );
        this.rvDokterHewan.setLayoutManager(lm);
        this.rvDokterHewan.setAdapter(dokterHewanAdapter);

    }
}