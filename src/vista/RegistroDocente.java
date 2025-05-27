package vista;
import javax.swing.*;
import java.sql.*;
import java.awt.Color;

public class RegistroDocente extends JInternalFrame {
    private JTextField txtCodigo, txtNombre;
    private JButton btnIngresar, btnLimpiar;

    public RegistroDocente() {
        super("Registro de Docentes", true, true, true, true);
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

        btnIngresar.addActionListener(e -> insertarDocente());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        
        
        getContentPane().setBackground(new Color(200, 255, 100));
        Color fondoComponente = new Color(240, 255, 240); // Verde más claro para componentes
        Color fondoBoton = new Color(180, 230, 180); // Botón verde suave
        
        
        
        
        
    }

    private void insertarDocente() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();

        if (codigo.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tallet_practico", "root", "kmilo");
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO docentes (cod_docente, nom_docente) VALUES (?, ?)")) {

            stmt.setString(1, codigo);
            stmt.setString(2, nombre);
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Docente registrado exitosamente");
            limpiarCampos();

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar docente");
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
    }
}