/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.service;

import com.mycompany.cardealership.data.PurchaseRepository;
import com.mycompany.cardealership.data.UserRepository;
import com.mycompany.cardealership.data.VehicleRepository;
import com.mycompany.cardealership.models.Purchase;
import com.mycompany.cardealership.models.User;
import com.mycompany.cardealership.models.Vehicle;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author G10-DEV10W3
 */
@Transactional
@Service
public class PurchaseService {
    
    @Autowired
    PurchaseRepository repo;
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    VehicleRepository vehicleRepo;

    public Result<Purchase> savePurchase(Purchase p) {
        p = grabUserAndVehicle(p);
        Result<Purchase> result = validate(p);
        if (result.isSuccess()) {
            p = repo.save(p);
            result.setPayload(p);
            Vehicle v = p.getVehicle();
            vehicleRepo.purchaseVehicle(v.getVehicleId());
        }
        return result;
    }

    private Purchase grabUserAndVehicle(Purchase p) {
        Optional<User> u = userRepo.findById(p.getUser().getUserId());
        if (u.isPresent()) {
            User user = u.get();
            p.setUser(user);
        } 

        Optional<Vehicle> v = vehicleRepo.findById(p.getVehicle().getVehicleId());
        if (v.isPresent()) {
            Vehicle veh = v.get();
            p.setVehicle(veh);
        }
        
        return p;
    }

    private Result<Purchase> validate(Purchase p) {
        Result<Purchase> result = new Result<>();
        BigDecimal NinetyFivePercent = new BigDecimal(.95);
        BigDecimal NinetyFivePercentOfSalePrice = p.getVehicle().getSalePrice()
                .multiply(NinetyFivePercent);
        Optional<User> u = userRepo.findById(p.getUser().getUserId());

        //Add other validation logic here
        if (p.getEmail().length() == 0 && p.getPhone().length() == 0) {
            result.addMessage("Either a phone number or email address is required.");
        }

        if (p.getPurchasePrice().compareTo(p.getVehicle().getMsrp()) == 1) {
            result.addMessage("Purchase price cannot be larger than MSRP.");
        }

        if (p.getPurchasePrice().compareTo(NinetyFivePercentOfSalePrice) == -1) {
            result.addMessage("Purchase price cannot be less than "
                    + "95% of sales price");
        }

        //Get all validation errors from model annotations
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Purchase>> errs = validator.validate(p);
        for (ConstraintViolation<Purchase> err : errs) {
            result.addMessage(err.getMessage());
        }

        return result;
    }
}
