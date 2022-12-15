package Client.Controllers.Account;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import Client.Config.ConnectConfig;
import Client.Controllers.AdminAppController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signin_btn;

    @FXML
    private Button signup_btn;

    @FXML
    void initialize() {
        signup_btn.setOnAction(actionEvent -> openRegistrationWindow());
        signin_btn.setOnAction(actionEvent -> sendAuthData());
    }

    private void openRegistrationWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Client/Registration.fxml"));
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

    private void openAdminAppWindow(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Client/AdminApp.fxml"));
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

    private void sendAuthData(){
        String login = login_field.getText();
        String password = password_field.getText();
        if(login.isEmpty() || password.isEmpty()){
            System.out.println("Все поля должны быть заполнены");
            return;
        }
        try(Socket clientSocket = new Socket(ConnectConfig.IP,ConnectConfig.PORT);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            writer.write("authentication");writer.newLine();
            writer.write(login);writer.newLine();
            writer.write(password); writer.newLine();
            writer.flush();
            String flag = reader.readLine();
            if(flag == null){
                System.out.println("Неверный логин или пароль!");
                return;
            }
           System.out.println(flag);
            if(flag.equals("success")){
                String acc_name = reader.readLine();
                String acc_login = reader.readLine();
                String acc_email = reader.readLine();
                System.out.println("Вы услешно вошли в аккаунт "+acc_name+" "+acc_login+" "+acc_email);
                AdminAppController.account_name = acc_name;
                AdminAppController.account_login = acc_login;
                openAdminAppWindow();
            }else{
                System.out.println("Неверный логин или пароль!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
