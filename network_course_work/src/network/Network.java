package network;

import Tariff.BaseTariff;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class Network {
    private static Network network;
    private final String companyName;
    private final String companyNumber;
    private final String companyEmail;
    //private List<BaseTariff> tariffs;
    public static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static final String DEFAULT_URL = "jdbc:sqlserver://DESKTOP-J4UMDEH:1433;databaseName=Deanery";
    private static final String DEFAULT_USERNAME = "student";
    private static final String DEFAULT_PASSWORD = "2022";
    public static final String FIND_ALL_CUSTOMERS_QUERY = "SELECT Fname, Address FROM Customers ";
    private static final String BY_FIRST_NAME = "WHERE FNAME = ? ";
    private static Connection con;



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
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-J4UMDEH:1433;databaseName=Network;user=student;password=2022;encrypt=true;trustServerCertificate=true");
        System.out.println("open");
    }



    public int getNumberTariffs() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT COUNT(ID) AS S FROM Tariff");
        if (rs.next())
            return Integer.parseInt(rs.getString("S"));
        return 0;
    }
    /*public List<BaseTariff> getTariffs() {
        return tariffs;
    }*/

    public int calculateUserNumber() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT SUM(Users) AS S FROM Tariff");
        if (rs.next())
            return Integer.parseInt(rs.getString("S"));
        return 0;

        /*int sumUsers = 0;
        for (BaseTariff tariff: network.getTariffs()) {
            sumUsers += tariff.getUser();
        }
        return sumUsers;*/
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
        System.out.println("\n\nworks");
        return printAllTariffs();
    }
    public boolean isTariffIDExists(int ID) throws SQLException {
        Statement stat = con.createStatement();
        return stat.executeQuery("SELECT * FROM Tariff WHERE ID = " + ID) != null;
    }
    public void exit() throws SQLException {
        con.close();
    }

}


/*
    //CreateList();private void CreateList() throws IOException {

        tariffs = new ArrayList<>() {};
        tariffs.add(new StartTariff(1,"1", "Start",5, 123, 3, 10));
        tariffs.add( new StartTariff(2,"2", "Start", 9,12, 4, 15));
        tariffs.add(new SuperTariff(3,"3", "Super", 10,55, 40, 150, 36, 66));
        tariffs.add(new SuperNetTariff(4,"3", "SuperNet", 20, 55,
                40, 150, 36, 66, 999));
        /*tariffs.add();
        tariffs.add();
        tariffs.add();
         // readFile();


    }*/