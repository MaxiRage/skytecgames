package app.Repository;

import java.util.Map;

public interface ClanRepository {
    void createClan(String nameClan);
    Map<Integer, String> getAllClans(); // TODO убрать, если не используется
    int getClanId(String nameClan);
    String getClanName(int clanId);
}
