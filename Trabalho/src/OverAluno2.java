import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import trabalho.MinhaConexao;
public class OverAluno2 extends javax.swing.JFrame {
String loginn;
Connection conexao = MinhaConexao.getInstance().sqlConnection;
JFrame father;
    /**
     * Creates new form OverAluno2
     */
    public OverAluno2() {
        initComponents();
    }

    OverAluno2(String loginn1) {
        initComponents();
        loginn = loginn1;
    }
    public OverAluno2(JFrame father) {
        initComponents();
        this.father = father;
// TODO add your handling code here:
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel13 = new javax.swing.JLabel();
        sel2 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        sel1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Insira a sub-matéria da pergunta :");

        sel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        sel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sel2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Insira a matéria :");

        sel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ArrayList<String> strList = new ArrayList<String>();

        String query = "SELECT * FROM materia";

        PreparedStatement ps = null;
        try {
            ps = conexao.prepareStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(CadastraTopico.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CadastraTopico.class.getName()).log(Level.SEVERE, null, ex);
        }
        strList.add("Selecionar Outro");
        try {
            while(rs.next()){

                strList.add(rs.getString("nome_materia"));

            }} catch (SQLException ex) {
                Logger.getLogger(CadastraTopico.class.getName()).log(Level.SEVERE, null, ex);
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(strList.toArray());
            sel1.setModel(model);
            sel1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    sel1ActionPerformed(evt);
                }
            });

            jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel1.setText("Insira de qual tópico você deseja saber o seu desempenho :");

            jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton1.setText("Voltar");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jButton2.setText("Ver desempenho");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(204, 204, 204)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(sel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(sel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(137, 137, 137)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(434, 434, 434)
                            .addComponent(jButton1)))
                    .addContainerGap(189, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jButton2)
                    .addGap(378, 378, 378))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(140, 140, 140)
                    .addComponent(jLabel1)
                    .addGap(54, 54, 54)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(50, 50, 50)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                    .addComponent(jButton2)
                    .addGap(32, 32, 32)
                    .addComponent(jButton1)
                    .addGap(97, 97, 97))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void sel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sel2ActionPerformed
        sel2.setSelectedItem(this);
    }//GEN-LAST:event_sel2ActionPerformed

    private void sel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sel1ActionPerformed
        sel1.setSelectedItem(this);
        ArrayList<String> strList1 = new ArrayList<String>();
        Object mat = sel1.getSelectedItem();
        String query1 = "SELECT * FROM topic";

        PreparedStatement ps1 = null;
        try {
            ps1 = conexao.prepareStatement(query1);
        } catch (SQLException ex) {
            Logger.getLogger(CadastraTopico.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs1 = null;
        try {
            rs1 = ps1.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CadastraTopico.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while(rs1.next()){
                if(mat.equals(rs1.getString("nome_materia")))
                strList1.add(rs1.getString("nome"));

            }} catch (SQLException ex) {
                Logger.getLogger(CadastraTopico.class.getName()).log(Level.SEVERE, null, ex);
            }
            DefaultComboBoxModel model1 = new DefaultComboBoxModel(strList1.toArray());
            sel2.setModel(model1);

          
    }//GEN-LAST:event_sel1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
this.setVisible(false);
        OverAluno0 mc = new OverAluno0(loginn);
        mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mc.setVisible(true);                 // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Object materia = sel1.getSelectedItem();
        Object submateria = sel2.getSelectedItem();    
        if (materia.equals("Selecionar Outro")) {
            JOptionPane.showMessageDialog(null, "Selecione alguma materia.", "Erro ao selecionar!", JOptionPane.ERROR_MESSAGE); 
        }
        else
        {
             this.setVisible(false);
             OverAluno3 mc = new OverAluno3(loginn,materia,submateria);
             mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             mc.setVisible(true);   
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(OverAluno2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OverAluno2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OverAluno2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OverAluno2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OverAluno2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JComboBox sel1;
    private javax.swing.JComboBox sel2;
    // End of variables declaration//GEN-END:variables
}