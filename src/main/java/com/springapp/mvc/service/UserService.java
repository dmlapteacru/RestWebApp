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
        return userDao.getUserByName(username);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    public boolean checkPasswordConfirm(User user){
        if (user.getPassword().equals(user.getConfirmPassword())) return true;
        return false;
    }

    public boolean checkIfUserExists(User user){
        if (userDao.getListOfUsers().stream()
                .anyMatch(u -> u.getUsername().equals(user.getUsername()))) return true;
        return false;
    }
}
