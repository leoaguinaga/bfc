package utp.edu.pe.bfc.dao;

import utp.edu.pe.bfc.models.Iventario;
import utp.edu.pe.bfc.models.Producto;
import utp.edu.pe.bfc.utils.AppConfig;
import utp.edu.pe.bfc.utils.DataAccess;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    private final Connection cnn;

    public InventarioDAO() throws SQLException, NamingException {
        this.cnn = DataAccess.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccess.closeConnection(this.cnn);
    }

    public void createInventario(Iventario inventario) throws SQLException {
        String query = "INSERT INTO inventario (productoId, stock) VALUES (?, ?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, inventario.getProductoId());
            ps.setInt(2, inventario.getStock());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Iventario getInventario(int inventarioId) throws SQLException {
        String query = "SELECT * FROM inventario WHERE inventarioId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, inventarioId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ProductoDAO productoDAO = new ProductoDAO();
                    Producto producto = productoDAO.getProducto(rs.getInt("productoId"));

                    Iventario inventario = new Iventario(
                            rs.getInt("inventarioId"),
                            rs.getInt("productoId"),
                            producto,
                            rs.getInt("stock")
                    );
                    return inventario;
                }
            }
        } catch (SQLException | NamingException e) {
            throw new SQLException(e);
        }
        return null;
    }

    public void updateInventario(Iventario inventario) throws SQLException {
        String query = "UPDATE inventario SET productoId = ?, stock = ? WHERE inventarioId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, inventario.getProductoId());
            ps.setInt(2, inventario.getStock());
            ps.setInt(3, inventario.getInventarioId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void deleteInventario(int inventarioId) throws SQLException {
        String query = "DELETE FROM inventario WHERE inventarioId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, inventarioId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public List<Iventario> getAllInventarios() {
        List<Iventario> lista = new ArrayList<>();
        String query = "SELECT * FROM inventario";
        try (PreparedStatement ps = cnn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            ProductoDAO productoDAO = new ProductoDAO();
            while (rs.next()) {
                Producto producto = productoDAO.getProducto(rs.getInt("productoId"));
                Iventario inventario = new Iventario(
                        rs.getInt("inventarioId"),
                        rs.getInt("productoId"),
                        producto,
                        rs.getInt("stock")
                );
                lista.add(inventario);
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
}