package command;

import Tariff.BaseTariff;
import command.commandable.MenuCommand;
import network.Network;

import java.util.List;

public class UserNumber implements MenuCommand {
    public final static String NAME = "sum";
    private int userNumber = 0;
    Network network;

    public UserNumber(Network network) {
        this.network = network;
    }

    @Override
    public List<BaseTariff> execute(List<String> pr) {

        return null;
    }

    public int calculateUserNumber() {
        int sumUsers = 0;
        for (BaseTariff tariff: network.getTariffs()) {
            sumUsers += tariff.getUser();
        }
        return sumUsers;
    }
}
