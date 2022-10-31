package app.Models;

import java.util.concurrent.atomic.AtomicInteger;

public class Users {
    private final String nameUsers = "Player_";
    private final int skillArena = (int) (Math.random() * 10) + 1;
    private final int skillGamble = (int) (Math.random() * 30) + 1;
    private final int balances = 300;
    //    private static int count = 0;
    public static AtomicInteger count = new AtomicInteger(0);

    public synchronized String getNameUsers() {
        return nameUsers + count.incrementAndGet();
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
}