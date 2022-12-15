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

public class CityStatisticController extends EmployeeConfig {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label city_1;

    @FXML
    private Label city_2;

    @FXML
    private Label city_3;

    @FXML
    private Label city_4;

    @FXML
    private Label city_5;
    @FXML
    private Label city_6;
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
                        int s1,s2,s3,s4,s5,s6;
                        s1=s2=s3=s4=s5=s6= 0;
                        for(Employee e : employees){
                            if(e.getLocation().equals(CITY_1)){
                                s1++;
                            }
                            if(e.getLocation().equals(CITY_2)){
                                s2++;
                            }
                            if(e.getLocation().equals(CITY_3)){
                                s3++;
                            }
                            if(e.getLocation().equals(CITY_4)){
                                s4++;
                            }
                            if(e.getLocation().equals(CITY_5)){
                                s5++;
                            }
                            if(e.getLocation().equals(CITY_6)){
                                s6++;
                            }
                        }

                        city_1.setText(CITY_1+ " - "+s1+" сотрудников");
                        city_2.setText(CITY_2+ " - "+s2+" сотрудников");
                        city_3.setText(CITY_3+ " - "+s3+" сотрудников");
                        city_4.setText(CITY_4+ " - "+s4+" сотрудников");
                        city_5.setText(CITY_5+ " - "+s5+" сотрудников");
                        city_6.setText(CITY_6+ " - "+s6+" сотрудников");

                        ObservableList<PieChart.Data> piechartdata =
                                FXCollections.observableArrayList(
                                        new PieChart.Data(CITY_1,s1),
                                        new PieChart.Data(CITY_2,s2),
                                        new PieChart.Data(CITY_3,s3),
                                        new PieChart.Data(CITY_4,s4),
                                        new PieChart.Data(CITY_5,s5),
                                        new PieChart.Data(CITY_6,s6));
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
