package Server.Controllers;

import Server.Entity.Employee;
import Server.Services.DataBase.DataBaseHandlerEmployee;

import java.util.ArrayList;

public class EmployeeController {
    public static void AddEmployee(String name, String surname,String location, String department, String position, String salary){
        Integer sal = Integer.parseInt(salary);
        Employee employee = new Employee(name,surname,location,department,position,sal);
        DataBaseHandlerEmployee dbHandler = new DataBaseHandlerEmployee();
        dbHandler.putEmployee(employee);
    }

    public static ArrayList<Employee> getEmployers(){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        DataBaseHandlerEmployee db = new DataBaseHandlerEmployee();
        employees = db.getEmployees();
        return employees;
    }

    public static void deleteEmployee(int id){
        DataBaseHandlerEmployee db =new DataBaseHandlerEmployee();
        db.deleteEmployee(id);
    }

    public static Employee getEmployee(String id){
        DataBaseHandlerEmployee db = new DataBaseHandlerEmployee();
        return db.getEmployee(Integer.parseInt(id));
    }

    public static void editEmployee(Employee employee){
        DataBaseHandlerEmployee db = new DataBaseHandlerEmployee();
        db.editEmployee(employee);
    }

}
