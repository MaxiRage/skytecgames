package app.Actions;

import app.Entity.Details;
import app.Entity.Treasury;
import app.Entity.Users;
import lombok.SneakyThrows;

/***
 * При вступлении в клан пользователь уплачивает взнос в виде произведения скилла на коэффициет 2
 */
public class JoiningClan {

    int rateFee = 2;
        // TODO Проверку, что юзер уже в клане
    @SneakyThrows
    public void feeTreasury(String nameUser) {

        int balanceUser = new Users().getUserBalance(nameUser);
        int amountForJoining = new Users().getSkillArena(nameUser) * rateFee;

        if (balanceUser > amountForJoining) {
            new Users().updateBalanceUsers(amountForJoining, nameUser);

            new Treasury().updateBalanceTreasury(amountForJoining);

            new Details().insertTo(nameUser, String.valueOf(EnamActions.JOININGCLAN), amountForJoining);

        } else System.out.println(Thread.currentThread() + " в работе: " +"Не хватает средств на членство");

        System.out.printf(Thread.currentThread() + " в работе: " +
                        "Пользователь %s вступил в клан \"PowerRangers\" и заплатил %d в КАЗНУ \n"
                , nameUser, amountForJoining);
    }
}