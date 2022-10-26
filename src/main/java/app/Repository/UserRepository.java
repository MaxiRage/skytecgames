package app.Repository;

import lombok.SneakyThrows;

public interface UserRepository {

    void createNewUser(String nameUser, int count, String nameClan, int skillArena, int skillGamble, int balances);

    String getUserName(int idUser);

    int getUserId(String nameUser);

    int getUserBalance(String nameUser);

    int getSkillArena(String nameUser);

    void updateBalanceUsers(int amount, String nameUser);

    @SneakyThrows
    int getCountRowsUsersTable();

    public boolean checkIsPresentUserInBD(String nameUsers);

}
