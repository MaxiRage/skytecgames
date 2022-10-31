package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.ClanRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ClanRepositoryImpl implements ClanRepository {

    @Override
    public synchronized void createClan(String nameClan) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO clans (name_clan) " +
                    "VALUES ('%s')", nameClan);
            ManagementTables.statement(connection, SQLRequest);
            System.out.println(Thread.currentThread() + " in work: Cоздал клан " + nameClan);
        } catch (SQLException e) {
            throw new RuntimeException(e + " " + Thread.currentThread() + " in work: Не удалось создать новый Клан");
        }
    }

    @Override
    public synchronized Map<Integer, String> getAllClans() {

        Map<Integer, String> listClans = new HashMap<>();
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String SQLRequest = "SELECT * FROM clans";
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            while (resultSet.next()) {
                listClans.put(resultSet.getInt("clans_id"), resultSet.getString("name_clan"));
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e + " " + Thread.currentThread() + " in work: Ошибка получения записей в таблице Кланов");
        }
        return listClans;
    }

    @Override
    public synchronized int getClanId(String nameClan) {
        int clan_id = 0;
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String requestIdUser = String.format("SELECT clans_id FROM clans " +
                    "WHERE name_clan = '%s'", nameClan);

            ResultSet resultSet = statement.executeQuery(requestIdUser);

            while (resultSet.next()) {
                clan_id = resultSet.getInt("clans_id");
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e + " " + Thread.currentThread() + " in work: Не найден id клана " + nameClan);
        }
        return clan_id;
    }

    @Override
    public synchronized String getClanName(int clanId) {
        String nameClan = "";
        try (Connection connection = ManagementTables.getConnection()) {
            Statement statement = connection.createStatement();
            String requestIdUser = String.format("SELECT name_clan FROM clans " +
                    "WHERE clans_id = %d", clanId);

            ResultSet resultSet = statement.executeQuery(requestIdUser);

            while (resultSet.next()) {
                nameClan = resultSet.getString("name_clan");
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nameClan;
    }
}