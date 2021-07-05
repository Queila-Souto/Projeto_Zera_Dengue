package com.example.projetozeradengue.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.ControllerDenounces;
import com.example.projetozeradengue.controller.ControllerUser;
import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.model.User;

public class Splash extends AppCompatActivity {

int time = 5000;
ControllerUser controllerUser;
ControllerDenounces controllerDenounces;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        goToActivity();
        controllerUser = new ControllerUser(getApplicationContext());
        Log.d(AppUtil.TAG , "Splash: Objeto controlador de usuario estanciado/ conectado");
        controllerDenounces = new ControllerDenounces(getApplicationContext());
        Log.d(AppUtil.TAG , "Splash: Objeto controlador de denuncias estanciado/ conectado");

    }

    private void goToActivity(){
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Splash.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    },time);

    }


}