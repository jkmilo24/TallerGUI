package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Color;

public class ListadoMatriculas extends JInternalFrame {

    public ListadoMatriculas() {
        super("Listado de Matrículas", true, true, true, true);
        setSize(500, 300);
        setLayout(new java.awt.BorderLayout());

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, java.awt.BorderLayout.CENTER);

        modelo.addColumn("Estudiante");
        modelo.addColumn("Curso");
        
        
        getContentPane().setBackground(new Color(200, 255, 100));
        Color fondoComponente = new Color(240, 255, 240); // Verde más claro para componentes
        Color fondoBoton = new Color(180, 230, 180); // Botón verde suave

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT e.nom_estudiante, c.nom_curso FROM matricula m JOIN estudiantes e ON m.cod_estudiante = e.cod_estudiante JOIN cursos c ON m.cod_curso = c.cod_curso")) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("nom_estudiante"),
                    rs.getString("nom_curso")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar matrículas");
        }
    }
}
