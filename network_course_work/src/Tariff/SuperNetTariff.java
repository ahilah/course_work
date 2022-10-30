package Tariff;

public class SuperNetTariff extends BaseTariff {
    private double other;
    private double abroad;
    private double Internet;
    public SuperNetTariff(int ID, String name, String type, int user, int SMS, double thisN, int price,
                          double other, double abroad, double Internet) {
        super(ID, name,type, user, SMS, thisN, price);
        this.other = other;
        this.abroad = abroad;
        this.Internet = Internet;
    }
    public double getOther() {
        return other;
    }

    public double getAbroad() {
        return abroad;
    }


    public double getInternet() {
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
