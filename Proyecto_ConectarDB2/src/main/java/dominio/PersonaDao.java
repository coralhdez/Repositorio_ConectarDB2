
package dominio;

//DAO: DATA ACCESS OBJECT

import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

//para acceder a la infor de las entidades
//por cada entidad hay que tener un dao correspondiente
public class PersonaDao {
    private static final String SQL_SELECT = "SELECT * FROM persona";
    
    //MÉTODO PARA LEER
    public List<Persona> seleccionar()throws SQLException{
        //1: creamos nuestros objetos a null
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        // creo objetos de cada linea que leo
        List<Persona> personas = new ArrayList<>();
        
        try{
            conn = getConnection(); //ESTABLECE LA CONEXIÓN
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();//ejecutar lo que tengo en stmt
            
            while(rs.next()){
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombrre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");   
                
                //creo el objeto y lo añado al ArrayList
                personas.add(new Persona(idPersona, nombre, apellido, email, telefono));
            }
            
            
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }finally{
            //LLAMO AL METODO CLOSE DE LA CLASE CONEXION
            close(rs);
            close(stmt);
            close(conn);   
        } 
        return personas;
    }
}
