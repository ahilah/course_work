package Tariff;

public class BaseTariff {
    private int ID;
    private String name;
    private String type;
    private int SMS;

    private double thisN;
    private int price;

    private int numberOfUsers = 0;

    BaseTariff(int tariffID, String name, String type, int SMS, double thisN, int price) {
        this.name = name;
        this.type = type;
        this.SMS = SMS;
        this.thisN = thisN;
        this.price = price;
        this.ID = tariffID;
    }
    public int getNumberOfUsers() {
        return this.numberOfUsers;
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
    public double getThisN() {
        return thisN;
    }


    public void setNumberOfUsers(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
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

    public String toString() {
        return  this.name + "\n\t\t\t(number of SMS: " + this.SMS +
                ",\n\t\t\tnumber of minutes on this operator: " + this.thisN +
                ",\n\t\t\tprice of tariff in hryvnias: " + this.price +
                ",\n\t\t\ttariff ID: " + this.ID +
                ",\n\t\t\tgeneral number of tariff users: " + this.numberOfUsers;
    }
}