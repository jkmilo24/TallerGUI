package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ListadoEstudiantes extends JInternalFrame {
    private JTable tabla;
    private DefaultTableModel modelo;

    public ListadoEstudiantes() {
        super("Listado de Estudiantes", true, true, true, true);
        setSize(500, 300);
        setLayout(new java.awt.BorderLayout());

        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, java.awt.BorderLayout.CENTER);

        modelo.addColumn("Código");
        modelo.addColumn("Nombre");

        cargarEstudiantes();
    }

    private void cargarEstudiantes() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT cod_estudiante, nom_estudiante FROM estudiantes")) {

            while (rs.next()) {
                String codigo = rs.getString("cod_estudiante");
                String nombre = rs.getString("nom_estudiante");
                modelo.addRow(new Object[]{codigo, nombre});
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar estudiantes");
        }
    }
}