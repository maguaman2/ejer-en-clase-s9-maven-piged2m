package com.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDao extends Conexion {

    public void crearClient(Client client) {
        try (Connection connection = connectToDB()) {
            PreparedStatement ps = null;
            String query = "insert into client (nombre, direccion, telefono, email, estado)" +
                    " values (?,?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, client.getNombre());
            ps.setString(2, client.getDireccion());
            ps.setString(3, client.getTelefono());
            ps.setString(4, client.getEmail());
            ps.setBoolean(5, client.isEstado());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
