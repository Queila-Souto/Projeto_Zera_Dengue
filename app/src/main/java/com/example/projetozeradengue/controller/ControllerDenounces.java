package com.example.projetozeradengue.controller;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.example.projetozeradengue.core.AppUtil;
import com.example.projetozeradengue.datamodel.DenouncesDataModel;
import com.example.projetozeradengue.datasource.AppDatabase;
import com.example.projetozeradengue.model.Denounces;

import java.util.List;

public class ControllerDenounces extends AppDatabase implements ICrud<Denounces> {
    ContentValues contentValues;
    public ControllerDenounces(Context context) {
        super(context);
        Log.d(AppUtil.TAG , "ControllerDenounces: Banco de dados conectado");

    }

    @Override
    public boolean create(Denounces obj) {
        contentValues = new ContentValues();
        contentValues.put(DenouncesDataModel.ID, obj.getId());
        contentValues.put(DenouncesDataModel.USER_ID, obj.getUserId());
        contentValues.put(DenouncesDataModel.STREET, obj.getA_Street());
        contentValues.put(DenouncesDataModel.NUMBER, obj.getA_number());
        contentValues.put(DenouncesDataModel.DISTRICT, obj.getA_district());
        contentValues.put(DenouncesDataModel.COMPLEMENT, obj.getA_complement());
        contentValues.put(DenouncesDataModel.CEP, obj.getCep());
        contentValues.put(DenouncesDataModel.NOTES, obj.getNote());
        contentValues.put(DenouncesDataModel.CITY, obj.getA_city());
        contentValues.put(DenouncesDataModel.STATE, obj.getA_state());

      return insert(DenouncesDataModel.TABLE, contentValues);
    }

    @Override
    public List<Denounces> retrieve() {

        return showDenounce(DenouncesDataModel.TABLE);
    }

    @Override
    public boolean update(Denounces obj) {
        contentValues = new ContentValues();
        contentValues.put(DenouncesDataModel.ID, obj.getId());
        contentValues.put(DenouncesDataModel.USER_ID, obj.getUserId());
        contentValues.put(DenouncesDataModel.STREET, obj.getA_Street());
        contentValues.put(DenouncesDataModel.NUMBER, obj.getA_number());
        contentValues.put(DenouncesDataModel.DISTRICT, obj.getA_district());
        contentValues.put(DenouncesDataModel.COMPLEMENT, obj.getA_complement());
        contentValues.put(DenouncesDataModel.CEP, obj.getCep());
        contentValues.put(DenouncesDataModel.NOTES, obj.getNote());
        contentValues.put(DenouncesDataModel.CITY, obj.getA_city());
        contentValues.put(DenouncesDataModel.STATE, obj.getA_state());

        return update(DenouncesDataModel.TABLE, contentValues);
    }

    @Override
    public boolean delete(String id) {

        return deleteById(DenouncesDataModel.TABLE, id);
    }
}
