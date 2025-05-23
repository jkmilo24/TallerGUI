
package CONEXION;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CONEXION {
    Connection conectar;
    
    String usuario = "root";
    String contraseña = "kmilo";
    String bd = "inicio";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection conectar() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost/tu_base", "usuario", "contraseña");
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

   
}
