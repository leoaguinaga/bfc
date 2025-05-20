<%@ page import="utp.edu.pe.bfc.models.enums.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Categoria> categorias = Categoria.getCategorias();
%>
<html>
<head>
  <title>Crear producto</title>
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
<form action="crear-producto" method="post">
  <div>
    <label>Ingresa el nombre del producto:</label>
    <input type="text" name="nombre">
  </div>
  <div>
    <label>Ingresa el precio del producto:</label>
    <input type="text" name="precio">
  </div>
  <div>
    <label>Ingresa la imagen del producto:</label>
    <input type="text" name="imagen">
  </div>
  <div>
    <label>Ingresa la categoria del producto:</label>
    <select name="categoria">
      <% for (Categoria categoria : categorias) { %>
      <option value="<%= categoria %>"><%= categoria.getDisplayName() %></option>
      <% } %>
    </select>
  </div>
  <div>
    <input type="submit" value="Crear producto">
  </div>
</form>
</body>
</html>