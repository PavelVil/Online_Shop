package shop.service;


import shop.model.User;

import java.util.List;

public interface UserService {


    List<User> getAllUsers();

    User getUserByName(String name);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(String name);

    void saveRole(User user, String roleName);

}
