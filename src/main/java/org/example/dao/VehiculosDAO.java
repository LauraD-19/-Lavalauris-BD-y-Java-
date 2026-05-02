package org.example.dao;

import org.example.Model.Vehiculos;

import java.util.List;

public interface VehiculosDAO {
    void crear(Vehiculos vehiculos);
    Vehiculos leer(int vehiculo_id);
    void actualizar(Vehiculos vehiculos);
    void eliminar(int vehiculo_id);
    List<Vehiculos> listar();
}
