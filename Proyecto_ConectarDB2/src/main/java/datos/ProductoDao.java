package datos;

import static datos.Conexion.*;
import dominio.Persona;
import dominio.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductoDao {

    //VARIABLES
    private static final String SQL_SELECT = "SELECT * FROM producto";
    private static final String SQL_INSERT = "INSERT INTO producto (idproducto, nombre, precio, cantidad, descripcion) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE producto SET nombre = ? , precio = ? , cantidad = ? , descripcion = ? WHERE idproducto = ? ";
    private static final String SQL_DELETE = "DELETE FROM producto WHERE idproducto = ?";

    public List<Producto> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Producto> productos = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("idproducto");
                String nombre = rs.getString("nombre");
                double precio = rs.getFloat("precio");
                int cantidad = rs.getInt("cantidad");
                String descripcion = rs.getString("descripcion");
                
                productos.add(new Producto(idProducto, nombre, precio, cantidad, descripcion));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(rs);
            close(stmt);
            close(conn);

        }
        return productos;
    }

    public int insertar(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setInt(1, producto.getIdProducto());
            stmt.setString(2, producto.getNombre());
            stmt.setDouble(3, producto.getPrecio());
            stmt.setInt(4, producto.getCantidad());
            stmt.setString(5, producto.getDescripcion());

            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }

    public int actualizar(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCantidad());
            stmt.setString(4, producto.getDescripcion());
            stmt.setInt(5, producto.getIdProducto());

            registros = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            close(stmt);
            close(conn);
        }
        return registros;
    }

    public int borrar(Producto producto) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);

            //borrar por id
            stmt.setInt(1, producto.getIdProducto());

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
