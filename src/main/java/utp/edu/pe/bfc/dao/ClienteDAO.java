package utp.edu.pe.bfc.dao;

import utp.edu.pe.bfc.models.Cliente;


import utp.edu.pe.bfc.utils.AppConfig;
import utp.edu.pe.bfc.utils.DataAccess;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private final Connection cnn;

    public ClienteDAO() throws SQLException, NamingException {
        this.cnn = DataAccess.getConnection(AppConfig.getDatasource());

    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccess.closeConnection(this.cnn);
    }

    public void createCliente(Cliente cliente) throws SQLException {
        String query = "INSERT INTO cliente (nombreCompleto, numeroTelefono, correo, contrasena) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombreCompleto());
            ps.setString(2, cliente.getNumeroTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getContrasena());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Cliente getCliente(int clienteId) throws SQLException {
        String query = "SELECT * FROM cliente WHERE clienteId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setClienteId(rs.getInt("clienteId"));
                    cliente.setNombreCompleto(rs.getString("nombreCompleto"));
                    cliente.setNumeroTelefono(rs.getString("numeroTelefono"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setContrasena(rs.getString("contrasena"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return null;
    }

    public void updateCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE cliente SET nombreCompleto = ?, numeroTelefono = ?, correo = ?, contrasena = ? WHERE clienteId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, cliente.getNombreCompleto());
            ps.setString(2, cliente.getNumeroTelefono());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getContrasena());
            ps.setInt(5, cliente.getClienteId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void deleteCliente(int clienteId) throws SQLException {
        String query = "DELETE FROM cliente WHERE clienteId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, clienteId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Cliente> getAllAClientes(){
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setClienteId(rs.getInt("clienteId"));
                cliente.setNombreCompleto(rs.getString("nombreCompleto"));
                cliente.setNumeroTelefono(rs.getString("numeroTelefono"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setContrasena(rs.getString("contrasena"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clientes;
    }


}
