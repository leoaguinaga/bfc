package utp.edu.pe.bfc.dao;


import com.mysql.cj.jdbc.result.UpdatableResultSet;
import utp.edu.pe.bfc.models.Admin;
import utp.edu.pe.bfc.models.Pedido;
import utp.edu.pe.bfc.models.enums.EstadoPedido;
import utp.edu.pe.bfc.models.enums.Tipo;
import utp.edu.pe.bfc.utils.AppConfig;
import utp.edu.pe.bfc.utils.DataAccess;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private final Connection cnn;

    public PedidoDAO() throws SQLException, NamingException {
        this.cnn = DataAccess.getConnection(AppConfig.getDatasource());
    }

    public void close() throws SQLException {
        if (this.cnn != null) DataAccess.closeConnection(this.cnn);
    }

    public void createPedido(Pedido pedido) throws SQLException {
        String query = "INSERT INTO pedido (cliente, admin, fecha, direccion, monto, tipo) VALUES (?, ?, ?, ?,?,?)";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, pedido.getCliente().getClienteId());
            ps.setInt(2, pedido.getAdmin().getAdminId());
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(pedido.getFecha()));
            ps.setString(4, pedido.getDireccion());
            ps.setDouble(5, pedido.getMonto());
            ps.setString(6, pedido.getTipo().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Pedido getPedido(int pedidoId) throws SQLException {
        String query = "SELECT * FROM pedido WHERE pedidoId = ?";
        try (PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setInt(1, pedidoId);
            try (ResultSet rs = ps.executeQuery()) {
                ClienteDAO clienteDAO = new ClienteDAO();
                AdminDAO adminDAO = new AdminDAO();
                if (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setPedidoId(rs.getInt("pedidoId"));
                    int clienteId = rs.getInt("cliente");
                    int adminId = rs.getInt("admin");
                    pedido.setCliente(clienteDAO.getCliente(clienteId));
                    pedido.setAdmin(adminDAO.getAdmin(adminId));
                    pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                    pedido.setDireccion(rs.getString("direccion"));
                    pedido.setMonto(rs.getDouble("monto"));
                    pedido.setTipo(EstadoPedido.valueOf(rs.getString("tipo")));
                    return pedido;
                }
            }
        } catch (SQLException | NamingException e) {
            throw new SQLException(e);
        }
        return null;
    }

    public void updatePedido(Pedido pedido) throws SQLException{
        String query="UPDATE pedido SET cliente = ? , admin= ?, fecha=?, direccion=?,monto=?,tipo=?";
        try(PreparedStatement ps= cnn.prepareStatement(query)){
            ps.setInt(1,pedido.getCliente().getClienteId());
            ps.setInt(2,pedido.getAdmin().getAdminId());
            ps.setTimestamp(3, Timestamp.valueOf(pedido.getFecha()));
            ps.setString(4,pedido.getDireccion());
            ps.setDouble(5,pedido.getMonto());
            ps.setString(6,pedido.getTipo().toString());
            ps.setInt(7,pedido.getPedidoId());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new SQLException(e);
        }
    }
    public void deletePedido(int pedidoId) throws SQLException{
        String query ="DELETE FROM pedido WHERE pedidoId = ?";
        try(PreparedStatement ps = cnn.prepareStatement(query)){
            ps.setInt(1,pedidoId);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public List<Pedido> getAllAPedidos(){
        List<Pedido> pedidos = new ArrayList<>();
        String query= "SELECT * FROM pedido";
        try(PreparedStatement ps = cnn.prepareStatement(query);
            ResultSet rs= ps.executeQuery()){
            ClienteDAO clienteDAO = new ClienteDAO();
            AdminDAO adminDAO = new AdminDAO();
                while (rs.next()){
                    Pedido pedido = new Pedido();
                    pedido.setPedidoId(rs.getInt("pedidoId"));
                    pedido.setCliente(clienteDAO.getCliente(rs.getInt("clienteId")));
                    pedido.setAdmin(adminDAO.getAdmin(rs.getInt("adminId")));
                    pedido.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                    pedido.setDireccion(rs.getString("dirección"));
                    pedido.setMonto(rs.getDouble("monto"));
                    pedido.setTipo(EstadoPedido.valueOf(rs.getString("tipo")));
                    pedidos.add(pedido);
                }
        } catch (SQLException e){
                throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

}
