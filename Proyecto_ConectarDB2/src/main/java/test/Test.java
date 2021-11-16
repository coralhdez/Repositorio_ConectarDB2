
package test;

import dominio.Persona;
import dominio.PersonaDao;
import java.sql.SQLException;
import java.util.List;

public class Test {
    
    public static void main(String[] args) throws SQLException {
       
        //1: Crear variable de tipo PersonaDao
       PersonaDao personaDao = new PersonaDao();
       int registros = personaDao.actualizar(new Persona(2, "Martaaa","López","martlopez@gmail.com","67781254"));
       
       
       Persona persona1 = new Persona(4,"Coral","Hernández", "coral@gmail.com", "633822602");
       personaDao.insertar(persona1);
       
       personaDao.actualizar(persona1);
       
          //seleccionar me devuelve un listado de objetos por eso creo un listado de personas
       List<Persona> personas = personaDao.seleccionar();
       personas.forEach(persona -> {
           System.out.println("Persona: " + persona);
       });
       
       
      
    }
}
