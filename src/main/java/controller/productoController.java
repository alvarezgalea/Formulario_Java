/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.Producto;
import view.ProductoView;
import model.IRepositoryProducto;
import view.actualizacionView;
        

/**
 *
 * @author Miguel Alvarez
 */
public class productoController implements ActionListener {
    ArrayList<Producto> listaProductos;
    
    IRepositoryProducto repositoryProducto;
    ProductoView productoView;
    actualizacionView actualizacionView;

    
    
    public void setListaProductos(ArrayList<Producto> listaProductos){
        this.listaProductos= listaProductos;
        
    }
    
    

    public productoController(IRepositoryProducto repositoryProducto, ProductoView productoView, actualizacionView actualizacionView) {
        this.repositoryProducto = repositoryProducto;
        this.productoView = productoView;
        this.actualizacionView= actualizacionView; 
        addEvents();
        getProductos();
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("click...");  
        if(e.getSource()==this.productoView.getAddButton()){
            add();
        }
        
      //  if(e.getSource()==this.productoView.getFindButton()){
       //     search();
       // }
        
        if(e.getSource()==this.productoView.getUpdateButton()){
            update();
        }
        
        if(e.getSource()==this.productoView.getDeleteButton()){
            delete();
        }
       
         if(e.getSource()==this.productoView.getReportButton()){
            report();
        }
        
        
         
        if(e.getSource()==this.actualizacionView.getActualizarActButton()){
            actualizarDatos();
        }
        
        
         
         
         getProductos();
        
    }

    private void addEvents() {
        this.productoView.getAddButton().addActionListener(this);
        this.productoView.getUpdateButton().addActionListener(this);
        this.productoView.getDeleteButton().addActionListener(this);
        this.productoView.getReportButton().addActionListener(this);
        
        this.actualizacionView.getActualizarActButton().addActionListener(this);
    }

    private List<Producto> getProductos() {
         List<Producto> losDatos = (List<Producto>) repositoryProducto.findAll();
          JTable tableReport = this.productoView.getProductoReport();
         
          int row = 0;
           for (int i = 0; i < losDatos.size(); i++){
               tableReport.setValueAt(losDatos.get(i).codigo,row,0);
               tableReport.setValueAt(losDatos.get(i).nombre,row,1);
               tableReport.setValueAt(losDatos.get(i).precio,row,2);
               tableReport.setValueAt(losDatos.get(i).inventario,row,3);
               row++;
           }
           
           for (int i = 0; i < tableReport.getRowCount(); i++){
               tableReport.setValueAt("",row,0);
               tableReport.setValueAt("",row,1);
               tableReport.setValueAt("",row,2);
               tableReport.setValueAt("",row,3);
           }
           
           return losDatos;
    }
   
    
public void add(){
  
    try {
  
  String nombre = this.productoView.getNameField().getText();
 Double precio =  Double.parseDouble(this.productoView.getPrecioField().getText());
 Integer inventario =  Integer.parseInt(this.productoView.getInventarioField().getText());
Producto producto =Producto.createProducto(nombre,precio,inventario);
    repositoryProducto.save(producto);
    }
catch(Exception e) {
   JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios",
  "Advertencia", JOptionPane.WARNING_MESSAGE);
}
    
    


}

public void delete(){

        try{
                JTable tableReport = this.productoView.getProductoReport();
                Integer codigo = (Integer) tableReport.getModel().getValueAt(tableReport.getSelectedRow(), 0);
                if (codigo >-1){
                    repositoryProducto.deleteById(codigo);
                    JOptionPane.showMessageDialog(null, "El producto fue borrado exitosamente",
                    "Información", JOptionPane.PLAIN_MESSAGE);
                }else{
                    System.out.println("Error");
                 }
        }catch(Exception e) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado una linea",
                 "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
}
       



 static int obtenerCodigo = 0;

    public void update(){
    
    try{
                JTable tableReport = this.productoView.getProductoReport();
                Integer codigo = (Integer) tableReport.getModel().getValueAt(tableReport.getSelectedRow(), 0);
                if (codigo >-1){
                
                    this.actualizacionView.setVisible(true);
                    Producto founded = repositoryProducto.findById(codigo).get();
      
                    this.actualizacionView.getNombreActField().setText(founded.nombre);
                    String precio = founded.precio+"";
                    this.actualizacionView.getPrecioActField().setText(precio);
                    this.actualizacionView.getInventarioActField().setText(founded.inventario+"");
                    obtenerCodigo = codigo;
                    
                }else{
                    System.out.println("Error");
                 }
        }catch(Exception e) {
                JOptionPane.showMessageDialog(null, "No ha seleccionado una linea",
                 "Advertencia", JOptionPane.WARNING_MESSAGE);

         }    
     
        
    }
    
   
public void actualizarDatos(){

        try {
            
            Integer codigo = obtenerCodigo;
            String nombre = this.actualizacionView.getNombreActField().getText();
            Double precio =  Double.parseDouble(this.actualizacionView.getPrecioActField().getText());
            Integer inventario =  Integer.parseInt(this.actualizacionView.getInventarioActField().getText());
            Producto founded = repositoryProducto.findById(codigo).get();
            founded.setInventario(inventario);
            founded.setPrecio(precio);
            founded.setNombre(nombre);
            repositoryProducto.save(founded);
        
            this.actualizacionView.dispose();
        }catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios",
                 "Advertencia", JOptionPane.WARNING_MESSAGE);
        
    }
}

   
    //Reporte
    
    public void report(){
        String message = generarInforme();//lo tomo del metodo generarInforme();
        JOptionPane.showMessageDialog(productoView,message,"Reporte",  JOptionPane.PLAIN_MESSAGE);
    
    }    
        
        
    public String generarInforme(){
        
        return ("Producto precio mayor: "+obtenerProductoMasCostoso()+"\n "
                + "Producto precio menor: "+obtenerProductoMasEconomico()+"\n "
                +"Promedio precios: "+obtenerPromedioProductos()+"\n "
                +"Valor del inventario: "+obtenerInventarioTotal());
    }
        
    public String obtenerProductoMasCostoso(){
        
        return repositoryProducto.getTopPriceProduct();
    }
    
    public String obtenerProductoMasEconomico(){
        return repositoryProducto.getBottomPriceProduct();
    }
    
    public Double obtenerPromedioProductos(){
        return repositoryProducto.getAvgInventory();
    }
    
    public Double obtenerInventarioTotal(){
        return repositoryProducto.getInventory();
    }

    private String valueOf(Double precio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
} 
