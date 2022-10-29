package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.ClanRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class ClanRepositoryImpl implements ClanRepository {

    @Override
    public void createClan(String nameClan) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO clans (name_clan) " +
                    "VALUES ('%s')", nameClan);
            ManagementTables.statement(connection, SQLRequest);
            System.out.println(Thread.currentThread() + " в работе: Cоздал клан " + nameClan);
        } catch (SQLException e) {
            throw new RuntimeException(e + " " + Thread.currentThread() + " в работе: Не удалось создать новый Клан");
        }
    }
}