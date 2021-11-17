package test;

import dominio.*;
import datos.*;
import java.sql.*;
import java.util.*;

public class Test {

    public static void main(String[] args) throws SQLException {
        /*  
        PersonaDao personaDao1 = new PersonaDao(); // Objeto de PersonaDao
        
        Persona p1 = new Persona(10, "Maria", "Luengo", "maria@gmail.com", "669931387");
        Persona p2 = new Persona(4, "Coral", "Hernandez", "coral@gmail.com", "655555555");
        Persona p3 = new Persona(7, "Pepe", "Pérez", "pepe@gmail.com", "657965125");
        
        //INSERTAR
         personaDao1.insertar(p3);//-> lo comentamos xq siempre me va a suplicar el ID
        //ACTUALIZAR
        int registros = personaDao1.actualizar(new Persona(2,"Martaaa","Sanchez", 
                "msanchez@gmail.com","123458795"));  
        //BORRAR    
        personaDao1.borrar(p2);
          //como devuelve un array lo meto en un array
        List<Persona> personas = personaDao1.seleccionar();
        personas.forEach(persona -> {
            System.out.println("Persona=" + persona);      
        
        });
         */
        //OBJETO CLASE PRODUCTODAO
        ProductoDao productoDaoObjeto = new ProductoDao(); //objeto que llama a los métodos de la clase ProductoDao: leer, actualizar, añadir, borrar

        //OBJETOS CLASE PRODUCTO
        Producto producto1 = new Producto(4, "Deportivas", 75, 1, "Talla 38");
        Producto producto2 = new Producto(5, "Chaqueta", 80, 4, "Talla S, Talla M");
        Producto producto3 = new Producto(6, "Vestido", 45, 2, "Talla S, Talla L");

        //AÑADIR OBJETOS A LA BD
        //productoDaoObjeto.insertar(producto1);
        //productoDaoObjeto.insertar(producto2);
        //productoDaoObjeto.insertar(producto3);
        
        //ACTUALIZAR
         productoDaoObjeto.actualizar(new Producto(4, "deportivasModificadas222", 80, 1, "Talla 38"));
        
        //BORRAR 
        productoDaoObjeto.borrar(producto3);
        
        //LEER
        List<Producto> productos = productoDaoObjeto.seleccionar();
        productos.forEach(producto -> {
            System.out.println("Producto: " + producto);

        });

    }
}
