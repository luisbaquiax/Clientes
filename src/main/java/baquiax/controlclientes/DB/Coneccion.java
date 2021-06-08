/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baquiax.controlclientes.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Luis
 */
public class Coneccion {

    private static final String URL = "jdbc:mysql://localhost:3306/CLIENTES?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "@luis.baquiax95";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void close(ResultSet resulset) throws SQLException {
        resulset.close();
    }

    public static void close(Statement statement) throws SQLException {
        statement.close();
    }

    public static void close(PreparedStatement preStement) throws SQLException {
        preStement.close();
    }

    public static void close(Connection coneccion) throws SQLException {
        coneccion.close();
    }
}
