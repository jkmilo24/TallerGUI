package vista;

import javax.swing.*;
import java.sql.*;

public class RegistroCurso extends JInternalFrame {
    private JTextField txtCodigoCurso, txtNombreCurso;
    private JComboBox<String> comboDocentes;
    private JButton btnIngresar;

    public RegistroCurso() {
        super("Registro de Cursos", true, true, true, true);
        setLayout(null);
        setSize(350, 250);

        JLabel lblCodigoCurso = new JLabel("Código Curso:");
        lblCodigoCurso.setBounds(30, 20, 120, 25);
        add(lblCodigoCurso);

        txtCodigoCurso = new JTextField();
        txtCodigoCurso.setBounds(150, 20, 150, 25);
        add(txtCodigoCurso);

        JLabel lblNombreCurso = new JLabel("Nombre Curso:");
        lblNombreCurso.setBounds(30, 60, 120, 25);
        add(lblNombreCurso);

        txtNombreCurso = new JTextField();
        txtNombreCurso.setBounds(150, 60, 150, 25);
        add(txtNombreCurso);

        JLabel lblDocente = new JLabel("Docente:");
        lblDocente.setBounds(30, 100, 120, 25);
        add(lblDocente);

        comboDocentes = new JComboBox<>();
        comboDocentes.setBounds(150, 100, 150, 25);
        add(comboDocentes);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(110, 180, 100, 25);
        add(btnIngresar);

        btnIngresar.addActionListener(e -> insertarCurso());

        cargarDocentes();
    }

    private void cargarDocentes() {
        comboDocentes.removeAllItems(); // Limpia opciones anteriores
        comboDocentes.addItem("Seleccione"); // Opción inicial por defecto
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT cod_docente, nom_docente FROM docentes")) {

            while (rs.next()) {
                comboDocentes.addItem(rs.getString("cod_docente") + " - " + rs.getString("nom_docente"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar docentes");
        }
    }

    private void insertarCurso() {
        String codCurso = txtCodigoCurso.getText();
        String nomCurso = txtNombreCurso.getText();
        String seleccionDocente = (String) comboDocentes.getSelectedItem();

        if (codCurso.isEmpty() || nomCurso.isEmpty() || seleccionDocente == null || seleccionDocente.equals("Seleccione")) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos y seleccionar un docente válido");
            return;
    }

        String codDocente = seleccionDocente.split(" - ")[0];

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO cursos (cod_curso, nom_curso, cod_docente) VALUES (?, ?, ?)")) {

            stmt.setString(1, codCurso);
            stmt.setString(2, nomCurso);
            stmt.setString(3, codDocente);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Curso registrado exitosamente");
            txtCodigoCurso.setText("");
            txtNombreCurso.setText("");
            comboDocentes.setSelectedIndex(0); // Volver a "Seleccione"

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar curso");
        }
    }
}