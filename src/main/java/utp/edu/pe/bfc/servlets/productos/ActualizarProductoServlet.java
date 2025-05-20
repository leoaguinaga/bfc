package utp.edu.pe.bfc.servlets.productos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.bfc.dao.ProductoDAO;
import utp.edu.pe.bfc.models.Producto;
import utp.edu.pe.bfc.models.enums.Categoria;

import java.io.IOException;

@WebServlet("/actualizar-producto")
public class ActualizarProductoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productoId = Integer.parseInt(req.getParameter("productoId"));
        String nombre = req.getParameter("nombre");
        float precio = Float.parseFloat(req.getParameter("precio"));
        String imagen = req.getParameter("imagen");
        Categoria categoria = Categoria.valueOf(req.getParameter("categoria"));

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setImagen(imagen);
        producto.setCategoria(categoria);
        producto.setProductoId(productoId);

        try {
            ProductoDAO productoDAO = new ProductoDAO();
            productoDAO.updateProducto(producto);
            productoDAO.close();
            resp.sendRedirect("productos");
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
