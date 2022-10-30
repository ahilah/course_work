package Tariff;

public class SuperTariff extends BaseTariff {
    private double other;
    private double abroad;

    public SuperTariff(int tariffID, String name, String type, int SMS, double thisN, int price, double other,
                       double abroad) {
        super(tariffID, name,type, SMS, thisN, price);
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
                ",\n\t\t\tnumber of minutes on other countries: " + this.abroad + ")";
    }
}
