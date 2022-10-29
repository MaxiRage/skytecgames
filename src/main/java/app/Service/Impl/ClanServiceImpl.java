package app.Service.Impl;

import app.Repository.DetailsRepository;
import app.Repository.TreasuryRepository;
import app.Repository.UserRepository;
import app.Service.ClanService;
import app.Utility.EnamActions;
import lombok.SneakyThrows;

import java.util.concurrent.locks.LockSupport;

public class ClanServiceImpl implements ClanService {
    private UserRepository userRepository;
    private TreasuryRepository treasuryRepository;
    private DetailsRepository detailsRepository;

    @SneakyThrows
    @Override
    public void JoiningClan(String nameUser) {
        int rateFee = 2;
        int idClan = (int) ((Math.random() * 3) + 1);

        int balanceUser = userRepository.getUserBalance(nameUser);
        int amountForJoining = userRepository.getSkillArena(nameUser) * rateFee;

        if (balanceUser > amountForJoining) {

            userRepository.reduceBalanceUsers(amountForJoining, nameUser);

            treasuryRepository.increaseBalanceTreasury(idClan, amountForJoining);

            detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.JOININGCLAN), amountForJoining, idClan);

            System.out.printf(Thread.currentThread() + " в работе: " +
                            "Пользователь %s вступил в клан \"PowerRangers\" и заплатил %d в КАЗНУ \n"
                    , nameUser, amountForJoining);
        } else {
            System.out.println(Thread.currentThread() + " в работе: " + "Не хватает средств на членство");
            // паркуем поток пока у Юзера не будет деньги
            Runnable task = LockSupport::park;
            while (balanceUser < amountForJoining) {
                balanceUser = userRepository.getUserBalance(nameUser);
                amountForJoining = userRepository.getSkillArena(nameUser) * rateFee;
            }
            LockSupport.unpark(new Thread(task));


//            JoiningClan(nameUser);
        }
    }
}
