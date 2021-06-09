/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.controlador;

import baquiax.controlclientes.clienteDB.ClienteDB;
import baquiax.controlclientes.modelo.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis
 */
@WebServlet("/controladorCliente")
public class ServletCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //List<Cliente> clientes = new ClienteDB().listarClientes();
        ClienteDB cdb = new ClienteDB();
        List<Cliente> clientes = cdb.listarClientes();

//        List<Cliente> clientes = new ArrayList<>();
//        clientes.add(new Cliente(1, "cliente1", "apellido1", "email1", "654897", 4850.00));
//        clientes.add(new Cliente(2, "cliente2", "apellido2", "email2", "654897", 4850.00));
        request.setAttribute("clientes", clientes);
        request.getRequestDispatcher("/jsp/clientes.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

    }

}
