package com.example.projetozeradengue.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.datamodel.UserDataModel;
import com.example.projetozeradengue.datasource.AppDatabase;
import com.example.projetozeradengue.model.User;

import java.text.ParseException;
import java.util.List;

//A Classe ControllerUser extende AppDatabase, pois todas a comunicação com o BD será deita aqui,
// dentro da camada controller.
// Implementa a interface  Icrud para controlar as ações do bd(CRUD)
public class ControllerUser extends AppDatabase implements  ICrud<User>{

    ContentValues contentValues;
    //ContentValues passa uma key e um valor

    public ControllerUser(Context context) {

        super(context);
        Log.d(AppUtil.TAG , "ControllerUser: Banco de dados conectado");

    }
    @Override
    public boolean create(User obj) {
        //Novo registro em SQL = INSERT INTO TABLE ( ... ) VALUES ( ****)

        contentValues = new ContentValues();
        contentValues.put(UserDataModel.NOME, obj.getNameUser());
        contentValues.put(UserDataModel.DATEOFBORN, String.valueOf(obj.getDob()));
        contentValues.put(UserDataModel.EMAIL, obj.getEmail());
        contentValues.put(UserDataModel.PASSWORD, obj.getPassword());
    return insert(UserDataModel.TABLE, contentValues);
    }

    @Override
    public List<User> retrieve() throws ParseException {
    return showUser(UserDataModel.TABLE);
    }

    @Override
    public boolean update(User obj) {
        //Método UPDATE do SQL: UPDATE FROM TABELA WHERE ID = xxx
        //Lembrando sempre de respeitar o primary Key (ID)
        contentValues = new ContentValues();
        contentValues.put(UserDataModel.ID, obj.getId());
        contentValues.put(UserDataModel.NOME, obj.getNameUser());
        contentValues.put(UserDataModel.EMAIL, obj.getEmail());
        contentValues.put(UserDataModel.PASSWORD, obj.getPassword());
        contentValues.put(UserDataModel.DATEOFBORN, obj.getDob().toString());

        return update(UserDataModel.TABLE, contentValues);

    }

    @Override
    public boolean delete(String id) {
        //Método DELETE do SQL: DELETE FROM TABELA WHERE ID = xxx
        //Lembrando sempre de respeitar o primary Key (id)

        return deleteById(UserDataModel.TABLE, id);
    }




}
