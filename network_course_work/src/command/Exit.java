package command;

import Tariff.BaseTariff;
import command.commandable.MenuCommand;
import network.Network;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Exit implements MenuCommand {
    private static final Logger logger = LogManager.getLogger(Exit.class.getName());
    public final static String NAME = "exit";
    Network network;
    public Exit(Network network)
    {
        this.network = network;
    }
    @Override
    public ResultSet execute(List<String> pr) throws InterruptedException, SQLException {
        logger.info("Exit command is executed");
        try {
            network.exit();
            logger.info("Connection to SQL Server is over");
        } catch (SQLException e) {
            logger.warn("Can't close connection to SQL Server");
        }
        logger.info("Program is ended");
        System.exit(0);
        return null;
    }
}