package com.example.projetozeradengue.core;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetozeradengue.R;
import com.example.projetozeradengue.controller.Conexao;

public class Teste extends AppCompatActivity {

    Button button;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SegundoPlano tarefa = new SegundoPlano();
                tarefa.execute("https://viacep.com.br/ws/01001000/json/");
            }
        });
    }


    private class SegundoPlano extends AsyncTask<String,String,String> {

        @Override
    protected String doInBackground(String... strings) {
        String retorno = Conexao.getDados(strings[0]);
        return retorno;
    }

    @Override
    protected void onPostExecute(String s) {
    textView.setText(s);



    }
}

}