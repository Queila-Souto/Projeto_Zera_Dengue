package com.example.projetozeradengue.view.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.projetozeradengue.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Profile extends Fragment implements View.OnClickListener {
    MaterialTextView tvName;
    MaterialTextView tvBornDate;
    MaterialTextView tvEmail;
    String usuarioAtual;
    String dataNascimento;
    String email;
    private MaterialButton btn_back;
    private FirebaseAuth auth;
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public Profile() {
    }


    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iniciandoElementos();
        fetchValores();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

       @Override
    public void onStart() {
        super.onStart();

    }

       @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    private void iniciandoElementos() {
        tvName = requireActivity().findViewById(R.id.textNameProfile);
        tvBornDate = requireActivity().findViewById(R.id.textBodProfile);
        tvEmail = requireActivity().findViewById(R.id.textEmailProfile);
        btn_back = requireActivity().findViewById(R.id.btn_Back);

    }

    private void fetchValores() {
        FirebaseUser user = auth.getCurrentUser();
        assert user != null;
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child("users")
                .child(user.getUid());
        Log.d("teste", "id do usuario "+dref.child("nameUser").getRef().toString());

        dref.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                usuarioAtual = String.valueOf(Objects.requireNonNull(task.getResult()).child("nameUser").getValue());
                dataNascimento = String.valueOf(task.getResult().child("dob").getValue());
                email = String.valueOf(task.getResult().child("email").getValue());
                tvName.setText(usuarioAtual);
                tvBornDate.setText(dataNascimento);
                tvEmail.setText(email);
            }
        });

    }

}
