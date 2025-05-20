package utp.edu.pe.bfc.services;

import utp.edu.pe.bfc.models.Cliente;
import utp.edu.pe.bfc.models.Admin;
import utp.edu.pe.bfc.models.enums.Tipo;
import utp.edu.pe.bfc.utils.AppConfig;
import utp.edu.pe.bfc.utils.DataAccess;

import javax.naming.NamingException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Auth {
    public void close() throws SQLException {
    }

    public static String md5(String data) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            return byteArrayToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }

    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (byte b : a) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public Admin isValidAdmin(String email, String password) throws SQLException, IOException {
        String query = "SELECT * FROM admin WHERE correo = ? AND contrasena = ?";
        Admin admin = new Admin();
        try (Connection cnn = DataAccess.getConnection(AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, md5(password));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    admin.setAdminId(rs.getInt("adminId"));
                    admin.setNombreCompleto(rs.getString("nombreCompleto"));
                    admin.setCorreo(rs.getString("correo"));
                    admin.setContrasena(rs.getString("contrasena"));
                    admin.setTipo(Tipo.valueOf(rs.getString("tipo")));
                } else {
                    throw new SQLException("No se pudo encontrar el usuario en la base de datos.");
                }
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return admin;
    }

    public Cliente isValidCliente(String email, String password) throws SQLException, IOException {
        String query = "SELECT * FROM cliente WHERE correo = ? AND contrasena = ?";
        Cliente cliente = new Cliente();
        try (Connection cnn = DataAccess.getConnection(AppConfig.getDatasource());
             PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, md5(password));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente.setClienteId(rs.getInt("clienteId"));
                    cliente.setNombreCompleto(rs.getString("nombreCompleto"));
                    cliente.setNumeroTelefono(rs.getString("numeroTelefono"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setContrasena(rs.getString("contrasena"));
                } else {
                    throw new SQLException("No se pudo encontrar el cliente en la base de datos.");
                }
            }
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}