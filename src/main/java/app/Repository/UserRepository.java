package app.Repository;

import java.util.List;

public interface UserRepository {

    void createNewUser(String nameUser, int skillArena, int skillGamble, int balances);

    String getUserName(int idUser);

    int getUserClan(String nameUser);

    int getUserId(String nameUser);

    int getUserBalance(String nameUser);

    int getSkillArena(String nameUser);

    int getSkillGamble(String nameUser);

    void reduceBalanceUsers(int amount, String nameUser);

    void increaseBalanceUsers(int amount, String nameUser);

    List<String> getAllUsers();

    List<String> getAllUsersFromClan();

    boolean checkIsPresentUserInBD(String nameUsers);

    boolean checkUsersMemberClans(String nameUser);

    void userJoinsClan(String nameUser, int idClan);
}
