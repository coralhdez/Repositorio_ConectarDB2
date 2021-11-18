package test;

import dominio.*;
import datos.*;
import java.sql.*;
import java.util.*;

public class Test {

    public static void main(String[] args) throws SQLException {
        //CONEXIÓN GLOBAL
        Connection conexion = null;

        try {

            conexion = Conexion.getConnection(); //llamo al menoto getConection de la clase Conexion que hemos creado

            if (conexion.getAutoCommit()) { //quiere decir que autocommit esta a true, y todas las sentencias sql se van a ejecutar, pero quiero hacer varias operaciones en forma de bloque y abrir y cerrrar todas de una al final
                //por eso la pongo a false para que solo me haga un autocomit general al final
                conexion.setAutoCommit(false); //desactivo la propiedad de la conexion que por cada execute lo modifique automaticamente
            }

            PersonaDaoJDBC personaDao1 = new PersonaDaoJDBC(conexion); // constructor que se ha creado en PersonaDaoJDBC con el parámetro de la conexción

            PersonaDTO p1 = new PersonaDTO(10, "Maria", "Luengo", "maria@gmail.com", "669931387");
            PersonaDTO p2 = new PersonaDTO(4, "Coral", "Hernandez", "coral@gmail.com", "655555555");
            PersonaDTO p3 = new PersonaDTO(7, "Pepe", "Pérez", "pepe@gmail.com", "657965125");

            //1. Insertamos
            //personaDao1.insertar(p3);//-> lo comentamos xq siempre me va a suplicar el ID
            personaDao1.insertar(new PersonaDTO(8, "Antonio", "González", "antoniogonzalez@gmail.com", "623514702"));
            //2. Actualizamos
            int registros = personaDao1.actualizar(new PersonaDTO(2, "Martaaa", "Saaaaanchez",
                    "msanchez@gmail.com", "123458795"));
            //BORRAR    
            //personaDao1.borrar(p2);
            //como devuelve un array lo meto en un array
            //3. Seleccionamos
            List<PersonaDTO> personas = personaDao1.seleccionar();
            personas.forEach(persona -> {
                System.out.println("Persona=" + persona);

            });

            conexion.commit(); //comiteamos el 1,2 y 3 a la vez

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos en el rollback");
            try{
                conexion.rollback(); //si hay error vuelve al estado anterior
            }catch(SQLException e){
                e.printStackTrace(System.out);
            }   
        }

    }

    public static void pruebaProducto()throws SQLException  {

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
