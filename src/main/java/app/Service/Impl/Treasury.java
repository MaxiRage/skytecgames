package app.Service.Impl;

import app.Repository.TreasuryRepository;

public class Treasury extends Thread{

    String nameClans = "PowerRangers";
    int balances = 0;

    TreasuryRepository treasuryRepository;

    @Override
    public void run() {
        treasuryRepository.createNewTreasury(nameClans, balances);
    }
}
