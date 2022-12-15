package Client.Controllers.Statistic;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Client.Config.ConnectConfig;
import Client.Config.EmployeeConfig;
import Server.Entity.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class DepartmentStatisticController extends EmployeeConfig {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label department_1;

    @FXML
    private Label department_2;

    @FXML
    private Label department_3;

    @FXML
    private Label department_4;

    @FXML
    private Label department_5;


    @FXML
    private PieChart piechart;

    @FXML
    void initialize() {
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
                    int d1,d2,d3,d4,d5;
                    d1=d2=d3=d4=d5 = 0;
                    for(Employee e : employees){
                        if(e.getDepartment().equals(DEP_1)){
                            d1++;
                        }
                        if(e.getDepartment().equals(DEP_2)){
                            d2++;
                        }
                        if(e.getDepartment().equals(DEP_3)){
                            d3++;
                        }
                        if(e.getDepartment().equals(DEP_4)){
                            d4++;
                        }
                        if(e.getDepartment().equals(DEP_5)){
                            d5++;
                        }
                    }

                    department_1.setText(DEP_1+ " - "+d1+" сотрудников");
                    department_2.setText(DEP_2+ " - "+d2+" сотрудников");
                    department_3.setText(DEP_3+ " - "+d3+" сотрудников");
                    department_4.setText(DEP_4+ " - "+d4+" сотрудников");
                    department_5.setText(DEP_5+ " - "+d5+" сотрудников");

                    ObservableList<PieChart.Data> piechartdata =
                            FXCollections.observableArrayList(
                                    new PieChart.Data(DEP_1,d1),
                                    new PieChart.Data(DEP_2,d2),
                                    new PieChart.Data(DEP_3,d3),
                                    new PieChart.Data(DEP_4,d4),
                                    new PieChart.Data(DEP_5,d5));
                    piechart.getData().clear();
                    piechart.getData().addAll(piechartdata);
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

}
