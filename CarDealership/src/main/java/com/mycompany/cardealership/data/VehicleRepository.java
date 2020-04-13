/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardealership.data;

import com.mycompany.cardealership.models.Vehicle;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author G10-DEV10W3
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    @Query(value = "SELECT * FROM vehicle v"
            + " Inner join model mo on v.model_id = mo.model_id"
            + " Inner join Make ma on v.make_id = ma.make_id"
            + " WHERE"
            + " `type` = ?1"
            + " AND ('' = ?2 or ma.make LIKE ?2 or mo.model LIKE ?2 or `year` LIKE ?2)"
            + " AND ('' = ?3 or `year` > ?3)"
            + " AND ('' = ?4 or `year` < ?4)"
            + " AND (?5 is null or sale_price > ?5)"
            + " AND (?6 is null or sale_price < ?6)"
            + " AND `sold` = ?7"
            + " ORDER BY msrp DESC LIMIT 20;", nativeQuery = true)
//    @Query(value = "SELECT * FROM vehicle v"
//            + " Inner join model mo on v.model_id = mo.model_id"
//            + " Inner join Make ma on v.make_id = ma.make_id"
//            + " WHERE"
//            + " `type` = (?1, new)"
//            + " AND v.year > ifnull(?2, 0.0)"
//            + " AND v.sale_price > ifnull(?3, 6000)"
//            + " ORDER BY msrp DESC LIMIT 20;", nativeQuery = true)
    public List<Vehicle> getNewOrUsedVehicleSearch(String type, String makeModelYear,
            int minYear, int maxYear, 
            BigDecimal minPrice, BigDecimal maxPrice, String sold);
    
    @Query(value = "SELECT * FROM vehicle v"
            + " Inner join model mo on v.model_id = mo.model_id"
            + " Inner join make ma on v.make_id = ma.make_id"
            + " WHERE"
            + " `sold` = ?1"
            + " AND ('' = ?2 or ma.make LIKE ?2 or mo.model LIKE ?2 or `year` LIKE ?2 )"
            + " AND ('' = ?3 or `year` > ?3)"
            + " AND ('' = ?4 or `year` < ?4)"
            + " AND ('' = ?5 or sale_price > ?5)"
            + " AND ('' = ?6 or sale_price < ?6)"
            + " ORDER BY msrp DESC LIMIT 20;", nativeQuery = true)
    public List<Vehicle> getAllAvailableVehiclesSearch(String sold, String makeModelYear, int minYear, int maxYear, 
            BigDecimal minPrice, BigDecimal maxPrice);

    
    @Query(value = "SELECT * FROM vehicle WHERE featured = true", nativeQuery = true)
    public List<Vehicle> getFeaturedVehicles();
    
     @Modifying
     @Query(value = "UPDATE vehicle SET sold = true WHERE vehicle.vehicle_id = ?1", nativeQuery = true)
     public void purchaseVehicle(int VehicleId);
    
}