package com.example.projetozeradengue.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.ControllerUser;
import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.model.User;
import com.example.projetozeradengue.view.fragments.Cadastro;
import com.example.projetozeradengue.view.fragments.Footer;
import com.example.projetozeradengue.view.fragments.Header;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_user);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new Cadastro()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout1,new Header()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout3,new Footer()).commit();
    }


}