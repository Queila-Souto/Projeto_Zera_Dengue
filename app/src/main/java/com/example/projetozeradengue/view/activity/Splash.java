package com.example.projetozeradengue.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import com.example.projetozeradengue.R;
import com.example.projetozeradengue.datasource.AppDatabase;

public class Splash extends AppCompatActivity {

int time = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CarregamentoParalelo().execute();


    }

    @SuppressLint("StaticFieldLeak")
    private class CarregamentoParalelo extends AsyncTask<Void,Void,Void>{
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This will normally run on a background thread. But to better
         * support testing frameworks, it is recommended that this also tolerates
         * direct execution on the foreground thread, as part of the {@link #execute} call.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(Void... voids) {
        new AppDatabase(getApplicationContext());

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            goToActivity();
        }
    }
    private void goToActivity(){
    Handler handler = new Handler();
    handler.postDelayed(() -> {
        Intent intent = new Intent(Splash.this, LoginActivity.class);
        startActivity(intent);
        finish();
    },time);

    }


}