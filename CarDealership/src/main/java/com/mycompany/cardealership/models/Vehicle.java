/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.models;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author G10-DEV10W3
 */
@Data
@Entity
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;
    private String vin;
    
    @ManyToOne
    @JoinColumn(name = "make_id")
    private Make make;
    
    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
    
    private String color;
    private String type;
    private String bodyStyle;
    private String transmission;
    private String interior;
    
    @Digits(integer=4, fraction=0, message="Year must be 4 digits.")
    @Min(value=2000, message="Year must be between 2000 and next year.")
    private int year;
    
    
    private BigDecimal msrp;
    private BigDecimal salePrice;
    private int mileage;
    
    @Size(min=1, message="Description is required.")
    private String description;
    
    @Size(min=1, message="Photo is required.")
    private String photo;
    private boolean featured;
    private boolean sold;
}
