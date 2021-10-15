package com.example.projetozeradengue.datamodel;
// Modelo objeto relacional: transforma o objeto em tabela

import android.util.Log;

import com.example.projetozeradengue.core.AppUtil;

/*
Atributos e métodos necessarios para a classe Data Model em qualquer projeto
1- Atributo nome da tabela
2-Atributo um para um com o nome dos campos
3- Query para criar a tabela no banco de dados
4- Método para gerar o Script para criar tabela;
5-Queries de consultas gerais

 */
public class DenouncesDataModel {

    //PASSO 1 - NOME DA TABELA
    public static final String TABLE = "Denuncias";
    public static final String REFERENCES_Table =  UserDataModel.TABLE;
    public static final String REFERENCES_Column= UserDataModel.ID;

    //PASSO 2 - ATRIBUINDO OS CAMPOS(COLUNAS)
    public static final String ID = "id";
    public static final String USER_ID = "cod_usuario";
    public static final String FOREIGN_KEY="fk_userid";
    public static final String STREET = "rua";
    public static final String NUMBER = "numero";
    public static final String DISTRICT = "bairro";
    public static final String CITY = "cidade";
    public static final String STATE = "estado";
    public static final String COMPLEMENT = "complemento";
    public static final String CEP = "CEP";
    public static final String NOTES = "Obs";

    //PASO 3 - CRIANDO A QUERY
    public static String queryCreateTable="";
    //PASSO 4 - MÉTODO PARA GERAR O SCRIPT PARA CRIAR TABELAS
    public static String createTable(){
        Log.d(AppUtil.TAG , "Denounces Data Model: Criando tabela de denúncias");

        queryCreateTable = "CREATE TABLE "+TABLE+" ("+ID+" integer primary key autoincrement, "+USER_ID+" integer, "+STREET+" text, "+NUMBER+" text, "+COMPLEMENT+" text, "+DISTRICT+" text, "+CITY+ " text, "+ STATE+" text, "+CEP+" integer, "+NOTES+" text, "+ "CONSTRAINT "+FOREIGN_KEY+" FOREIGN KEY ("+USER_ID+") REFERENCES "+ REFERENCES_Table+" ("+REFERENCES_Column+") )";


        return  queryCreateTable;
    }


}

//PASSO 5 - QUERIES DE CONSULTA






