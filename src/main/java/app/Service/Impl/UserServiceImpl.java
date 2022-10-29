package app.Service.Impl;

import app.Models.Users;
import app.Repository.Impl.UserRepositoryImpl;
import app.Repository.UserRepository;
import app.Service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public void createNewUser() {
        Users newUser = new Users();
        new UserRepositoryImpl().createNewUser(newUser.getNameUsers()
                , newUser.getSkillArena()
                , newUser.getSkillGamble()
                , newUser.getBalances());
    }
}
