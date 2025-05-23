package utp.edu.pe.bfc.servlets.usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.bfc.dao.UsuarioDAO;
import utp.edu.pe.bfc.models.Usuario;
import utp.edu.pe.bfc.models.enums.Tipo;

import java.io.IOException;

@WebServlet("/crear-usuario")
public class CrearUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nombreCompleto = req.getParameter("nombreCompleto");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String tipo = req.getParameter("tipo");
            String telefono = req.getParameter("telefono");

            Usuario usuario = new Usuario();
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setCorreo(email);
            usuario.setContrasena(password);
            usuario.setTipo(Tipo.valueOf(tipo));
            usuario.setTelefono(telefono);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.createUsuario(usuario);
            usuarioDAO.close();

            if (usuario.getTipo() == Tipo.CLIENTE) {
                req.getRequestDispatcher("admins").forward(req, resp);
            } else {
                req.getRequestDispatcher("clientes").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
