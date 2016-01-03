/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import communication.ConnectionDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Class for manipulating Project data
 * @author Win7
 */
public class Project {

    private int id;
    private String project_name;

    public Project() {
        super();
    }

    private static final Logger log = LogManager.getLogger(Department.class);
    
    /**
     * Add data to project table in database.
     *
     * @param name name of project
     * @throws SQLException exception handling
     */
    public void insertSQL(String name) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String addProject = "INSERT INTO project\n"
                + "(project_name)\n"
                + "VALUES(?)";

        log.info(addProject);
        PreparedStatement add = cd.prepareStatement(addProject);
        try {
            cd.setAutoCommit(false);
            add.setString(1, name);
            add.executeUpdate();
            cd.commit();
            System.out.println("Successful entry!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (add != null) {
                add.close();
            }
            cd.setAutoCommit(true);
            if (cd != null) {
                cd.close();
            }
        }

    }

    /**
     * Delete data from project table in database.
     *
     * @param id id of project
     * @throws SQLException exception handling
     */
    public void deleteSQL(int id) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String deleteProject = "DELETE FROM  \"public\".project\n"
                + "WHERE id=(?)";
        
        log.info(deleteProject);
        PreparedStatement add = null;
        try {
            cd.setAutoCommit(false);
            add = cd.prepareStatement(deleteProject);
            add.setInt(1, id);
            add.executeUpdate();
            cd.commit();
            System.out.println("Successful delete!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (add != null) {
                add.close();
            }
            cd.setAutoCommit(true);
            if (cd != null) {
                cd.close();
            }
        }
    }
}
