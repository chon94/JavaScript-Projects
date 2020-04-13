/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.service;

import com.mycompany.cardealership.data.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author G10-DEV10W3
 */
public class PurchaseServiceTest {
    
    @Autowired
    PurchaseRepository repo;
    
    @Test
    public void testSavePurchaseValid() {
    }
    
    @Test
    public void testSavePurchaseLowPurchasePrice() {
    }
    
    @Test
    public void testSavePurchaseNoPhoneOrEmail() {
    }
    
    @Test
    public void testSavePurchaseNoName() {
    }
    
    @Test
    public void testSavePurchaseZipNot5Digits() {
    }
    
    @Test
    public void testSavePurchaseHighPurchasePriceNoCity() {
    }
    
}
