package app.Service.Impl;

import app.Repository.UserRepository;
import lombok.SneakyThrows;

/**
 * Данный класс объединяет возможные действия пользователей
 * Первый делом пользователь вступает в клан, а дальше идёт на арену, либо на задания, либо в играет в азартные игры
 */
public class ActionPipeline extends Thread{
    @Override
    public void run() {
        runGameUser();
    }

    //TODO Вернуть метод по подсчету строк в Юзерах = количетво
    UserRepository userRepository;

    int countUsers = userRepository.getCountRowsUsersTable();

    @SneakyThrows
    public void runGameUser() {
        int counter = countUsers;
        while (counter-- > 0) {
            int randomCountActions = (int) (Math.random() * 10) + 1;
                String nameUser = userRepository.getUserName(countUsers--);
                if (userRepository.checkIsPresentUserInBD(nameUser)) {
                    System.out.println(Thread.currentThread() + " в работе: Игрок " + nameUser + " выбирает клан"); //TODO Сделать выбор клана
                    new JoiningClan().feeTreasury(nameUser);
                    System.out.println(actionsUsers(nameUser, randomCountActions));
                } else runGameUser();
        }
    }

    /**
     * Метод выполняет рандомное действие
     */
    private static String actionsUsers(String nameUser, int countActions) {
        if (countActions <= 0)
            return Thread.currentThread() + " в работе: Пользователь " + nameUser + " завершил игру без действий";
        else {
            --countActions;
            int randNumber = (int) ((Math.random() * 3) + 1);
            switch (randNumber) {
                case 1 -> System.out.println(Thread.currentThread() + " в работе: Игрок " + nameUser + " был на арене");
                case 2 ->
                        System.out.println(Thread.currentThread() + " в работе: Игрок " + nameUser + " играл в азартные игры");
                default ->
                        System.out.println(Thread.currentThread() + " в работе: Игрок " + nameUser + " был на задании");
            }
            actionsUsers(nameUser, countActions);
        }
        return Thread.currentThread() + " в работе: Пользователь " + nameUser + " завершил игру и принёс клану пользу";
    }
}