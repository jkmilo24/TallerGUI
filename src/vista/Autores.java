package vista;

import javax.swing.*;
import java.awt.*;

public class Autores extends JInternalFrame {

    public Autores(JDesktopPane escritorio) {
        super("Autores", true, true, true, true);
        setLayout(new BorderLayout());

        // Ajustar tamaño a todo el escritorio
        setBounds(0, 0, escritorio.getWidth(), escritorio.getHeight());

        // Panel principal con fondo verde claro y diseño centrado
        JPanel panel = new JPanel();
        panel.setBackground(new Color(204, 255, 204)); // Verde claro
        panel.setLayout(new GridBagLayout());

        JLabel autor1 = new JLabel("Juan Camilo Muñoz Cañaveral");
        JLabel autor2 = new JLabel("Héctor Steven Perea");

        autor1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        autor2.setFont(new Font("Segoe UI", Font.BOLD, 28));
        autor1.setForeground(Color.DARK_GRAY);
        autor2.setForeground(Color.DARK_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(autor1, gbc);
        gbc.gridy = 1;
        panel.add(autor2, gbc);

        add(panel, BorderLayout.CENTER);
    }
}
