/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.modelo;

import java.io.Serializable;

/**
 *
 * @author Luis
 */
public class Administrador implements Serializable {

    private int idAdmin;
    private String contraseña;
    private String usuario;
    private String nombre;

    /**
     *
     * @param idAdmin
     * @param contraseña
     * @param usuario
     * @param nombre
     */
    public Administrador(int idAdmin, String contraseña, String usuario, String nombre) {
        this.idAdmin = idAdmin;
        this.contraseña = contraseña;
        this.usuario = usuario;
        this.nombre = nombre;
    }

    /**
     * Para agregar un administrador
     *
     * @param contraseña
     * @param usuario
     * @param nombre
     */
    public Administrador(String contraseña, String usuario, String nombre) {
        this.contraseña = contraseña;
        this.usuario = usuario;
        this.nombre = nombre;
    }

    /**
     *
     * @param contraseña
     * @param usuario
     */
    public Administrador(String contraseña, String usuario) {
        this.contraseña = contraseña;
        this.usuario = usuario;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the idAdmin
     */
    public int getIdAdmin() {
        return idAdmin;
    }

    @Override
    public String toString() {
        return "Administrador{" + "idAdmin=" + idAdmin + ", contrase\u00f1a=" + contraseña + ", usuario=" + usuario + ", nombre=" + nombre + '}';
    }

}
