import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;

public class MainStageTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().
                getResource("mainStage.fxml")));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testCreateSalad(){
        //verifyThat("#companyName",hasText("Company Name: Emirates Airline"));
        clickOn("#Show all tariffs");
        sleep(10000);
        /*clickOn("#Name").write("Dream");
        sleep(600);
        clickOn("#id").write("013");
        sleep(600);
        clickOn("#Confirm");

        String example =  Salad.getBoxForm().toString() + Salad.getId();
        assertEquals(" Its name: " + "Dream" +
                " Its ID: " + 013 ,example);*/
    }
}
