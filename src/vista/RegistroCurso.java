package vista;

import javax.swing.JInternalFrame;

public class RegistroCurso extends JInternalFrame {

    public RegistroCurso() {
        setTitle("Registro de Cursos");
        setClosable(true);
        setIconifiable(true);
        setResizable(false);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(250, 200);
        setLayout(null);

        javax.swing.JLabel lblCodigo = new javax.swing.JLabel("Codigo:");
        javax.swing.JLabel lblNombre = new javax.swing.JLabel("Nombre:");
        javax.swing.JTextField txtCodigo = new javax.swing.JTextField();
        javax.swing.JTextField txtNombre = new javax.swing.JTextField();
        javax.swing.JButton btnIngresar = new javax.swing.JButton("Ingresar");
        javax.swing.JButton btnLimpiar = new javax.swing.JButton("Limpiar");

        lblCodigo.setBounds(20, 20, 80, 25);
        lblNombre.setBounds(20, 50, 80, 25);
        txtCodigo.setBounds(100, 20, 120, 25);
        txtNombre.setBounds(100, 50, 120, 25);
        btnIngresar.setBounds(20, 100, 90, 25);
        btnLimpiar.setBounds(130, 100, 90, 25);

        add(lblCodigo);
        add(lblNombre);
        add(txtCodigo);
        add(txtNombre);
        add(btnIngresar);
        add(btnLimpiar);
    }
}

