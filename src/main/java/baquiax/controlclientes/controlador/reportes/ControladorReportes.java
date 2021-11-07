/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.controlador.reportes;

import baquiax.controlclientes.DB.Coneccion;
import baquiax.servicesReportes.ServicioReportes;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author luis
 */
@WebServlet(name = "ControladorReportes", urlPatterns = {"/ControladorReportes"})
public class ControladorReportes extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=reporte.pdf");

        String tarea = request.getParameter("reporte");
        ServicioReportes servicioReportes = null;
        try {
            if (tarea.equals("1")) {
                servicioReportes = new ServicioReportes(Coneccion.getConnection());
                servicioReportes.reporteConJavaBeansClientes(response.getOutputStream());

            } else if (tarea.equals("2")) {
                servicioReportes = new ServicioReportes(Coneccion.getConnection());
                servicioReportes.reporteConConeccion(response.getOutputStream());
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException | JRException e) {
        }

    }

}
