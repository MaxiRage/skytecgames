package app.Repository;

public interface UserRepository {

    void createNewUser(String nameUser, int skillArena, int skillGamble, int balances);

    String getUserName(int idUser);

    int getUserId(String nameUser);

    int getUserBalance(String nameUser);

    int getSkillArena(String nameUser);

    void reduceBalanceUsers(int amount, String nameUser);
    void increaseBalanceUsers(int amount, String nameUser);

    int getCountRowsUsersTable();

    public boolean checkIsPresentUserInBD(String nameUsers);
}
