package vista;

import CONEXION.CONEXION;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegistroEstudiante extends JInternalFrame {

    private JTextField txtCodigo, txtNombre;
    private JButton btnIngresar, btnLimpiar;

    public RegistroEstudiante() {
        super("Registro de Estudiantes", true, true, true, true);
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
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();

            if (codigo.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
                return;
            }

            try {
                CONEXION conexionBD = new CONEXION();
                Connection con = conexionBD.conectar();

                String sql = "INSERT INTO estudiantes (cod_estudiante, nom_estudiante) VALUES (?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, codigo);
                ps.setString(2, nombre);

                int filas = ps.executeUpdate();
                if (filas > 0) {
                    JOptionPane.showMessageDialog(this, "Estudiante registrado exitosamente.");
                    txtCodigo.setText("");
                    txtNombre.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo registrar el estudiante.");
                }

                con.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al registrar estudiante.");
            }
        });

        btnLimpiar.addActionListener(e -> {
            txtCodigo.setText("");
            txtNombre.setText("");
        });
    }
}