package app.Repository;

public interface TreasuryRepository {

    void createNewTreasury(int idClan, int balances);

    void increaseBalanceTreasury(int idClan, int amount);
    void reduceBalanceTreasury(int idClan, int amount);
}