package com.example.projetozeradengue.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.core.AppUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText ed_email, ed_password;
    MaterialButton btn_sigin, btn_register,btn_newsenha;
    ProgressBar progressbar;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StartingComponents();
        btn_sigin.setOnClickListener(this);
        btn_newsenha.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
    }

    private void StartingComponents() {
        ed_email = findViewById(R.id.username);
        ed_password = findViewById(R.id.password);
        btn_register = findViewById(R.id.buttonRegisterUser);
        btn_sigin = findViewById(R.id.login);
        progressbar = findViewById(R.id.loading);
        btn_newsenha = findViewById(R.id.newpassword);


    }


    //implementa o metodo onclick verificando qual Id se refere o click(se cadastro ou login)
    @Override
    public void onClick(View v) {
        progressbar.setVisibility(View.VISIBLE);

        switch (v.getId()) {
            case R.id.buttonRegisterUser:
                gotoActivity(NewUserActivity.class);
                break;
            case R.id.login:
                check();
                 break;
            case R.id.newpassword:
                newpassword();
                break;
        }
        progressbar.setVisibility(View.GONE);
    }

    public void gotoActivity(Class go) {
        Intent intent = new Intent(this,go);
        startActivity(intent);
    }

    private void newpassword() {
    String email = ed_email.getText().toString().trim();
    if (email.isEmpty()){
        Toast toast = Toast.makeText(this,"Preencha o campo e-mail e clique novamente em esqueci a senha. Enviaremos a senha para o email cadastrado em nosso banco de dados",Toast.LENGTH_LONG);
        toast.show();}
     else  {
        newpassword_sendemail(); }
    }


    private void check() {
        String email = ed_email.getText().toString().trim(); // A função trim remove os espaços que o usuario tiver cadastrado
        String password = ed_password.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) {
            Toast toast = Toast.makeText(this, "Favor preencher todos os campos para autenticação", Toast.LENGTH_LONG);
            toast.show();
        } else {
            authent(email, password);
        }
    }




        private void authent(String email, String password){

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    //task é a tarefa de autenticação. Verificar se houve suscesso
                    if (task.isSuccessful()) {
                       Toast toast =Toast.makeText(getBaseContext(), "Login efetuado com suscesso", Toast.LENGTH_LONG);
                        toast.show();
                        gotoActivity(MainActivity.class);
                    } else {
                        Toast toast =Toast.makeText(getBaseContext(), "Usuário não cadastrado", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            });


        }

    private void newpassword_sendemail() {
       String email = ed_email.getText().toString().trim();
    auth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void avoid) {
            Toast toast = Toast.makeText(getBaseContext(), "O e-mail para redefinição de senha foi enviado com sucesso", Toast.LENGTH_LONG);
        toast.show();
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull @NotNull Exception e) {
            Toast toast = Toast.makeText(getBaseContext(), "Email inválido", Toast.LENGTH_LONG);
            toast.show();
        }
    });
}
}
