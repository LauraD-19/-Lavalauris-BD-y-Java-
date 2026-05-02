package org.example.dao;

import org.example.Model.Clientes;
import org.example.Model.Vehiculos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculosDAOimpl implements VehiculosDAO {
    //conecction
    private final Connection connection;
    public VehiculosDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Vehiculos vehiculos) {
        String sql = "INSERT INTO Vehiculos "+"(id_cliente, marca, modelo, placa, color, tipo)"+"VALUES (?,?,?,?,?,?)";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, vehiculos.getId_cliente());
            statement.setString(2, vehiculos.getMarca());
            statement.setString(3, vehiculos.getModelo());
            statement.setString(4, vehiculos.getPlaca());
            statement.setString(5, vehiculos.getColor());
            statement.setString(5, vehiculos.getTipo());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Vehiculos leer(int vehiculo_id) {
        String sql = "SELECT *FROM Vehiculos WHERE vehiculo_id =?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, vehiculo_id);

            ResultSet resultSet =statement.executeQuery();

            if(resultSet.next()){
                Vehiculos vehiculos = new Vehiculos(
                        resultSet.getInt("id_cliente"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getString("placa"),
                        resultSet.getString("color"),
                        resultSet.getString("tipo")

                );
                vehiculos.setVehiculo_id(resultSet.getInt("vehiculo_id"));
                return vehiculos;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void actualizar(Vehiculos vehiculos) {
        String sql="UPDATE Vehiculos SET id_cliente=?, marca=?, modelo=?, placa=?, color=?, tipo=? WHERE vehiculo_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, vehiculos.getId_cliente());
            statement.setString(2, vehiculos.getMarca());
            statement.setString(3, vehiculos.getModelo());
            statement.setString(4, vehiculos.getPlaca());
            statement.setString(5, vehiculos.getColor());
            statement.setString(5, vehiculos.getTipo());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void eliminar(int vehiculo_id) {
        String sql ="DELETE FROM Vehiculos WHERE vehiculo_id=?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, vehiculo_id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<Vehiculos> listar() {
        List<Vehiculos> vehiculos = new ArrayList<>();
        String sql = "SELECT *FROM Vehiculos";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Vehiculos vehiculos1 = new Vehiculos(//para que se pueda mostrar el id
                        resultSet.getInt("id_cliente"),
                        resultSet.getString("marca"),
                        resultSet.getString("modelo"),
                        resultSet.getString("placa"),
                        resultSet.getString("color"),
                        resultSet.getString("tipo")
                );

                vehiculos1.setVehiculo_id(resultSet.getInt("id_cliente"));
                vehiculos.add(vehiculos1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return vehiculos;
    }
}
