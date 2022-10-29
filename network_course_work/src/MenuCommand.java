import Tariff.BaseTariff;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MenuCommand {
    List<BaseTariff> execute(List<String> pr) /*throws InterruptedException, IOException, SQLException*/;
}
