package Server.Services.DataBase;

import Server.Config.ConstEmployee;
import Server.Entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseHandlerEmployee extends ConnectToDB {

    public void putEmployee(Employee employee){
        String insert = "INSERT INTO "+ ConstEmployee.TABLE + "(" + ConstEmployee.NAME +
                ","+ConstEmployee.SURNAME+","+ConstEmployee.LOCATION+","+ ConstEmployee.DEPARTMENT+","+
                ConstEmployee.POSITION+","+ConstEmployee.SALARY+")"+" VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, employee.getName());
            prSt.setString(2, employee.getSurname());
            prSt.setString(3, employee.getLocation());
            prSt.setString(4, employee.getDepartment());
            prSt.setString(5, employee.getPosition());
            prSt.setInt(6,employee.getSalary());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> employees = new ArrayList<Employee>();
        String select = "SELECT * FROM " + ConstEmployee.TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                Employee employee = new Employee();

                employee.setId(resSet.getInt(ConstEmployee.EMPLOYEE_ID));
                employee.setName(resSet.getString(ConstEmployee.NAME));
                employee.setSurname(resSet.getString(ConstEmployee.SURNAME));
                employee.setLocation(resSet.getString(ConstEmployee.LOCATION));
                employee.setDepartment(resSet.getString(ConstEmployee.DEPARTMENT));
                employee.setPosition(resSet.getString(ConstEmployee.POSITION));
                employee.setSalary(resSet.getInt(ConstEmployee.SALARY));
                employees.add(employee);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployee(Integer id){
        Employee employee = new Employee();
        String select = "SELECT * FROM " + ConstEmployee.TABLE + " WHERE "+ConstEmployee.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, String.valueOf(id));
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                employee.setId(resSet.getInt(ConstEmployee.EMPLOYEE_ID));
                employee.setName(resSet.getString(ConstEmployee.NAME));
                employee.setSurname(resSet.getString(ConstEmployee.SURNAME));
                employee.setLocation(resSet.getString(ConstEmployee.LOCATION));
                employee.setDepartment(resSet.getString(ConstEmployee.DEPARTMENT));
                employee.setPosition(resSet.getString(ConstEmployee.POSITION));
                employee.setSalary(resSet.getInt(ConstEmployee.SALARY));
            }
            System.out.println("id принятое на сервер = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public void editEmployee(Employee employee){
        String update = "UPDATE " + ConstEmployee.TABLE +
                " SET " + ConstEmployee.NAME + "=?, " + ConstEmployee.SURNAME + "=?, "
                + ConstEmployee.LOCATION + "=?, " + ConstEmployee.DEPARTMENT + "=?, "+ConstEmployee.POSITION + "=?, " + ConstEmployee.SALARY + "=?" +
                " WHERE " + ConstEmployee.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getLocation());
            preparedStatement.setString(4, employee.getDepartment());
            preparedStatement.setString(5, employee.getPosition());
            preparedStatement.setInt(6, employee.getSalary());
            preparedStatement.setInt(7, employee.getId());
            System.out.println("edit employee successfull");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(int id){
        String delete = "DELETE FROM " + ConstEmployee.TABLE + " WHERE " + ConstEmployee.EMPLOYEE_ID + "='" + id + "'; ";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(delete);
            prSt.executeUpdate();
            System.out.println("id принятое на сервер = " + id);
            System.out.println("Сотрудник успешно удален");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
