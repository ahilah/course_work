import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Objects;

public class StageTest extends ApplicationTest {

    private static Logger logger = Logger.getLogger(StageTest.class.getName());

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Before
    public void setUp() throws Exception {
    }

    @Override
    public void start(Stage stage) throws Exception {
        DOMConfigurator.configure("D:\\programming\\" +
                "appliedProgramming\\course_work\\network_course_work\\resources\\log4j.xml");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("mainStage.fxml")));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testCheckTable() {
        FxRobot robot = new FxRobot();
        robot.clickOn("show_all_btn");
        sleep(10000);
        Assertions.assertNotNull(MainMenu.getTableTariffs().getColumns());
    }

    @Test
    public void testCheckExit() {

        FxRobot robot = new FxRobot();
        robot.clickOn("exit_prg");
        sleep(10000);
    }

    @Test
    public void testCheckSort() {
        FxRobot robot = new FxRobot();
        robot.clickOn("sort_btn");
        sleep(10000);
    }

    @Test
    public void testCheckNet() {
        FxRobot robot = new FxRobot();
        robot.clickOn("networkInfo_btn");
        sleep(10000);
    }

}
