package com.proyectou3.servlets;

import com.proyectou3.models.Personaje;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author erika
 */
@WebServlet(name = "SvPersonaje", urlPatterns = {"/SvPersonaje"})
public class SvPersonaje extends HttpServlet {

    List<Personaje> listaPersonajes = new ArrayList<Personaje>();
    private final String url = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databaseName=appsWeb;encrypt=true;trustServerCertificate=true";
    private final String user = "JavaUser";
    private final String password = "Erik04122312";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvPersonaje</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvPersonaje at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("El driver de base de datos no se encontro", e);
        }
        try (Connection con = (Connection) DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM Personajes";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            response.setContentType("text/html;charset=UTF-8");
            listaPersonajes.clear();
            while (rs.next()) {
                Personaje a = new Personaje(rs.getInt("id"), rs.getString("nombre"), rs.getInt("daño"), rs.getString("habilidad"), rs.getInt("costo"));
                listaPersonajes.add(a);
            }

            request.setAttribute("lista", listaPersonajes);
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Error al conectar con la base de datos");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("El driver de base de datos no se encontro", e);
        }

        String nombre = request.getParameter("txtNombre");
        int daño = Integer.parseInt(request.getParameter("txtDanio"));
        String habilidad = request.getParameter("txtHabilidad");
        int costo = Integer.parseInt(request.getParameter("txtCosto"));
        
        try (Connection con = (Connection) DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO Personajes(nombre, daño, habilidad, costo) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, nombre);
            ps.setInt(2, daño);
            ps.setString(3, habilidad);
            ps.setInt(4, costo);
            ps.executeUpdate();
            
            doGet(request, response);
            
        } catch (SQLException e) {
            throw new ServletException("Error al conectar con la base de datos", e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
