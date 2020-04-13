/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.data;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author G10-DEV10W3
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ContactMessageRepositoryTest {
    
    @Autowired
    private ContactMessageRepository repo;

    public ContactMessageRepositoryTest() {
    }
    
    @Test
    public void testMessage() {
        
        assertTrue(repo.findAll().size() > 0);
    }
    

}
