package com.example.projetozeradengue.view.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.core.AppUtil;
import com.google.android.material.textfield.TextInputEditText;


public class Cadastro extends Fragment {
    FragmentManager obj;
    public static TextInputEditText m_name, m_dob, m_email, m_password;
    public static String name, dob, email, password;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);
        Log.d(AppUtil.TAG, "Cadastro: Tela Cadastro criada");


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        title1();
        title2();
        Log.d(AppUtil.TAG, "Cadastro: atribuir elementos");

        m_name = getActivity().findViewById(R.id.ed_name);
        m_dob = getActivity().findViewById(R.id.ed_date);
        m_email = getActivity().findViewById(R.id.ed_email);
        m_password = getActivity().findViewById(R.id.ed_password);
        name = m_name.toString();
        dob = m_dob.toString();
        email = m_email.toString();
        password = m_password.toString();



    }

 public void    title1() {

    TextView mtitle = getActivity().findViewById(R.id.title);
    mtitle.setText("Cadastro");
}

public void title2(){
        TextView mtitle2 = getActivity().findViewById(R.id.title2);
        mtitle2.setText(" Informe seus dados e clique em CADASTRAR \n para se cadastrar em nossa plataforma");
}

}