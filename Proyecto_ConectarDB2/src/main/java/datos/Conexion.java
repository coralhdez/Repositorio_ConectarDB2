
package datos;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_java?"
                +"useSSL=false&useTimezone=true&serverTimezone=UTC&"
                +"allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root"; //en casa al hacerlo co xamp es vacio
    
    //MÉTODO QUE ME DEVUELVE UN DATASOURCE DE SQL   
    public static DataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_URL);
        ds.setPassword(JDBC_PASSWORD);
        
        ds.setInitialSize(5); //cuanta gente se va a conectar , 5 conexiones
        
        return ds;
    }
    
    
            //MÉTODO PARA ESTABLECER LA CONEXIÓN
    public static Connection getConnection() throws SQLException{
        //return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); 
        return getDataSource().getConnection();
    }
    
                //MÉTODOS CLOSE DE LA CONEXIÓN
    public static void close(ResultSet re) throws SQLException{
        re.close();
    }
    
    public static void close(Statement stmt)throws SQLException{
        stmt.close();
    }
    
    public static void close(PreparedStatement stmt)throws SQLException{
        stmt.close();
    }
    
    public static void close(Connection conn)throws SQLException{
        conn.close();
    }
    
    
    
}
