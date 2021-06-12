/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.clienteDB;

import baquiax.controlclientes.DB.Coneccion;
import baquiax.controlclientes.modelo.Administrador;
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
public class AdministradorDB {

    private static final String SELECT = "SELECT ID, contraseña, usuario, nombre FROM ADMINISTRADOR";
    private static final String SELECT_CLIENTE = "SELECT * FROM ADMINISTRADOR";
    private static final String SELECT_BY_CONTRASENA_USUARIO = "SELECT ID, contraseña, usuario, nombre FROM ADMINISTRADOR WHERE contraseña = ? AND usuario = ? LIMIT 1";
    private static final String INSERTAR = "INSERT INTO ADMINISTRADOR(contraseña, usuario, nombre) VALUES(?, ?, ?)";
    private static final String ACTUALIZAR = "UPDATE ADMINISTRADOR SET contraseña = ?, usuario = ?, nombre = ? WHERE ID = ?";
    private static final String ELIMINAR = "DELETE FROM ADMINISTRADOR WHERE ID = ?";

    public AdministradorDB() {
    }

    /**
     * Listado de clientes
     *
     * @return
     */
    public List<Administrador> listarAdmistradores() {
        Connection coneccion = null;
        PreparedStatement preStatement = null;
        ResultSet res = null;
        Administrador admin = null;
        List<Administrador> adminis = new ArrayList<>();

        try {
            //coneccion = Coneccion.getInstance();
            coneccion = Coneccion.getConnection();
            preStatement = coneccion.prepareStatement(SELECT_CLIENTE);
            res = preStatement.executeQuery();
            while (res.next()) {
                admin = new Administrador(res.getInt("ID"),
                        res.getString("contraseña"),
                        res.getString("usuario"),
                        res.getString("nombre"));
                adminis.add(admin);
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

        return adminis;
    }

    /**
     * Seleccionar cliente por ID
     *
     * @param contraseña
     * @param usuario
     * @return
     */
    public Administrador buscarAdministrador(String contraseña, String usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet res = null;
        Administrador admin = null;
        try {
            conn = Coneccion.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_CONTRASENA_USUARIO);
            stmt.setString(1, contraseña);
            stmt.setString(2, usuario);
            res = stmt.executeQuery();

            while (res.next()) {
                admin = new Administrador(
                        res.getInt("ID"),
                        res.getString("contraseña"),
                        res.getString("usuario"),
                        res.getString("nombre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDB.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Coneccion.close(res);
            Coneccion.close(stmt);
            Coneccion.close(conn);
        }
        return admin;
    }

    /**
     * Insertar cliente en la BD
     *
     * @param admin
     * @return
     */
    public int insertarAdministrador(Administrador admin) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        int registros = 0;
        try {
            coneccion = Coneccion.getConnection();
            preS = coneccion.prepareStatement(INSERTAR);
            preS.setString(1, admin.getContraseña());
            preS.setString(2, admin.getUsuario());
            preS.setString(3, admin.getNombre());
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
     * @param admin
     */
    public void modificarAdministrador(Administrador admin) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        int registros = 0;
        try {
            coneccion = Coneccion.getConnection();
            preS = coneccion.prepareStatement(ACTUALIZAR);
            preS.setString(1, admin.getContraseña());
            preS.setString(2, admin.getUsuario());
            preS.setString(3, admin.getNombre());

            preS.setInt(4, admin.getIdAdmin());
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
     * @param admin
     */
    public void eliminarAdministrador(Administrador admin) {
        Connection coneccion = null;
        PreparedStatement preS = null;
        int registros = 0;

        try {
            coneccion = Coneccion.getConnection();
            preS = coneccion.prepareStatement(ELIMINAR);
            preS.setInt(1, admin.getIdAdmin());
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
