package app.Repository;

public interface TreasuryRepository {

    void createNewTreasury(int idClan, String nameClan);
    void increaseBalanceTreasury(int idClan, int amount);
    void reduceBalanceTreasury(int idClan, int amount);
}