package command.add;

import Tariff.BaseTariff;
import Tariff.StartTariff;
import Tariff.SuperNetTariff;
import Tariff.SuperTariff;
import command.Delete;
import command.commandable.MenuCommand;
import network.Network;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class Add implements MenuCommand {
    private static final Logger logger = LogManager.getLogger(Add.class.getName());
    public final static String NAME = "add";
    Network network;
    public Add(Network network) {
        this.network = network;
    }
    @Override
    public ResultSet execute(List<String> pr) throws InterruptedException, SQLException {
        logger.info("Add command is executed");
        if (pr.size() < 6) {
            logger.warn("Not all parameters were inputted");
            return null;
        }

        BaseTariff tariff;
        try {
            switch (pr.get(1).toLowerCase(Locale.ROOT)) {
                case "start" -> {
                    tariff = new StartTariff(network.getLastID() + 1,
                            pr.get(0), pr.get(1), Integer.parseInt(pr.get(2)),
                            Integer.parseInt(pr.get(3)), Integer.parseInt(pr.get(4)),
                            Integer.parseInt(pr.get(5)));
                    network.addStart((StartTariff) tariff);
                }
                case "super" -> {
                    tariff = new SuperTariff(network.getLastID() + 1,
                            pr.get(0), pr.get(1), Integer.parseInt(pr.get(2)),
                            Integer.parseInt(pr.get(3)), Integer.parseInt(pr.get(4)),
                            Integer.parseInt(pr.get(5)), Integer.parseInt(pr.get(6)),
                            Integer.parseInt(pr.get(7)));
                    network.addSuper((SuperTariff) tariff);
                }
                case "net" -> {
                    tariff = new SuperNetTariff(network.getLastID() + 1,
                            pr.get(0), "Super" + pr.get(1), Integer.parseInt(pr.get(2)),
                            Integer.parseInt(pr.get(3)), Integer.parseInt(pr.get(4)),
                            Integer.parseInt(pr.get(5)), Integer.parseInt(pr.get(6)),
                            Integer.parseInt(pr.get(7)), Integer.parseInt(pr.get(8)));
                    network.addSuperNet((SuperNetTariff) tariff);
                }
                default -> {
                    logger.error("Tariff has wrong kind and wasn't added");
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("Can't process adding SQL Query");
            return null;
        }
        // logger.info("Tariff with ID " + tariff.getID() + " was successfully added");
        return null;
    }
}