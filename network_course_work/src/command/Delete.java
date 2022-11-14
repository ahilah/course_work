package command;

import command.commandable.MenuCommand;
import network.Network;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Delete implements MenuCommand {
    private static final Logger logger = LogManager.getLogger(Delete.class.getName());
    public final static String NAME = "delete";
    public static final String RED_UNDERLINED = "\033[4;31m";
    public static final String ANSI_RESET = "\033[0m";  // Text Reset
    Network network;
    public Delete(Network network) {
        this.network = network;
    }
    @Override
    public ResultSet execute(List<String> pr) throws SQLException {
        logger.info("Delete command is executed");
        int tariffID = 0;
        try {
            tariffID = Integer.parseInt(pr.get(0));
            logger.info("Tariff ID was correctly inputted: " + pr.get(0));
        }
        catch (NumberFormatException e) {
            logger.info("Tariff ID was inputted in inappropriate way (not int): " + pr.get(0));
            System.out.println("\n\t" + RED_UNDERLINED + "Wrong input string!" + ANSI_RESET);
            return  null;
        }
        try {
            if (!network.isTariffIDExists(tariffID)) {
                logger.info("Tariff with ID " + pr.get(0) + " does not exists");
                return null;
            }
            logger.info("Tariff with ID " + pr.get(0) + " exists and will be deleted");
            return network.removeTariff(Integer.parseInt(pr.get(0)));
        } catch (SQLException e) {
            logger.error("Can't delete existing tariff with ID " + pr.get(0));
            return null;
        }
    }
}