package com.example.projetozeradengue.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.datamodel.DenouncesDataModel;
import com.example.projetozeradengue.datamodel.UserDataModel;
import com.example.projetozeradengue.model.Denounces;
import com.example.projetozeradengue.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    public boolean deleteById(String TableName, int id){
        boolean retorno = false;
        db = getWritableDatabase();

        try {
            retorno = db.delete(TableName, "id = ?", new String[]{String.valueOf(id)})>0;
        } catch (Exception e){
            Log.d(TAG, "Excessão ao deletar dados. "+e.getMessage());
        }

        return retorno;
    }

    public boolean update(String TableName, ContentValues values){
        //implementar regra de negócio
        boolean retorno = false;
        db = getWritableDatabase();

        try {
            retorno = db.update(TableName, values, "id=?", new String[]{String.valueOf(values.get("id"))})>0;
        } catch (Exception e){
            Log.d(TAG, "Excessão ao inserir dados. "+e.getMessage());
        }

        return retorno;
    }

    public List<User> showUser (String nameTable) throws ParseException {
    db = getReadableDatabase();
    List<User> userList = new ArrayList<>();
    String sql = "SELECT * FROM " + nameTable;
        Cursor cursor;
        cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");


                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(UserDataModel.ID)));
                user.setNameUser(cursor.getString(cursor.getColumnIndex(UserDataModel.NOME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(UserDataModel.EMAIL)));
                user.setDob(formato.parse(cursor.getString(cursor.getColumnIndex(UserDataModel.DATEOFBORN))));
                userList.add(user);

            } while (cursor.moveToNext());

        }

        return userList;
    }

    public List<Denounces> showDenounce (String nameTable){
        db = getReadableDatabase();
        List<Denounces> denouncesList = new ArrayList<>();
        String sql = "SELECT * FROM " + nameTable;
        Cursor cursor;
        cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                Denounces denounce = new Denounces();
                denounce.setId(cursor.getInt(cursor.getColumnIndex(DenouncesDataModel.ID)));
                denounce.setUserId(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.USER_ID)));
                denounce.setCep(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.CEP)));
                denounce.setA_Street(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.STREET)));
                denounce.setA_number(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.NUMBER)));
                denounce.setA_complement(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.COMPLEMENT)));
                denounce.setA_district(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.DISTRICT)));
                denounce.setA_city(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.CITY)));
                denounce.setA_state(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.STATE)));
                denounce.setNote(cursor.getString(cursor.getColumnIndex(DenouncesDataModel.NOTES)));
                denouncesList.add(denounce);

            } while (cursor.moveToNext());

        }

        return denouncesList;
    }

}
