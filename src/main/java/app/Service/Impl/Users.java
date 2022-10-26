package app.Service.Impl;

import app.Repository.UserRepository;
import lombok.SneakyThrows;

public class Users extends Thread {
    private final String nameUsers = "Player_";
    private final String nameClans = "PowerRangers";
    private final int skillArena = (int) (Math.random() * 10) + 1;
    private final int skillGamble = (int) (Math.random() * 30) + 1;
    private final int balances = 300;
    private static int count = 0;

    public Users() {
        count ++;
    }

    UserRepository userRepository;

    @SneakyThrows
    @Override
    public void run() {
        userRepository.createNewUser(nameUsers, count, nameClans, skillArena, skillGamble, balances);
    }
}