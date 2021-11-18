
package datos;

import dominio.*;
import java.sql.*;
import java.util.*;


public interface PersonaDAO {
    
    List<PersonaDTO> seleccionar() throws SQLException;
    
    int insertar(PersonaDTO persona) throws SQLException;
    
    int actualizar(PersonaDTO persona) throws SQLException;
    
    int borrar(PersonaDTO persona)throws SQLException;
    
}
