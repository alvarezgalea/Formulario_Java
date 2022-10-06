/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Miguel Alvarez
 */
@Repository
public interface IRepositoryProducto extends CrudRepository<Producto,Integer>  {
    
    @Query("Select sum(precio*inventario) total_inventario from productos")
    Double getInventory();
   
    @Query("Select nombre from productos order by precio desc limit 1")
    String getTopPriceProduct();
    
    @Query("Select nombre from productos order by precio asc limit 1")
    String getBottomPriceProduct();
    
    @Query("Select avg(precio) total_inventario from productos")
    Double getAvgInventory();

}
