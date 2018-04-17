package com.springapp.mvc.controller;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import com.springapp.mvc.validator.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import static com.springapp.mvc.model.Gender.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserValidation userValidation;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

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
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @RequestMapping(value = "/registration", method = POST)
    public String registration(@ModelAttribute @Valid User user, BindingResult result, Model model){
        userValidation.validate(user, result);
        if (result.hasErrors()){
            return "registration";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/error")
    public String returnError() {
        return "error";
    }

    @RequestMapping(value = "/allusers", method = GET)
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("usernameOfCurrentUser", name);
        model.addAttribute("userId", userService.getUserByName(name).get().getId());
        return "welcome";
    }

    @RequestMapping(value = "/showMales", method = GET)
    public String showOnlyMales(Model model) {
        model.addAttribute("gender", "boys");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userId", userService.getUserByName(name).get().getId());
        model.addAttribute("list", userService.getAllByGender(MALE));
        return "gender";
    }

    @RequestMapping(value = "/showFemales", method = GET)
    public String showOnlyFemales(Model model) {
        model.addAttribute("gender", "girls");
        model.addAttribute("list", userService.getAllByGender(FEMALE));
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userId", userService.getUserByName(name).get().getId());
        return "gender";
    }

    @RequestMapping(value = "/secret", method = GET)
    public String secretPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userId", userService.getUserByName(name).get().getId());
        return "secret";
    }

    @RequestMapping(value = "/editUser/{id}", method = GET)
    public String editUser(Model model, @PathVariable int id){
        model.addAttribute("user", userService.getUserById(id).get());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userId", userService.getUserByName(name).get().getId());
        return "profileEdit";
    }

    @RequestMapping(value = "/editUser", method = POST)
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult result){
        userValidation.validateExistsUser(user, result);
        if (result.hasErrors()){
            return "profileEdit";
        }
        userService.editUser(user);
        return "redirect:/adminpanel";
    }

    @RequestMapping(value = "/profile", method = GET)
    public String profile(Model model, @RequestParam(value = "id") int id){
        model.addAttribute("user", userService.getUserById(id).get());
        return "profile";
    }

    @RequestMapping(value = "/adminpanel", method = GET)
    public String adminPanel(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userId", userService.getUserByName(name).get().getId());
        model.addAttribute("list", userService.getAllUsers().stream().filter(u -> !u.getUsername().equals(name)).collect(Collectors.toList()));
        return "adminpanel";
    }

    @RequestMapping(value = "/deleteUser", method = GET)
    public String deleteUser(@RequestParam(value = "id") int id){
        userService.deleteUser(id);
        return "redirect:/adminpanel";
    }

    @RequestMapping(value = "/newuser", method = GET)
    public String newUser(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        model.addAttribute("userId", userService.getUserByName(name).get().getId());
        model.addAttribute("user", new User());
        return "newuser";
    }
    @RequestMapping(value = "/newuser", method = POST)
    public String newUser(@ModelAttribute @Valid User user, BindingResult result, Model model){
        userValidation.validate(user, result);
        if (result.hasErrors()){
            return "newuser";
        }
        userService.saveUser(user);
        return "redirect:/adminpanel";
    }
}