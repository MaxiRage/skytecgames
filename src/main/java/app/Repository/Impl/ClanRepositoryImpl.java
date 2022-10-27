package app.Repository.Impl;

import app.DB.ManagementTables;
import app.Repository.ClanRepository;
import lombok.SneakyThrows;

import java.sql.Connection;

public class ClanRepositoryImpl implements ClanRepository {
    @SneakyThrows
    @Override
    public void createClan(String nameClan, int balanceClan) {
        try (Connection connection = ManagementTables.getConnection()) {
            String SQLRequest = String.format("INSERT INTO clan (name_clan, balance) " +
                    "VALUES ('%s', %d", nameClan, balanceClan);
            ManagementTables.statement(connection, SQLRequest);
        }
    }
}