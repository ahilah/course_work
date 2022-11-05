package command;

import command.commandable.MenuCommand;
import network.Network;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class View implements MenuCommand {
    public final static String NAME = "view";
    Network network;
    public View(Network network)
    {
        this.network = network;
    }

    public ResultSet execute(List<String> pr) throws SQLException {
        if ("all".equals(pr.get(0))) {
            return network.printAllTariffs();
        } else if ("sms".equals(pr.get(0)) && pr.size() == 3) {
            return network.printTariffsWithSMS(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
        } else if ("price".equals(pr.get(0)) && pr.size() == 3) {
            return network.printTariffsWithPrice(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
        } else if ("this".equals(pr.get(0)) && pr.size() == 3){
            return network.printTariffsWithMinThisNet(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
        } else if ("other".equals(pr.get(0)) && pr.size() == 3) {
            return network.printTariffsWithMinOtherNet(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
        } else if ("abroad".equals(pr.get(0)) && pr.size() == 3) {
            return network.printTariffsWithAbroad(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
        } else {
            return network.printTariffsWithInternet(Integer.parseInt(pr.get(1)), Integer.parseInt(pr.get(2)));
        }
    }
}
