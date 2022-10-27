package app.Service.Impl;

import app.Repository.ClanRepository;
import app.Service.Clan;
import app.Utility.EnamActions;
import app.Repository.DetailsRepository;
import app.Repository.TreasuryRepository;
import app.Repository.UserRepository;
import lombok.SneakyThrows;

/***
 * При вступлении в клан пользователь уплачивает взнос в виде произведения скилла на коэффициет 2
 */

public class ClanImpl implements Clan {

    UserRepository userRepository;
    TreasuryRepository treasuryRepository;
    DetailsRepository detailsRepository;
    ClanRepository clanRepository;
    int rateFee = 2;


    // TODO Проверку, что юзер уже в клане
    @SneakyThrows
    @Override
    public void JoiningClan(String nameUser) { // TODO Перенести в действия?
        int randNumberJoiningClan = (int) ((Math.random() * 3) + 1);
        String nameClans1 = "PowerRangers";
        String nameClans2 = "MadWolves";
        String nameClans3 = "ForestElves";

        switch (randNumberJoiningClan) {
            case 1 -> {
                System.out.println(Thread.currentThread() + " в работе: Игрок " + nameUser + " вступил в клан" + nameClans1);
            }
            case 2 ->
                    System.out.println(Thread.currentThread() + " в работе: Игрок " + nameUser + " играл в азартные игры");
            default -> System.out.println(Thread.currentThread() + " в работе: Игрок " + nameUser + " был на задании");
        }

        int balanceUser = userRepository.getUserBalance(nameUser);
        int amountForJoining = userRepository.getSkillArena(nameUser) * rateFee;

        if (balanceUser > amountForJoining) {
            userRepository.updateBalanceUsers(amountForJoining, nameUser);

            treasuryRepository.updateBalanceTreasury(amountForJoining);

            detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.JOININGCLAN), amountForJoining);

        } else System.out.println(Thread.currentThread() + " в работе: " + "Не хватает средств на членство");

        System.out.printf(Thread.currentThread() + " в работе: " +
                        "Пользователь %s вступил в клан \"PowerRangers\" и заплатил %d в КАЗНУ \n"
                , nameUser, amountForJoining);
    }
}
