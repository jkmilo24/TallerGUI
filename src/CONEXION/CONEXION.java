
package CONEXION;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CONEXION {
    Connection conectar;

    String usuario = "root";
    String contraseña = "kmilo";
    String bd = "tallet_practico"; // aquí asegúrate de poner el nombre correcto de tu BD
    String ip = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd + "?serverTimezone=UTC";

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            return conectar;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            return null;
        }
    }
}

