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
@Table ("tb_museum")
public class Museum {
    
    @Id
    @Column("mus_id")
    public Long id;
    
    @Column("mus_name")
    public String name;
    
    @Column("mus_city")
    public String city;

    private Museum(Long id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    
    public static Museum createMuseum(String name, String city){
        return new Museum(null,name,city);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
    
}

