package com.example.projetozeradengue.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.view.fragments.Header;
import com.example.projetozeradengue.view.fragments.MainFragment;
import com.google.android.material.textview.MaterialTextView;

import org.jetbrains.annotations.NotNull;

public class MainActivity2 extends AppCompatActivity {
MaterialTextView tvlatitude;
MaterialTextView tvlongitude;
    double latitude;
    double longitude;
    boolean gpsAtivo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvlatitude = this.findViewById(R.id.txtViewLatitude);
        tvlongitude= this.findViewById(R.id.txtViewLongitude);
        checarGPSAtivo ();
    }

    private void checarGPSAtivo (){
        if (gpsAtivo){
            obterCoordenadas();
        } else {
            latitude = 0.00;
            longitude = 0.00;
            tvlatitude.setText(String.valueOf(latitude));
            tvlongitude.setText(String.valueOf(longitude));
            Toast.makeText(this, "Não foi possível capturar a coordenada", Toast.LENGTH_LONG);

        }

    }

    private void obterCoordenadas() {

        //tenho permissao para usar gps?
        boolean permissaoAtiva = false;
        if (permissaoAtiva){
            capturarUltimaLocalização();
        } else{
            obterLocalização();
        }



    }

    private void obterLocalização() {
        //TODO

    }

    private void capturarUltimaLocalização() {
        latitude = 23.6666;
        longitude = 23.555;
        tvlatitude.setText(String.valueOf(latitude));
        tvlatitude.setText(String.valueOf(longitude));
    }

}