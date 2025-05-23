package vista;

import javax.swing.*;

public class RegistroDocente extends JInternalFrame {

    public RegistroDocente() {
        setTitle("Registro de Docentes");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(280, 250);
        setLayout(null);

        JLabel lblCodigo = new JLabel("Codigo:");
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblCodCurso = new JLabel("Codigo Curso:");
        JLabel lblNomCurso = new JLabel("Nombre Curso:");
        JTextField txtCodigo = new JTextField();
        JTextField txtNombre = new JTextField();
        JComboBox<String> cbCursos = new JComboBox<>(new String[]{"Seleccione", "Curso1", "Curso2"});
        JTextField txtNomCurso = new JTextField();
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnLimpiar = new JButton("Limpiar");

        lblCodigo.setBounds(20, 20, 100, 25);
        txtCodigo.setBounds(120, 20, 130, 25);
        lblNombre.setBounds(20, 50, 100, 25);
        txtNombre.setBounds(120, 50, 130, 25);
        lblCodCurso.setBounds(20, 80, 100, 25);
        cbCursos.setBounds(120, 80, 130, 25);
        lblNomCurso.setBounds(20, 110, 100, 25);
        txtNomCurso.setBounds(120, 110, 130, 25);
        btnIngresar.setBounds(20, 160, 90, 25);
        btnLimpiar.setBounds(140, 160, 90, 25);

        add(lblCodigo);
        add(txtCodigo);
        add(lblNombre);
        add(txtNombre);
        add(lblCodCurso);
        add(cbCursos);
        add(lblNomCurso);
        add(txtNomCurso);
        add(btnIngresar);
        add(btnLimpiar);
    }
}