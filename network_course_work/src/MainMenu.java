import Tariff.*;
import command.*;
import command.commandable.MenuCommand;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import network.Network;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static javafx.application.Application.launch;

public class MainMenu extends Application {

    private static Network network;



    private LinkedHashMap<String, MenuCommand> menuItems;
    public MainMenu() throws IOException, SQLException {
        network = Network.getNetwork("LvivNet", "+380666990915",
                "lvivnet@microsoft.com");
        menuItems = new LinkedHashMap<>();
        menuItems.put(View.NAME, new View(network));
        menuItems.put(Exit.NAME, new Exit(network));
        menuItems.put(UserNumber.NAME, new UserNumber(network));
        /*menuItems.put(command.Add.NAME, new command.Add(TP));
         menuItems.put(command.Delete.NAME, new command.Delete(TP));
        menuItems.put(Order.NAME, new Order(TP));
        menuItems.put(Agr.NAME, new Agr(TP));
        menuItems.put(Show.NAME, new Show(TP));
       ;*/
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
    private TableView tableTariffs;
    @FXML
    private TableColumn<BaseTariff, Double> ABROAD;
    @FXML
    private TableColumn<BaseTariff, Integer> ID;
    @FXML
    private TableColumn <BaseTariff, Integer> users_col;
    //public TableColumn <BaseTariff, Integer>internet_id;
    @FXML
    private TableColumn<BaseTariff, Double> Internet;
    @FXML
    private TableColumn<BaseTariff, String> NAME;
    @FXML
    private TableColumn<BaseTariff, Double> Other_Net;
    @FXML
    private TableColumn<BaseTariff, Integer> PRICE;
    @FXML
    private TableColumn<BaseTariff, String> SMS;
    @FXML
    private TableColumn<BaseTariff, String> TYPE;
    @FXML
    private TableColumn<BaseTariff, Double> This_Net;
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
    public void initialize() {
        ID.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("ID"));
        NAME.setCellValueFactory(new PropertyValueFactory<BaseTariff, String>("name"));
        TYPE.setCellValueFactory(new PropertyValueFactory<BaseTariff, String>("type"));
        users_col.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("user"));
        SMS.setCellValueFactory(new PropertyValueFactory<BaseTariff, String>("SMS"));
        This_Net.setCellValueFactory(new PropertyValueFactory<BaseTariff, Double>("thisN"));
        PRICE.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("price"));
        Other_Net.setCellValueFactory(new PropertyValueFactory<BaseTariff, Double>("other"));
        ABROAD.setCellValueFactory(new PropertyValueFactory<BaseTariff, Double>("abroad"));
        Internet.setCellValueFactory(new PropertyValueFactory<BaseTariff, Double>("Internet"));
        //tableTariffs.setItems(tariffData);
    }

    @FXML
    void click(ActionEvent event) throws SQLException, IOException, InterruptedException {
        BaseTariff tariff = null;
        tariffData.clear();
        MenuCommand cm;
        ResultSet rs;
        /*tariffData.addAll(network.getTariffs());
        System.out.println("tariffs are added");*/

       if (event.getSource() == show_all_btn) {
           cm = menuItems.get("view");
           rs = cm.execute(List.of("all"));
           showTariff(rs);
           //showTariff(tariffData);
       } else if (event.getSource() == exit_prg) {
           cm = menuItems.get("exit");
           cm.execute(List.of());
       }
    }

    private void showTariff(ResultSet rs) throws SQLException {
        BaseTariff tariff = null;
        tariffData.clear();
        while (rs.next())
        {
            if (rs.getString("Type").contains("Start")) {
                tariff = new StartTariff(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getInt("Users"),
                        rs.getInt("SMS"),
                        rs.getInt("MinutesThisNet"),
                        rs.getInt("Price"));
            }
            else if (rs.getString("Type").contains("Net")) {
                tariff = new SuperNetTariff(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getInt("Users"),
                        rs.getInt("SMS"),
                        rs.getInt("MinutesThisNet"),
                        rs.getInt("Price"),
                        rs.getInt("MinutesOtherNet"),
                        rs.getInt("Abroad"),
                        rs.getInt("Internet"));
            }
            else {
                tariff = new SuperTariff(rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("Type"),
                        rs.getInt("Users"),
                        rs.getInt("SMS"),
                        rs.getInt("MinutesThisNet"),
                        rs.getInt("Price"),
                        rs.getInt("MinutesOtherNet"),
                        rs.getInt("Abroad"));
            }
            tariffData.add(tariff);
        }

        tableTariffs.setItems(tariffData);
        //tariff_numb_btn.setText("" + network.getNumberTariffs());
        //user_numb_btn.setText("" + network.calculateUserNumber());
    }


}
