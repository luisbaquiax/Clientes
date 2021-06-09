<%-- 
    Document   : clientes
    Created on : 6 jun. 2021, 22:05:33
    Author     : Luis
<%@page contentType="text/html" pageEncoding="UTF-8"%>
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Clientes</title>
    </head>
    <body>
        <h1>Clientes </h1>
        <ul>
            <c:forEach var="cliente" items="${clientes}">
                <li>${cliente.id}${cliente.nombre} ${cliente.apellido}${cliente.email}${cliente.telefono}${cliente.saldo}</li>
            </c:forEach>
            ${error}
        </ul>
    </body>
</html>
