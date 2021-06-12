/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.clienteDB;

import baquiax.controlclientes.DB.Coneccion;
import baquiax.controlclientes.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luis
 */
public class ClienteDB {

    private static final String SELECT = "SELECT ID, nombre, apellido, email, telefono, saldo FROM CLIENTE";
    private static final String SELECT_CLIENTE = "SELECT * FROM CLIENTE";
    private static final String SELECT_BY_ID = "SELECT ID, nombre, apellido, email, telefono, saldo FROM CLIENTE WHERE ID = ? LIMIT 1";
    private static final String INSERTAR = "INSERT INTO CLIENTE(nombre, apellido, email, telefono, saldo) VALUES(?, ?, ?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE CLIENTE SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ? WHERE ID = ?";
    private static final String ELIMINAR = "DELETE FROM CLIENTE WHERE ID = ?";

    public ClienteDB() {
    }

    /**
     * Listado de clientes
     *
     * @return
     */
    public List<Cliente> listarClientes() {
        Connection coneccion = null;
        PreparedStatement preStatement = null;
        ResultSet res = null;
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            coneccion = Coneccion.getConnection();
            preStatement = coneccion.prepareStatement(SELECT_CLIENTE);
            res = preStatement.executeQuery();
            while (res.next()) {
                int id = res.getInt("ID");
                String nombre = res.getString("nombre");
                String apellido = res.getString("apellido");
                String email = res.getString("email");
                String telefono = res.getString("telefono");
                Double saldo = res.getDouble("saldo");
                cliente = new Cliente(id, nombre, apellido, email, telefono, saldo);

                clientes.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(res);
            Coneccion.close(preStatement);
            Coneccion.close(coneccion);
        }

        return clientes;
    }

    /**
     * Seleccionar cliente por ID
     *
     * @return
     */
    public Cliente buscarClienteID(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            conn = Coneccion.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            //rs.absolute(1);//nos posicionamos en el primer registro devuelto

            while (rs.next()) {
                cliente = new Cliente(rs.getInt("ID"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getDouble("saldo"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(rs);
            Coneccion.close(stmt);
            Coneccion.close(conn);
        }
        return cliente;
    }

    /**
     * Insertar cliente en la BD
     *
     * @param cliente
     * @return
     */
    public int insertarCliente(Cliente cliente) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        int registros = 0;
        try {
            coneccion = Coneccion.getConnection();
            preS = coneccion.prepareStatement(INSERTAR);
            preS.setString(1, cliente.getNombre());
            preS.setString(2, cliente.getApellido());
            preS.setString(3, cliente.getEmail());
            preS.setString(4, cliente.getTelefono());
            preS.setDouble(5, cliente.getSaldo());
            registros = preS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(preS);
            Coneccion.close(coneccion);
        }
        return registros;
    }

    /**
     * Modificar cliente en DB
     *
     * @param cliente
     */
    public void modificarCliente(Cliente cliente) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        int registros = 0;
        try {
            coneccion = Coneccion.getConnection();
            preS = coneccion.prepareStatement(ACTUALIZAR);
            preS.setString(1, cliente.getNombre());
            preS.setString(2, cliente.getApellido());
            preS.setString(3, cliente.getEmail());
            preS.setString(4, cliente.getTelefono());
            preS.setDouble(5, cliente.getSaldo());

            preS.setInt(6, cliente.getId());
            registros = preS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(preS);
            Coneccion.close(coneccion);
        }
        //return registros;
    }

    /**
     * Eliminar cliente de DB
     *
     * @param cliente
     */
    public void eliminarCliente(Cliente cliente) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        int registros = 0;

        try {
            coneccion = Coneccion.getConnection();
            preS = coneccion.prepareStatement(ELIMINAR);
            preS.setInt(1, cliente.getId());
            registros = preS.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(preS);
            Coneccion.close(coneccion);
        }
        //return registros;
    }
}
