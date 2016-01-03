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
import java.util.logging.Logger;

/**
 *
 * @author Win7
 */
public class Project {

    private int id;
    private String project_name;

    

    public Project() {
        super();
    }

    public void insertSQL(String name) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String addProject = "INSERT INTO project\n"
                + "(project_name)\n"
                + "VALUES(?)";

        PreparedStatement add = cd.prepareStatement(addProject);
        try {
            cd.setAutoCommit(false);
            add.setString(1, name);
            add.executeUpdate();
            cd.commit();
            System.out.println("Successful entry!");
        } catch (SQLException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
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

    public void deleteSQL(int id) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String deleteProject = "DELETE FROM  \"public\".project\n"
                + "WHERE id=(?)";
        
        PreparedStatement add = null;
        try {
            cd.setAutoCommit(false);
            add = cd.prepareStatement(deleteProject);
            add.setInt(1, id);
            add.executeUpdate();
            cd.commit();
            System.out.println("Successful delete!");
        } catch (SQLException ex) {
            Logger.getLogger(Department.class.getName()).log(Level.SEVERE, null, ex);
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
