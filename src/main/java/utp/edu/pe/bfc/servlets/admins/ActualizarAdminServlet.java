package utp.edu.pe.bfc.servlets.admins;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.bfc.dao.AdminDAO;
import utp.edu.pe.bfc.models.Admin;
import utp.edu.pe.bfc.models.enums.Tipo;

import java.io.IOException;

@WebServlet("/actualizar-admin")
public class ActualizarAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("adminId"));
            String nombreCompleto = req.getParameter("nombreCompleto");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String tipo = req.getParameter("tipo");

            Admin admin = new Admin();
            admin.setAdminId(id);
            admin.setNombreCompleto(nombreCompleto);
            admin.setCorreo(email);
            admin.setContrasena(password);
            admin.setTipo(Tipo.valueOf(tipo));

            AdminDAO adminDAO = new AdminDAO();
            adminDAO.createAdmin(admin);
            adminDAO.close();
            resp.sendRedirect("admins");
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
