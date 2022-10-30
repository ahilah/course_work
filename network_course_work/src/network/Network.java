package network;

import Tariff.BaseTariff;
import Tariff.StartTariff;
import Tariff.SuperNetTariff;
import Tariff.SuperTariff;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Network {

    private static Network network;
    private final String companyName;
    private final String companyNumber;
    private final String companyEmail;
    private List<BaseTariff> tariffs;
    private BufferedReader buff;
    public static Network getNetwork(String companyName, String companyNumber, String companyEmail) throws IOException {
        if (network == null) {
            network = new Network(companyName, companyNumber, companyEmail);
        }
        return network;
    }
    private Network(String companyName, String companyNumber, String companyEmail) throws IOException {
        this.companyName = companyName;
        this.companyNumber = companyNumber;
        this.companyEmail = companyEmail;
        CreateList();
    }

    private void CreateList() throws IOException {
        tariffs = new ArrayList<>() {};
        tariffs.add(new StartTariff(1,"1", "Start",5, 123, 3, 10));
        tariffs.add( new StartTariff(2,"2", "Start", 9,12, 4, 15));
        tariffs.add(new SuperTariff(3,"3", "Super", 10,55, 40, 150, 36, 66));
        tariffs.add(new SuperNetTariff(4,"3", "SuperNet", 20, 55,
                40, 150, 36, 66, 999));
        /*tariffs.add();
        tariffs.add();
        tariffs.add();
         // readFile();
        */

    }

    public int getNumberTariffs() {
        return tariffs.size();
    }
    public List<BaseTariff> getTariffs() {
        return tariffs;
    }

    public int calculateUserNumber() {
        int sumUsers = 0;
        for (BaseTariff tariff: network.getTariffs()) {
            sumUsers += tariff.getUser();
        }
        return sumUsers;
    }

    public void exit() {

    }

}
