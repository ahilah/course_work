import Tariff.*;
import command.Exit;
import command.Sort;
import command.View;
import command.add.Add;
import command.add.NewTariffSQL;
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
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static command.Delete.ANSI_RESET;
import static command.Delete.RED_UNDERLINED;

//--module-path "D:\downloads\javaFX\javafx-sdk-19\lib" --command.add-modules javafx.controls,javafx.fxml
// D:\test1.txt tariffs

public class MainMenu extends Application {
    private static Logger log = Logger.getLogger(MainMenu.class.getName());
    private static Network network;
    private NewTariffSQL newTariffSQL;
    private LinkedHashMap<String, MenuCommand> menuItems;
    private ObservableList<BaseTariff> tariffData = FXCollections.observableArrayList();

    public MainMenu() throws IOException, SQLException {
        newTariffSQL = new NewTariffSQL();
        network = Network.getNetwork("LvivNet", "+380666990915",
                "lvivnet@microsoft.com");
        menuItems = new LinkedHashMap<>();
        fillCommands();
    }

    private void fillCommands() {
        log.info("Fill main menu commands");
        menuItems.put(View.NAME, new View(network));
        menuItems.put(Exit.NAME, new Exit(network));
        menuItems.put(Sort.NAME, new Sort(network));
        menuItems.put(command.Delete.NAME, new command.Delete(network));
        menuItems.put(Add.NAME, new Add(network));
    }

    public static Network getNetwork() {
        return network;
    }

    public ObservableList<BaseTariff> getTariffData() {
        return tariffData;
    }

