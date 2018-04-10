package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.springapp.mvc.model.Gender.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String redirectLogin() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = GET)
    public String printWelcome(Model model) {
        model.addAttribute("message", "Hi there! Log in, please");
        return "index";
    }

    @RequestMapping(value = "/registration", method = GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = POST)
    public String registration(@ModelAttribute("userFrom") User user, Model model) {

        if (userService.checkIfUserExists(user)) {
            model.addAttribute("messageErrorPassConfirm", "Username already exists");
            return "registration";
        }
        if (userService.checkPasswordConfirm(user)) {
            userService.saveUser(user);
            return "redirect:/login";
        }
        model.addAttribute("messageErrorPassConfirm", "Passwords does not match");
        return "registration";
    }

    @RequestMapping(value = "/error")
    public String returnError() {
        return "error";
    }

    @RequestMapping(value = "/allusers", method = GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "welcome";
    }

    @RequestMapping(value = "/showMales", method = GET)
    public String showOnlyMales(Model model) {
        model.addAttribute("gender", "boys");
        model.addAttribute("list", userService.getAllByGender(MALE));
        return "gender";
    }

    @RequestMapping(value = "/showFemales", method = GET)
    public String showOnlyFemales(Model model) {
        model.addAttribute("gender", "girls");
        model.addAttribute("list", userService.getAllByGender(FEMALE));
        return "gender";
    }

    @RequestMapping(value = "/secret", method = GET)
    public String secretPage() {
        return "secret";
    }
}