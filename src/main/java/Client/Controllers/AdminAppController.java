package Client.Controllers;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.Config.ConnectConfig;
import Server.Entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminAppController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_efficiency;
    @FXML
    private Button add_emploee_btn;
    @FXML
    private Button city_statistic_btn;
    @FXML
    private Button delete_employee_btn;
    @FXML
    private Button edit_employee_btn;
    @FXML
    private Button department_statistic_btn;

    @FXML
    private TextField id_for_delete;

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> employee_department;

    @FXML
    private TableColumn<Employee, Integer> employee_id;

    @FXML
    private TableColumn<Employee, String> employee_location;

    @FXML
    private TableColumn<Employee, String> employee_name;

    @FXML
    private TableColumn<Employee, String> employee_position;

    @FXML
    private TableColumn<Employee, Integer> employee_salary;

    @FXML
    private TableColumn<Employee, String> employee_surname;

    @FXML
    private Label login_label;

    @FXML
    private Label name_label;

    @FXML
    private Button reload_btn;

    public static String account_name = "";
    public static String account_login ="";


    @FXML
//    void initialize() {
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_label.setText(account_login);
        name_label.setText(account_name);

        add_emploee_btn.setOnAction(actionEvent -> openAddWorkerWindow());
        reload_btn.setOnAction(actionEvent -> reloadInfoAboutEmployees());
        delete_employee_btn.setOnAction(actionEvent -> deleteEmployee());
        edit_employee_btn.setOnAction(actionEvent -> openEditEmployeeWindow());
        department_statistic_btn.setOnAction(actionEvent -> openDepartmentStatisticWindow());
        city_statistic_btn.setOnAction(actionEvent -> openCityStatisticWindow());
        add_efficiency.setOnAction(actionEvent -> openAddEfficiencyWindow());
    }

    void openAddEfficiencyWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Client/AddEmployeeEfficiency.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    void openDepartmentStatisticWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Client/DepartmentStatistic.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    void openCityStatisticWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Client/CityStatistic.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    void openAddWorkerWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Client/AddEmployee.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    void reloadInfoAboutEmployees(){
        try(Socket clientSocket = new Socket(ConnectConfig.IP,ConnectConfig.PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("getEmployees");writer.newLine();
            writer.flush();
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                try {
                    Object object = objectInputStream.readObject();
                    ArrayList<Employee> employees = (ArrayList<Employee>) object;
                    System.out.println(employees.size());
                    printEmployees(employees);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void printEmployees(ArrayList<Employee> employees){
        for(Employee e:employees){
            System.out.println(e.toString());
        }
        ObservableList<Employee> observableList = FXCollections.observableArrayList(employees);
        employeeTable.setItems(observableList);
        employee_id.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("id"));
        employee_name.setCellValueFactory(new PropertyValueFactory<Employee,String>("name"));
        employee_surname.setCellValueFactory(new PropertyValueFactory<Employee,String>("surname"));
        employee_location.setCellValueFactory(new PropertyValueFactory<Employee,String>("location"));
        employee_department.setCellValueFactory(new PropertyValueFactory<Employee,String>("department"));
        employee_position.setCellValueFactory(new PropertyValueFactory<Employee,String>("position"));
        employee_salary.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("salary"));
    }

    void deleteEmployee(){
        try(Socket clientSocket = new Socket(ConnectConfig.IP,ConnectConfig.PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
                String id = id_for_delete.getText();
                writer.write("deleteEmployee");writer.newLine();
                writer.write(id);
                writer.flush();
                id_for_delete.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void openEditEmployeeWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Client/EditEmployee.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
