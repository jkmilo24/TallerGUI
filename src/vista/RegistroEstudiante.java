package vista;

import javax.swing.*;

public class RegistroEstudiante extends JInternalFrame {

    private JTextField txtCodigo, txtNombre;
    private JButton btnIngresar, btnLimpiar;

    public RegistroEstudiante() {
        super("Registro de Estudiantes", true, true, true, true); // título, closable, resizable, etc.
        setLayout(null);
        setSize(300, 200);

        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 20, 80, 25);
        add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(110, 20, 140, 25);
        add(txtCodigo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 60, 80, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(110, 60, 140, 25);
        add(txtNombre);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(30, 110, 100, 25);
        add(btnIngresar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(150, 110, 100, 25);
        add(btnLimpiar);

        btnIngresar.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();

            if (codigo.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
            } else {
                JOptionPane.showMessageDialog(this, "Estudiante registrado:\nCódigo: " + codigo + "\nNombre: " + nombre);
                txtCodigo.setText("");
                txtNombre.setText("");
            }
        });

        btnLimpiar.addActionListener(e -> {
            txtCodigo.setText("");
            txtNombre.setText("");
        });
    }
}
