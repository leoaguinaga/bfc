package utp.edu.pe.bfc.dao;

import utp.edu.pe.bfc.models.Proveedor;
import utp.edu.pe.bfc.utils.AppConfig;
import utp.edu.pe.bfc.utils.DataAccess;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private final Connection cnn;

    public ProveedorDAO() throws SQLException, NamingException {
        this.cnn = DataAccess.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccess.closeConnection(this.cnn);
    }

    public void createProveedor(Proveedor proveedor) throws SQLException {
        String query = "INSERT INTO proveedor (nombreEmpresa, ruc, direccion, telefono, correo, delegado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, proveedor.getNombreEmpresa());
            ps.setString(2, proveedor.getRuc());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getCorreo());
            ps.setString(6, proveedor.getDelegado());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Proveedor getProveedor(int proveedorId) throws SQLException {
        String query = "SELECT * FROM proveedor WHERE proveedorId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, proveedorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setProveedorId(rs.getInt("proveedorId"));
                    proveedor.setNombreEmpresa(rs.getString("nombreEmpresa"));
                    proveedor.setRuc(rs.getString("ruc"));
                    proveedor.setDireccion(rs.getString("direccion"));
                    proveedor.setTelefono(rs.getString("telefono"));
                    proveedor.setCorreo(rs.getString("correo"));
                    proveedor.setDelegado(rs.getString("delegado"));
                    return proveedor;
                }
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return null;
    }

    public void updateProveedor(Proveedor proveedor) throws SQLException {
        String query = "UPDATE proveedor SET nombreEmpresa = ?, ruc = ?, direccion = ?, telefono = ?, correo = ?, delegado = ? WHERE proveedorId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, proveedor.getNombreEmpresa());
            ps.setString(2, proveedor.getRuc());
            ps.setString(3, proveedor.getDireccion());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getCorreo());
            ps.setString(6, proveedor.getDelegado());
            ps.setInt(7, proveedor.getProveedorId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void deleteProveedor(int proveedorId) throws SQLException {
        String query = "DELETE FROM proveedor WHERE proveedorId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, proveedorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Proveedor> getAllProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String query = "SELECT * FROM proveedor";
        try (PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setProveedorId(rs.getInt("proveedorId"));
                proveedor.setNombreEmpresa(rs.getString("nombreEmpresa"));
                proveedor.setRuc(rs.getString("ruc"));
                proveedor.setDireccion(rs.getString("direccion"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setCorreo(rs.getString("correo"));
                proveedor.setDescripcion(rs.getString("descripcion"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return proveedores;
    }
}
