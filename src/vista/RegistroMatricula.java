package vista;

import CONEXION.CONEXION;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class RegistroMatricula extends JInternalFrame {

    private JComboBox<String> comboEstudiantes;
    private JLabel lblNombreEstudiante;
    private JList<String> listaCursos;
    private JButton btnRegistrar, btnLimpiar;
    private JLabel lblMensaje;

    public RegistroMatricula() {
        setTitle("Registro de Matrículas");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(500, 400);
        setLayout(null);

        JLabel lblCodigoEstudiante = new JLabel("Código de Estudiante:");
        lblCodigoEstudiante.setBounds(20, 20, 130, 25);
        add(lblCodigoEstudiante);

        comboEstudiantes = new JComboBox<>();
        comboEstudiantes.setBounds(160, 20, 120, 25);
        add(comboEstudiantes);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 55, 130, 25);
        add(lblNombre);

        lblNombreEstudiante = new JLabel("");
        lblNombreEstudiante.setBounds(160, 55, 140, 25);
        add(lblNombreEstudiante);

        JLabel lblCurso = new JLabel("Curso a Matricular:");
        lblCurso.setBounds(20, 90, 130, 25);
        add(lblCurso);

        listaCursos = new JList<>();
        JScrollPane scrollCursos = new JScrollPane(listaCursos);
        scrollCursos.setBounds(160, 90, 160, 70);
        add(scrollCursos);

        btnRegistrar = new JButton("Registrar Matrícula");
        btnRegistrar.setBounds(40, 180, 150, 30);
        add(btnRegistrar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(200, 180, 80, 30);
        add(btnLimpiar);

        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setBounds(20, 210, 250, 40);
        lblMensaje.setForeground(new java.awt.Color(0, 128, 0));
        add(lblMensaje);

        comboEstudiantes.addActionListener(e -> mostrarNombreEstudiante());

        btnRegistrar.addActionListener(e -> registrarMatricula());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarEstudiantes();
        cargarCursos();
    }

    private void cargarEstudiantes() {
        comboEstudiantes.removeAllItems(); // Limpia cualquier dato anterior
        comboEstudiantes.addItem("Seleccione"); // Agrega la opción inicial
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT cod_estudiante FROM estudiantes")) {
            while (rs.next()) {
                comboEstudiantes.addItem(rs.getString("cod_estudiante"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrarNombreEstudiante() {
        String codEst = (String) comboEstudiantes.getSelectedItem();
            if (codEst == null || codEst.equals("Seleccione")) {
            lblNombreEstudiante.setText("");
            return;
    }

    try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
        PreparedStatement ps = conn.prepareStatement("SELECT nom_estudiante FROM estudiantes WHERE cod_estudiante = ?")) {
        ps.setString(1, codEst);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            lblNombreEstudiante.setText(rs.getString("nom_estudiante"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private void cargarCursos() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT cod_curso, nom_curso FROM cursos")) {
            while (rs.next()) {
                String curso = rs.getString("cod_curso") + " - " + rs.getString("nom_curso");
                modelo.addElement(curso);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        listaCursos.setModel(modelo);
    }

    private void registrarMatricula() {
        String codEstudiante = (String) comboEstudiantes.getSelectedItem();
        String cursoSeleccionado = listaCursos.getSelectedValue();
        if (codEstudiante == null || codEstudiante.equals("Seleccione") || cursoSeleccionado == null) {
            lblMensaje.setText("Debe seleccionar estudiante y curso.");
            return;
        }

        String codCurso = cursoSeleccionado.split(" - ")[0];

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             PreparedStatement ps = conn.prepareStatement("INSERT INTO matricula (cod_estudiante, cod_curso) VALUES (?, ?)")) {
            ps.setString(1, codEstudiante);
            ps.setString(2, codCurso);
            ps.executeUpdate();
            lblMensaje.setText("Matrícula registrada con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            lblMensaje.setText("Error al registrar la matrícula.");
        }
    }

    private void limpiarCampos() {
        comboEstudiantes.setSelectedIndex(-1);
        listaCursos.clearSelection();
        lblMensaje.setText("");
        lblNombreEstudiante.setText("");
    }
}