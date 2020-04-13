/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.service;

import com.mycompany.cardealership.data.UserRepository;
import com.mycompany.cardealership.models.User;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author G10-DEV10W3
 */
@Service
public class UserService {
    
    @Autowired
    UserRepository repo;

    public Result<List<User>> getAllUsers() {
        Result<List<User>> result = new Result<>();
        result.setPayload(repo.findAll());
        return result;
    }

    public Result<User> saveUser(User u) {
        Result<User> result = validate(u);
        if (result.isSuccess()) {
            u = repo.save(u);
            result.setPayload(u);
        }
        return result;

    }

    private Result<User> validate(User u) {
        Result<User> result = new Result<>();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> errs = validator.validate(u);
        for (ConstraintViolation<User> err : errs) {
            result.addMessage(err.getMessage());
        }

        return result;
    }
}
