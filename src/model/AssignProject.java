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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Class for assigning projects to employees
 * @author Win7
 */
public class AssignProject {

    private int id;
    private int project_id;
    private int employee_id;

    public AssignProject() {
        super();
    }

    private static final Logger log = LogManager.getLogger(Department.class);
    
    /**
     * Method for assigning projects to employees.
     *
     * @param projID id of project
     * @param employID name of employee
     * @throws SQLException exception handling
     */
    public void assignProject(int projID, int employID) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String insertSQL = "INSERT INTO assign_project\n"
                + "(project_id, employee_id)\n"
                + "VALUES(?,?)";

        log.info(insertSQL);
        PreparedStatement add = cd.prepareStatement(insertSQL);
        try {
            cd.setAutoCommit(false);
            add.setInt(1, projID);
            add.setInt(2, employID);
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
}
