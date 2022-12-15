module com.example.itcompanyemploers {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    opens Client to javafx.fxml;
    exports Client;
    exports Client.Controllers;
    opens Client.Controllers to javafx.fxml;

    exports Server.Entity;
    opens Server.Entity to javafx.fxml;
    exports Client.Controllers.Employee;
    opens Client.Controllers.Employee to javafx.fxml;
    exports Client.Controllers.Efficiency;
    opens Client.Controllers.Efficiency to javafx.fxml;
    exports Client.Controllers.Account;
    opens Client.Controllers.Account to javafx.fxml;
    exports Client.Controllers.Statistic;
    opens Client.Controllers.Statistic to javafx.fxml;
}