package datos;

//DAO: DATA ACCESS OBJECT
import dominio.Persona;
import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

//para acceder a la infor de las entidades
//por cada entidad hay que tener un dao correspondiente
//convierte a objetos para que la capa de negocio pueda trabajar con ellos 
public class PersonaDao {

    private static final String SQL_SELECT = "SELECT * FROM persona ";
    private static final String SQL_INSERT = "INSERT INTO persona (id_persona, nombre, apellido, email,telefono) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ? , apellido = ? , email = ? , telefono = ? WHERE id_persona = ? ";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ? ";

    //MÉTODO PARA LEER
    public List<Persona> seleccionar() throws SQLException {

        //1: creamos nuestros objetos a null
        Connection conn = null;  //objeto de conexion: conn
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // creo objetos de cada linea que leo
        List<Persona> personas = new ArrayList<>();

        try {
            conn = getConnection(); //ESTABLECE LA CONEXIÓN
            stmt = conn.prepareStatement(SQL_SELECT); //prepara la consulta
            rs = stmt.executeQuery();//ejecutar lo que tengo en stmt

            while (rs.next()) {
                int idPersona = rs.getInt("id_persona"); //escribirlo igual que en la bd
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                //creo el objeto y lo añado al ArrayList
                personas.add(new Persona(idPersona, nombre, apellido, email, telefono));
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            //LLAMO AL METODO CLOSE DE LA CLASE CONEXION
            close(rs);
            close(stmt);
            close(conn);
        }
        return personas;
    }

    public int insertar(Persona persona) throws SQLException {   //recibo un entero, le paso por parametro objetos de persona

        //1 variable para la conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setInt(1, persona.getIdPersona()); // 1 simboliza el primer interrogante, el indice del primer interrogante
            stmt.setString(2, persona.getNombre()); //2º interrogante y accedo al valor
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, persona.getEmail());
            stmt.setString(5, persona.getTelefono());

            registros = stmt.executeUpdate(); //Ejecutar la consulta
            //EXECUTEUPDATE: cuando modifique mi bbdd, como actualizar, inserter, borrar... 
            //executeQuery: cuando no modifica, en el select

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            close(conn);
        }

        return registros;
    }

    public int actualizar(Persona persona) throws SQLException {   //recibo un entero, le paso por parametro objetos de persona

        //1 variable para la conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, persona.getNombre()); //2º interrogante y accedo al valor
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona()); //identificador que le paso en el where

            registros = stmt.executeUpdate(); //Ejecutar la consulta
            //EXECUTEUPDATE: cuando modifique mi bbdd, como actualizar, inserter, borrar... 
            //executeQuery: cuando no modifica, en el select

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            close(conn);
        }

        return registros;
    }

    public int borrar(Persona persona) throws SQLException { //BORRAR POR EL ID
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; //para saber cuantos registros estamos borrando

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, persona.getIdPersona());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }

}
