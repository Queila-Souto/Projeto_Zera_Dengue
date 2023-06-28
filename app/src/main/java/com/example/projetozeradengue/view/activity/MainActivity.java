package com.example.projetozeradengue.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.view.fragments.Header;
import com.example.projetozeradengue.view.fragments.MainFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout1, new Header()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout2, new MainFragment()).commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        //verifica se o google play service está atualizado
        int errorCode = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        switch (errorCode){
            case ConnectionResult
                    .SERVICE_MISSING:
            case ConnectionResult
                    .SERVICE_VERSION_UPDATE_REQUIRED:
            case ConnectionResult.SERVICE_DISABLED:
                Log.d("Teste", "show dialog");
                //exibe o dialogo padrao da Google para que o usuário ative o GooglePlay Service
                GoogleApiAvailability.getInstance().getErrorDialog(this, errorCode, 0, new DialogInterface.OnCancelListener() {
                    @Override
                    //esse método determina o comportamento do app ao clicar fora do diálogo
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                });

                break;
            case ConnectionResult.SUCCESS:
                Log.d("Teste", "Google Play Services up-to-date");
                break;

        }
    }
}