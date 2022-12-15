package Server.Controllers;

import Server.Services.DataBase.DataBaseHandlerEfficiency;

public class EfficiencyController {
    static public void addEfficiency(String id_){
        Integer id = Integer.parseInt(id_);
        DataBaseHandlerEfficiency db = new DataBaseHandlerEfficiency();
        db.addEfficiency(id);
    }
}
