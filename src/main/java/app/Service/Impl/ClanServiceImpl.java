package app.Service.Impl;

import app.Repository.ClanRepository;
import app.Repository.DetailsRepository;
import app.Repository.Impl.ClanRepositoryImpl;
import app.Repository.Impl.DetailsRepositoryImpl;
import app.Repository.Impl.TreasuryRepositoryImpl;
import app.Repository.Impl.UserRepositoryImpl;
import app.Repository.TreasuryRepository;
import app.Repository.UserRepository;
import app.Service.ClanService;
import app.Utility.EnamActions;
import lombok.SneakyThrows;

public class ClanServiceImpl implements ClanService {
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final TreasuryRepository treasuryRepository = new TreasuryRepositoryImpl();
    private final DetailsRepository detailsRepository = new DetailsRepositoryImpl();
    private final ClanRepository clanRepository = new ClanRepositoryImpl();

    @SneakyThrows
    @Override
    public boolean JoiningClan(String nameUser) {

        if (userRepository.checkUsersMemberClans(nameUser)) return true;

        int rateFee = 2;
        int idClan = (int) ((Math.random() * 3) + 1);
        int idTreasury = treasuryRepository.getIdTreasury(idClan);

        int balanceUser = userRepository.getUserBalance(nameUser);
        int amountForJoining = userRepository.getSkillArena(nameUser) * rateFee;

        if (balanceUser > amountForJoining) {

            userRepository.reduceBalanceUsers(amountForJoining, nameUser);
            detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.JOINING_CLAN), amountForJoining, idTreasury);
//            treasuryRepository.increaseBalanceTreasury(idTreasury, amountForJoining);
            userRepository.userJoinsClan(nameUser, idClan);

            System.out.printf(Thread.currentThread() + " is working: " +
                            "User %s joined the clan " + clanRepository.getClanName(idClan) + " and pay %d â treasury \n"
                    , nameUser, amountForJoining);
            return true;
        } else
            System.out.println(Thread.currentThread() + " is working: " + nameUser + " not enough funds for membership");
        return false;
    }
}
