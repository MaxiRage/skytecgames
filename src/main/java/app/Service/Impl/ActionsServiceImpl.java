package app.Service.Impl;

import app.Repository.DetailsRepository;
import app.Repository.Impl.DetailsRepositoryImpl;
import app.Repository.Impl.TreasuryRepositoryImpl;
import app.Repository.Impl.UserRepositoryImpl;
import app.Repository.TreasuryRepository;
import app.Repository.UserRepository;
import app.Service.ActionsService;
import app.Service.ClanService;
import app.Utility.EnamActions;

/**
 * This class combines possible user actions
 * First, the user joins the clan, and then goes to the arena, either on tasks, or in gambling
 * <p>
 * Arena and Gambling Rules:
 * The opponent is selected randomly from the list of users
 * If the player wins, he receives the opponent's contribution
 * If it is a draw, then the refund of the fee to the players
 * In case of defeat, the contribution is transferred to the opponent
 * The winning player is charged % of the amount exceeding the contribution and credited to the clan treasury
 * <p>
 * In quests and hunting, the player receives a random amount of gold and is charged % to the clan treasury
 */
public class ActionsServiceImpl implements ActionsService {

    private final UserRepository userRepository = new UserRepositoryImpl();
    private final TreasuryRepository treasuryRepository = new TreasuryRepositoryImpl();
    private final DetailsRepository detailsRepository = new DetailsRepositoryImpl();
    private final ClanService clanService = new ClanServiceImpl();

    /**
     * The method performs a random action
     */
    @Override
    public void runGames(String nameUser) {
        int idUser = userRepository.getUserId(nameUser);

        int randomIdUser;
        String nameOpponent;

        do {
            randomIdUser = (int) ((Math.random() * userRepository.getAllUsers().size()) + 1);
            nameOpponent = userRepository.getUserName(randomIdUser);
        } while (idUser == randomIdUser);

        if (!userRepository.checkUsersMemberClans(nameUser)) {
            clanService.JoiningClan(nameUser);
        }
        if (!userRepository.checkUsersMemberClans(nameOpponent)) {
            clanService.JoiningClan(nameOpponent);
        }

        int randNumber = (int) ((Math.random() * 4) + 1);
        switch (randNumber) {
            case 1 -> battle(nameUser, nameOpponent);
            case 2 -> gamble(nameUser, nameOpponent);
            case 3 -> tasks(nameUser);
            default -> hunts(nameUser);
        }
    }

    @Override
    public void battle(String nameUser, String nameOpponent) {
        int payment = 25;
        double deduct = 0.2;
        int winPaycheck = payment << 1;
        int deductTreasury = (int) ((winPaycheck - payment) * deduct);

        int balanceUser = userRepository.getUserBalance(nameUser);

        int balanceOpponent = userRepository.getUserBalance(nameOpponent);
        int idClanOpponent = userRepository.getUserClan(nameOpponent);
        int idTreasuryOpponent = treasuryRepository.getIdTreasury(idClanOpponent);

        if (balanceUser > payment && balanceOpponent > payment) {

            int skillOpponent = userRepository.getSkillArena(nameOpponent);
            int skillUser = userRepository.getSkillArena(nameUser);

            int idClan = userRepository.getUserClan(nameUser);
            int idTreasury = treasuryRepository.getIdTreasury(idClan);

            userRepository.reduceBalanceUsers(payment, nameUser);
            detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.BATTLES_PAYMENT), payment, idTreasury);

            userRepository.reduceBalanceUsers(payment, nameOpponent);
            detailsRepository.insertTo(nameOpponent, String.valueOf(EnamActions.BATTLES_PAYMENT), payment, idTreasuryOpponent);

