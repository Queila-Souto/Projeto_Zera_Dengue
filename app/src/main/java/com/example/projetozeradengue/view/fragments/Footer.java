package com.example.projetozeradengue.view.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.ControllerUser;
import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.model.User;
import com.example.projetozeradengue.view.activity.LoginActivity;
import com.example.projetozeradengue.view.activity.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Footer extends Fragment implements View.OnClickListener, Serializable {

    public static TextInputEditText mname, memail, mpassword, mbod;
    MaterialButton mbtn_save, mbtn_back;
    User user = new User();
    FirebaseAuth newUser = FirebaseAuth.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_footer, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        startingComponents();
        mbtn_back.setOnClickListener(this);
        mbtn_save.setOnClickListener(this);


    }

    private void startingComponents() {
        Log.d(AppUtil.TAG, "FOOTER: Iniciando componentes");

        mbtn_save = getActivity().findViewById((R.id.btn_save));
        mbtn_back = getActivity().findViewById((R.id.btn_back));
        mbod = getActivity().findViewById(R.id.ed_date);
        mname = getActivity().findViewById(R.id.ed_name);
        memail = getActivity().findViewById(R.id.ed_email);
        mpassword = getActivity().findViewById(R.id.ed_password);


    }

    private void backActivity() {
        Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }


    private void createNewAuth() {
        String email = memail.getText().toString();
        String password = mpassword.getText().toString();

        newUser.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    try {
                        saveUserDataBase();
                        authenticate(user.getEmail(), user.getPassword());

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    user.save();
                    Toast.makeText(getContext(), "Usuario cadastrado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Erro ao cadastrar usuário", Toast.LENGTH_LONG).show();
                }

            }


        });


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

                Log.d(AppUtil.TAG, "FOOTER: Criando acesso");
                createNewAuth();

                break;


        }
    }

    private void authenticate(String email, String password) {
        newUser.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                //task é a tarefa de autenticação. Verificar se houve suscesso
                if (task.isSuccessful()) {
                    Toast toast = Toast.makeText(getContext(), "Login efetuado com suscesso", Toast.LENGTH_LONG);
                    toast.show();
                    gotoActivity(MainActivity.class);
                } else {
                    Toast toast = Toast.makeText(getContext().getApplicationContext(), "Usuário não cadastrado", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });


    }

    public void gotoActivity(Class go) {
        Intent intent = new Intent(getActivity(), go);
        startActivity(intent);
    }

    private void saveUserDataBase() throws ParseException {

        String name = mname.getText().toString();
        String bod = mbod.getText().toString();
        String email = memail.getText().toString();
        String password = mpassword.getText().toString();

        user.setId(newUser.getCurrentUser().getUid());
        user.setNameUser(name);
        user.setEmail(email);
        user.setDob(bod);
        user.setPassword(password);

        ControllerUser controllerUser = new ControllerUser(getActivity().getBaseContext());

        if (controllerUser.create(user)) {
            Log.i(AppUtil.TAG, "incluido com sucesso");


        } else {
            Log.e(AppUtil.TAG, "erro ao incluir");
        }


    }

    private void errorField() {
        Toast.makeText(getContext(), "Existem campos vazios. Favor preencher todos os campos", Toast.LENGTH_LONG).show();
        Log.e("ZeraDengue", "Erro no campo");
    }
}


