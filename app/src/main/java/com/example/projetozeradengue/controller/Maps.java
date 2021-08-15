package com.example.projetozeradengue.controller;

public class Maps {

    double latitude;
    double longitude;
    boolean gpsAtivo = false;
    

    private void checarGPSAtivo (){
       if (gpsAtivo){
        obterCoordenadas();
    } else {
        latitude = 0.00;
        longitude = 0.00;

    }

}

    private void obterCoordenadas() {

        //tenho permissao para usar gps?
        boolean permissaoAtiva = false;
        if (permissaoAtiva){
            capturarUltimaLocalização();
        } else{
            obterLocalização();
        }



    }

    private void obterLocalização() {
        //TODO

    }

    private void capturarUltimaLocalização() {
        latitude = 23.6666;
        longitude = 23.555;
    }

}
