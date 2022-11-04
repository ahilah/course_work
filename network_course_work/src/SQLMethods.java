import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLMethods {

    public static final String DEFAULT_DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static final String DEFAULT_URL = "jdbc:sqlserver://DESKTOP-J4UMDEH:1433;databaseName=Deanery";
    private static final String DEFAULT_USERNAME = "student";
    private static final String DEFAULT_PASSWORD = "2022";
    public static final String FIND_ALL_CUSTOMERS_QUERY = "SELECT Fname, Address FROM Customers ";
    private static final String BY_FIRST_NAME = "WHERE FNAME = ? ";
    private static Connection con;

    public static void main(String[] args) throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-J4UMDEH:1433;databaseName=Deanery;user=student;password=2022;encrypt=true;trustServerCertificate=true");
        System.out.println("open");
    }
}
