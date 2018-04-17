package com.springapp.mvc.service;


import com.springapp.mvc.datasource.UserDao;

import com.springapp.mvc.model.Gender;
import com.springapp.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userDao.getListOfUsers();
    }

    public List<User> getAllByGender(Gender gender) {
        return userDao.getAllByGender(gender);
    }

    public Optional<User> getUserByName(String username) {
        Optional<User> user = userDao.getUserByName(username);
        return user;
    }

    public Optional<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    public boolean checkPasswordConfirm(User user){
        if (user.getPassword().equals(user.getConfirmPassword())) return true;
        return false;
    }

    public boolean checkIfUserExistsByUsername(User user){
        if (userDao.getListOfUsers().stream()
                .anyMatch(u -> u.getUsername().equals(user.getUsername()))) return true;
        return false;
    }

    public boolean checkIfUserExistsByEmail(User user){
        if (userDao.getListOfUsers().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()))) return true;
        return false;
    }

    public void editUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.editUser(user);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }
}
