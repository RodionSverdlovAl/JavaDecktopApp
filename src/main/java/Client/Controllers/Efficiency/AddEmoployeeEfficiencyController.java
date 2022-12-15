package Client.Controllers.Efficiency;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Client.Config.ConnectConfig;
import Server.Entity.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddEmoployeeEfficiencyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_btn;

    @FXML
    private TextField id;

    @FXML
    private Label info;

    @FXML
    private Button select_btn;

    Employee emp;

    @FXML
    void initialize() {
        select_btn.setOnAction(actionEvent -> selectEmployee());
        add_btn.setOnAction(actionEvent -> addEmployeeInEfficiency());
    }

    void addEmployeeInEfficiency(){
        String id = this.id.getText();
        try(Socket clientSocket = new Socket(ConnectConfig.IP,ConnectConfig.PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("addEfficiency");writer.newLine();
            writer.write(id);writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void selectEmployee(){
        String id = this.id.getText();
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
                info.setText(
                        employee.getName()+ " "+
                                employee.getSurname()+" "+
                                employee.getLocation()+" "+
                                employee.getDepartment()+" "+
                                employee.getPosition()+" "+
                                employee.getSalary());
                this.emp = employee;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
