/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.clienteDB;

import baquiax.controlclientes.modelo.Administrador;
import baquiax.controlclientes.modelo.Cliente;
import java.sql.Array;
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
        AdministradorDB adb = new AdministradorDB();
        Administrador buscado = adb.buscarAdministrador("bien", "luis");
        if (buscado != null) {
            System.out.println(buscado.toString());
        } else {
            System.out.println("No se encontró el usuario");

        }
    }
}
