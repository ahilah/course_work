import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Objects;

import static javafx.application.Application.launch;

public class MainMenu extends Application {

    private static Network network;

    private LinkedHashMap<String, MenuCommand> menuItems;
    public MainMenu() {
        network = new Network(/*"TaxiPark1", "+380994130557", "taxipark1@taxi.com"*/);
        menuItems = new LinkedHashMap<>();
        /*menuItems.put(Add.NAME, new Add(TP));
        menuItems.put(Delete.NAME, new Delete(TP));
        menuItems.put(Order.NAME, new Order(TP));
        menuItems.put(Agr.NAME, new Agr(TP));
        menuItems.put(Show.NAME, new Show(TP));
        menuItems.put(Exit.NAME, new Exit(TP));*/
    }

    public void execute() throws InterruptedException, IOException, SQLException {
        launch();
    }

    private ObservableList<BaseTariff> tariffData = FXCollections.observableArrayList();

    public ObservableList<BaseTariff> getTariffData() {
        return tariffData;
    }

    @FXML
    public void handle(ActionEvent actionEvent) {

    }
    static Stage main_stage;
    static Parent root = null;
    static Parent adding_sc = null;
    static Parent price_sc = null;
    static Scene main_scene;
    static Scene addition_scene;
    static Scene price_scene;

    @Override
    public void start(Stage stage) throws Exception {
        main_stage = stage;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        /*adding_sc = FXMLLoader.load(MainMenu.class.getResource("adding.fxml"));
        price_sc = FXMLLoader.load(MainMenu.class.getResource("price.fxml"));*/
        main_scene = new Scene(root);
        /*addition_scene = new Scene(adding_sc, 1000, 800);
        price_scene = new Scene(price_sc, 1000, 800);*/
        main_stage.setTitle("Курсова робота Гілети Анастасії КН-201");
        main_stage.setScene(main_scene);
        main_stage.show();
    }

    @FXML
    private TableColumn<BaseTariff, Double> ABROAD;

    @FXML
    private TableColumn<BaseTariff, Integer> ID;

    @FXML
    private TableColumn<BaseTariff, String> Internet;

    @FXML
    private TableColumn<BaseTariff, String> NAME;

    @FXML
    private TableColumn<BaseTariff, String> Other_Net;

    @FXML
    private TableColumn<BaseTariff, String> PRICE;

    @FXML
    private TableColumn<BaseTariff, String> SMS;

    @FXML
    private TableColumn<BaseTariff, String> TYPE;

    @FXML
    private TableColumn<BaseTariff, String> This_Net;

    @FXML
    private ToggleGroup addTariff;

    @FXML
    private Button add_tariff;

    @FXML
    private Button exit_prg;

    @FXML
    private TextField lower_te;

    @FXML
    private RadioButton nt_rbt;

    @FXML
    private ToggleGroup showGP;

    @FXML
    private Button show_all_btn;

    @FXML
    private Button show_tariff_btn;

    @FXML
    private RadioButton sr_rbt;

    @FXML
    private RadioButton strt_rbt;

    @FXML
    private Text tariff_numb_btn;

    @FXML
    private TextField upper_te;

    @FXML
    private Text user_numb_btn;
    @FXML
    private Button sort_btn;


    @FXML
    void click(ActionEvent event) {

    }

}
