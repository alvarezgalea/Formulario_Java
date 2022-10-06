/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 *
 * @author Miguel Alvarez
 */
@Table("productos")
public class Producto {
    @Id
    @Column("codigo")
    public Integer codigo;
    @Column("nombre")
    public String nombre;
    @Column("precio")
    public double precio;
    @Column("inventario")
    public int inventario;

   
     private Producto(Integer codigo, String nombre, Double precio, Integer inventario) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }
    
    public static Producto createProducto(String nombre, Double precio, Integer inventario){
        return new Producto(null,nombre,precio,inventario);
    }


   
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
 
    
}
