package com.mobile.petkuy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


public class BatalJanjiFragment extends Fragment {
    private EditText etAlasan;
    private Button btBatal;

    public BatalJanjiFragment() {
        // Required empty public constructor
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View v = inflater.inflate(R.layout.fragment_batal_janji, container, false);
//
//        etAlasan = v.findViewById(R.id.etAlasan);
//        btBatal = v.findViewById(R.id.btBatal);
//
//        btBatal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String alasan = etAlasan.getText().toString();
//                ((BatalJanji)getActivity()).proceedToKonfirmasi(alasan);
//            }
//        });
//
//        return v;
//    }
}
