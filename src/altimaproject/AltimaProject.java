/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altimaproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import model.AssignProject;
import model.Department;
import model.Employee;
import model.Project;

/**
 *
 * @author Win7
 */
public class AltimaProject {

    /**
     * @param args the command line arguments
     */
    static Department d = new Department();
    static Employee e = new Employee();
    static Project p = new Project();
    static AssignProject ap = new AssignProject();

    public static void main(String[] args) {

        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line = "";

            while ((line = br.readLine()) != null && !line.equals("exit")) {
                String[] tokens = line.trim().split(" ");
                System.out.println(tokens[0]);

                switch (tokens[0].toLowerCase()) {
                    case "add-department":
                        if (tokens.length>1) {
                        addDepartment(tokens[1]);
                        }
                        break;
                    case "add-project":
                        if (tokens.length>1) {
                        addProject(tokens[1]);
                        }
                        break;
                    case "add-employee":
                        if (tokens.length>1) {
                        addEmployee(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                        }
                        break;
                    case "assign-project":
                        if (tokens.length>1) {
                        assignProject(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                        }
                        break;
                    case "find-largest-department":
                        largestDepartment();
                        break;
                    case "find-employee-with-most-projects":
                        employeeWithMostProjects();
                        break;
                    case "delete-employee":
                        if (tokens.length > 1) {
                            deleteEmployee(Integer.parseInt(tokens[1]));
                        }
                        break;
                    case "delete-project":
                        if (tokens.length > 1) {
                            deleteProject(Integer.parseInt(tokens[1]));
                        }
                        break;
                    case "delete-department":
                        if (tokens.length > 1) {
                            deleteDepartment(Integer.parseInt(tokens[1]));
                        }
                        break;
                    default:
                        System.out.println("Neispravna naredba");
                        break;
                }

            }
            isr.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public static void addDepartment(String name) {
        try {
            d.insertSQL(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addProject(String name) {
        try {
            p.insertSQL(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addEmployee(String name, String surname, String street, String city, int depID, int supvisID) {
        try {
            e.insertSQL(name, surname, street, city, depID, supvisID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void assignProject(int projectID, int employeeID) {
        try {
            ap.assignProject(projectID, employeeID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void largestDepartment() {
        try {
            d.largestDepartment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void employeeWithMostProjects() {
        try {
            e.mostProjects();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteEmployee(int id) {
        try {
            e.deleteSQL(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProject(int id) {
        try {
            p.deleteSQL(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteDepartment(int id) {
        try {
            d.deleteSQL(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
