package org.example.dao;

import org.example.Model.Servicios;

import java.util.List;

public interface ServiciosDAO {
    void crear(Servicios servicios);
    Servicios leer(int servicio_id);
    void actualizar(Servicios servicios);
    void eliminar(int servicio_id);
    List<Servicios> listar();
}
