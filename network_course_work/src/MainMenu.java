import Tariff.*;
import add.NewTariffSQL;
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
import command.View;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static command.Delete.ANSI_RESET;
import static command.Delete.RED_UNDERLINED;
import static javafx.application.Application.launch;

public class MainMenu extends Application {
    private static Network network;

    private NewTariffSQL newTariffSQL;

    private LinkedHashMap<String, MenuCommand> menuItems;
    public MainMenu() throws IOException, SQLException {
        newTariffSQL = new NewTariffSQL();
        network = Network.getNetwork("LvivNet", "+380666990915",
                "lvivnet@microsoft.com");
        menuItems = new LinkedHashMap<>();
        fillCommands();
    }
    private void fillCommands() {
        menuItems.put(View.NAME, new View(network));
        menuItems.put(Exit.NAME, new Exit(network));
        menuItems.put(Sort.NAME, new Sort(network));
        menuItems.put(command.Delete.NAME, new command.Delete(network));
        menuItems.put(add.Add.NAME, new add.Add(network));
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
    @FXML
    static Stage main_stage;
    @FXML
    static Parent root = null;
    @FXML
    static Parent addingStart_sc = null;
    @FXML
    static Parent addingSuper_sc = null;
    @FXML
    static Parent addingSuperNet_sc = null;
    @FXML
    static Parent price_sc = null;
    @FXML
    static Scene main_scene;
    @FXML
    static Scene additionStart_scene;
    @FXML
    static Scene additionSuper_scene;
    @FXML
    static Scene additionSuperNet_scene;
    @FXML
    static Scene price_scene;

    @Override
    public void start(Stage stage) throws Exception {
        main_stage = stage;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainStage.fxml")));
        addingStart_sc = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("addStartTariff.fxml")));
        addingSuper_sc  = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("addSuperTariff.fxml")));
        addingSuperNet_sc = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("addSuperNetTariff.fxml")));
        main_scene = new Scene(root);
        additionStart_scene = new Scene(addingStart_sc);
        additionSuper_scene = new Scene(addingSuper_sc);
        additionSuperNet_scene = new Scene(addingSuperNet_sc);
        main_stage.setTitle("Курсова робота Гілети Анастасії КН-201");
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    @FXML
    private TableView tableTariffs;
    @FXML
    private TableColumn<BaseTariff, Integer> ABROAD;
    @FXML
    private TableColumn<BaseTariff, Integer> ID;
    @FXML
    private TableColumn <BaseTariff, Integer> users_col;
    @FXML
    private TableColumn<BaseTariff, Integer> Internet;
    @FXML
    private TableColumn<BaseTariff, String> NAME;
    @FXML
    private TableColumn<BaseTariff, Integer> Other_Net;
    @FXML
    private TableColumn<BaseTariff, Integer> PRICE;
    @FXML
    private TableColumn<BaseTariff, String> SMS;
    @FXML
    private TableColumn<BaseTariff, String> TYPE;
    @FXML
    private TableColumn<BaseTariff, Integer> This_Net;
    @FXML
    private ToggleGroup addTariff;
    @FXML
    private Button add_tariff;
    @FXML
    private Button exit_prg;
    @FXML
    private TextField lower_te;
    @FXML
    private RadioButton start_rbt;
    @FXML
    private RadioButton super_rbt;
    @FXML
    private RadioButton net_rbt;
    @FXML
    private ToggleGroup showGP;
    @FXML
    private Button show_all_btn;
    @FXML
    private Button show_between_btn;
    @FXML
    private Text tariff_numb_btn;
    @FXML
    private TextField upper_te;
    @FXML
    private Text user_numb_btn;
    @FXML
    private Button sort_btn;
    @FXML
    private Button delete_btn;
    @FXML
    private TextField tariffID_txt;
    @FXML
    private RadioButton thisNet_rbt;
    @FXML
    private RadioButton sms_rbt;
    @FXML
    private RadioButton price_rbt;
    @FXML
    private RadioButton internet_rbt;
    @FXML
    private RadioButton abroad_rbt;
    @FXML
    private RadioButton otherNet_rbt;
    @FXML
    private Button cancelStart_btn;
    @FXML
    private TextField nameStart_rbt;
    @FXML
    private TextField sms_Start_rbt;
    @FXML
    private TextField this_Start_rbt;
    @FXML
    private TextField usersStart_rbt;
    @FXML
    private Button addStart_btn;
    @FXML
    private TextField priceStart_btn;
    @FXML
    private TextField abroadSuper_txt;
    @FXML
    private Button addSuper_btn;
    @FXML
    private Button cancelSuper_btn;
    @FXML
    private TextField nameSuper_rbt;
    @FXML
    private TextField otherSuper_txt;
    @FXML
    private TextField priceSuper_btn;
    @FXML
    private TextField sms_Super_rbt;
    @FXML
    private TextField this_Super_rbt;
    @FXML
    private TextField userSuper_rbt;

    // без цього ексепшени про null pointer
    @FXML
    public void initialize() {
        if (root == null) {
            addTable();
        }
    }

    @FXML
    public void addTable() {
        ID.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("ID"));
        NAME.setCellValueFactory(new PropertyValueFactory<BaseTariff, String>("name"));
        TYPE.setCellValueFactory(new PropertyValueFactory<BaseTariff, String>("type"));
        users_col.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("user"));
        SMS.setCellValueFactory(new PropertyValueFactory<BaseTariff, String>("SMS"));
        This_Net.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("thisN"));
        PRICE.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("price"));
        Other_Net.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("other"));
        ABROAD.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("abroad"));
        Internet.setCellValueFactory(new PropertyValueFactory<BaseTariff, Integer>("Internet"));
        //tableTariffs.setItems(tariffData);
    }

    @FXML
    void click(ActionEvent event) throws SQLException, IOException, InterruptedException {
        //network.setLastID();
        tariffData.clear();
        MenuCommand cm;
        ResultSet rs;
       if (event.getSource() == show_all_btn) {
           cm = menuItems.get("view");
           rs = cm.execute(List.of("all"));
           showTariff(rs);
       }
       if (event.getSource() == exit_prg) {
           cm = menuItems.get("exit");
           cm.execute(List.of());
       }
       if (event.getSource() == sort_btn) {
           cm = menuItems.get("sort");
           rs = cm.execute(List.of());
           showTariff(rs);
       }
       if (event.getSource() == delete_btn) {
           cm = menuItems.get("delete");
           rs = cm.execute(Arrays.asList(tariffID_txt.getText()));
           showTariff(rs);
       }
        if (event.getSource() == show_between_btn) {
            cm = menuItems.get("view");
            try {
                int lower = Integer.parseInt(lower_te.getText());
                int upper = Integer.parseInt(upper_te.getText());
                if (thisNet_rbt.isSelected()) {
                    rs = cm.execute(Arrays.asList("this", "" + lower, "" + upper));
                } else if (sms_rbt.isSelected()) {
                    rs = cm.execute(Arrays.asList("sms", "" + lower, "" + upper));
                } else if (price_rbt.isSelected()){
                    rs = cm.execute(Arrays.asList("price", "" + lower, "" + upper));
                } else if (otherNet_rbt.isSelected()) {
                    rs = cm.execute(Arrays.asList("other", "" + lower, "" + upper));
                } else if (abroad_rbt.isSelected()) {
                    rs = cm.execute(Arrays.asList("abroad", "" + lower, "" + upper));
                } else {
                    rs = cm.execute(Arrays.asList("internet", "" + lower, "" + upper));
                }
                showTariff(rs);
            } catch (InterruptedException | IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        if (event.getSource() == add_tariff) {
            if (start_rbt.isSelected()) {
                main_stage.setScene(additionStart_scene);
                main_stage.show();
            } else if (super_rbt.isSelected()) {
                main_stage.setScene(additionSuper_scene);
                main_stage.show();
            } else {
                main_stage.setScene(additionSuperNet_scene);
                main_stage.show();
            }
        }
        if (event.getSource() == addStart_btn) {
            cm = menuItems.get("add");
            cm.execute(Arrays.asList(nameStart_rbt.getText(),
                    "Start", usersStart_rbt.getText(),
                    sms_Start_rbt.getText(),
                    this_Start_rbt.getText(),
                    priceStart_btn.getText()));

            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == cancelStart_btn) {
            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == addSuper_btn) {
            cm = menuItems.get("add");
            cm.execute(Arrays.asList(nameSuper_rbt.getText(),
                    "Super", userSuper_rbt.getText(),
                    sms_Super_rbt.getText(),
                    this_Super_rbt.getText(),
                    priceSuper_btn.getText(),
                    otherSuper_txt.getText(),
                    abroadSuper_txt.getText()));

            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == cancelSuper_btn){
            main_stage.setScene(main_scene);
            main_stage.show();
        }

    }

    private void showTariff(ResultSet rs) throws SQLException, IllegalStateException {
        BaseTariff tariff = null;
        tariffData.clear();
        try {
            while (rs.next()) {
                if (rs.getString("Type").contains("Start")) {
                    tariff = newTariffSQL.newStartTariff(rs);
                } else if (rs.getString("Type").contains("Net")) {
                    tariff = newTariffSQL.newSuperNetTariff(rs);
                } else {
                    tariff = newTariffSQL.newSuperTariff(rs);
                }
                tariffData.add(tariff);
            }
            tableTariffs.setItems(tariffData);
            showInfoNumbers();
        }
        catch (NullPointerException e) {
            System.out.println("\n\t" + RED_UNDERLINED + "Null pointer!" + ANSI_RESET);
        }
    }
    private void showInfoNumbers() throws SQLException {
        tariff_numb_btn.setText("" + network.getNumberTariffs());
        user_numb_btn.setText("" + network.calculateUserNumber());
    }


}
