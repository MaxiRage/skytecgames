package app.Repository;

import lombok.SneakyThrows;

public interface DetailsRepository {
    void insertTo(String nameUser, String actions, int amount, int idClan);
}
