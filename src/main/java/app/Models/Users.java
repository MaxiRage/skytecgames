package app.Models;

import app.Repository.Impl.UserRepositoryImpl;
import app.Repository.UserRepository;
import lombok.Getter;

public class Users {
    private String nameUsers = "Player_";
    private int skillArena = (int) (Math.random() * 10) + 1;
    private int skillGamble = (int) (Math.random() * 30) + 1;
    private int balances = 300;
    private static int count = 0;

    public synchronized String getNameUsers() {
        return nameUsers + ++count;
    }

    public synchronized int getSkillArena() {
        return skillArena;
    }

    public synchronized int getSkillGamble() {
        return skillGamble;
    }

    public synchronized int getBalances() {
        return balances;
    }

    public synchronized static int getCount() {
        return count;
    }
}