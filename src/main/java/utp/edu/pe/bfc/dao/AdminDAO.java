package utp.edu.pe.bfc.dao;

import utp.edu.pe.bfc.models.Admin;
import utp.edu.pe.bfc.models.enums.Tipo;
import utp.edu.pe.bfc.utils.AppConfig;
import utp.edu.pe.bfc.utils.DataAccess;

import javax.naming.NamingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private final Connection cnn;

    public AdminDAO() throws SQLException, NamingException{
        this.cnn = DataAccess.getConnection(AppConfig.getDatasource());
    }

    public AdminDAO(Connection cnn) {
        this.cnn = cnn;
    }
    public void close() throws SQLException{
        if(this.cnn !=null) DataAccess.closeConnection(this.cnn);
    }

    public void createAdmin(Admin admin) throws SQLException{
        String query= "INSERT INTO admin(nombreCompleto, correo, contrasena,tipo)VALUES(?,?,?,?)";
        try (PreparedStatement ps= cnn.prepareStatement(query)){
            ps.setString(1,admin.getNombreCompleto());
            ps.setString(2,admin.getCorreo());
            ps.setString(3,admin.getContrasena());
            ps.setString(4,admin.getTipo().toString());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public Admin getAdmin(int adminId) throws SQLException{
        String query="SELECT * FROM admin WHERE adminID= ?";
        try (PreparedStatement ps= cnn.prepareStatement(query)){
            ps.setInt(1,adminId);
            try(ResultSet rs= ps.executeQuery()){
                if(rs.next()){
                    Admin admin = new Admin();
                    admin.setAdminId(rs.getInt("adminId"));
                    admin.setNombreCompleto(rs.getString("nombreCompleto"));
                    admin.setCorreo(rs.getString("correo"));
                    admin.setContrasena(rs.getString("contrasena"));
                    admin.setTipo(Tipo.valueOf(rs.getString("tipo")));
                    return admin;
                }
            }

        }catch (SQLException e){
            throw new SQLException(e);
        }
        return null;
    }

    public void updateAdmin(Admin admin) throws SQLException{
        String query= "UPDATE admin SET nombreCompleto=?, correo= ?,contrasena=?, tipo=? WHERE adminId=?";
        try(PreparedStatement ps=cnn.prepareStatement(query)){
            ps.setString(1,admin.getNombreCompleto());
            ps.setString(2,admin.getCorreo());
            ps.setString(3,admin.getContrasena());
            ps.setString(4,admin.getTipo().toString());
            ps.setInt(5,admin.getAdminId());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }


    }

    public void deleteAdmin(int adminId) throws SQLException{
        String query="DELETE FROM admin WHERE adminId= ?";
        try(PreparedStatement ps= cnn.prepareStatement(query)){
            ps.setInt(1,adminId);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new SQLException(e);
        }

    }

    public List<Admin> getAllAAdmins(){
        List<Admin> admins = new ArrayList<>();
        String query="SELECT * FROM admin";
        try (PreparedStatement ps= cnn.prepareStatement(query);
        ResultSet rs= ps.executeQuery()){
            while (rs.next()){
                Admin admin = new Admin();
                admin.setAdminId(rs.getInt("adminID"));
                admin.setNombreCompleto(rs.getString("nombreCompleto"));
                admin.setCorreo(rs.getString("correo"));
                admin.setContrasena(rs.getString("contraseña"));
                admin.setTipo(Tipo.valueOf(rs.getString("tipo")));
                admins.add(admin);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
         return admins;

    }

}
