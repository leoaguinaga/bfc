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

@WebServlet("/actualizar-usuario")
public class ActualizarUsuarioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("usuarioId"));
            String nombreCompleto = req.getParameter("nombreCompleto");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String tipo = req.getParameter("tipo");

            Usuario usuario = new Usuario();
            usuario.setUsuarioId(id);
            usuario.setNombreCompleto(nombreCompleto);
            usuario.setCorreo(email);
            usuario.setContrasena(password);
            usuario.setTipo(Tipo.valueOf(tipo));

            UsuarioDAO usuarioDAO= new UsuarioDAO();

            usuarioDAO.createUsuario(usuario);
            usuarioDAO.close();
            resp.sendRedirect("admins");
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
