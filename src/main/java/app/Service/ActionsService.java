package app.Service;

public interface ActionsService {
    void runGames(String nameUser);

    void battle(String nameUser, String nameOpponent);

    void gamble(String nameUser, String nameOpponent);

    void tasks(String nameUser);

    void hunts(String nameUser);
}
