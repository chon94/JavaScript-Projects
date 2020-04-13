/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.service;


import com.mycompany.cardealership.data.ContactMessageRepository;
import com.mycompany.cardealership.models.ContactMessage;
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
public class ContactMessageService {
    
    @Autowired
    ContactMessageRepository repo;

    public Result<ContactMessage> addContactMessage(ContactMessage m) {
        Result<ContactMessage> result = validate(m);
        if (result.isSuccess()) {
            m = repo.save(m);
            result.setPayload(m);
        }
        return result;

    }

    private Result<ContactMessage> validate(ContactMessage m) {
        Result<ContactMessage> result = new Result<>();

        if (m.getEmail().length() == 0 && m.getPhone().length() == 0) {
            result.addMessage("Either a phone number or email address is required.");
        }

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ContactMessage>> errs = validator.validate(m);
        for (ConstraintViolation<ContactMessage> err : errs) {
            result.addMessage(err.getMessage());
        }

        return result;
    }

}
