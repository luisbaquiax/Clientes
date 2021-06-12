/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.controlador;

import baquiax.controlclientes.clienteDB.ClienteDB;
import baquiax.controlclientes.modelo.Cliente;
import java.io.IOException;
import java.util.List;
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
@WebServlet("/controladorCliente")
public class ServletCliente extends HttpServlet {

    private final ClienteDB clienteDB;

    public ServletCliente() {
        this.clienteDB = new ClienteDB();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String parametro = request.getParameter("accion");
        if (parametro != null) {
            switch (parametro) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.accionPorDefecto(request, response);
            }
        } else {
            this.accionPorDefecto(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parametro = request.getParameter("accion");

        if (parametro != null) {
            switch (parametro) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                default:
                    this.accionPorDefecto(request, response);
            }
        } else {
            this.accionPorDefecto(request, response);
        }

    }

    private void accionPorDefecto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDB.listarClientes();
        HttpSession sesion = request.getSession();

        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }

    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("id"));
        this.clienteDB.eliminarCliente(new Cliente(idCliente));
        this.accionPorDefecto(request, response);
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String saldo = request.getParameter("saldo");
        double saldoDouble = 0;

        if ((saldo != null) && (!"".equals(saldo))) {
            saldoDouble = Double.parseDouble(saldo);
        }
        Cliente nuevo = new Cliente(nombre, apellido, email, telefono, saldoDouble);
        this.clienteDB.insertarCliente(nuevo);
        this.accionPorDefecto(request, response);
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("id"));
        Cliente cliente = this.clienteDB.buscarClienteID(idCliente);
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);

    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String saldo = request.getParameter("saldo");
        double saldoDouble = 0;
        if ((saldo != null) && (!"".equals(saldo))) {
            saldoDouble = Double.parseDouble(saldo);
        }
        this.clienteDB.modificarCliente(new Cliente(id, nombre, apellido, email, telefono, saldoDouble));
        this.accionPorDefecto(request, response);
    }
}
