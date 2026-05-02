package org.example.dao;

import org.example.Model.RegistroLavado;

import java.util.List;

public interface RegistroLavadoDAO {
    void crear(RegistroLavado registroLavado);
    RegistroLavado leer(int registro_id);
    void actualizar(RegistroLavado registroLavado);
    void eliminar(int registro_id);
    List<RegistroLavado> listar();
}
