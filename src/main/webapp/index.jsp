<%-- 
    Document   : index
    Created on : 25 oct 2026, 14:28:28
    Author     : erika
--%>
<%@page import="com.proyectou3.models.Personaje" %>
<%@page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personajes</title>
        <link rel="stylesheet" href="style.css"/>
    </head>
    <body>

        <header>
            <nav class="navbar">
                <div>
                    <img class="logo" src="resources/Pereira Castor.jpg" alt="Pereira Castor bn cute"/>
                </div>
                <a href="#">Inicio</a> 
                <a href="#">Personajes</a>
            </nav>
        </header>

        <main>

            <section class="personajes-add">
                <form action="${pageContext.request.contextPath}/SvPersonaje" method="POST">
                    <div>
                        <label>Nombre del personaje:</label>
                        <input type="text" name="txtNombre">
                    </div>
                    <div>
                        <label>Daño:</label>
                        <input type="number" name="txtDanio" required>
                    </div>
                    <div>
                        <label>Habilidad:</label>
                        <input type="text" name="txtHabilidad">
                    </div>
                    <div>
                        <label>Costo:</label>
                        <input type="number" name="txtCosto" required>
                    </div>

                    <button type="submit">Enviar Personaje</button>
                </form>
            </section>

            <section class="container">
                <form action="${pageContext.request.contextPath}/SvPersonaje" method="GET">
                    <button type="submit" >Refrescar</button>
                </form>
                <h2>Personajes</h2>
                <div class="personajes-container">

                    <c:if test="${not empty lista}">
                        <c:forEach var="personaje" items="${lista}">
                            <div class="personaje">
                                <img src="resources/Personaje.jpg" alt="Personaje aca bien aca uwi"/>
                                <h3>${personaje.nombre}</h3>
                                <p>Daño: <span>${personaje.danio}</span></p>
                                <p>Habilidad: <span>${personaje.habilidad}</span></p>
                                <p>Costo <span>${personaje.costo}</span></p>
                            </div>
                        </c:forEach>
                    </c:if>

<!--                    <div class="personaje">
                        <img src="resources/Personaje.jpg" alt="Personaje aca bien aca uwi"/>
                        <h3>El Bacaneria</h3>
                        <p>Daño: <span>9999</span></p>
                        <p>Habilidad: <span>Puede atravesar la realidad misma y viajar a dimensiones jamas vistas y jamas posibles de procesar por el ser humano</span></p>
                        <p>Costo <span>∞</span></p>
                    </div>-->

                </div>


            </section>
        </main>

    </body>
</html>
