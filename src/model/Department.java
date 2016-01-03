/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import communication.ConnectionDatabase;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Win7
 */
public class Department {

    private int id;
    private String department_name;
    Logger log=Logger.getLogger("model/Department");
    File propertiesFile = new File("src/altimaproject/log4j.properties");
    
    public Department() {
        super();
    }

    public void insertSQL(String department_name) throws SQLException {
        PropertyConfigurator.configure(propertiesFile.toString());
        Connection cd = new ConnectionDatabase().connect();
        String addDepartment = "INSERT INTO department\n"
                + "(department_name)\n"
                + "VALUES(?)";

        PreparedStatement add = null;
        try {
            cd.setAutoCommit(false);
            add = cd.prepareStatement(addDepartment);
            add.setString(1, department_name);
            add.executeUpdate();
            cd.commit();
            System.out.println("Successful entry!");
            log.debug("debug");
            
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
