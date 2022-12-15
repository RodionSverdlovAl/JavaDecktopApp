package Client.Controllers.Employee;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Client.Config.ConnectConfig;
import Client.Config.EmployeeConfig;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AddEmployeeController extends EmployeeConfig {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_employee_btn;

    @FXML
    private ComboBox<String> city_list;

    @FXML
    private ComboBox<String> department_list;

    @FXML
    private TextField name_field;

    @FXML
    private TextField position_field;

    @FXML
    private Spinner<Integer> salary_field;
    SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(3,50,10);

    @FXML
    private TextField surname_field;



    @FXML
    void initialize() {
        setLists();
        add_employee_btn.setOnAction(actionEvent -> sendEmployeeData());

    }

    void setLists(){
        city_list.setItems(cities);
        department_list.setItems(departments);
        salary_field.setValueFactory(svf);
    }


    void sendEmployeeData(){
        String name = name_field.getText();
        String surname = surname_field.getText();
        String city = city_list.getValue();
        String department = department_list.getValue();
        String position = position_field.getText();
        Integer salary = salary_field.getValue();

        try(Socket clientSocket = new Socket(ConnectConfig.IP,ConnectConfig.PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("addEmployee");writer.newLine();
            writer.write(name);writer.newLine();
            writer.write(surname); writer.newLine();
            writer.write(city); writer.newLine();
            writer.write(department); writer.newLine();
            writer.write(position); writer.newLine();
            writer.write(salary.toString()); writer.newLine();
            writer.flush();
//            String flag = reader.readLine();
//            if(flag == null){
//                System.out.println("Ошибка добвления!");
//                return;
//            }
//            System.out.println(flag);
//            if(flag.equals("success")){
//               // тут закроем окно с добавлением
//            }else{
//                System.out.println("не удалось добавить пользователя!");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
