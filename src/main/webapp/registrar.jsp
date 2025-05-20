<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
</head>
<body>
    <h2>Registrarse</h2>
    <form action="Registrar" method="post">
        Primer Nombre: <input type="text" name="pri_nombre"><br>
        Segundo Nombre: <input type="text" name="seg_nombre"><br>
        Apellido Paterno: <input type="text" name="pat_apellido"><br>
        Apellido Materno: <input type="text" name="mat_apellido"><br>
        Edad: <input type="text" name="edad"><br>
        Telefono: <input type="text" name="numeroTelefono"><br>
        Correo: <input type="email" name="correo"><br>
        Contraseña: <input type="password" name="contrasena"><br>
        Confirmar Contraseña: <input type="password" name="confirmar"><br><br>
        <input type="submit" value="Registrarse">
    </form>

    <form action="login.jsp">
        <input type="submit" value="Ya tengo cuenta">
    </form>

    <%
        String msg = request.getParameter("msg");
        if ("ok".equals(msg)) {
    %>
        <p style="color: green;">?Registrado exitosamente! Redirigiendo a login...</p>
        <script>
            setTimeout(() => {
                window.location.replace("login.jsp");
            }, 3000);
        </script>
    <% } else if ("error".equals(msg)) { %>
        <p style="color: red;">Las contraseñas no coinciden.</p>
    <% } %>
</body>
</html>


