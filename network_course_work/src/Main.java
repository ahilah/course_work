import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
        DOMConfigurator.configure("D:\\programming\\appliedProgramming\\course_work\\network_course_work\\resources\\log4j.xml");
        //PropertyConfigurator.configure("D:\\programming\\appliedProgramming\\course_work\\network_course_work\\resources\\log4j.properties");
        logger.info("Start program");
        MainMenu mainMenu = new MainMenu();
        mainMenu.execute();
    }
}


