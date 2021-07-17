package com.example.projetozeradengue.retrofit_APIS.service;

import com.example.projetozeradengue.retrofit_APIS.model.CEP;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by thiagocury on 21/11/17.
 */

public interface APIRetrofitService {

    String BASE_URL = "https://viacep.com.br/ws/";

    /* Retorna uma lista de objetos CEP */
    @GET("{estado}/{cidade}/{endereco}/json/")
    Call<List<CEP>> getCEPByCidadeEstadoEndereco(@Path("estado") String estado,
                                                 @Path("cidade") String cidade,
                                                 @Path("endereco") String endereco);

    /* Retorna apenas um objeto CEP */
    @GET("{CEP}/json/")
    Call<CEP> getEnderecoByCEP(@Path("CEP") String CEP);

}