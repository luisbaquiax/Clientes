/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.servicesReportes;

import baquiax.controlclientes.clienteDB.ClienteDB;
import baquiax.controlclientes.modelo.Cliente;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author luis
 */
public class ServicioReportes {

    private ClienteDB clienteDB;
    private Connection conn;

    public ServicioReportes(Connection conn) {
        this.conn = conn;
        this.clienteDB = new ClienteDB();
    }

    public void reporteConJavaBeansClientes(OutputStream targetStream) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/clientes.jasper");

        List<Cliente> clientes = this.clienteDB.listarClientes();
        System.out.println("mis clientes " + clientes.toArray());
        JRDataSource source = new JRBeanCollectionDataSource(clientes);

        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, source);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }

    public void reporteConConeccion(OutputStream targetStream) throws JRException {
        InputStream compiledReport = getClass().getClassLoader().getResourceAsStream("reportes/clientes2.jasper");
        JasperPrint printer = JasperFillManager.fillReport(compiledReport, null, conn);

        JasperExportManager.exportReportToPdfStream(printer, targetStream);
    }
}
