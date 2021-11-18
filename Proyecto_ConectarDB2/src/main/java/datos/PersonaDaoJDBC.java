package datos;

//DAO: DATA ACCESS OBJECT
import dominio.PersonaDTO;
import static datos.Conexion.*;
import java.sql.*;
import java.util.*;

//para acceder a la infor de las entidades
//por cada entidad hay que tener un dao correspondiente
//convierte a objetos para que la capa de negocio pueda trabajar con ellos 
public class PersonaDaoJDBC  implements PersonaDAO {

    private static final String SQL_SELECT = "SELECT * FROM persona ";
    private static final String SQL_INSERT = "INSERT INTO persona (id_persona, nombre, apellido, email,telefono) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ? , apellido = ? , email = ? , telefono = ? WHERE id_persona = ? ";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ? ";

    //CONEXIÓN TRANSACCIONAL
    //Conexion desde fuera de los propios métodos. Solo abro  una vez la conexion, ejecuto una serie de sentencias sql y luego cierro
    //en vez de abrir-cerrar en cada metodo
    //variable conexionTransaccional para hacer la conexión desde fuera
    private Connection conexionTransaccional;

    public PersonaDaoJDBC() {

    }

    public PersonaDaoJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    //MÉTODO PARA LEER
    @Override
    public List<PersonaDTO> seleccionar() throws SQLException {

        //1: creamos nuestros objetos a null
        Connection conn = null;  //objeto de conexion: conn
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // creo objetos de cada linea que leo
        List<PersonaDTO> personas = new ArrayList<>();

        try {
            //si la conexion es distinta de vacía, quiere decir que cojo la conexión creada global
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection(); //ESTABLECE LA CONEXIÓN
            stmt = conn.prepareStatement(SQL_SELECT); //prepara la consulta
            rs = stmt.executeQuery();//ejecutar lo que tengo en stmt

            while (rs.next()) {
                int idPersona = rs.getInt("id_persona"); //escribirlo igual que en la bd
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                //creo el objeto y lo añado al ArrayList
                personas.add(new PersonaDTO(idPersona, nombre, apellido, email, telefono));
            }
        } finally {
            //LLAMO AL METODO CLOSE DE LA CLASE CONEXION
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null) {
                //aqui me he creado la conexión localmente en el método y la cierro
                close(conn);
            }

        }
        return personas;
    }
    
     @Override
    public int insertar(PersonaDTO persona)throws SQLException{   //recibo un entero, le paso por parametro objetos de persona

        //1 variable para la conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setInt(1, persona.getIdPersona()); // 1 simboliza el primer interrogante, el indice del primer interrogante
            stmt.setString(2, persona.getNombre()); //2º interrogante y accedo al valor
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, persona.getEmail());
            stmt.setString(5, persona.getTelefono());

            registros = stmt.executeUpdate(); //Ejecutar la consulta
            //EXECUTEUPDATE: cuando modifique mi bbdd, como actualizar, inserter, borrar... 
            //executeQuery: cuando no modifica, en el select

        } finally {
            close(stmt);
            if (this.conexionTransaccional == null) {
                //aqui me he creado la conexión localmente en el método y la cierro
                close(conn);
            }
        }

        return registros;
    }

     @Override
    public int actualizar(PersonaDTO persona) throws SQLException {   //recibo un entero, le paso por parametro objetos de persona

        //1 variable para la conexion
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, persona.getNombre()); //2º interrogante y accedo al valor
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona()); //identificador que le paso en el where

            registros = stmt.executeUpdate(); //Ejecutar la consulta
            //EXECUTEUPDATE: cuando modifique mi bbdd, como actualizar, inserter, borrar... 
            //executeQuery: cuando no modifica, en el select
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null) {
                //aqui me he creado la conexión localmente en el método y la cierro
                close(conn);
            }
        }

        return registros;
    }

     @Override
    public int borrar(PersonaDTO persona) throws SQLException { //BORRAR POR EL ID
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; //para saber cuantos registros estamos borrando

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, persona.getIdPersona());

            registros = stmt.executeUpdate();
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null) {
                //aqui me he creado la conexión localmente en el método y la cierro
                close(conn);
            }
        }
        return registros;
    }

}
