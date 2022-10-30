package command;

import Tariff.BaseTariff;
import command.commandable.MenuCommand;
import network.Network;

import java.util.List;

public class View implements MenuCommand {
    public final static String NAME = "view";
    Network network;

    public View(Network network)
    {
        this.network = network;
    }

    public List<BaseTariff> execute(List<String> pr)  {
        if ("all".equals(pr.get(0)))
        {
            return network.getTariffs();
        }
        /*else if (pr.size() == 0)
        {
            return network.printAvailableCars();
        }
        else if ("speed".equals(pr.get(0)) && pr.size() == 3)
        {
            return network.printCarsWithSpeed(Double.parseDouble(pr.get(1)), Double.parseDouble(pr.get(2)));
        }
        else if ("price".equals(pr.get(0)) && pr.size() == 3)
        {
            return network.printCarsWithPrice(Double.parseDouble(pr.get(1)), Double.parseDouble(pr.get(2)));
        }
        else if ("consumption".equals(pr.get(0)) && pr.size() == 3)
        {
            return network.printCarsWithConsum(Double.parseDouble(pr.get(1)), Double.parseDouble(pr.get(2)));
        }
        else
        {
            return network.printAvailableCars();
        }*/
        return null;
    }

}