package Tariff;

public class BaseTariff {
    private final int ID;
    private final String name;
    private final String type;
    private final int SMS;
    private final int thisN;
    private final int price;
    private int user = 0;
    BaseTariff(int tariffID, String name, String type, int user, int SMS, int thisN, int price) {
        this.name = name;
        this.type = type;
        this.SMS = SMS;
        this.thisN = thisN;
        this.price = price;
        this.ID = tariffID;
        this.user = user;
    }
    public int getUser() {
        return this.user;
    }
    public int getTariffID() {
        return ID;
    }
    public int getPrice() {
        return price;
    }
    public int getSMS() {
        return SMS;
    }
    public int getThisN() {
        return thisN;
    }
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    @Override
    public String toString() {
        return  this.name + "\n\t\t\t(number of SMS: " + this.SMS +
                ",\n\t\t\tnumber of minutes on this operator: " + this.thisN +
                ",\n\t\t\tprice of tariff in hryvnias: " + this.price +
                ",\n\t\t\ttariff ID: " + this.ID +
                ",\n\t\t\tgeneral number of tariff users: " + this.user;
    }
}
