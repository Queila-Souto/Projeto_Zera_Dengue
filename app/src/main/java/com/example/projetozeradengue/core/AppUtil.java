package com.example.projetozeradengue.core;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetozeradengue.view.activity.LoginActivity;

// os métodos e variáveis declaradas nessa classe devem ser público e estático.
public class AppUtil extends AppCompatActivity {
public static final String TAG="Projeto Zera Dengue";
public static final int SUCESS_RESULT = 0;
public static final int FAILURE_RESULT = 1;

    public static final String PACKAGE_NAME = "com.google.gms.location.sample.locationaddress";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION.DATA.EXTRA";

    public static final String REALTIME_DATABASE_USERS = "users";
    public static final String REALTIME_DATABASE_DATE_BORN = "dob";
    public static final String REALTIME_DATABASE_EMAIL = "email";
    public static final String REALTIME_DATABASE_ID = "id";
    public static final String REALTIME_DATABASE_NAME = "nameUser";
    public static final String REALTIME_DATABASE_PASSWORD = "password";

}

