package Client.Controllers.Account;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Client.Config.ConnectConfig;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML private ResourceBundle resources;
    @FXML private URL location;
    @FXML private PasswordField confirm_password_field;
    @FXML private TextField email_field;
    @FXML private TextField login_field;
    @FXML private TextField name_field;
    @FXML private PasswordField password_field;
    @FXML private Button signup_btn;

    @FXML
    void initialize() {
        signup_btn.setOnAction(actionEvent -> sendRegData());
    }

    private void sendRegData(){

        String name, login, email, password, password_confirm;
        name= name_field.getText();
        login = login_field.getText();
        email = email_field.getText();
        password = password_field.getText();
        password_confirm = confirm_password_field.getText();

        if(name.isEmpty()){
            System.out.println("поле с именем пусто!");
            return;
        }
        if(login.isEmpty()){
            System.out.println("поле с логином пусто!");
            return;
        }
        if(email.isEmpty()){
            System.out.println("поле с электронной почтой пусто!");
            return;
        }
        if(password.isEmpty()){
            System.out.println("поле с паролем пусто!");
            return;
        }
        if(password_confirm.isEmpty()){
            System.out.println("поле подтверждения пароля пусто!");
            return;
        }
        if(!password.equals(password_confirm)){
            System.out.println("пароли не совпадают!");
            return;
        }

        try(Socket clientSocket = new Socket(ConnectConfig.IP,ConnectConfig.PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("registration");writer.newLine();
            writer.write(name);writer.newLine();
            writer.write(login);writer.newLine();
            writer.write(email); writer.newLine();
            writer.write(password); writer.newLine();
            writer.flush();
            String response = reader.readLine();
            System.out.println(response);
            if(response.equals("success")){
                signup_btn.getScene().getWindow().hide();
            }else{
                System.out.println("Ошибка регистрации");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
