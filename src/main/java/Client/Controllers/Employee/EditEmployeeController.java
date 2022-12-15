package Client.Controllers.Employee;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.Config.ConnectConfig;
import Client.Config.EmployeeConfig;
import Server.Entity.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class EditEmployeeController extends EmployeeConfig {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button edit_btn;

    @FXML
    private ComboBox<String> employee_department;

    @FXML
    private TextField employee_id;

    @FXML
    private ComboBox<String> employee_location;

    @FXML
    private TextField employee_name;

    @FXML
    private TextField employee_position;

    @FXML
    private Spinner<Integer> employee_salary;

    @FXML
    private TextField employee_surname;

    @FXML
    private Button select_btn;

    @FXML
    private Label selected_employee_information;

    @FXML
    void initialize() {
        setLists();
        select_btn.setOnAction(actionEvent -> selectEmployee());
        edit_btn.setOnAction(actionEvent -> editEmployee());
    }

    void selectEmployee(){
        String id = employee_id.getText();
        try(Socket clientSocket = new Socket(ConnectConfig.IP,ConnectConfig.PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("getEmployee");writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            try {
                Object object = objectInputStream.readObject();
                Employee employee = (Employee) object ;
                selected_employee_information.setText(
                        employee.getName()+ " "+
                                employee.getSurname()+" "+
                                employee.getLocation()+" "+
                                employee.getDepartment()+" "+
                                employee.getPosition()+" "+
                                employee.getSalary());
                employee_name.setText(employee.getName());
                employee_surname.setText(employee.getSurname());
                employee_position.setText(employee.getPosition());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void editEmployee(){
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(employee_id.getText()));
        employee.setName(employee_name.getText());
        employee.setSurname(employee_surname.getText());
        employee.setLocation(employee_location.getValue());
        employee.setDepartment(employee_department.getValue());
        employee.setPosition(employee_position.getText());
        employee.setSalary(employee_salary.getValue());
        try(Socket clientSocket = new Socket(ConnectConfig.IP,ConnectConfig.PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("editEmployee");writer.newLine();
            writer.write(Integer.toString(employee.getId()));writer.newLine();
            writer.write(employee.getName());writer.newLine();
            writer.write(employee.getSurname());writer.newLine();
            writer.write(employee.getLocation());writer.newLine();
            writer.write(employee.getDepartment());writer.newLine();
            writer.write(employee.getPosition());writer.newLine();
            writer.write(Integer.toString(employee.getSalary()));writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void setLists(){
        employee_location.setItems(cities);
        employee_department.setItems(departments);
        employee_salary.setValueFactory(svf);
    }

}
