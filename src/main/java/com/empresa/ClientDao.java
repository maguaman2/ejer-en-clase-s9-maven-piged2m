package com.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


    //Traer los clientes de la base de datos

    public ArrayList<Client> listar() {
        ArrayList<Client> clientes= new ArrayList();

        try (Connection connection = connectToDB()) {
            String query = "SELECT * FROM Client"; //sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Client cliente = new Client(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getBoolean("estado")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
            // TODO: handle exception
        }
        return clientes;
    }

    public ArrayList<Client>  buscar(String nombre){
        ArrayList<Client> clientes= new ArrayList();

        try (Connection connection = connectToDB()) {
            String query = "select * from client where nombre like ? "; //sentencia
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            // J  --> J%
            preparedStatement.setString(1, nombre+"%");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Client cliente = new Client(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getBoolean("estado")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
            // TODO: handle exception
        }
        return clientes;


    }
    //Actualizar  un Cliente
    public void actualizarCliente(Client client) {
        try (Connection connection = connectToDB()) {
            PreparedStatement ps = null;
            String query = "update client set nombre = ?, direccion = ?, telefono = ?,email=? where id =?"; //ojo no olvidarsse del where
            ps = connection.prepareStatement(query);
            ps.setString(1, client.getNombre());
            ps.setString(2, client.getDireccion());
            ps.setString(3,  client.getTelefono());
            ps.setString(4,  client.getEmail());
            ps.setInt(5,client.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error:"+e.getMessage() );
            //estos errores guardar ennun archivo
        }
    }

    public void eliminarCliente(int id) {
        try (Connection connection = connectToDB()) {
            PreparedStatement ps = null;
            //Statement statement = connection.createStatement();
            String query = "delete from client  where id = ? ";///ojo con el where
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
