package com.example.projetozeradengue.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.ControllerUser;
import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.model.User;
import com.example.projetozeradengue.view.activity.LoginActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class Footer extends Fragment implements View.OnClickListener {

    public static TextInputEditText mname,memail, mpassword, mbod;
    MaterialButton mbtn_save, mbtn_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_footer,container,false); }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startingComponents();
        mbtn_back.setOnClickListener(this);
        mbtn_save.setOnClickListener(this);




    }

    private void startingComponents(){
        Log.d(AppUtil.TAG, "FOOTER: Iniciando componentes");

        mbtn_save=getActivity().findViewById((R.id.btn_save));
        mbtn_back=getActivity().findViewById((R.id.btn_back));
        mbod = getActivity().findViewById(R.id.ed_date);
        mname = getActivity().findViewById(R.id.ed_name);
        memail = getActivity().findViewById(R.id.ed_email);
        mpassword = getActivity().findViewById(R.id.ed_password);



    }
        private void backActivity() {
        Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
        startActivity(intent); }


    private void SaveUserAuth() {

        String email = memail.getText().toString();
        String password = mpassword.getText().toString();

        FirebaseAuth newUser = FirebaseAuth.getInstance();
    newUser.createUserWithEmailAndPassword(email, password); //recuperar dadoss da fragment cadastro
        Toast.makeText(getActivity().getBaseContext(), "usuario cadastrado", Toast.LENGTH_LONG).show();

    }




    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                backActivity();
                break;
            case R.id.btn_save:
                Log.d(AppUtil.TAG, "FOOTER: Autenticando");
                SaveUserAuth();

                Log.d(AppUtil.TAG, "FOOTER: Salvando dados");
                SaveUserDataBase();
                backActivity();
                break;


            }
        }

    private void SaveUserDataBase() {

        String name = mname.getText().toString();
        String bod = mbod.getText().toString();
        String email = memail.getText().toString();
        String password = mpassword.getText().toString();

        Log.d(AppUtil.TAG, "FOOTER: Salvando dados... Estanciando controladora");
        ControllerUser controllerUser = new ControllerUser(getActivity().getBaseContext());

        Log.d(AppUtil.TAG, "FOOTER: Salvando dados... Estanciando model");
        User user = new User();

        Log.d(AppUtil.TAG, "FOOTER: Salvando dados... Repassando dados para model");

        user.setNameUser(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setDob(bod);

        Log.d(AppUtil.TAG, "FOOTER: Salvando dados... teste");

        if (controllerUser.create(user)){
            Log.i(AppUtil.TAG, "incluido com sucesso");
                    } else{
            Log.e(AppUtil.TAG, "erro ao incluir");
        }



    }
}


