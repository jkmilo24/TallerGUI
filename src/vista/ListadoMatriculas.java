package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

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
