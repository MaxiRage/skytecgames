package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public synchronized void createNewUser(String nameUser, int skillArena, int skillGamble, int balances) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequestCreate = String.format("INSERT INTO USERS (NAME_USERS, SKILL_ARENA, SKILL_GAMBLE, BALANCES) " +
                    "VALUES ('%s', %d, %d, %d)", nameUser, skillArena, skillGamble, balances);
            ManagementTables.statement(connection, SQLRequestCreate);

            System.out.println(Thread.currentThread() + " is working: create user with name " + nameUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized String getUserName(int idUser) {
        String nameUser = "";
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String requestIdUser = String.format("SELECT name_users FROM users " +
                    "WHERE users_id = %d", idUser);

            ResultSet resultSet = statement.executeQuery(requestIdUser);

            while (resultSet.next()) {
                nameUser = resultSet.getString("name_users");
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nameUser;
    }

    @Override
    public synchronized int getUserId(String nameUser) {
        int user_id = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String requestIdUser = String.format("SELECT users_id FROM users " +
                    "WHERE name_users = '%s'", nameUser);

            ResultSet resultSet = statement.executeQuery(requestIdUser);

            while (resultSet.next()) {
                user_id = resultSet.getInt("users_id");
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user_id;
    }

    @Override
    public synchronized int getUserBalance(String nameUser) {
        int balanceUser = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();

            String requestBalanceUser = String.format("SELECT balances FROM USERS " +
                    "WHERE name_users = '%s'", nameUser);

            ResultSet resultSet = statement.executeQuery(requestBalanceUser);

            while (resultSet.next()) {
                balanceUser = resultSet.getInt("balances");
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return balanceUser;
    }

    @Override
    public synchronized int getSkillArena(String nameUser) {
        int skillArena = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();

            String requestBalanceUser = String.format("SELECT skill_arena FROM USERS " +
                    "WHERE name_users = '%s'", nameUser);

            ResultSet resultSet = statement.executeQuery(requestBalanceUser);

            while (resultSet.next()) {
                skillArena = resultSet.getInt("skill_arena");
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skillArena;
    }

    @Override
    public synchronized void reduceBalanceUsers(int amount, String nameUser) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE users SET balances=balances-%d WHERE name_users = '%s'", amount, nameUser);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void increaseBalanceUsers(int amount, String nameUser) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE users SET balances=balances+%d WHERE name_users = '%s'", amount, nameUser);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized List<String> getAllUsers() {
        List<String> listAllUsers = new ArrayList<>();
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            while (resultSet.next()) {
                listAllUsers.add(resultSet.getString("name_users"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listAllUsers;
    }
    @Override
    public synchronized List<String> getAllUsersFromClan() {
        List<String> listAllUsers = new ArrayList<>();
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = "SELECT * FROM users WHERE CLANS_ID IS NOT NULL";
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            while (resultSet.next()) {
                listAllUsers.add(resultSet.getString("name_users"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listAllUsers;
    }
    @Override
    public synchronized boolean checkIsPresentUserInBD(String nameUsers) {
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = String.format("SELECT name_users FROM users where name_users = '%s'", nameUsers);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            if (resultSet.next()) {
                resultSet.close();
                return true;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public synchronized void userJoinsClan(String nameUser, int idClan) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("UPDATE users Set clans_id=%d WHERE name_users = '%s'", idClan, nameUser);
            ManagementTables.statement(connection, SQLRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized int getSkillGamble(String nameUser) {
        int skillGamble = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();

            String requestBalanceUser = String.format("SELECT skill_gamble FROM USERS " +
                    "WHERE name_users = '%s'", nameUser);

            ResultSet resultSet = statement.executeQuery(requestBalanceUser);

            while (resultSet.next()) {
                skillGamble = resultSet.getInt("skill_gamble");
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return skillGamble;
    }

    @Override
    public synchronized boolean checkUsersMemberClans(String nameUser) {
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = String.format("Select CLANS_ID from USERS where NAME_USERS= '%s'", nameUser);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            while (resultSet.next()) {
                if (resultSet.getBoolean("clans_id")) {
                    resultSet.close();
                    return true;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public synchronized int getUserClan(String nameUser) {
        int idClan = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = String.format("Select CLANS_ID from USERS where NAME_USERS= '%s'", nameUser);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            if (resultSet.next()) {
                idClan = resultSet.getInt("clans_id");
                resultSet.close();
                return idClan;
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idClan;
    }
}