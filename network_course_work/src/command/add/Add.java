package command.add;

import Tariff.BaseTariff;
import Tariff.StartTariff;
import Tariff.SuperNetTariff;
import Tariff.SuperTariff;
import command.commandable.MenuCommand;
import network.Network;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class Add implements MenuCommand {
    public final static String NAME = "add";
    Network network;
    public Add(Network network) {
        this.network = network;
    }
    @Override
    public ResultSet execute(List<String> pr) throws InterruptedException, SQLException {
        if (pr.size() < 6) {
            return null;
        }

        BaseTariff tariff;
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
                        Integer.parseInt(pr.get(7)) );
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
                return null;
            }
        }
        return null;
    }
}
