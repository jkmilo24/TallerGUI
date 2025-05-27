package vista;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class RegistroCalificaciones extends JInternalFrame {

    private JComboBox<String> comboDocentes;
    private JLabel lblNombreDocente, lblNombreCurso;
    private JTable tablaEstudiantes;
    private JButton btnCalificar;
    private Map<String, String> mapDocentes;
    private Map<String, String> mapCursos;
    private String codCursoActual = "";

    public RegistroCalificaciones() {
        setTitle("Registro de Calificaciones");
        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        // Colores modernos
        Color fondoPrincipal = new Color(245, 248, 255);
        Color fondoPanel = new Color(230, 240, 255);
        Color colorBoton = new Color(100, 149, 237);
        Color textoBoton = Color.WHITE;

        getContentPane().setBackground(fondoPrincipal);

        JPanel panelDocente = new JPanel(null);
        panelDocente.setBounds(30, 60, 500, 120);
        panelDocente.setBackground(fondoPanel);
        panelDocente.setBorder(new LineBorder(new Color(180, 200, 255), 2, true));
        add(panelDocente);

        JLabel lblTitulo = new JLabel("Registro de Calificaciones por Docente");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setBounds(80, 0, 400, 60);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        JLabel lblCodigoDocente = new JLabel("Código Docente:");
        lblCodigoDocente.setBounds(20, 20, 120, 25);
        lblCodigoDocente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelDocente.add(lblCodigoDocente);

        comboDocentes = new JComboBox<>(new String[]{"Seleccione"});
        comboDocentes.setBounds(150, 20, 150, 25);
        comboDocentes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panelDocente.add(comboDocentes);

        JLabel lblNombre = new JLabel("Nombre Docente:");
        lblNombre.setBounds(20, 55, 120, 25);
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelDocente.add(lblNombre);

        lblNombreDocente = new JLabel("");
        lblNombreDocente.setBounds(150, 55, 300, 25);
        lblNombreDocente.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelDocente.add(lblNombreDocente);

        JLabel lblCurso = new JLabel("Curso Asignado:");
        lblCurso.setBounds(20, 85, 120, 25);
        lblCurso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelDocente.add(lblCurso);

        lblNombreCurso = new JLabel("");
        lblNombreCurso.setBounds(150, 85, 300, 25);
        lblNombreCurso.setFont(new Font("Segoe UI", Font.BOLD, 13));
        panelDocente.add(lblNombreCurso);

        JLabel lblTabla = new JLabel("Listado de Estudiantes:");
        lblTabla.setBounds(30, 160, 200, 25);
        lblTabla.setFont(new Font("Segoe UI", Font.BOLD, 15));
        add(lblTabla);

        tablaEstudiantes = new JTable(new DefaultTableModel(new Object[]{"Código", "Nombre", "Nota"}, 0));
        tablaEstudiantes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaEstudiantes.setRowHeight(25);
        JScrollPane scrollTabla = new JScrollPane(tablaEstudiantes);
        scrollTabla.setBounds(30, 190, 500, 120);
        add(scrollTabla);

        btnCalificar = new JButton("Registrar Notas");
        btnCalificar.setBounds(190, 350, 160, 35);
        btnCalificar.setBackground(colorBoton);
        btnCalificar.setForeground(textoBoton);
        btnCalificar.setFocusPainted(false);
        btnCalificar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCalificar.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
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
            comboDocentes.addItem("Seleccione");
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
                                    ""
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
