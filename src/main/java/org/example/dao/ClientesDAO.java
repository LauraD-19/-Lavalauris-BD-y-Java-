package org.example.dao;

import org.example.Model.Clientes;

import java.util.List;

public interface ClientesDAO {
    void crear(Clientes clientes);
    Clientes leer(int cliente_id);
    void actualizar(Clientes clientes);
    void eliminar(int cliente_id);
    List<Clientes> listar();

}
