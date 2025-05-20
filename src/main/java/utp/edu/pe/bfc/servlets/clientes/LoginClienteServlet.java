package utp.edu.pe.bfc.servlets.clientes;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.bfc.models.Admin;
import utp.edu.pe.bfc.models.Cliente;
import utp.edu.pe.bfc.services.Auth;

import java.io.IOException;

@WebServlet("/loginCliente")
public class LoginClienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String correo = req.getParameter("correo");
            String contrasena = req.getParameter("contrasena");

            Auth auth = new Auth();
            Cliente cliente = auth.isValidCliente(correo, contrasena);

            if (cliente != null) {
                req.getSession().setAttribute("cliente", cliente);
                req.getRequestDispatcher("cliente.jsp").forward(req, resp);
            } else {
                req.setAttribute("message", "Invalid email or password");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
