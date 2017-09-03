package shop.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.UserDao;
import shop.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(String name) {
        userDao.deleteUser(name);
    }

    @Override
    public void saveRole(User user, String roleName) {
        userDao.saveRole(user, roleName);
    }
}
