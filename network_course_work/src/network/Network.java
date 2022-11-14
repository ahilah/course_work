package network;

import Tariff.StartTariff;
import Tariff.SuperNetTariff;
import Tariff.SuperTariff;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.*;

import static command.Delete.ANSI_RESET;
import static command.Delete.RED_UNDERLINED;

public class Network {
    private static Logger logger = Logger.getLogger(Network.class.getName());
    private static Network network;
    public static final String ANSI_GREEN = "\033[0;32m";   // GREEN
    private final String companyName;
    private final String companyNumber;
    private final String companyEmail;
    private static Connection con;
    private int lastID = 17;

    public static Network getNetwork(String companyName, String companyNumber, String companyEmail)
            throws IOException, SQLException {
        if (network == null) {
            try {
                network = new Network(companyName, companyNumber, companyEmail);
                logger.info("Instance of network was get successfully");
            } catch (IOException e) {
                logger.fatal("Can't get new instance of network");
            } catch (SQLException e) {
                logger.fatal("Can't get new instance of network because of sql exception");
            }
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
            logger.info("SQL Driver was successfully connected");
            System.out.println("\n\t" + ANSI_GREEN + "Connected to database!" + ANSI_RESET);
        } catch (SQLException e) {
            System.out.println("\n\t" + RED_UNDERLINED + "Not connected to database. Error.\n\n" + ANSI_RESET);
            try {
                exit();
            } catch (SQLException ex) {
                logger.error("Can't close SQL Connector");
            }
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
        try {
            PreparedStatement stat = con.prepareStatement(sql);
            logger.info("Executing statement for adding new Start Tariff was created successfully");
            stat.setString(1, tariff.getName());
            stat.setString(2, tariff.getType());
            stat.setInt(3, tariff.getUser());
            stat.setInt(4, tariff.getSMS());
            stat.setInt(5, tariff.getThisN());
            stat.setInt(6, tariff.getPrice());

            stat.executeUpdate();
            lastID = tariff.getID();
        } catch (SQLException e) {
            logger.error("Can't set query string or execute update in Start Tariff");
        }
    }

    public void addSuper(SuperTariff tariff) throws SQLException {
        String sql = "INSERT INTO Tariff ([Name], [Type], Users," +
                " SMS, MinutesThisNet, Price, MinutesOtherNet, Abroad) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stat = con.prepareStatement(sql);
            logger.info("Executing statement for adding new Super Tariff was created successfully");
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
        } catch (SQLException e) {
            logger.error("Can't set query string or execute update in Super Tariff");
        }
    }

