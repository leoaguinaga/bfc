<%@ page import="utp.edu.pe.bfc.models.enums.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page import="utp.edu.pe.bfc.models.Producto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Categoria> categorias = Categoria.getCategorias();
  Producto producto = (Producto) request.getAttribute("producto");
%>
<html>
<head>
  <title>Actualizar producto</title>
</head>
<style>
  body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 20px;
  }

  label {
    display: block;
    margin-bottom: 10px;
  }
  input[type="text"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  select {
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  input[type="submit"] {
    width: 100%;
    padding: 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
</style>
<body>
<form action="actualizar-producto" method="post">
  <input type="hidden" name="productoId" value="<%= producto.getProductoId() %>">
  <div>
    <label>Ingresa el nombre del producto:</label>
    <input type="text" name="nombre" value="<%= producto.getNombre() %>">
  </div>
  <div>
    <label>Ingresa el precio del producto:</label>
    <input type="text" name="precio" value="<%= producto.getPrecio() %>">
  </div>
  <div>
    <label>Ingresa la imagen del producto:</label>
    <input type="text" name="imagen" value="<%= producto.getImagen() %>">
  </div>
  <div>
    <label>Ingresa la categoria del producto:</label>
    <select name="categoria">
      <% for (Categoria categoria : categorias) { %>
      <% if (categoria.equals(producto.getCategoria())) { %>
      <option value="<%= categoria %>" selected><%= categoria.getDisplayName() %></option>
      <% } else { %>
      <option value="<%= categoria %>"><%= categoria.getDisplayName() %></option>
      <% } %>
      <% } %>
    </select>
  </div>
  <div>
    <input type="submit" value="Actualizar producto">
  </div>
</form>
</body>
</html>
