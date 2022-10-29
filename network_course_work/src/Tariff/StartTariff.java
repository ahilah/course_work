package Tariff;

import Tariff.BaseTariff;

public class StartTariff extends BaseTariff {

    public StartTariff(int tariffID, String nameTariff,String type, int SMSNumber, double numberMinutesThisOperator, int priceTariff) {
        super(tariffID, nameTariff,type, SMSNumber, numberMinutesThisOperator, priceTariff);
    }

    @Override
    public String toString() {
        return "Start Tariff " + super.toString() + ")";
    }

    @Override
    public String rowTable() {
        return String.format("|%-17s ", " Start Tariff ") + super.rowTable() +
                String.format(" %-14s| %-14s| %-9s|", "    -    ", "    -    ", "    -    ") +
                "\n|-------------------------------------" +
                "-----------------------------------------" +
                "-----------------------------------------------------------------------|";
    }
}
