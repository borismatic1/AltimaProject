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
public class AssignProject {

    private int id;
    private int project_id;
    private int employee_id;

    public AssignProject() {
        super();
    }

    public void assignProject(int projID, int employID) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String insertSQL = "INSERT INTO assign_project\n"
                + "(project_id, employee_id)\n"
                + "VALUES(?,?)";

        PreparedStatement add = cd.prepareStatement(insertSQL);
        try {
            cd.setAutoCommit(false);
            add.setInt(1, projID);
            add.setInt(2, employID);
            add.executeUpdate();
            cd.commit();
            System.out.println("Successful entry!");
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
