/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import altimaproject.AltimaProject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Win7
 */
public class ConnectionDatabase {

    private String username = "nbovnyrf";
    private String password = "9kkPToIGSHbfnjIRtQYtnca0tl0Klny_";

    org.slf4j.Logger log=LoggerFactory.getLogger(ConnectionDatabase.class);
    public ConnectionDatabase() {
        super();

    }

    public Connection connect() {
        try {
            //Class.forName("org.postgresql.Driver");
            Class.forName("net.sf.log4jdbc.DriverSpy");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AltimaProject.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Can not find PostgreSQL JDBC Driver");
        }

        Connection connection = null;

        try {
            /*
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://pellefant-01.db.elephantsql.com:5432/nbovnyrf", username,
                    password);*/
            connection = DriverManager.getConnection(
                    "jdbc:log4jdbc:postgresql://pellefant-01.db.elephantsql.com:5432/nbovnyrf", username,
                    password);
            log.info("test");

        } catch (SQLException ex) {
            System.out.println("Connection Failed! Check output console");
            Logger.getLogger(AltimaProject.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (connection != null) {
            System.out.println("Connected! Executing query...");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }
}
