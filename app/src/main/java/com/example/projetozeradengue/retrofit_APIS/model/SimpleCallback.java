package com.example.projetozeradengue.retrofit_APIS.model;

public interface SimpleCallback<T> {
    void onResponse (T response);
    void onError (String error);
}
