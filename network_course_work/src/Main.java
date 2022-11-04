import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.execute();

    }
}


//--module-path "D:\downloads\javaFX\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
// D:\test1.txt tariffs