package com.springapp.mvc.controller;


import com.google.gson.Gson;
import com.springapp.mvc.model.Gender;
import com.springapp.mvc.model.Role;
import com.springapp.mvc.model.Roles;
import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = GET)
    public String getUsers() {
        Gson gson = new Gson();
        String obj = gson.toJson(userService.getAllUsers());
        return obj;
    }

    @RequestMapping(value = "/users/{id}", method = GET)
    public String getUserById(@PathVariable int id){
        Gson gson = new Gson();
        String obj = gson.toJson(userService.getUserById(id));
        return obj;
    }

    @RequestMapping(value = "/usersByUsername", method = GET)
    public String getUserByUsername(@RequestParam(value = "username") String username){
        Gson gson = new Gson();
        String obj = gson.toJson(userService.getUserByName(username));
        return obj;
    }

    @RequestMapping(value = "/usersByAgeOver/{age}", method = GET)
    public String getUsersByAgeOver(@PathVariable double age){
        Gson gson = new Gson();
        String obj = gson.toJson(userService.getAllUsersByAgeOver(age));
        return obj;
    }

    @RequestMapping(value = "/usersByAgeUnder/{age}", method = GET)
    public String getUsersByAgeUnder(@PathVariable double age){
        Gson gson = new Gson();
        String obj = gson.toJson(userService.getAllUsersByAgeUnder(age));
        return obj;
    }

    @RequestMapping(value = "/usersByGender/{gender}", method = GET)
    public String getUsersByGender(@PathVariable Gender gender){
        Gson gson = new Gson();
        String obj = gson.toJson(userService.getAllByGender(gender));
        return obj;
    }

    @RequestMapping(value = "/usersByRole/{role}", method = GET)
    public String getUsersByAgeUnder(@PathVariable Role role){
        Gson gson = new Gson();
        String obj = gson.toJson(userService.getAllUsersByRole(role));
        return obj;
    }

    @RequestMapping(value = "/addUser", method = POST)
    public String addUser(@RequestBody String string){
        Gson gson = new Gson();
        User newUser = gson.fromJson(string, User.class);
        userService.saveUser(newUser);
        return "success";
    }

    @RequestMapping(value = "/addRole", method = POST)
    public String addRole(@RequestBody String string){
        Gson gson = new Gson();
        Roles newRole = gson.fromJson(string, Roles.class);
        userService.saveRole(newRole);
        return "success";
    }

    @RequestMapping(value = "/edituser", method = PUT)
    public String editUser(@RequestBody String string){
        Gson gson = new Gson();
        User newUser = gson.fromJson(string, User.class);
        userService.editUser(newUser);
        return "success";
    }

    @RequestMapping(value = "/editRole", method = PUT)
    public String editRole(@RequestBody String string){
        Gson gson = new Gson();
        Roles newRole = gson.fromJson(string, Roles.class);
        userService.editRole(newRole);
        return "success";
    }

    @RequestMapping(value = "/removeUser/{id}", method = DELETE)
    public String removeUser(@PathVariable int id){
        userService.deleteUser(id);
        return "success";
    }

    @RequestMapping(value = "/removeRole/{id}", method = DELETE)
    public String removeRole(@PathVariable int id){
        userService.deleteRole(id);
        return "success";
    }


}
