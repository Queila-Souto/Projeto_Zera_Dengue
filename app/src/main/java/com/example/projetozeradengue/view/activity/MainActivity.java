package com.example.projetozeradengue.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.view.fragments.Header;
import com.example.projetozeradengue.view.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout1,new Header()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2,new MainFragment()).commit();

    }
}