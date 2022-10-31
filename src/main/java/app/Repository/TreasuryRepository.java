package app.Repository;

public interface TreasuryRepository {

    void createNewTreasury(int idClan, String nameClan);

    void increaseBalanceTreasury(int idTreasury, int amount);

    void reduceBalanceTreasury(int idTreasury, int amount);

    int getBalance(int idTreasury);

    int getIdTreasury(int idClan);
}