import Tariff.BaseTariff;
import Tariff.StartTariff;
import Tariff.SuperNetTariff;
import Tariff.SuperTariff;

import java.io.BufferedReader;
import java.io.FileReader;
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
        tariffs = new ArrayList<>();
       // readFile();
    }


    public List<BaseTariff> getTariffs() {
        return tariffs;
    }

    public void exit() {

    }

}
