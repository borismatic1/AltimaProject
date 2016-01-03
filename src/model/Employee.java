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
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Class for manipulating Employee data
 * @author Win7
 */
public class Employee {

    private int id;
    private String employee_name;
    private String street;
    private String city;
    private int department_id;
    private int supervisor_id;

    public Employee() {
        super();
    }

    private static final Logger log = LogManager.getLogger(Department.class);
    
    /**
     * Add data to employee table in database.
     *
     * @param name name of employee
     * @param surname last name of employee
     * @param street street where employee lives
     * @param city city where employee lives
     * @param department_id id of department where employee works
     * @param supervisor_id id of employees supervisor
     * @throws SQLException exception handling
     */
    public void insertSQL(String name, String surname, String street, String city, int department_id, int supervisor_id) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String addEmployee = "INSERT INTO employee\n"
                + "(employee_name, street, city, department_id, supervisor_id)\n"
                + "VALUES (?,?,?,?,?)";

        log.info(addEmployee);
        PreparedStatement add = cd.prepareStatement(addEmployee);
        try {
            cd.setAutoCommit(false);
            add.setString(1, name + " " + surname);
            add.setString(2, street);
            add.setString(3, city);
            add.setInt(4, department_id);
            add.setInt(5, supervisor_id);
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
     * Delete data from employee table in database.
     *
     * @param id id of employee
     * @throws SQLException exception handling
     */
    public void deleteSQL(int id) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String deleteEmployee = "DELETE FROM employee\n"
                + "WHERE id=(?)";

        log.info(deleteEmployee);
        PreparedStatement add = cd.prepareStatement(deleteEmployee);
        try {
            cd.setAutoCommit(false);
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
     * Finds employee who works on most projects.
     *
     * @throws SQLException exception handling
     */
    public void mostProjects() throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String mostProjects = "select employee_name \n"
                + "from employee \n"
                + "INNER JOIN assign_project\n"
                + "ON (assign_project.employee_id=employee.id) \n"
                + "group by employee_name\n"
                + "order by count(*) desc\n"
                + "limit 1";
        
        log.info(mostProjects);
        PreparedStatement add = cd.prepareStatement(mostProjects);
        try {
            ResultSet rs=add.executeQuery();
            System.out.println("Executed!");
            while (rs.next()){
                String depart_name=rs.getString("employee_name");
                System.out.println("Employee with most projects: "+depart_name);
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
