package Server;

import Server.Controllers.AuthController;
import Server.Controllers.EfficiencyController;
import Server.Controllers.EmployeeController;
import Server.Controllers.RegistrationController;
import Server.Entity.Account;
import Server.Entity.Employee;

import java.io.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerApplication {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8081)){
            System.out.println("Сервер запущен...");
            while (true)
                try{
                    Socket socket = serverSocket.accept();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try(BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(socket.getInputStream()));
                                BufferedWriter writer = new BufferedWriter(
                                        new OutputStreamWriter(socket.getOutputStream())))
                            {
                                System.out.println("work");
                                String menu = reader.readLine(); // принимаем пункт меню
                                System.out.println(menu); // выводим пункт меню в консоль
                                switch (menu){
                                    case "registration":{
                                        System.out.println("addUser case");
                                        String name = reader.readLine();
                                        String login = reader.readLine();
                                        String email = reader.readLine();
                                        String password = reader.readLine();
                                        System.out.println(name + " "+ login+" "+email+" "+password);
                                        RegistrationController reg = new RegistrationController();
                                        writer.write(reg.registration(name,login,email,password));writer.newLine();
                                        writer.flush();
                                    }break;
                                    case "authentication":{
                                        System.out.println("authentication case");
                                        String login = reader.readLine();
                                        String password = reader.readLine();
                                        System.out.println(login+ " " +password);
                                        AuthController auth = new AuthController();
                                        Account account = auth.authentication(login, password);
                                        if(account==null){
                                            writer.write("not_success");writer.newLine();
                                            writer.flush();
                                        }else {
                                            writer.write("success");writer.newLine();
                                            writer.write(account.getName());writer.newLine();
                                            writer.write(account.getLogin());writer.newLine();
                                            writer.write(account.getEmail());writer.newLine();
                                            writer.flush();
                                        }
                                    }break;
                                    case "addEmployee":{
                                        System.out.println("addEmployee case");
                                        String name = reader.readLine();
                                        String surname = reader.readLine();
                                        String location = reader.readLine();
                                        String department = reader.readLine();
                                        String position = reader.readLine();
                                        String salary = reader.readLine();
                                        System.out.println("Данные с клиента: ");
                                        System.out.println(name+" "+surname+" "+location+" "+department+" "+ position+" "+salary);
                                        EmployeeController.AddEmployee(name, surname,location,department,position,salary);
                                    }break;
                                    case "getEmployees":{
                                        ArrayList<Employee> emp = EmployeeController.getEmployers();
                                        for(Employee e: emp){
                                            System.out.println(e.toString());
                                        }
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(emp);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "deleteEmployee":{
                                        String id = reader.readLine();
                                        EmployeeController.deleteEmployee(Integer.parseInt(id));
                                    }break;
                                    case "getEmployee":{
                                        System.out.println("getEmployee case");
                                        String id = reader.readLine();
                                        Employee employee = EmployeeController.getEmployee(id);
                                        try {
                                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                                            objectOutputStream.writeObject(employee);
                                        }
                                        catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }break;
                                    case "editEmployee":{
                                        System.out.println("editEmployee case");
                                        Employee employee = new Employee();
                                        String id = reader.readLine();
                                        String name = reader.readLine();
                                        String surname = reader.readLine();
                                        String location = reader.readLine();
                                        String department = reader.readLine();
                                        String position = reader.readLine();
                                        String salary = reader.readLine();

                                        employee.setId(Integer.parseInt(id));
                                        employee.setName(name);
                                        employee.setSurname(surname);
                                        employee.setLocation(location);
                                        employee.setDepartment(department);
                                        employee.setPosition(position);
                                        employee.setSalary(Integer.parseInt(salary));
                                        EmployeeController.editEmployee(employee);
                                    }break;
                                    case "addEfficiency":{
                                        System.out.println("addEfficiency case");
                                        String id = reader.readLine();
                                        EfficiencyController.addEfficiency(id);

                                    }break;
                                    default:
                                        System.out.println("Error choose");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
