package Server.Services.DataBase;

import Server.Config.Configs;
import Server.Config.ConstEfficiency;
import Server.Config.ConstEmployee;
import Server.Entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseHandlerEfficiency extends ConnectToDB {
    public void addEfficiency(Integer id){

        ResultSet result =  getEfficiency(id);
        int counter = 0;
        try{
            while(result.next()){
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(counter >= 1){
            System.out.println("Работник уже добавлен!");
            return;
        }
        String insert = "INSERT INTO "+ ConstEfficiency.TABLE + "(" + ConstEfficiency.EMPLOYEE_ID +
                ","+ConstEfficiency.HOUR+","+ConstEfficiency.REPRIMAND+")"+" VALUES(?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setInt(1, id);
            prSt.setInt(2, 0);
            prSt.setInt(3, 0);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet getEfficiency(Integer employee_id) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + ConstEfficiency.TABLE + " WHERE " +
                ConstEfficiency.EMPLOYEE_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setInt(1,employee_id);

            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}