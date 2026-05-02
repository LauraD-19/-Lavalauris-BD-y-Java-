package org.example.dao;

import org.example.Model.Clientes;
import org.example.Model.Servicios;

import java.sql.*;
import java.util.*;

public class ServiciosDAOimpl implements ServiciosDAO {

    private final Connection connection;
    public ServiciosDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Servicios servicios) {
        String sql = "INSERT INTO Servicios "+"(nombre, precio)"+"VALUES (?,?)";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setString(1, servicios.getNombre());
            statement.setDouble(2, servicios.getPrecio());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Servicios leer(int servicio_id) {
        String sql = "SELECT *FROM Servicios WHERE servicio_id =?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, servicio_id);

            ResultSet resultSet =statement.executeQuery();

            if(resultSet.next()){
                Servicios servicios = new Servicios(
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio")

                );
                servicios.setServicio_id(resultSet.getInt("servicio_id"));
                return servicios;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(Servicios servicios) {
        String sql="UPDATE Servicios SET nombre=?, precio=? WHERE servicio_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, servicios.getNombre());
            statement.setDouble(2, servicios.getPrecio());
            statement.setInt(3, servicios.getServicio_id());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void eliminar(int servicio_id) {
        String sql ="DELETE FROM Servicios WHERE servicio_id=?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, servicio_id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Servicios> listar() {
        List<Servicios> servicios = new ArrayList<>();
        String sql = "SELECT *FROM Servicios";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Servicios servicios1 = new Servicios(//para que se pueda mostrar el id
                        resultSet.getString("nombre"),
                        resultSet.getDouble("precio")
                );

                servicios1.setServicio_id(resultSet.getInt("servicio_id"));
                servicios.add(servicios1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return servicios;
    }
}
