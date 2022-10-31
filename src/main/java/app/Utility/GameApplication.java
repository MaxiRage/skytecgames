package app.Utility;

import app.DB.ManagementTables;
import app.Repository.ClanRepository;
import app.Repository.Impl.ClanRepositoryImpl;
import app.Repository.Impl.TreasuryRepositoryImpl;
import app.Repository.Impl.UserRepositoryImpl;
import app.Repository.TreasuryRepository;
import app.Repository.UserRepository;
import app.Service.ActionsService;
import app.Service.ClanService;
import app.Service.Impl.ActionsServiceImpl;
import app.Service.Impl.ClanServiceImpl;
import app.Service.Impl.UserServiceImpl;
import app.Service.UserService;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * This class creates and connects to database's. Next, user's, clan's, and treasuries are created and user actions are triggered
 */
public class GameApplication {

    @SneakyThrows
    public static void run(int countUsers) {
        //Connect and create database's
        ManagementTables managementTables = new ManagementTables();
        managementTables.dropAllTable();
        managementTables.createAllTables();

        ClanRepository clanRepository = new ClanRepositoryImpl();
        TreasuryRepository treasuryRepository = new TreasuryRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        UserService userService = new UserServiceImpl();
        ActionsService actionsService = new ActionsServiceImpl();
        ClanService clanService = new ClanServiceImpl();


        ExecutorService executors = Executors.newCachedThreadPool();

        //Create clans and treasury's
        Arrays.stream(new String[]{"PowerRangers", "MadWolves", "ForestElves"})
                .parallel()
                .forEach(nameClan -> {
                    clanRepository.createClan(nameClan);
                    int idClans = clanRepository.getClanId(nameClan);
                    treasuryRepository.createNewTreasury(idClans, nameClan);
                });

        //Create user's
        while (countUsers-- > 0)
            executors.submit(userService::createNewUser);

        //Users join a clan and perform action's
        Thread.sleep(100); //to work correctly with all users
        userRepository.getAllUsers()
                .stream()
                .parallel()
                .forEach(nameUser ->
                        executors.submit(() -> {
                                    clanService.JoiningClan(nameUser);
                                    AtomicInteger randomCountActions = new AtomicInteger((int) (Math.random() * 10) + 1);
                                    while (randomCountActions.getAndDecrement() > 0) {
                                                actionsService.runGames(nameUser);
                                    }
                                }
                        ));
        executors.shutdown();
    }
}