/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.controllers;

import com.mycompany.cardealership.models.ContactMessage;
import com.mycompany.cardealership.service.ContactMessageService;
import com.mycompany.cardealership.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author G10-DEV10W3
 */
@CrossOrigin
@RestController
@RequestMapping("/message")
public class ContactMessageController {
    
    @Autowired
    private ContactMessageService service;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContactMessage> addContactMessage(@RequestBody ContactMessage m) {
        
        Result<ContactMessage> result = service.addContactMessage(m);
        if (result.isSuccess()) {
            return ResponseEntity.ok(m);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    

//    @PostMapping("/start/{rows}/{columns}")
//    public ResponseEntity<ContactMessage> addContactMessage(@RequestBody ContactMessage m) {
//        try {
//            return new ResponseEntity(service.addContactMessage(m), HttpStatus.CREATED);
//        } catch (IllegalArgumentException ex) {
//            return new ResponseEntity(
//                    new ContactMessage(ex.getMessage()),
//                    HttpStatus.CONFLICT);
//        }
//    }
    
    //memory game code "return new response entity"
}
