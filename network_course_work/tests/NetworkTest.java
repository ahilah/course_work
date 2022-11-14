import Tariff.BaseTariff;
import Tariff.StartTariff;
import Tariff.SuperNetTariff;
import Tariff.SuperTariff;
import command.Delete;
import command.Exit;
import command.Sort;
import command.View;
import command.add.Add;
import command.add.NewTariffSQL;
import network.Network;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.*;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static command.Delete.ANSI_RESET;
import static command.Delete.RED_UNDERLINED;
import static org.junit.Assert.*;

public class NetworkTest {
    private static final Logger logger = Logger.getLogger(NetworkTest.class.getName());
    MainMenu mm;
    Network network;
    static Connection con;
    NewTariffSQL ntsql;

    @Before
    public void NetTest() throws SQLException, IOException {
        DOMConfigurator.configure("D:\\programming\\appliedProgramming" +
                "\\course_work\\network_course_work\\resources\\log4j.xml");
        logger.info("Testing network was started");
        mm = new MainMenu();
        network = Network.getNetwork("LvivNet", "+380666990915",
                "lvivnet@microsoft.com");
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-J4UMDEH:1433;" +
                "databaseName=Network;user=student;password=2022;" +
                "encrypt=true;trustServerCertificate=true");
        logger.info("Connected to SQL");
        //tariffs = new ArrayList<>(30);
        ntsql = new NewTariffSQL();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        con.close();
        logger.info("Connection to SQL was closed");
    }

    @Test
    public void getNumberTariffs(){
        try {
            Assertions.assertEquals(17, network.getNumberTariffs());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getNetwork() throws SQLException, IOException {
        Network network = Network.getNetwork("LvivNet", "+380666990915",
                "lvivnet@microsoft.com");
        assertEquals(network, MainMenu.getNetwork());
        assertEquals(network.getCompanyName(), MainMenu.getNetwork().getCompanyName());
        assertEquals(network.getCompanyEmail(), MainMenu.getNetwork().getCompanyEmail());
        assertEquals(network.getCompanyNumber(), MainMenu.getNetwork().getCompanyNumber());
    }

    @Test
    public void getCon() throws SQLException {
        Connection expectedCon = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-J4UMDEH:1433;" +
                "databaseName=Network;user=student;password=2022;" +
                "encrypt=true;trustServerCertificate=true");
        Connection actualCon = Network.getCon();
        assertNotEquals(expectedCon, actualCon);
    }

    @Test
    public void calculateUserNumber() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-J4UMDEH:1433;" +
                "databaseName=Network;user=student;password=2022;" +
                "encrypt=true;trustServerCertificate=true");
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT SUM(Users) AS S FROM Tariff");
        int expectedUserNumber;
        rs.next();
        expectedUserNumber =Integer.parseInt(rs.getString("S"));
        int actualUserNumber = network.calculateUserNumber();
        assertEquals(expectedUserNumber, actualUserNumber);
    }

    @Test
    public void sortTariffs() throws SQLException {
        //Statement stat = con.createStatement();
        //ResultSet expSet = stat.executeQuery("SELECT * FROM Tariff ORDER BY Price ASC");
        ResultSet actSet = network.sortTariffs();
        List<BaseTariff> expList = new ArrayList<>(2);
        expList.add(new StartTariff(17, "Angst", "Start", 304, 25, 300, 20));
        List<BaseTariff> actList = createList(actSet);
        assertEquals(expList.get(0).getTariffID(), actList.get(0).getTariffID());

        Sort sort = new Sort(network);
        Assertions.assertNotNull(sort.execute(Collections.singletonList("")));
    }

    private List<BaseTariff> createList(ResultSet rs) throws SQLException, IllegalStateException {
        List<BaseTariff> tariffs = new ArrayList<>();
        BaseTariff tariff = null;
        //tariffs.clear();
        try {
            logger.info("Start creating testing list of tariffs");
            while (rs.next()) {
                if (rs.getString("Type").contains("Start")) {
                    tariff = ntsql.newStartTariff(rs);
                } else if (rs.getString("Type").contains("Net")) {
                    tariff = ntsql.newSuperNetTariff(rs);
                } else {
                    tariff = ntsql.newSuperTariff(rs);
                }
                tariffs.add(tariff);
            }
            return tariffs;
        }
        catch (NullPointerException e) {
            logger.warn("List of testing tariffs is null");
            System.out.println("\n\t" + RED_UNDERLINED + "Null pointer!" + ANSI_RESET);
            return null;
        }
    }

    @Test
    public void SuperNetTariffTest() {
        SuperNetTariff superNT = new SuperNetTariff(39,"Hayloft","SuperNet",
                200, 75, 200,
                120, 50, 11, 10);
        Assertions.assertEquals("Super Net Tariff Hayloft\n" +
                "\t\t\t(number of SMS: 75,\n" +
                "\t\t\tnumber of minutes on this operator: 200,\n" +
                "\t\t\tprice of tariff in hryvnias: 120,\n" +
                "\t\t\ttariff ID: 39,\n" +
                "\t\t\tgeneral number of tariff users: 200,\n" +
                "\t\t\tnumber of minutes on other network: 50,\n" +
                "\t\t\tnumber of minutes on other countries: 11,\n" +
                "\t\t\tGB of mobile Internet: 10)", superNT.toString());

        Assertions.assertEquals(11, superNT.getAbroad());
        Assertions.assertEquals(50, superNT.getOther());
        Assertions.assertEquals(10, superNT.getInternet());
        Assertions.assertEquals(39, superNT.getID());
        Assertions.assertEquals("Hayloft", superNT.getName());
        Assertions.assertEquals(39, superNT.getTariffID());
        Assertions.assertEquals(120, superNT.getPrice());
        Assertions.assertEquals(75, superNT.getSMS());
        Assertions.assertEquals(200, superNT.getThisN());
        Assertions.assertEquals("SuperNet", superNT.getType());
        Assertions.assertEquals(200, superNT.getUser());
    }

    @Test
    public void SuperTariffTest() {
        SuperTariff superT = new SuperTariff(114,"Test1","Super",
                100, 15, 180,
                50, 120, 15);
        Assertions.assertEquals("Super Tariff Test1\n" +
                "\t\t\t(number of SMS: 15,\n" +
                "\t\t\tnumber of minutes on this operator: 180,\n" +
                "\t\t\tprice of tariff in hryvnias: 50,\n" +
                "\t\t\ttariff ID: 114,\n" +
                "\t\t\tgeneral number of tariff users: 100,\n" +
                "\t\t\tnumber of minutes on other network: 120)", superT.toString());

        Assertions.assertEquals(15, superT.getAbroad());
        Assertions.assertEquals(120, superT.getOther());
    }

    @Test
    public void StartTariffTest() {
        StartTariff startT = new StartTariff(24,"Test2","Start",
                60, 31, 400,
                45);
        Assertions.assertEquals("Start Tariff Test2\n" +
                "\t\t\t(number of SMS: 31,\n" +
                "\t\t\tnumber of minutes on this operator: 400,\n" +
                "\t\t\tprice of tariff in hryvnias: 45,\n" +
                "\t\t\ttariff ID: 24,\n" +
                "\t\t\tgeneral number of tariff users: 60)", startT.toString());
    }

    @Test
    public void ViewTest() {
        View view = new View(network);
        List<String> param = new ArrayList<>(7);
        param.add("all");
        try {
            Assertions.assertNotNull(view.execute(param));
            param.clear();
            param.add("sms");
            param.add("1");
            param.add("1000");
            Assertions.assertNotNull(view.execute(param));

            param.clear();
            param.add("price");
            param.add("1");
            param.add("1000");
            Assertions.assertNotNull(view.execute(param));

            param.clear();
            param.add("this");
            param.add("1");
            param.add("1000");
            Assertions.assertNotNull(view.execute(param));

            param.clear();
            param.add("other");
            param.add("1");
            param.add("1000");
            Assertions.assertNotNull(view.execute(param));

            param.clear();
            param.add("abroad");
            param.add("1");
            param.add("1000");
            Assertions.assertNotNull(view.execute(param));

            param.clear();
            param.add("internet");
            param.add("1");
            param.add("1000");
            Assertions.assertNotNull(view.execute(param));
        } catch (SQLException e) {
            logger.error("SQL exception");
            throw new RuntimeException(e);
        }
    }

    @Test
    public void DeleteTariffTest() throws SQLException, InterruptedException {
        Delete delete = new Delete(network);

        List<String> param = new ArrayList<>(7);
        param.add("lol");

        Assertions.assertNull(delete.execute(param));
    }

    @Test
    public void AddTariffTest() throws SQLException, InterruptedException {
        Add add = new Add(network);
        Assertions.assertNull(add.execute(Collections.singletonList("1 2 3")));

        List<String> param = new ArrayList<>(7);

        param.add("1");
        param.add("test");
        param.add("3");
        param.add("4");
        param.add("5");
        param.add("6");
        param.add("7");
        Assertions.assertNull(add.execute(param));
    }

    @Test
    public void AddAndDeleteSuperTariffTest() throws SQLException, InterruptedException {
        Add add = new Add(network);
        Delete delete = new Delete(network);
        Assertions.assertNull(add.execute(Collections.singletonList("1 2 3")));

        List<String> param = new ArrayList<>(7);
        param.add("KhersonLove");
        param.add("super");
        param.add("300");
        param.add("40");
        param.add("500");
        param.add("36");
        param.add("7");
        param.add("8");
        Assertions.assertNull(add.execute(param));

        List<String> id = new ArrayList<>(7);
        id.add("27");
        Assertions.assertNotNull(delete.execute(id));
    }

    @Test
    public void AddAndDeleteSuperNetTariffTest() throws SQLException, InterruptedException {
        Add add = new Add(network);
        Assertions.assertNull(add.execute(Collections.singletonList("1 2 3")));

        List<String> param = new ArrayList<>(7);
        param.add("KhersonLove");
        param.add("net");
        param.add("300");
        param.add("40");
        param.add("500");
        param.add("36");
        param.add("7");
        param.add("8");
        param.add("9");
        Assertions.assertNull(add.execute(param));

        Assertions.assertNotNull(network.removeTariff(28));
    }

    @Test
    public void AddStartTariffTest() throws SQLException, InterruptedException {
        Add add = new Add(network);
        Assertions.assertNull(add.execute(Collections.singletonList("1 2 3")));

        List<String> param = new ArrayList<>(7);

        param.add("1");
        param.add("start");
        param.add("3");
        param.add("4");
        param.add("5");
        param.add("6");
        param.add("7");
        network.exit();
        Assertions.assertNull(add.execute(param));
        logger.warn("Disconnected from SQL");

        Assertions.assertEquals(0, network.getNumberTariffs());
        Assertions.assertNull(network.printAllTariffs());
        Assertions.assertNull(network.printTariffsWithInternet(1,100));
        Assertions.assertNull(network.printTariffsWithAbroad(1,100));
        Assertions.assertNull(network.printTariffsWithMinOtherNet(1,100));
        Assertions.assertNull(network.printTariffsWithSMS(1,100));
        Assertions.assertNull(network.printTariffsWithMinThisNet(1,100));
        Assertions.assertNull(network.printTariffsWithPrice(1,100));
        Assertions.assertTrue(network.isTariffIDExists(20));
        Assertions.assertNull(network.removeTariff(30));
        Assertions.assertNull(network.sortTariffs());
        Assertions.assertEquals(0, network.calculateUserNumber());
        network.exit();
    }

    @Test
    public void ExitProgramTest() throws SQLException, InterruptedException {
        Exit exit = new Exit(network);
        Assertions.assertNull(exit.execute(Collections.singletonList("")));
    }
}