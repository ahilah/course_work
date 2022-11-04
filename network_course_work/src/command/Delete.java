package command;

import command.commandable.MenuCommand;
import network.Network;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Delete implements MenuCommand {
    public final static String NAME = "delete";
    Network network;
    public Delete(Network network) {
        this.network = network;
    }
    @Override
    public ResultSet execute(List<String> pr) throws InterruptedException, SQLException {
        if (!network.isTariffIDExists(Integer.parseInt(pr.get(0)))) return null;
        return network.removeTariff(Integer.parseInt(pr.get(0)));
         // null;
    }
}
