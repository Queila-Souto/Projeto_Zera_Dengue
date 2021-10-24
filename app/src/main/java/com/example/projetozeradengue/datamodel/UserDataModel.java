package com.example.projetozeradengue.datamodel;
// Modelo objeto relacional: transforma o objeto em tabela

/*
Atributos e métodos necessarios para a classe Data Model em qualquer projeto
1- Atributo nome da tabela
2-Atributo um para um com o nome dos campos
3- Query para criar a tabela no banco de dados
4- Método para gerar o Script para criar tabela;
5-Queries de consultas gerais

 */

import android.util.Log;

import com.example.projetozeradengue.core.AppUtil;

public class UserDataModel {

    //PASSO 1 - NOME DA TABELA
    public static final String TABLE = "Usuarios";

    //PASSO 2 - ATRIBUINDO OS CAMPOS(COLUNAS)
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "senha";
    public static final String DATEOFBORN = "dn";

//PASO 3 - CRIANDO A QUERY
    public static String queryCreateTable="";
//PASSO 4 - MÉTODO PARA GERAR O SCRIPT PARA CRIAR TABELAS
    public static String createTable(){
        Log.d(AppUtil.TAG , "User Data Model: Criando tabela de usuarios");

        queryCreateTable = "CREATE TABLE "+TABLE+" ("+ID+" integer primary key , "+NOME+" text, "+EMAIL+" text, "+PASSWORD+" integer, "+DATEOFBORN+" integer )";


        return  queryCreateTable;
    }


}

//PASSO 5 - QUERIES DE CONSULTA


