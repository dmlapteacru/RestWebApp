package com.springapp.mvc.validator;

import com.springapp.mvc.model.User;
import com.springapp.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidation implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.checkIfUserExistsByUsername(user)){
            errors.rejectValue("username", "Username.exists");
        }
        if (userService.checkIfUserExistsByEmail(user)){
            errors.rejectValue("email", "Email.exists");
        }
        if (!userService.checkPasswordConfirm(user)){
            errors.rejectValue("confirmPassword", "Password.matchingPasswords");
        }
    }

    public void validateExistsUser(Object o, Errors errors){
        User user = (User) o;
        if (userService.checkIfUserExistsByEmail(user)){
            if (userService.checkIfUserExistsByUsername(user)){

            } else
                errors.rejectValue("email", "Email.exists");
        }
        if (!userService.checkPasswordConfirm(user)){
            errors.rejectValue("confirmPassword", "Password.matchingPasswords");
        }

    }
}
