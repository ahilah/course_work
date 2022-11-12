package command;

import command.commandable.MenuCommand;
import network.Network;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class View implements MenuCommand {
    private static final Logger logger = LogManager.getLogger(View.class.getName());
    public final static String NAME = "view";
    Network network;
    public View(Network network)
    {
        this.network = network;
    }

    public ResultSet execute(List<String> pr) throws SQLException {
        logger.info("View command is executed");
        try {
            logger.info("Start searching data for kind of view");
            if ("all".equals(pr.get(0))) {
                return network.printAllTariffs();
            } else if ("sms".equals(pr.get(0)) && pr.size() == 3) {
                return network.printTariffsWithSMS(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
            } else if ("price".equals(pr.get(0)) && pr.size() == 3) {
                return network.printTariffsWithPrice(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
            } else if ("this".equals(pr.get(0)) && pr.size() == 3) {
                return network.printTariffsWithMinThisNet(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
            } else if ("other".equals(pr.get(0)) && pr.size() == 3) {
                return network.printTariffsWithMinOtherNet(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
            } else if ("abroad".equals(pr.get(0)) && pr.size() == 3) {
                return network.printTariffsWithAbroad(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
            } else {
                return network.printTariffsWithInternet(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
            }
        } catch (SQLException e) {
            logger.error("Can't return data set for view");
            return null;
        }
    }
}
