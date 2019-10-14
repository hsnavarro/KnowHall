
import javax.swing.DefaultListModel;
import javax.swing.WindowConstants;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author silve
 */ 
public class TelaProfessor1 extends javax.swing.JFrame {
private String login1;
    /**
     * Creates new form TelaProfessor1
     */
    public TelaProfessor1() {
        initComponents();
    }
public TelaProfessor1(String nome) {
        initComponents();
        login1 = nome;
        atualizaClientes(nome);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCadastraQuestionario = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnOverview = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("Seja Bem-Vindo ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Escolha uma das opçoes a seguir:");

        btnCadastraQuestionario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnCadastraQuestionario.setText("Cadastrar Questionário");
        btnCadastraQuestionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastraQuestionarioActionPerformed(evt);
            }
        });

        btnSair.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnOverview.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOverview.setText("Overview de Questionário");
        btnOverview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOverviewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOverview)
                    .addComponent(btnSair)
                    .addComponent(btnCadastraQuestionario)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(209, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(43, 43, 43)
                .addComponent(btnCadastraQuestionario)
                .addGap(28, 28, 28)
                .addComponent(btnOverview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(btnSair)
                .addGap(118, 118, 118))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
this.setVisible(false);
LoginProfessor1 mc = new LoginProfessor1();
mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
mc.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnCadastraQuestionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastraQuestionarioActionPerformed
        this.setVisible(false);
        CadastraQuestao mc = new CadastraQuestao(login1,1);
        mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mc.setVisible(true);
    }//GEN-LAST:event_btnCadastraQuestionarioActionPerformed

    private void btnOverviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOverviewActionPerformed
       this.setVisible(false);
        InsiraPinProf mc = new InsiraPinProf(login1);
        mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mc.setVisible(true);
    }//GEN-LAST:event_btnOverviewActionPerformed
 private void atualizaClientes(String nome) {   
            jLabel2.setText(nome);
    }
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
            java.util.logging.Logger.getLogger(TelaProfessor1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaProfessor1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaProfessor1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProfessor1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProfessor1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastraQuestionario;
    private javax.swing.JButton btnOverview;
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
