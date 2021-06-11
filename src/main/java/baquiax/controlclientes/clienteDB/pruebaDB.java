/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.clienteDB;

import baquiax.controlclientes.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis
 */
public class pruebaDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ClienteDB cdb = new ClienteDB();
        List<Cliente> clientes = cdb.listarClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
        //cdb.insertarCliente(new Cliente("Leonel", "Messi", "email5", "687979", 4546.00));
        Cliente buscado = new Cliente(3);
        System.out.println(cdb.buscarClienteID(1).toString());
      
    }
}
