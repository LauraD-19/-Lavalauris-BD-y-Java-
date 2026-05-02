package org.example.dao;

import org.example.Model.RegistroLavado;

import java.sql.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroLavadoDAOimpl implements RegistroLavadoDAO{
    private final Connection connection;
    public RegistroLavadoDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(RegistroLavado registroLavado) {
        String sql = "INSERT INTO RegistroLavado "+"(id_vehiculo, id_servicio, fechaLavado, horaInicio, horaFin, precioTotal)"+"VALUES (?,?,?,?,?,?)";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, registroLavado.getId_vehiculo());
            statement.setInt(2, registroLavado.getId_servicio());
            statement.setDate(3, java.sql.Date.valueOf(registroLavado.getFechaLavado()));//localdate
            statement.setTime(4, java.sql.Time.valueOf(registroLavado.getHoraInicio()));//localtime
            statement.setTime(5, java.sql.Time.valueOf(registroLavado.getHoraFin()));//localtime
            statement.setDouble(6, registroLavado.getPrecioTotal());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public RegistroLavado leer(int registro_id) {
        String sql = "SELECT *FROM RegistroLavado WHERE registro_id =?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, registro_id);

            ResultSet resultSet =statement.executeQuery();

            if(resultSet.next()){
                LocalDate fechaLavado = resultSet.getDate("fechaLavado").toLocalDate();
                LocalTime horaInicio = resultSet.getTime("horaInicio").toLocalTime();
                LocalTime horaFin = resultSet.getTime("horaFin").toLocalTime();
                RegistroLavado registroLavado = new RegistroLavado(
                        resultSet.getInt("id_vehiculo"),
                        resultSet.getInt("id_servicio"),
                        fechaLavado,
                        horaInicio,
                        horaFin,
                        resultSet.getDouble("precioTotal")

                );
                registroLavado.setRegistro_id(resultSet.getInt("registro_id"));
                return registroLavado;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void actualizar(RegistroLavado registroLavado) {
        String sql="UPDATE RegistroLavado SET id_vehiculo=?, id_servicio=?, fechaLavado=?, horaInicio=?, horaFin=?, precioTotal=? WHERE registro_id=?";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, registroLavado.getId_vehiculo());
            statement.setInt(2, registroLavado.getId_servicio());
            statement.setDate(3, java.sql.Date.valueOf(registroLavado.getFechaLavado()));//localdate
            statement.setTime(4, java.sql.Time.valueOf(registroLavado.getHoraInicio()));//localtime
            statement.setTime(5, java.sql.Time.valueOf(registroLavado.getHoraFin()));//localtime
            statement.setDouble(6, registroLavado.getPrecioTotal());
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void eliminar(int registro_id) {
        String sql ="DELETE FROM RegistroLavado WHERE registro_id=?";

        try(PreparedStatement statement=connection.prepareStatement(sql)){
            statement.setInt(1, registro_id);
            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public List<RegistroLavado> listar() {
        List<RegistroLavado> registroLavados = new ArrayList<>();
        String sql = "SELECT *FROM RegistroLavado";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                LocalDate fechaLavado = resultSet.getDate("fechaLavado").toLocalDate();
                LocalTime horaInicio = resultSet.getTime("horaInicio").toLocalTime();
                LocalTime horaFin = resultSet.getTime("horaFin").toLocalTime();
                RegistroLavado registroLavado = new RegistroLavado(//para que se pueda mostrar el id
                        resultSet.getInt("id_vehiculo"),
                        resultSet.getInt("id_servicio"),
                        fechaLavado,
                        horaInicio,
                        horaFin,
                        resultSet.getDouble("precioTotal")
                );

                registroLavado.setRegistro_id(resultSet.getInt("registro_id"));
                registroLavados.add(registroLavado);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return registroLavados;
    }
}
