package com.example.projetozeradengue.controller;

import java.util.List;

public interface ICrud<T> {
    public boolean create(T obj);
    public List<T> retrieve();
    public boolean update(T obj);
    public boolean delete(int id);

}
/*A interface contem metodos que serão obrigatóriamente implementados
nas classes a quem ela for atribuída. Nesse caso, usaremos a interface
para implementar o crud do banco de dados, por tanto, ela será atribuída
as nossas classes models.
C - CREATE - CRIAR / ADICIONAR;
R - RETRIEVE - RECUPERAR;
U - UPDATE - ATUALIZAR;
D - DELETE - EXCLUIR;

*/