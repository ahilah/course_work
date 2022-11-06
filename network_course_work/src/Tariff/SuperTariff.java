package Tariff;

public class SuperTariff extends BaseTariff {
    private final int other;
    private final int abroad;

    public SuperTariff(int ID, String name, String type, int user, int SMS, int thisN, int price,
                       int other, int abroad) {
        super(ID, name,type, user, SMS, thisN, price);
        this.other = other;
        this.abroad = abroad;
    }
    public int getOther() {
        return other;
    }
    public int getAbroad() {
        return abroad;
    }
    @Override
    public String toString() {
        return "Super Tariff " + super.toString() +
                ",\n\t\t\tnumber of minutes on other network: " + this.other +
                /*",\n\t\t\tnumber of minutes on other countries: " + this.abroad +*/ ")";
    }
}
