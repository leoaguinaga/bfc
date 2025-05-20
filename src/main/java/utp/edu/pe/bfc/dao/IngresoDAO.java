package utp.edu.pe.bfc.dao;

import utp.edu.pe.bfc.models.Ingreso;
import utp.edu.pe.bfc.models.Producto;
import utp.edu.pe.bfc.models.Proveedor;
import utp.edu.pe.bfc.utils.AppConfig;
import utp.edu.pe.bfc.utils.DataAccess;

import javax.naming.NamingException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IngresoDAO {
    private final Connection cnn;

    public IngresoDAO() throws SQLException, NamingException {
        this.cnn = DataAccess.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccess.closeConnection(this.cnn);
    }

    public void createIngreso(Ingreso ingreso) throws SQLException {
        String query = "INSERT INTO ingreso (productoId, proveedorId, cantidad, fecha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, ingreso.getProducto().getProductoId());
            ps.setInt(2, ingreso.getProveedor().getProveedorId());
            ps.setInt(3, ingreso.getCantidad());
            ps.setDate(4, Date.valueOf(ingreso.getFecha()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Ingreso getIngreso(int ingresoId) throws SQLException {
        String query = "SELECT * FROM ingreso WHERE ingresoId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, ingresoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProductoDAO productoDAO = new ProductoDAO();
                    ProveedorDAO proveedorDAO = new ProveedorDAO();

                    Producto producto = productoDAO.getProducto(rs.getInt("productoId"));
                    Proveedor proveedor = proveedorDAO.getProveedor(rs.getInt("proveedorId"));

                    return new Ingreso(
                            rs.getInt("proveedorId"),
                            rs.getInt("cantidad"),
                            producto,
                            proveedor,
                            rs.getDate("fecha").toLocalDate()
                    );
                }
            }
        } catch (SQLException | NamingException e) {
            throw new SQLException(e);
        }
        return null;
    }

    public void updateIngreso(Ingreso ingreso) throws SQLException {
        String query = "UPDATE ingreso SET productoId = ?, proveedorId = ?, cantidad = ?, fecha = ? WHERE ingresoId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, ingreso.getProducto().getProductoId());
            ps.setInt(2, ingreso.getProveedor().getProveedorId());
            ps.setInt(3, ingreso.getCantidad());
            ps.setDate(4, Date.valueOf(ingreso.getFecha()));
            ps.setInt(5, ingreso.getIngresoId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void deleteIngreso(int ingresoId) throws SQLException {
        String query = "DELETE FROM ingreso WHERE ingresoId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, ingresoId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Ingreso> getAllIngresos() {
        List<Ingreso> lista = new ArrayList<>();
        String query = "SELECT * FROM ingreso";
        try (PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            ProductoDAO productoDAO = new ProductoDAO();
            ProveedorDAO proveedorDAO = new ProveedorDAO();

            while (rs.next()) {
                Producto producto = productoDAO.getProducto(rs.getInt("productoId"));
                Proveedor proveedor = proveedorDAO.getProveedor(rs.getInt("proveedorId"));

                Ingreso ingreso = new Ingreso(
                        rs.getInt("proveedorId"),
                        rs.getInt("cantidad"),
                        producto,
                        proveedor,
                        rs.getDate("fecha").toLocalDate()
                );
                ingreso.setIngresoId(rs.getInt("ingresoId"));
                ingreso.setProductoId(rs.getInt("productoId"));
                lista.add(ingreso);
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}
