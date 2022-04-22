package com.example.projetozeradengue.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import com.example.projetozeradengue.R;
import com.example.projetozeradengue.datasource.AppDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Splash extends AppCompatActivity {

    int time = 10000;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CarregamentoParalelo().execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class CarregamentoParalelo extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            new AppDatabase(getApplicationContext());
            puxarDados();
            return null;
        }

        private void puxarDados() {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            goToActivity();
        }
    }

    private void goToActivity() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(Splash.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, time);

    }


}