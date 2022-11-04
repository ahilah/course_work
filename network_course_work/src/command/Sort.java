package command;

import command.commandable.MenuCommand;
import network.Network;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Sort implements MenuCommand {
    public final static String NAME = "sort";
    Network network;

    public Sort(Network network)
    {
        this.network = network;
    }

    public ResultSet execute(List<String> pr) throws SQLException {
            return network.sortTariffs();
    }
}
