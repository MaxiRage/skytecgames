package app.Repository;

import lombok.SneakyThrows;

import java.sql.SQLException;

public interface TreasuryRepository {

    void createNewTreasury(String nameClan, int balances);

    void updateBalanceTreasury(int amount);
}