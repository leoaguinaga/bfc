<%@ page import="java.util.List" %>
<%@ page import="utp.edu.pe.bfc.models.Producto" %>
<%@ page import="utp.edu.pe.bfc.models.enums.Estado" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Producto> productos = (List<Producto>) request.getAttribute("productos");
%>
<jsp:include page="components/head.jsp"/>
<jsp:include page="components/sidebar.jsp"/>

<div class="container-fluid">
    <div class="card">
        <div class="card-body">
            <div>
                <button type="button" class="btn btn-success m-1" onclick="location.href='crear-producto.jsp'">Agregar producto</button>
            </div>
            <div class="table-responsive">
                <table class="table text-center table-responsive">
                    <thead>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Imagen</th>
                    <th>Categoria</th>
                    <th>Estado</th>
                    <th>Acción</th>
                    </thead>
                    <tbody>
                    <% if (!productos.isEmpty()) { %>
                    <tr>
                        <% for (Producto producto : productos) { %>
                        <td><%= producto.getProductoId() %></td>
                        <td><%= producto.getNombre() %></td>
                        <td><%= producto.getPrecio() %></td>
                        <td><img src="image?img=<%= producto.getImagen() %>" width="100" height="100" style="border-radius: 999px"/></td>
                        <td><%= producto.getCategoria().getDisplayName() %></td>
                        <td><%= producto.getEstado().getDisplayName() %></td>
                        <td>
                            <a
                                    type="button"
                                    class="btn btn-primary m-1"
                                    href="obtener-producto?productoId=<%= producto.getProductoId() %>"
                            >Editar</a>
                            <% if (producto.getEstado().equals(Estado.ACTIVE)) { %>
                            <a
                                    type="button"
                                    class="btn btn-danger m-1"
                                    href="borrar-producto?productoId=<%= producto.getProductoId() %>"
                            >Desactivar</a>
                            <% } else { %>
                            <a
                                    type="button"
                                    class="btn btn-success m-1"
                                    href="activar-producto?productoId=<%= producto.getProductoId() %>"
                            >Activar</a>
                            <% } %>
                        </td>
                        <% } %>
                    </tr>
                    <% } else { %>
                    <tr>
                        <td colspan="6">No se encontraron productos en la base de datos</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp"/>
