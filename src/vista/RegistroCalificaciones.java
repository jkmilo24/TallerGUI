package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class RegistroCalificaciones extends JInternalFrame {

    private JComboBox<String> comboDocentes;
    private JLabel lblNombreDocente, lblNombreCurso;
    private JTable tablaEstudiantes;
    private JButton btnCalificar;
    private Map<String, String> mapDocentes; // cod_docente -> nom_docente
    private Map<String, String> mapCursos;   // cod_docente -> nom_curso
    private String codCursoActual = "";

    public RegistroCalificaciones() {
        setTitle("Calificaciones");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(700, 500);
        setLayout(null);

        JLabel lblCodigoDocente = new JLabel("Código Docente:");
        lblCodigoDocente.setBounds(20, 20, 120, 25);
        add(lblCodigoDocente);

        comboDocentes = new JComboBox<>(new String[]{"Seleccione"});
        comboDocentes.setBounds(150, 20, 150, 25);
        add(comboDocentes);

        JLabel lblNombre = new JLabel("Nombre Docente:");
        lblNombre.setBounds(20, 55, 120, 25);
        add(lblNombre);

        lblNombreDocente = new JLabel("");
        lblNombreDocente.setBounds(150, 60, 250, 15);
        add(lblNombreDocente);

        JLabel lblCurso = new JLabel("Nombre Curso:");
        lblCurso.setBounds(20, 85, 120, 25);
        add(lblCurso);

        lblNombreCurso = new JLabel("");
        lblNombreCurso.setBounds(150, 90, 250, 15);
        add(lblNombreCurso);

        tablaEstudiantes = new JTable(new DefaultTableModel(new Object[]{"CodEst", "NombreEst", "Nota Curso"}, 0));
        JScrollPane scrollTabla = new JScrollPane(tablaEstudiantes);
        scrollTabla.setBounds(20, 120, 280, 80);
        add(scrollTabla);

        btnCalificar = new JButton("Calificar");
        btnCalificar.setBounds(110, 210, 85, 20);
        add(btnCalificar);

        comboDocentes.addActionListener(e -> cargarCursoYEstudiantes());

        btnCalificar.addActionListener(e -> registrarNotas());

        cargarDocentesDesdeBD();
    }

    private void cargarDocentesDesdeBD() {
        mapDocentes = new HashMap<>();
        mapCursos = new HashMap<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT d.cod_docente, d.nom_docente, c.nom_curso, c.cod_curso " +
                                              "FROM docentes d LEFT JOIN cursos c ON d.cod_docente = c.cod_docente")) {
            comboDocentes.removeAllItems();
            comboDocentes.addItem("Seleccione"); // <-- Línea agregada
            while (rs.next()) {
                String cod = rs.getString("cod_docente");
                String nombre = rs.getString("nom_docente");
                String nomCurso = rs.getString("nom_curso");
                String codCurso = rs.getString("cod_curso");
                comboDocentes.addItem(cod);
                mapDocentes.put(cod, nombre);
                if (nomCurso != null) {
                    mapCursos.put(cod, nomCurso);
                    if (codCurso != null) {
                        codCursoActual = codCurso;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar datos del docente.");
        }
    }

    private void cargarCursoYEstudiantes() {
        String codDocente = (String) comboDocentes.getSelectedItem();
        if (codDocente != null && mapDocentes.containsKey(codDocente)) {
            lblNombreDocente.setText(mapDocentes.get(codDocente));
            lblNombreCurso.setText(mapCursos.getOrDefault(codDocente, "Sin curso"));
            cargarEstudiantesDelCurso(codDocente);
        }
    }

    private void cargarEstudiantesDelCurso(String codDocente) {
        DefaultTableModel modelo = (DefaultTableModel) tablaEstudiantes.getModel();
        modelo.setRowCount(0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             PreparedStatement psCurso = conn.prepareStatement("SELECT cod_curso FROM cursos WHERE cod_docente = ?");
             PreparedStatement psEst = conn.prepareStatement("SELECT e.cod_estudiante, e.nom_estudiante FROM estudiantes e JOIN matricula m ON e.cod_estudiante = m.cod_estudiante WHERE m.cod_curso = ?")) {

            psCurso.setString(1, codDocente);
            try (ResultSet rsCurso = psCurso.executeQuery()) {
                if (rsCurso.next()) {
                    codCursoActual = rsCurso.getString("cod_curso");

                    psEst.setString(1, codCursoActual);
                    try (ResultSet rs = psEst.executeQuery()) {
                        while (rs.next()) {
                            modelo.addRow(new Object[]{
                                    rs.getString("cod_estudiante"),
                                    rs.getString("nom_estudiante"),
                                    ""  // Nota por ingresar
                            });
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar estudiantes.");
        }
    }

    private void registrarNotas() {
        DefaultTableModel modelo = (DefaultTableModel) tablaEstudiantes.getModel();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo")) {
            String update = "UPDATE matricula SET nota_curso = ? WHERE cod_estudiante = ? AND cod_curso = ?";
            try (PreparedStatement ps = conn.prepareStatement(update)) {
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    String codEst = (String) modelo.getValueAt(i, 0);
                    String notaStr = String.valueOf(modelo.getValueAt(i, 2));
                    if (notaStr != null && !notaStr.isEmpty()) {
                        float nota = Float.parseFloat(notaStr);
                        ps.setFloat(1, nota);
                        ps.setString(2, codEst);
                        ps.setString(3, codCursoActual);
                        ps.executeUpdate();
                    }
                }
                JOptionPane.showMessageDialog(this, "Notas registradas exitosamente.");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar notas.");
        }
    }
}
