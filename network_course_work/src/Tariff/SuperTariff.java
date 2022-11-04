package Tariff;

public class SuperTariff extends BaseTariff {
    private final double other;
    private final double abroad;

    public SuperTariff(int ID, String name, String type,int user, int SMS, double thisN, int price,
                       double other, double abroad) {
        super(ID, name,type, user, SMS, thisN, price);
        this.other = other;
        this.abroad = abroad;
    }
    public double getOther() {
        return other;
    }
    public double getAbroad() {
        return abroad;
    }
    @Override
    public String toString() {
        return "Super Tariff " + super.toString() +
                ",\n\t\t\tnumber of minutes on other network: " + this.other +
                /*",\n\t\t\tnumber of minutes on other countries: " + this.abroad +*/ ")";
    }
}
