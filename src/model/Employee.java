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
import java.util.logging.Logger;

/**
 *
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

    public void insertSQL(String name, String surname, String street, String city, int department_id, int supervisor_id) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String addEmployee = "INSERT INTO employee\n"
                + "(employee_name, street, city, department_id, supervisor_id)\n"
                + "VALUES (?,?,?,?,?)";

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

    public void deleteSQL(int id) throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String deleteEmployee = "DELETE FROM employee\n"
                + "WHERE id=(?)";

        PreparedStatement add = cd.prepareStatement(deleteEmployee);
        try {
            cd.setAutoCommit(false);
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

    public void mostProjects() throws SQLException {
        Connection cd = new ConnectionDatabase().connect();
        String mostProjects = "select employee_name \n"
                + "from employee \n"
                + "INNER JOIN assign_project\n"
                + "ON (assign_project.employee_id=employee.id) \n"
                + "group by employee_name\n"
                + "order by count(*) desc\n"
                + "limit 1";
        
        PreparedStatement add = cd.prepareStatement(mostProjects);
        try {
            ResultSet rs=add.executeQuery();
            System.out.println("Executed!");
            while (rs.next()){
                String depart_name=rs.getString("employee_name");
                System.out.println("Employee with most projects: "+depart_name);
            }
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
