package Server.Services.DataBase;

import Server.Config.Configs;
import Server.Config.ConstAccounts;
import Server.Entity.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DataBaseHandlerAccounts extends ConnectToDB {


    public Integer putAccount(Account account){
        String insert = "INSERT INTO "+ ConstAccounts.TABLE + "(" + ConstAccounts.NAME +
                ","+ConstAccounts.LOGIN+","+ConstAccounts.EMAIL+","+ ConstAccounts.PASSWORD+","+
                ConstAccounts.SALT+","+ConstAccounts.ROLE+")"+" VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, account.getName());
            prSt.setString(2, account.getLogin());
            prSt.setString(3, account.getEmail());
            prSt.setString(4, account.getPassword());
            prSt.setString(5, account.getSalt());
            prSt.setBoolean(6,account.getRole());
            prSt.executeUpdate();

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public Account getAccountForAuth(String login){
        ResultSet resSet = null;
        Account account = new Account();
        String select = "SELECT * FROM "+ConstAccounts.TABLE+" WHERE " +
                ConstAccounts.LOGIN + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,login);
            resSet = prSt.executeQuery();
            while (resSet.next()){
                account.setAccount_id(resSet.getInt("id"));
                account.setName(resSet.getString("name"));
                account.setLogin(resSet.getString("login"));
                account.setEmail(resSet.getString("email"));
                account.setPassword(resSet.getString("password"));
                account.setSalt(resSet.getString("salt"));
                account.setRole(resSet.getBoolean("role"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return account;
    }
}
