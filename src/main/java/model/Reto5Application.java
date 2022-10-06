package model;

import controller.productoController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import view.ProductoView;
import view.actualizacionView;


@SpringBootApplication
public class Reto5Application {
    
        
        @Autowired
        IRepositoryProducto repositoryProducto;
                
	public static void main(String[] args) {
                       
                    SpringApplicationBuilder builder = new SpringApplicationBuilder(Reto5Application.class);
                    builder.headless(false);
                    ConfigurableApplicationContext context = builder.run(args);
                    
	}
        
        @Bean
        ApplicationRunner applicationRunner(){
            return args->{
                System.out.println("Hola...");
                productoController productoController = new productoController(repositoryProducto, new ProductoView(), new actualizacionView());
                
            
            };
        }
        
}
