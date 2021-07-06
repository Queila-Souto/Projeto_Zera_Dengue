package com.example.projetozeradengue.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.datamodel.UserDataModel;
import com.example.projetozeradengue.datasource.AppDatabase;
import com.example.projetozeradengue.model.User;

import java.util.ArrayList;
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
        contentValues.put(UserDataModel.DATEOFBORN, obj.getDob());
        contentValues.put(UserDataModel.EMAIL, obj.getEmail());
        contentValues.put(UserDataModel.PASSWORD, obj.getPassword());
    return insert(UserDataModel.TABLE, contentValues);
    }

    @Override
    public List<User> retrieve() {
        List<User> lista = new ArrayList<>();
    return lista;
    }

    @Override
    public boolean update(User obj) {
        //Método UPDATE do SQL: UPDATE FROM TABELA WHERE ID = xxx
        //Lembrando sempre de respeitar o primary Key (ID)
        contentValues = new ContentValues();
        contentValues.put(UserDataModel.ID, obj.getId());
        return true;

    }

    @Override
    public boolean delete(int id) {
        //Método DELETE do SQL: DELETE FROM TABELA WHERE ID = xxx
        //Lembrando sempre de respeitar o primary Key (id)

        return deleteById(UserDataModel.TABLE, id);





    }
}
