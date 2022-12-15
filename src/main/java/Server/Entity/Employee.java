package Server.Entity;

import java.io.Serializable;

public class Employee implements Serializable {
    private Integer id;
    private String name;
    private String surname;
    private String location;
    private String department;
    private String position;
    private Integer salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Employee() {

    }

    public Employee(Integer id, String name, String surname, String location, String department, String position, Integer salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.location = location;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }

    public Employee(String name, String surname, String location, String department, String position, Integer salary) {
        this.name = name;
        this.surname = surname;
        this.location = location;
        this.department = department;
        this.position = position;
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", location='" + location + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
