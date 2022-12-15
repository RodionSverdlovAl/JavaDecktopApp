package Client.Config;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SpinnerValueFactory;

public class EmployeeConfig {

    protected static final String DEP_1 = "Разработка";
    protected static final String DEP_2 = "Фронт";
    protected static final String DEP_3 = "Дизайн";
    protected static final String DEP_4 = "HR";
    protected static final String DEP_5 = "Тестирование";

    protected static final String CITY_1 = "Минск";
    protected static final String CITY_2 = "Гомель";
    protected static final String CITY_3 = "Могилев";
    protected static final String CITY_4 = "Брест";
    protected static final String CITY_5 = "Витебск";
    protected static final String CITY_6 = "Гродно";


    protected static final ObservableList<String> cities = FXCollections.observableArrayList(CITY_1,CITY_2,
            CITY_3,CITY_4,CITY_5,CITY_6);

    protected static final ObservableList<String> departments =
            FXCollections.observableArrayList(DEP_1, DEP_2, DEP_3, DEP_4, DEP_5);

    protected static final SpinnerValueFactory<Integer> svf =
            new SpinnerValueFactory.IntegerSpinnerValueFactory(3,50,10);
}
