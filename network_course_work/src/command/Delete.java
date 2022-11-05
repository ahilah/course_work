package command;

import command.commandable.MenuCommand;
import network.Network;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Delete implements MenuCommand {
    public final static String NAME = "delete";
    public static final String RED_UNDERLINED = "\033[4;31m";
    public static final String ANSI_RESET = "\033[0m";  // Text Reset
    Network network;
    public Delete(Network network) {
        this.network = network;
    }
    @Override
    public ResultSet execute(List<String> pr) throws InterruptedException, SQLException {
        int tariffID = 0;
        try {
            tariffID = Integer.parseInt(pr.get(0));
        }
        catch (NumberFormatException e){
            System.out.println("\n\t" + RED_UNDERLINED + "Wrong input string!" + ANSI_RESET);
            return  null;
        }
        if (!network.isTariffIDExists(tariffID)) return null;
        return network.removeTariff(Integer.parseInt(pr.get(0)));
    }
}
