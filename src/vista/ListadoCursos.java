package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ListadoCursos extends JInternalFrame {

    public ListadoCursos() {
        super("Listado de Cursos", true, true, true, true);
        setSize(500, 300);
        setLayout(new java.awt.BorderLayout());

        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, java.awt.BorderLayout.CENTER);

        modelo.addColumn("Código Curso");
        modelo.addColumn("Nombre Curso");
        modelo.addColumn("Docente");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT c.cod_curso, c.nom_curso, d.nom_docente FROM cursos c JOIN docentes d ON c.cod_docente = d.cod_docente")) {

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("cod_curso"),
                    rs.getString("nom_curso"),
                    rs.getString("nom_docente")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar cursos");
        }
    }
}