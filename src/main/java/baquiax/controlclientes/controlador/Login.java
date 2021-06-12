/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.controlador;

import baquiax.controlclientes.clienteDB.AdministradorDB;
import baquiax.controlclientes.modelo.Administrador;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luis
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

    private final AdministradorDB adminstradorDB;

    public Login() {
        this.adminstradorDB = new AdministradorDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String usuario = request.getParameter("usuario");
            String contra = request.getParameter("contra");
            Administrador admin = null;
            try {
                admin = this.adminstradorDB.buscarAdministrador(contra, usuario);
            } catch (Exception e) {
                String error = "Contraseña o usuario incorrecto";
                request.setAttribute("error", error);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            if (admin != null) {
                System.out.println(admin.toString());
                HttpSession sesion = request.getSession();
                sesion.setAttribute("admin", admin);
                response.sendRedirect("controladorCliente");
            } else {
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (IOException | ServletException e) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}
