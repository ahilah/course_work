package command;

import command.commandable.MenuCommand;
import network.Network;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Sort implements MenuCommand {
    private static final Logger logger = LogManager.getLogger(Sort.class.getName());
    public final static String NAME = "sort";
    Network network;
    public Sort(Network network) {
        this.network = network;
    }
    public ResultSet execute(List<String> pr) throws SQLException {
        logger.info("Sort command is executed");
        try {
            return network.sortTariffs();
        } catch (SQLException e) {
            logger.error("Can't sort the data");
            return null;
        }
    }
}