    public void addSuperNet(SuperNetTariff tariff) throws SQLException {
        String sql = "INSERT INTO Tariff ([Name], [Type], Users," +
                " SMS, MinutesThisNet, Price, MinutesOtherNet, Abroad, Internet) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stat = con.prepareStatement(sql);
            logger.info("Executing statement for adding new Super Net Tariff was created successfully");
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
        } catch (SQLException e) {
            logger.error("Can't set query string or execute update in Super Net Tariff");
        }
    }

    public int getLastID() {
        return lastID;
    }

    public int getNumberTariffs() throws SQLException {
        try {
            Statement stat = con.createStatement();
            logger.info("Executing statement for getting number of tariffs was created successfully");
            ResultSet rs = stat.executeQuery("SELECT COUNT(ID) AS S FROM Tariff");
            if (rs.next()) {
                return Integer.parseInt(rs.getString("S"));
            }
            logger.warn("Table Tariff is empty");
            return 0;
        } catch (SQLException e) {
            logger.error("Can't execute query for getting number of tariffs");
            return 0;
        }
    }

    public int calculateUserNumber() throws SQLException {
        try {
            Statement stat = con.createStatement();
            logger.info("Executing statement for calculating number of customers was created successfully");
            ResultSet rs = stat.executeQuery("SELECT SUM(Users) AS S FROM Tariff");
            if (rs.next()) {
                return Integer.parseInt(rs.getString("S"));
            }
            logger.warn("Table Tariff is empty");
            return 0;
        } catch (SQLException e) {
            logger.error("Can't execute query for calculating number of customers");
            return 0;
        }
    }

    public ResultSet printAllTariffs() throws SQLException {
        try {
            Statement stat = con.createStatement();
            logger.info("Executing statement for getting tariff list was created successfully");
            return stat.executeQuery("SELECT * \n" +
                    "  FROM [Network].[dbo].[Tariff]");
        } catch (SQLException e) {
            logger.error("Can't execute query for getting tariff list");
            return null;
        }
    }

    public ResultSet sortTariffs() throws SQLException {
        try {
            Statement stat = con.createStatement();
            logger.info("Executing statement for sorting list of tariffs was created successfully");
            return stat.executeQuery("SELECT * FROM Tariff ORDER BY Price ASC");
        } catch (SQLException e) {
            logger.error("Can't execute query for sorting list of tariffs");
            return null;
        }
    }

    public ResultSet removeTariff(int ID) throws SQLException {
        try {
            Statement stat = con.createStatement();
            logger.info("Executing statement for removing tariff with ID "
                    + ID + " was created successfully");
            stat.executeUpdate("DELETE FROM Tariff WHERE ID = " + ID);
            return printAllTariffs();
        } catch (SQLException e) {
            logger.error("Can't execute query for removing tariff with ID " + ID);
            return null;
        }
    }

    public boolean isTariffIDExists(int ID) throws SQLException {
        try {
            Statement stat = con.createStatement();
            logger.info("Executing statement for searching tariff with ID "
                    + ID + " was created successfully");
            return stat.executeQuery("SELECT * FROM Tariff WHERE ID = " + ID) != null;
        } catch (SQLException e) {
            logger.error("Can't execute query for searching tariff with ID " + ID);
            return true;
        }
    }

    public void exit() throws SQLException {
        try {
            con.close();
            logger.info("Connection with SQL was successfully closed");
        } catch (SQLException e) {
            logger.error("Error closing SQL connection");
        }
    }

    public ResultSet printTariffsWithSMS(int min, int max) throws SQLException {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff" +
                    " WHERE SMS >= ? AND SMS <= ?");
            stat.setInt(1, min);
            stat.setInt(2, max);
            logger.info("Statement was successfully prepared");
            return stat.executeQuery();
        } catch (SQLException e) {
            logger.error("Can't execute query for getting tariffs with SMS [" + min + "; " + max + "]");
            return null;
        }
    }

    public ResultSet printTariffsWithPrice(int min, int max) throws SQLException {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                    "WHERE Price >= ? AND Price <= ?");
            stat.setInt(1, min);
            stat.setInt(2, max);
            logger.info("Statement was successfully prepared");
            return stat.executeQuery();
        } catch (SQLException e) {
            logger.error("Can't execute query for getting tariffs with price [" + min + "; " + max + "]");
            return null;
        }
    }

    public ResultSet printTariffsWithMinThisNet(int min, int max) throws SQLException {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                    "WHERE MinutesThisNet >= ? AND MinutesThisNet <= ?");
            stat.setInt(1, min);
            stat.setInt(2, max);
            logger.info("Statement was successfully prepared");
            return stat.executeQuery();
        } catch (SQLException e) {
            logger.error("Can't execute query for getting tariffs" +
                    " with minutes on this network [" + min + "; " + max + "]");
            return null;
        }
    }

    public ResultSet printTariffsWithMinOtherNet(int min, int max) throws SQLException {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                    "WHERE MinutesOtherNet >= ? AND MinutesOtherNet <= ?");
            stat.setInt(1, min);
            stat.setInt(2, max);
            logger.info("Statement was successfully prepared");
            return stat.executeQuery();
        } catch (SQLException e) {
            logger.error("Can't execute query for getting tariffs" +
                    " with minutes o other network [" + min + "; " + max + "]");
            return null;
        }
    }

    public ResultSet printTariffsWithAbroad(int min, int max) throws SQLException {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                    "WHERE Abroad >= ? AND Abroad <= ?");
            stat.setInt(1, min);
            stat.setInt(2, max);
            logger.info("Statement was successfully prepared");
            return stat.executeQuery();
        } catch (SQLException e) {
            logger.error("Can't execute query for getting tariffs with abroad [" + min + "; " + max + "]");
            return null;
        }
    }

    public ResultSet printTariffsWithInternet(int min, int max) throws SQLException {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                    "WHERE Internet >= ? AND Internet <= ?");
            stat.setInt(1, min);
            stat.setInt(2, max);
            logger.info("Statement was successfully prepared");
            return stat.executeQuery();
        } catch (SQLException e) {
            logger.error("Can't execute query for getting tariffs with Internet [" + min + "; " + max + "]");
            return null;
        }
    }
}


/*


    public static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DEFAULT_URL = "jdbc:sqlserver://DESKTOP-J4UMDEH:1433;databaseName=Deanery";
    private static final String DEFAULT_USERNAME = "student";
    private static final String DEFAULT_PASSWORD = "2022";
    public static final String FIND_ALL_CUSTOMERS_QUERY = "SELECT Fname, Address FROM Customers ";
    private static final String BY_FIRST_NAME = "WHERE FNAME = ? ";



public ResultSet one() throws SQLException {

        PreparedStatement stat = con.prepareStatement("SELECT * FROM Tariff " +
                "WHERE ID = ? OR ID = ?");
        stat.setInt(1, 1);
        stat.setInt(2, 6);
        return stat.executeQuery();

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

    public void setLastID() throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT TOP (1) ID AS L FROM Tariff ORDER BY ID DESC");
        if (rs.next()) {
            lastID = Integer.parseInt(rs.getString("S"));
        }
        lastID = 0;
    }


    public int getNumberTariffsParam(String nameCol) throws SQLException {
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery("SELECT COUNT(" + nameCol + ") AS S FROM Tariff");
        if (rs.next())
            return Integer.parseInt(rs.getString("S"));
        return 0;
    }


    }
    */