import Tariff.BaseTariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Exit implements MenuCommand{
    public final static String NAME = "exit";

    Network network;

    public Exit(Network network)
    {
        this.network = network;
    }

    @Override
    public List<BaseTariff> execute(List<String> pr) /*throws InterruptedException, SQLException*/ {
        network.exit();

        System.exit(0);
        return null;
    }
}
