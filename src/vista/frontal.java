
package vista;

import javax.swing.*;
import java.beans.PropertyVetoException;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.util.ArrayList;
import java.util.List;

public class frontal extends javax.swing.JFrame {
    
    private JDesktopPane escritorio;
    private final List<JInternalFrame> ventanasActivas = new ArrayList<>();

    
    public frontal() {
        
        initComponents();
        
        escritorio = new JDesktopPane();
        getContentPane().add(escritorio);
        
         setSize(700, 630);
  
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem2ActionPerformed(evt);
    }
});

        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem3ActionPerformed(evt);
    }
});
        
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem5ActionPerformed(evt);
    }
});
        
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        jMenuItem6ActionPerformed(evt);
    }
}); 
        
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            ListadoEstudiantes ventana = new ListadoEstudiantes();
            ventana.setClosable(true);
            ventana.setMaximizable(true);
            ventana.setResizable(true);
            ventana.setIconifiable(true);
            agregarVentana(ventana);
    }
});
        
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            Autores ventana = new Autores(escritorio);
            ventana.setClosable(true);
            ventana.setResizable(true);
            ventana.setIconifiable(true);
            ventana.setMaximizable(true);
            agregarVentana(ventana);
    }
});

        
        jMenuItem8.addActionListener(e -> {
        ListadoCursos ventana = new ListadoCursos();
        agregarVentana(ventana);
});

        jMenuItem9.addActionListener(e -> {
        ListadoMatriculas ventana = new ListadoMatriculas();
        agregarVentana(ventana);
});

        jMenuItem10.addActionListener(e -> {
        ListadoNotas ventana = new ListadoNotas();
        agregarVentana(ventana);
});
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
);
        layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
);
    }


     private void agregarVentana(JInternalFrame ventana) {
        if (ventanasActivas.size() >= 4) {
            JOptionPane.showMessageDialog(this, "Máximo 4 ventanas abiertas");
            return;
        }

        ventana.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        ventana.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosed(InternalFrameEvent e) {
                ventanasActivas.remove(ventana);
                organizarVentanas();
            }
        });

        ventanasActivas.add(ventana);
        escritorio.add(ventana);
        organizarVentanas();

        ventana.setVisible(true);
        try {
            ventana.setSelected(true);
        } catch (PropertyVetoException ex) {
            ex.printStackTrace();
        }
    }

    private void organizarVentanas() {
        int total = ventanasActivas.size();
        if (total == 0) return;

        int filas = 2;
        int columnas = 2;

        int ancho = escritorio.getWidth() / columnas;
        int alto = escritorio.getHeight() / filas;

        for (int i = 0; i < total; i++) {
            JInternalFrame v = ventanasActivas.get(i);
            int fila = i / columnas;
            int col = i % columnas;
            v.setSize(ancho, alto);
            v.setLocation(col * ancho, fila * alto);
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jMenu1.setText("Ingresar");

        jMenuItem1.setText("Estudiante");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Crusos");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Docentes");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Salir");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu6.setText("Matricula");

        jMenuItem5.setText("Matricula");
        jMenu6.add(jMenuItem5);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Ingreso Notas");

        jMenuItem6.setText("Calificaciones");
        jMenu7.add(jMenuItem6);

        jMenuBar1.add(jMenu7);

        jMenu4.setText("Listados");

        jMenuItem7.setText("Estudiantes");
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Cursos");
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("Matriculas");
        jMenu4.add(jMenuItem9);

        jMenuItem10.setText("Notas");
        jMenu4.add(jMenuItem10);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Autores");

        jMenuItem11.setText("Dar Click");
        jMenu5.add(jMenuItem11);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 599, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        RegistroEstudiante ventana = new RegistroEstudiante();
        ventana.setClosable(true);
        ventana.setMaximizable(true);
        ventana.setResizable(true);
        ventana.setIconifiable(true);
        agregarVentana(ventana);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        System.exit(0); // Cierra completamente la aplicación
    }//GEN-LAST:event_jMenuItem4ActionPerformed


    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        RegistroCurso ventana = new RegistroCurso();
        ventana.setClosable(true);
        ventana.setMaximizable(true);
        ventana.setResizable(true);
        ventana.setIconifiable(true);
        agregarVentana(ventana);
}

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        RegistroDocente ventana = new RegistroDocente();
        ventana.setClosable(true);
        ventana.setMaximizable(true);
        ventana.setResizable(true);
        ventana.setIconifiable(true);
        agregarVentana(ventana);
}
    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
    RegistroMatricula ventana = new RegistroMatricula();
    ventana.setClosable(true);
    ventana.setResizable(true);
    ventana.setIconifiable(true);
    ventana.setMaximizable(true);
    agregarVentana(ventana); // Usa tu método existente para agregar y organizar
}
    
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {
    RegistroCalificaciones ventana = new RegistroCalificaciones();
    ventana.setClosable(true);
    ventana.setResizable(true);
    ventana.setIconifiable(true);
    ventana.setMaximizable(true);
    agregarVentana(ventana);
}
    
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {
    ListadoEstudiantes ventana = new ListadoEstudiantes();
    ventana.setClosable(true);
    ventana.setResizable(true);
    ventana.setIconifiable(true);
    ventana.setMaximizable(true);
    agregarVentana(ventana);
}
    
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {
    ListadoCursos ventana = new ListadoCursos();
    agregarVentana(ventana);
}

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {
    ListadoMatriculas ventana = new ListadoMatriculas();
    agregarVentana(ventana);
}

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {
    ListadoNotas ventana = new ListadoNotas();
    agregarVentana(ventana);
}


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frontal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frontal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frontal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frontal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frontal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    // End of variables declaration//GEN-END:variables
}
