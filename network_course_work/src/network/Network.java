package network;

import Tariff.StartTariff;
import Tariff.SuperNetTariff;
import Tariff.SuperTariff;

import java.io.IOException;
import java.sql.*;

import static command.Delete.ANSI_RESET;
import static command.Delete.RED_UNDERLINED;

public class Network {
    private static Network network;
    public static final String ANSI_GREEN = "\033[0;32m";   // GREEN
    private final String companyName;
    private final String companyNumber;
    private final String companyEmail;


    public static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DEFAULT_URL = "jdbc:sqlserver://DESKTOP-J4UMDEH:1433;databaseName=Deanery";
    private static final String DEFAULT_USERNAME = "student";
    private static final String DEFAULT_PASSWORD = "2022";
    public static final String FIND_ALL_CUSTOMERS_QUERY = "SELECT Fname, Address FROM Customers ";
    private static final String BY_FIRST_NAME = "WHERE FNAME = ? ";

    private static Connection con;
    private int lastID = 17;

    public static Network getNetwork(String companyName, String companyNumber, String companyEmail) throws IOException, SQLException {
        if (network == null) {
            network = new Network(companyName, companyNumber, companyEmail);
        }
        return network;
    }

    private Network(String companyName, String companyNumber, String companyEmail) throws IOException, SQLException {
        this.companyName = companyName;
        this.companyNumber = companyNumber;
        this.companyEmail = companyEmail;
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-J4UMDEH:1433;" +
                    "databaseName=Network;user=student;password=2022;" +
                    "encrypt=true;trustServerCertificate=true");
            //System.out.println("\n\t" + ANSI_GREEN + "Connected to database!" + ANSI_RESET);

        } catch (SQLException e) {
            System.out.println("\n\t" + RED_UNDERLINED + "Not connected to database. Error.\n\n" + ANSI_RESET);
            exit();
            System.exit(1);
        }

    }

    public static Connection getCon() {
        return con;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void addStart(StartTariff tariff) throws SQLException {
        String sql = "INSERT INTO Tariff ([Name], [Type], Users," +
                " SMS, MinutesThisNet, Price) " +
                "VALUES(?, ?, ?, ?, ?, ?)";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, tariff.getName());
        stat.setString(2, tariff.getType());
        stat.setInt(3, tariff.getUser());
        stat.setInt(4, tariff.getSMS());
        stat.setInt(5, tariff.getThisN());
        stat.setInt(6, tariff.getPrice());

        stat.executeUpdate();
        lastID = tariff.getID();
    }

    public void addSuper(SuperTariff tariff) throws SQLException {
        String sql = "INSERT INTO Tariff ([Name], [Type], Users," +
                " SMS, MinutesThisNet, Price, MinutesOtherNet, Abroad) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, tariff.getName());
        stat.setString(2, tariff.getType());
        stat.setInt(3, tariff.getUser());
        stat.setInt(4, tariff.getSMS());
        stat.setInt(5, tariff.getThisN());
        stat.setInt(6, tariff.getPrice());
        stat.setInt(7, tariff.getOther());
        stat.setInt(8, tariff.getAbroad());

        stat.executeUpdate();
        lastID = tariff.getID();
    }

    public void addSuperNet(SuperNetTariff tariff) throws SQLException {
        String sql = "INSERT INTO Tariff ([Name], [Type], Users," +
                " SMS, MinutesThisNet, Price, MinutesOtherNet, Abroad, Internet) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement stat = con.prepareStatement(sql);
        stat.setString(1, tariff.getName());
        stat.setString(2, tariff.getType());
        stat.setInt(3, tariff.getUser());
        stat.setInt(4, tariff.getSMS());
        stat.setInt(5, tariff.getThisN());
        stat.setInt(6, tariff.getPrice());
        stat.setInt(7, tariff.getOther());
        stat.setInt(8, tariff.getAbroad());
        stat.setInt(9, tariff.getInternet());

        stat.executeUpdate();
        lastID = tariff.getID();
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT TOP (1) ID AS L FROM Tariff ORDER BY ID DESC");
        if (rs.next()) {
            lastID = Integer.parseInt(rs.getString("S"));
        }
        lastID = 0;
    }

    public int getNumberTariffs() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT COUNT(ID) AS S FROM Tariff");
        if (rs.next())
            return Integer.parseInt(rs.getString("S"));
        return 0;
    }

    public int getNumberTariffsParam(String nameCol) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT COUNT(" + nameCol + ") AS S FROM Tariff");
        if (rs.next())
            return Integer.parseInt(rs.getString("S"));
        return 0;
    }

    public int calculateUserNumber() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT SUM(Users) AS S FROM Tariff");
        if (rs.next())
            return Integer.parseInt(rs.getString("S"));
        return 0;
    }

    public ResultSet printAllTariffs() throws SQLException {
        Statement stat = con.createStatement();
        return stat.executeQuery("SELECT * \n" +
                "  FROM [Network].[dbo].[Tariff]");
    }

    public ResultSet sortTariffs() throws SQLException {
        Statement stat = con.createStatement();
        return stat.executeQuery("SELECT * FROM Tariff ORDER BY Price ASC");
    }

    public ResultSet removeTariff(int ID) throws SQLException {
        Statement stat = con.createStatement();
        stat.executeUpdate("DELETE FROM Tariff WHERE ID = " + ID);
        return printAllTariffs();
    }

    public boolean isTariffIDExists(int ID) throws SQLException {
        Statement stat = con.createStatement();
        return stat.executeQuery("SELECT * FROM Tariff WHERE ID = " + ID) != null;
    }

    public void exit() throws SQLException {
        con.close();
    }

    public ResultSet printTariffsWithSMS(int min, int max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff" +
                " WHERE SMS >= ? AND SMS <= ?");
        stat.setInt(1, min);
        stat.setInt(2, max);
        return stat.executeQuery();
    }

    public ResultSet printTariffsWithPrice(int min, int max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                "WHERE Price >= ? AND Price <= ?");
        stat.setInt(1, min);
        stat.setInt(2, max);
        return stat.executeQuery();
    }

    public ResultSet printTariffsWithMinThisNet(int min, int max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                "WHERE MinutesThisNet >= ? AND MinutesThisNet <= ?");
        stat.setInt(1, min);
        stat.setInt(2, max);
        return stat.executeQuery();
    }

    public ResultSet printTariffsWithMinOtherNet(int min, int max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                "WHERE MinutesOtherNet >= ? AND MinutesOtherNet <= ?");
        stat.setInt(1, min);
        stat.setInt(2, max);
        return stat.executeQuery();
    }

    public ResultSet printTariffsWithAbroad(int min, int max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                "WHERE Abroad >= ? AND Abroad <= ?");
        stat.setInt(1, min);
        stat.setInt(2, max);
        return stat.executeQuery();
    }

    public ResultSet printTariffsWithInternet(int min, int max) throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                "WHERE Internet >= ? AND Internet <= ?");
        stat.setInt(1, min);
        stat.setInt(2, max);
        return stat.executeQuery();
    }

    public ResultSet one() throws SQLException {
        PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                "WHERE ID = ? OR ID = ?");
        stat.setInt(1, 1);
        stat.setInt(2, 6);
        return stat.executeQuery();
    }
}


/*
    //CreateList();private void CreateList() throws IOException {

        tariffs = new ArrayList<>() {};
        tariffs.command.add(new StartTariff(1,"1", "Start",5, 123, 3, 10));
        tariffs.command.add( new StartTariff(2,"2", "Start", 9,12, 4, 15));
        tariffs.command.add(new SuperTariff(3,"3", "Super", 10,55, 40, 150, 36, 66));
        tariffs.command.add(new SuperNetTariff(4,"3", "SuperNet", 20, 55,
                40, 150, 36, 66, 999));
        /*tariffs.command.add();
        tariffs.command.add();
        tariffs.command.add();
         // readFile();


    }*/