            if (skillUser > skillOpponent) {
                detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.BATTLES_WIN), winPaycheck - deductTreasury, idTreasury);
                userRepository.increaseBalanceUsers(winPaycheck - deductTreasury, nameUser);
                detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.BATTLES_DEDUCT), deductTreasury, idTreasury);
                treasuryRepository.increaseBalanceTreasury(idTreasury, deductTreasury);
                System.out.println(Thread.currentThread() + " in work: " + nameUser + " won in the arena " + skillUser + " : " + skillOpponent +
                        " " + nameOpponent);
            } else if (skillUser == skillOpponent) {
                detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.RETURN_PAYMENT), payment, idTreasury);
                userRepository.increaseBalanceUsers(payment, nameUser);
                detailsRepository.insertTo(nameOpponent, String.valueOf(EnamActions.RETURN_PAYMENT), payment, idTreasuryOpponent);
                userRepository.increaseBalanceUsers(payment, nameOpponent);
                System.out.println(Thread.currentThread() + " in work: " + "Draw in the arena " + nameUser + " " + skillUser + " : " + skillOpponent +
                        " " + nameOpponent);
            } else {
                detailsRepository.insertTo(nameOpponent, String.valueOf(EnamActions.BATTLES_WIN), winPaycheck - deductTreasury, idTreasuryOpponent);
                userRepository.increaseBalanceUsers(winPaycheck - deductTreasury, nameOpponent);
                detailsRepository.insertTo(nameOpponent, String.valueOf(EnamActions.BATTLES_DEDUCT), deductTreasury, idTreasuryOpponent);
                treasuryRepository.increaseBalanceTreasury(idTreasuryOpponent, deductTreasury);
                System.out.println(Thread.currentThread() + " in work: " + nameUser + " loser in the arena " + skillUser + " : " + skillOpponent +
                        " " + nameOpponent);
            }
        } else
            System.out.println(Thread.currentThread() + " in work: " + "There is not enough money to enter the arena at " + nameUser);
    }

    @Override
    public void gamble(String nameUser, String nameOpponent) {
        int payment = 50;
        double deduct = 0.5;
        int winPaycheck = payment * 2;
        int deductTreasury = (int) ((winPaycheck - payment) * deduct);

        int balanceUser = userRepository.getUserBalance(nameUser);

        int balanceOpponent = userRepository.getUserBalance(nameOpponent);
        int idClanOpponent = userRepository.getUserClan(nameOpponent);
        int idTreasuryOpponent = treasuryRepository.getIdTreasury(idClanOpponent);

        if (balanceUser > payment && balanceOpponent > payment) {

            int skillOpponent = userRepository.getSkillGamble(nameOpponent);
            int skillUser = userRepository.getSkillGamble(nameUser);

            int idClan = userRepository.getUserClan(nameUser);
            int idTreasury = treasuryRepository.getIdTreasury(idClan);

            detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.GAMBLES_PAYMENT), payment, idTreasury);
            userRepository.reduceBalanceUsers(payment, nameUser);

            detailsRepository.insertTo(nameOpponent, String.valueOf(EnamActions.GAMBLES_PAYMENT), payment, idTreasuryOpponent);
            userRepository.reduceBalanceUsers(payment, nameOpponent);

            if (skillUser > skillOpponent) {
                detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.GAMBLES_WIN), winPaycheck - deductTreasury, idTreasury);
                userRepository.increaseBalanceUsers(winPaycheck - deductTreasury, nameUser);
                detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.GAMBLES_DEDUCT), deductTreasury, idTreasury);
                treasuryRepository.increaseBalanceTreasury(idTreasury, deductTreasury);
                System.out.println(Thread.currentThread() + " in work: " + nameUser + " won by playing cards " + skillUser + " : " + skillOpponent +
                        " " + nameOpponent);
            } else if (skillUser == skillOpponent) {
                detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.RETURN_PAYMENT), payment, idTreasury);
                userRepository.increaseBalanceUsers(payment, nameUser);
                detailsRepository.insertTo(nameOpponent, String.valueOf(EnamActions.RETURN_PAYMENT), payment, idTreasuryOpponent);
                userRepository.increaseBalanceUsers(payment, nameOpponent);
                System.out.println(Thread.currentThread() + " in work: Draw in the card game " + nameUser + " " + skillUser + " : " + skillOpponent +
                        " " + nameOpponent);
            } else {
                detailsRepository.insertTo(nameOpponent, String.valueOf(EnamActions.GAMBLES_WIN), winPaycheck - deductTreasury, idTreasuryOpponent);
                userRepository.increaseBalanceUsers(winPaycheck - deductTreasury, nameOpponent);
                detailsRepository.insertTo(nameOpponent, String.valueOf(EnamActions.GAMBLES_DEDUCT), deductTreasury, idTreasuryOpponent);
                treasuryRepository.increaseBalanceTreasury(idTreasuryOpponent, deductTreasury);
                System.out.println(Thread.currentThread() + " in work: " + "Игрок " + nameUser + " loser playing cards " + skillUser + " : " + skillOpponent +
                        " " + nameOpponent);
            }
        } else
            System.out.println(Thread.currentThread() + " in work: There is not enough money for gambling at " + nameUser);
    }

    @Override
    public void tasks(String nameUser) {
        double deduct = 0.3;
        int awardForTask = (int) ((Math.random() * 100) + 1);
        int deductTreasury = (int) (awardForTask * deduct);

        int idClan = userRepository.getUserClan(nameUser);
        int idTreasury = treasuryRepository.getIdTreasury(idClan);

        detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.TASKS), awardForTask - deductTreasury, idTreasury);
        userRepository.increaseBalanceUsers(awardForTask - deductTreasury, nameUser);
        detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.TASKS_DEDUCT), deductTreasury, idTreasury);
        treasuryRepository.increaseBalanceTreasury(idTreasury, deductTreasury);
        System.out.println(Thread.currentThread() + " in work: " + nameUser + " completed the task and received a reward = " + awardForTask);
    }

    @Override
    public void hunts(String nameUser) {
        double deduct = 0.25;
        int awardForTask = (int) ((Math.random() * 70) + 1);
        int deductTreasury = (int) (awardForTask * deduct);

        int idClan = userRepository.getUserClan(nameUser);
        int idTreasury = treasuryRepository.getIdTreasury(idClan);

        detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.HUNTING), awardForTask - deductTreasury, idTreasury);
        userRepository.increaseBalanceUsers(awardForTask - deductTreasury, nameUser);
        detailsRepository.insertTo(nameUser, String.valueOf(EnamActions.HUNTING_DEDUCT), deductTreasury, idTreasury);
        treasuryRepository.increaseBalanceTreasury(idTreasury, deductTreasury);
        System.out.println(Thread.currentThread() + " in work: " + nameUser + " went hunting and got a loot = " + awardForTask);
    }
}