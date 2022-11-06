package command.add;

import Tariff.StartTariff;
import Tariff.SuperNetTariff;
import Tariff.SuperTariff;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewTariffSQL {
    public StartTariff newStartTariff(ResultSet rs) throws SQLException {
        return new StartTariff(rs.getInt("ID"),
                rs.getString("Name"),
                rs.getString("Type"),
                rs.getInt("Users"),
                rs.getInt("SMS"),
                rs.getInt("MinutesThisNet"),
                rs.getInt("Price"));
    }
    public SuperTariff newSuperTariff(ResultSet rs) throws SQLException {
        return new SuperTariff(rs.getInt("ID"),
                rs.getString("Name"),
                rs.getString("Type"),
                rs.getInt("Users"),
                rs.getInt("SMS"),
                rs.getInt("MinutesThisNet"),
                rs.getInt("Price"),
                rs.getInt("MinutesOtherNet"),
                rs.getInt("Abroad"));
    }
    public SuperNetTariff newSuperNetTariff(ResultSet rs) throws SQLException {
        return new SuperNetTariff(rs.getInt("ID"),
                rs.getString("Name"),
                rs.getString("Type"),
                rs.getInt("Users"),
                rs.getInt("SMS"),
                rs.getInt("MinutesThisNet"),
                rs.getInt("Price"),
                rs.getInt("MinutesOtherNet"),
                rs.getInt("Abroad"),
                rs.getInt("Internet"));
    }
}
