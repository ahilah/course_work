package Tariff;

import Tariff.BaseTariff;

public class StartTariff extends BaseTariff {
    public StartTariff(int ID, String name, String type, int user, int SMS,
                       int thisN, int price) {
        super(ID, name,type, user, SMS, thisN, price);
    }

    @Override
    public String toString() {
        return "Start Tariff " + super.toString() + ")";
    }
}
