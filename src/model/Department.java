/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import communication.ConnectionDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class for manipulating Department data
 * @author Win7
 */
public class Department {

    private int id;
    private String department_name;
    
    public Department() {
        super();
    }

    private static final Logger log = LogManager.getLogger(Department.class);
    
    /**
     * Add data to department table in database.
     *
     * @param department_name name of department
     * @throws SQLException exception handling
     */
    public void insertSQL(String department_name) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String addDepartment = "INSERT INTO department\n"
                + "(department_name)\n"
                + "VALUES(?)";

        log.info(addDepartment);
        PreparedStatement add = null;
        try {
            cd.setAutoCommit(false);
            add = cd.prepareStatement(addDepartment);
            add.setString(1, department_name);
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
     * Delete data from department table in database.
     *
     * @param id id of department
     * @throws SQLException exception handling
     */
    public void deleteSQL(int id) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String deleteDepartment = "DELETE FROM department\n"
                + "WHERE id=(?)";
        PreparedStatement add = null;
        try {
            cd.setAutoCommit(false);
            add = cd.prepareStatement(deleteDepartment);
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

    /**
     * Finds department with biggest number of employees
     *
     * @throws SQLException exception handling
     */
    public void largestDepartment() throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String largestDepartment = "select department_name \n"
                + "from department \n"
                + "INNER JOIN employee\n"
                + "ON (employee.department_id=department.id) \n"
                + "group by department_name\n"
                + "order by count(*) desc\n"
                + "limit 1";

        PreparedStatement add = cd.prepareStatement(largestDepartment);
        try {
            ResultSet rs=add.executeQuery();
            System.out.println("Executed!");
            while (rs.next()){
                String depart_name=rs.getString("department_name");
                System.out.println("Biggest department is: "+depart_name);
            }
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
