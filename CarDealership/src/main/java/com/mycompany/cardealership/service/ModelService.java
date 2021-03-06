/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.service;

import com.mycompany.cardealership.data.ModelRepository;
import com.mycompany.cardealership.models.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author G10-DEV10W3
 */
@Service
public class ModelService {
    
    @Autowired
    ModelRepository repo;
    
    public Result<List<Model>> getAllModels() {
      Result<List<Model>> result = new Result<>();
        result.setPayload(repo.findAll());
        return result;
    }
    
    public Result<Model> saveModel(Model m) {
      Result<Model> result = validate(m);
        if (result.isSuccess()) {
            m = repo.save(m);
            result.setPayload(m);
        }
        return result;
    }

    private Result<Model> validate(Model m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
