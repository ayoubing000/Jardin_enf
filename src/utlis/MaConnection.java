/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utlis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ons
 */
public class MaConnection {

    String url = "jdbc:mysql://localhost:3306/jardin";
    String login = "root";
    String pwd = "";
    public static MaConnection db;
    public Connection con;

    private MaConnection() {
        try {
            con = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public static MaConnection getInstance() {
        if (db == null) {
            db = new MaConnection();
        }
        return db;
    }

    public Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
