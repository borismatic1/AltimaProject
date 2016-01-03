/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Win7
 */
public class Employee_on_limited_time {
    private int id;
    private String name;
    private int number_of_months;
    private int project_id;
    
    public Employee_on_limited_time(){
        super();
    }
    
    public void insertSQL(String employee_name,int months,int project_id){
        String addEmployee="";
    }
    
    public void deleteSQL(int id){
        String deleteEmployee="";
    }
}