    public void execute() throws InterruptedException, IOException, SQLException {
        log.info("Launch main stage");
        launch();
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
    static Parent networkInfo_sc = null;
    @FXML
    static Scene main_scene;
    @FXML
    static Scene additionStart_scene;
    @FXML
    static Scene additionSuper_scene;
    @FXML
    static Scene additionSuperNet_scene;
    @FXML
    static Scene additionalInfo_scene;
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
    private Text tariff_numb_txt;
    @FXML
    private TextField upper_te;
    @FXML
    private Text user_numb_txt;
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
    private TextField nameStart_txt;
    @FXML
    private TextField sms_Start_txt;
    @FXML
    private TextField this_Start_txt;
    @FXML
    private TextField usersStart_txt;
    @FXML
    private Button addStart_btn;
    @FXML
    private TextField priceStart_txt;
    @FXML
    private TextField abroadSuper_txt;
    @FXML
    private Button addSuper_btn;
    @FXML
    private Button cancelSuper_btn;
    @FXML
    private TextField nameSuper_txt;
    @FXML
    private TextField otherSuper_txt;
    @FXML
    private TextField priceSuper_txt;
    @FXML
    private TextField sms_Super_txt;
    @FXML
    private TextField this_Super_txt;
    @FXML
    private TextField userSuper_txt;
    @FXML
    private TextField abroadSuperNet_txt;
    @FXML
    private Button addSuperNet_btn;
    @FXML
    private Button cancelSuperNet_btn;
    @FXML
    private TextField internetSuperNet_txt;
    @FXML
    private TextField nameSuperNet_txt;
    @FXML
    private TextField otherSuperNet_txt;
    @FXML
    private TextField priceSuperNet_txt;
    @FXML
    private TextField sms_SuperNet_txt;
    @FXML
    private TextField this_SuperNet_txt;
    @FXML
    private TextField userSuperNet_txt;
    @FXML
    private Button networkInfo_btn;
    @FXML
    private Button cancelInfo_btn;
    @FXML
    private Text companyEmail_txt;
    @FXML
    private Text companyName_txt;
    @FXML
    private Text companyNumber_txt;
    @FXML
    private Button showInfo_btn;

    @Override
    public void start(Stage stage) throws Exception {
        log.info("Start stage method is running");
        try {
            main_stage = stage;
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainStage.fxml")));
            log.info("Root stage is loaded");
            addingStart_sc = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("addStartTariff.fxml")));
            log.info("Adding start tariff stage is loaded");
            addingSuper_sc = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("addSuperTariff.fxml")));
            log.info("Adding super tariff stage is loaded");
            addingSuperNet_sc = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("addSuperNetTariff.fxml")));
            log.info("Adding super net tariff stage is loaded");
            networkInfo_sc = FXMLLoader.load(Objects.requireNonNull(MainMenu.class.getResource("networkInfo.fxml")));
            log.info("Showing mobile network info stage is loaded");
            main_scene = new Scene(root);
            additionStart_scene = new Scene(addingStart_sc);
            additionSuper_scene = new Scene(addingSuper_sc);
            additionSuperNet_scene = new Scene(addingSuperNet_sc);
            additionalInfo_scene = new Scene(networkInfo_sc);
            main_stage.setTitle("Курсова робота Гілети Анастасії КН-201");
            main_stage.setScene(main_scene);
            main_stage.show();
        } catch (Exception e) {
            log.error("Stage root hasn't been loaded");
            log.fatal("Accidentally end of program");
            System.exit(-1);
        }
    }

    // без цього ексепшени про null pointer
    @FXML
    public void initialize() {
        if (root == null) {
            log.warn("Root is null. Start initializing table");
            addTable();
        }
    }

    @FXML
    public void addTable() {
        log.info("Adding table");
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
    }

    @FXML
    void click(ActionEvent event) throws SQLException, IOException, InterruptedException {
        log.info("Click method is running");
        tariffData.clear();
        MenuCommand cm;
        ResultSet rs;
        if (event.getSource() == show_all_btn) {
            log.info("Show all button is clicked");
            cm = menuItems.get("view");
            rs = cm.execute(List.of("all"));
            showTariff(rs);
        }
        if (event.getSource() == exit_prg) {
            log.info("Exit the program");
            cm = menuItems.get("exit");
            cm.execute(List.of());
        }
        if (event.getSource() == sort_btn) {
            log.info("Sort button is clicked");
            cm = menuItems.get("sort");
            rs = cm.execute(List.of());
            showTariff(rs);
        }
        if (event.getSource() == delete_btn) {
            log.info("Delete button is clicked");
            cm = menuItems.get("delete");
            rs = cm.execute(Arrays.asList(tariffID_txt.getText()));
            showTariff(rs);
        }
        if (event.getSource() == show_between_btn) {
            log.info("Show between button is clicked");
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
            log.info("Add tariff button is clicked");
            if (start_rbt.isSelected()) {
                main_stage.setScene(additionStart_scene);
                log.info("Try to adding new Start Tariff");
                main_stage.show();
            } else if (super_rbt.isSelected()) {
                main_stage.setScene(additionSuper_scene);
                log.info("Try to adding new Super Tariff");
                main_stage.show();
            } else {
                main_stage.setScene(additionSuperNet_scene);
                log.info("Try to adding new Super Net Tariff");
                main_stage.show();
            }
        }
        if (event.getSource() == addStart_btn) {
            cm = menuItems.get("add");
            cm.execute(Arrays.asList(nameStart_txt.getText(),
                    "Start", usersStart_txt.getText(),
                    sms_Start_txt.getText(),
                    this_Start_txt.getText(),
                    priceStart_txt.getText()));

            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == cancelStart_btn) {
            log.info("Cancel adding Start Tariff button is clicked");
            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == addSuper_btn) {
            cm = menuItems.get("add");
            cm.execute(Arrays.asList(nameSuper_txt.getText(),
                    "Super", userSuper_txt.getText(),
                    sms_Super_txt.getText(),
                    this_Super_txt.getText(),
                    priceSuper_txt.getText(),
                    otherSuper_txt.getText(),
                    abroadSuper_txt.getText()));

            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == cancelSuper_btn){
            log.info("Cancel adding Super Tariff button is clicked");
            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == addSuperNet_btn) {
            cm = menuItems.get("add");
            cm.execute(Arrays.asList(nameSuperNet_txt.getText(),
                    "Net", userSuperNet_txt.getText(),
                    sms_SuperNet_txt.getText(),
                    this_SuperNet_txt.getText(),
                    priceSuperNet_txt.getText(),
                    otherSuperNet_txt.getText(),
                    abroadSuperNet_txt.getText(),
                    internetSuperNet_txt.getText()));

            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == cancelSuperNet_btn) {
            log.info("Cancel adding Super Net Tariff button is clicked");
            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == networkInfo_btn) {
            log.info("Network info button is clicked");
            main_stage.setScene(additionalInfo_scene);
            main_stage.show();
        }
        if (event.getSource() == cancelInfo_btn) {
            log.info("Cancel showing network info button is clicked");
            main_stage.setScene(main_scene);
            main_stage.show();
        }
        if (event.getSource() == showInfo_btn) {
            log.info("Show info button is clicked");
            companyName_txt.setText("" + network.getCompanyName());
            companyEmail_txt.setText("" + network.getCompanyEmail());
            companyNumber_txt.setText("" + network.getCompanyNumber());
            log.info("Info about network was set successfully");
        }
    }

    private void showTariff(ResultSet rs) throws SQLException, IllegalStateException {
        log.info("Showing table");
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
            log.info("Items were set successfully for tariff table");
            showInfoNumbers();
        }
        catch (NullPointerException e) {
            log.error("Null pointer in result set");
            System.out.println("\n\t" + RED_UNDERLINED + "Null pointer!" + ANSI_RESET);
        }
    }
    private void showInfoNumbers() throws SQLException {
        try {
            tariff_numb_txt.setText("" + network.getNumberTariffs());
            user_numb_txt.setText("" + network.calculateUserNumber());
            log.info("Info about tariffs and users was set successfully");
        } catch (SQLException e) {
            log.error("SQL exception in show info tariff and general user number");
        }
    }
}