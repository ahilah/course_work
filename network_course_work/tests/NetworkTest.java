import Tariff.BaseTariff;
import Tariff.StartTariff;
import command.add.NewTariffSQL;
import network.Network;
import org.junit.*;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import static command.Delete.ANSI_RESET;
import static command.Delete.RED_UNDERLINED;
import static org.junit.Assert.*;

public class NetworkTest {
    MainMenu mm;
    Network network;
    static Connection con;
    NewTariffSQL ntsql;

    @Before
    public void NetTest() throws SQLException, IOException {
        mm = new MainMenu();
        network = Network.getNetwork("LvivNet", "+380666990915",
                "lvivnet@microsoft.com");
        con= DriverManager.getConnection("jdbc:sqlserver://DESKTOP-J4UMDEH:1433;" +
                "databaseName=Network;user=student;password=2022;" +
                "encrypt=true;trustServerCertificate=true");
        //tariffs = new ArrayList<>(30);
        ntsql = new NewTariffSQL();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        con.close();
    }

    @Test
    public void getNetwork() throws SQLException, IOException {
        Network network = Network.getNetwork("LvivNet", "+380666990915",
                "lvivnet@microsoft.com");
        assertEquals(network, MainMenu.getNetwork());
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
    }

    private List<BaseTariff> createList(ResultSet rs) throws SQLException, IllegalStateException {
        List<BaseTariff> tariffs = new ArrayList<>();
        BaseTariff tariff = null;
        //tariffs.clear();
        try {
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
            System.out.println("\n\t" + RED_UNDERLINED + "Null pointer!" + ANSI_RESET);
            return null;
        }
    }
}