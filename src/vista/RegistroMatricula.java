package vista;

import javax.swing.*;

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

        // Código de Estudiante
        JLabel lblCodigoEstudiante = new JLabel("Código de Estudiante:");
        lblCodigoEstudiante.setBounds(20, 20, 130, 25);
        add(lblCodigoEstudiante);

        comboEstudiantes = new JComboBox<>(new String[]{"Seleccione"});
        comboEstudiantes.setBounds(160, 20, 120, 25);
        add(comboEstudiantes);

        // Nombre Estudiante debajo del combo
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 55, 130, 25);
        add(lblNombre);

        lblNombreEstudiante = new JLabel("");  // nombre visible no editable
        lblNombreEstudiante.setBounds(160, 55, 140, 25);
        add(lblNombreEstudiante);

        // Curso a matricular - JList con scroll
        JLabel lblCurso = new JLabel("Curso a Matricular:");
        lblCurso.setBounds(20, 90, 130, 25);
        add(lblCurso);

        listaCursos = new JList<>();
        listaCursos.setVisibleRowCount(7);
        JScrollPane scrollCursos = new JScrollPane(listaCursos);
        scrollCursos.setBounds(160, 90, 130, 70);
        add(scrollCursos);

        // Botones
        btnRegistrar = new JButton("Registrar Matrícula");
        btnRegistrar.setBounds(40, 180, 150, 30);
        add(btnRegistrar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(200, 180, 80, 30);
        add(btnLimpiar);

        // Mensaje de estado
        lblMensaje = new JLabel("", SwingConstants.CENTER);
        lblMensaje.setBounds(20, 210, 200, 25);
        lblMensaje.setForeground(new java.awt.Color(0, 128, 0));
        add(lblMensaje);

        // Actualizar nombre al cambiar selección
        comboEstudiantes.addActionListener(e -> {
            int index = comboEstudiantes.getSelectedIndex();
            if (index >= 0) {
                lblNombreEstudiante.setText("Nombre del estudiante #" + (index + 1));
            } else {
                lblNombreEstudiante.setText("");
            }
        });

        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnRegistrar.addActionListener(e -> registrarMatricula());
    }

    private void limpiarCampos() {
        comboEstudiantes.setSelectedIndex(-1);
        listaCursos.clearSelection();
        lblMensaje.setText("");
        lblNombreEstudiante.setText("");
    }

    private void registrarMatricula() {
        lblMensaje.setText("Ingreso correctamente");
    }
}
