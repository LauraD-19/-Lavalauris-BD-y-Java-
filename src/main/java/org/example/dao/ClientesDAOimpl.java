package org.example.dao;

import org.example.Model.Clientes;

import java.sql.*;
import java.util.*;

public class ClientesDAOimpl implements ClientesDAO {
    //conecction
    private final Connection connection;
    public ClientesDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Clientes clientes) {
        String sql = "INSERT INTO Clientes "+"(nombre, apellido, telefono, email, direccion)"+"VALUES (?,?,?,?,?)";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setString(1, clientes.getNombre());
            statement.setString(2, clientes.getApellido());
            statement.setString(3, clientes.getTelefono());
            statement.setString(4, clientes.getEmail());
            statement.setString(5, clientes.getDireccion());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Clientes leer(int cliente_id) {
        String sql = "SELECT *FROM Clientes WHERE cliente_id =?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, cliente_id);

            ResultSet resultSet =statement.executeQuery();

            if(resultSet.next()){
                Clientes cliente = new Clientes(
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getString("direccion")

                );
                cliente.setCliente_id(resultSet.getInt("cliente_id"));
                return cliente;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void actualizar(Clientes clientes) {
        String sql="UPDATE Clientes SET nombre=?, apellido=?, telefono=?, email=?, direccion=? WHERE cliente_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, clientes.getNombre());
            statement.setString(2, clientes.getApellido());
            statement.setString(3, clientes.getTelefono());
            statement.setString(4, clientes.getEmail());
            statement.setString(5, clientes.getDireccion());
            statement.setInt(6, clientes.getCliente_id());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void eliminar(int cliente_id) {
        String sql ="DELETE FROM Clientes WHERE cliente_id=?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, cliente_id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Clientes> listar() {
        List<Clientes> clientes = new ArrayList<>();
        String sql = "SELECT *FROM Clientes";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Clientes cliente = new Clientes(//para que se pueda mostrar el id
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido"),
                        resultSet.getString("telefono"),
                        resultSet.getString("email"),
                        resultSet.getString("direccion")
                );

                cliente.setCliente_id(resultSet.getInt("cliente_id"));
                clientes.add(cliente);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return clientes;
    }
}
