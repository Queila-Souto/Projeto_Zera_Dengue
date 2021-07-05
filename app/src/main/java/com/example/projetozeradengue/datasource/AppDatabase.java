package com.example.projetozeradengue.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.datamodel.DenouncesDataModel;
import com.example.projetozeradengue.datamodel.UserDataModel;

import static com.example.projetozeradengue.core.AppUtil.TAG;

public class AppDatabase extends SQLiteOpenHelper {

    //MINHAS CONSTANTES
    private static final String DB_Name = "ZeraDengueDB.sqlite"; // atribuindo o nome do bd que desejo criar a uma String
    private static final int DB_VERSION = 1; // atribuindo o nome do bd que desejo criar a uma String
    SQLiteDatabase db;

    //construtor criado, solicitando apenas o contexto
    public AppDatabase(@Nullable Context context) {

        super(context, DB_Name, null, DB_VERSION);

        Log.d(TAG, "AppDatabase: Banco de dados Criado");

        db = getWritableDatabase(); // Abrindo o objeto database para escrita
    }


    @Override // MÉTODO PARA CRIAR O BANCO DE DADOS, UTILIZADO UMA UNICA VEZ
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(UserDataModel.createTable());
        Log.d(TAG, "AppDataBase: Tabela Usuarios criada "+ UserDataModel.createTable());

        db.execSQL(DenouncesDataModel.createTable());
        Log.d(TAG, "AppDataBase: Tabela Denuncias criada "+ DenouncesDataModel.createTable());

    }

    @Override // alterações no banco de dados.
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String TableName, ContentValues values){
        //implementar regra de negócio
        boolean retorno = false;
        db = getWritableDatabase();

        try {
            retorno = db.insert(TableName, null, values)>0;
        } catch (Exception e){
            Log.d(TAG, "Excessão ao inserir dados. "+e.getMessage());
        }

        return retorno;
    }

    public boolean delete(){
        boolean retorno = false;
        db = getWritableDatabase();

        return retorno;
    }

}
