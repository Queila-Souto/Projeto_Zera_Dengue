package com.example.projetozeradengue.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.ControllerDenounces;
import com.example.projetozeradengue.datamodel.AdapterDenounces;
import com.example.projetozeradengue.model.Denounces;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyDenounces extends Fragment implements View.OnClickListener {
    List<Denounces> dados = new ArrayList<>(); // Lista de dados a serem exibidos
    MaterialButton m_btnBack;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyDenounces() {
        // Required empty public constructor
    }

     public static MyDenounces newInstance(String param1, String param2) {
        MyDenounces fragment = new MyDenounces();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startingComponents();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_denounces, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        title1("Minhas Denúncias");
        title2(("Aqui estão suas denúncias"));
    }

    public void    title1(String title) {
        TextView mtitle = getActivity().findViewById(R.id.title);
        mtitle.setText(title);
    }

    public void title2(String title2){
        TextView mtitle2 = getActivity().findViewById(R.id.title2);
        mtitle2.setText(title2);
    }

    private void startingComponents() {
        m_btnBack = getActivity().findViewById(R.id.btn_back);
        m_btnBack.setOnClickListener(this);
        showDenounce();
    }

    private void back() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new MainFragment() ).commit();
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_back:
                    back();
                    break;
            }
        }

    private void showDenounce() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("denounces");
        FirebaseAuth auth;
        auth = FirebaseAuth.getInstance();
        String user = auth.getCurrentUser().getUid();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for (DataSnapshot snapshot : datasnapshot.getChildren()){
                    String value = snapshot.child("userId").getValue().toString();
                    String street = snapshot.child("a_Street").getValue().toString();
                    String complement = snapshot.child("a_complement").getValue().toString();
                    String district = snapshot.child("a_district").getValue().toString();
                    String number = snapshot.child("a_number").getValue().toString();
                    String cep = snapshot.child("cep").getValue().toString();
                    String idDenounce = snapshot.child("id").getValue().toString();
                    String note = snapshot.child("note").getValue().toString();
                    String city = snapshot.child("a_city").getValue().toString();
                    String state = snapshot.child("a_state").getValue().toString();
                    Denounces denuncias = new Denounces();
                    if (value.equals(user)){
                        denuncias.setA_complement(denuncias.getA_complement());
                        denuncias.setA_Street(street);
                        denuncias.setA_complement(complement);
                        denuncias.setA_district(district);
                        denuncias.setA_number(number);
                        denuncias.setCep(cep);
                        denuncias.setId(idDenounce);
                        denuncias.setA_state(state);
                        denuncias.setA_city(city);
                        denuncias.setNote(note);
                        dados.add(denuncias);
                    }
                }

                AdapterDenounces adapterDenounces = new AdapterDenounces(dados);
                RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_denounces);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapterDenounces);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


}

