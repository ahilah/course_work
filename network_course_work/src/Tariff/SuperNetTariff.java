package Tariff;

public class SuperNetTariff extends BaseTariff {
    private final int other;
    private final int abroad;
    private final int Internet;
    public SuperNetTariff(int ID, String name, String type, int user, int SMS, int thisN, int price,
                          int other, int abroad, int Internet) {
        super(ID, name,type, user, SMS, thisN, price);
        this.other = other;
        this.abroad = abroad;
        this.Internet = Internet;
    }
    public int getOther() {
        return other;
    }

    public int getAbroad() {
        return abroad;
    }

    public int getInternet() {
        return Internet;
    }

    @Override
    public String toString() {
        return "Super Net Tariff " + super.toString() +
                ",\n\t\t\tnumber of minutes on other network: " + this.other +
                ",\n\t\t\tnumber of minutes on other countries: " + this.abroad +
                ",\n\t\t\tGB of mobile Internet: " + this.Internet + ")";
    }
}
