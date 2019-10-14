
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
public class OverAluno3 extends javax.swing.JFrame {
    String loginn;
    Object materia;
    Object submateria;
    Connection conexao = trabalho.MinhaConexao.getInstance().sqlConnection;
    JFrame father;
    /**
     * Creates new form OverAluno3
     */
    public OverAluno3() {
        initComponents();
    }
    
    
    public OverAluno3(JFrame father) {
        initComponents();
        this.father = father;
    }
    DecimalFormat df = new DecimalFormat("#.00"); 

    OverAluno3(String loginn1, Object materia1, Object submateria1) {
        initComponents();
        loginn = loginn1;
        materia = materia1;
        submateria = submateria1;
        atualizaDados();
    }
    
    
    public void atualizaDados() {
        ArrayList <Questoes> questao = new ArrayList();
        String query = "SELECT questoesf,questoesc,questoesf1,questoesc1,questoesf2,questoesc2 FROM overaluno2 WHERE nome_submateria = ('"+ submateria +"') AND user_aluno = ('"+ loginn +"') ";
        Statement stmt = null;
         DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("Desempenho do aluno "+loginn+"");
        listModel.addElement(""+submateria+" ------- Porcentagem de acerto");
        int  cond = 0;
        try {
            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                cond = 1;
                
                int qf = rs.getInt("questoesf");
                int qc = rs.getInt("questoesc");
                int qf1 = rs.getInt("questoesf1");
                int qc1 = rs.getInt("questoesc1");
                int qf2 = rs.getInt("questoesf2");
                int qc2 = rs.getInt("questoesc2");
                
                    questao.add(new Questoes(qc,qf,qc1,qf1,qc2,qf2));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        listModel.addElement("\n");
        
        if (cond == 0)
        {
            listModel.addElement("Voce ainda nao respondeu nenhuma questao dessa submateria!");
        }
        int i=0;
        for (Questoes c : questao) {
                double p1 = (double)c.getQf();
                double p2 = (double)c.getQc();
                double p4 = (double)c.getQf1();
                double p5 = (double)c.getQc1();
                double p6 = (double)c.getQf2();
                double p7 = (double)c.getQc2();
                double p3 = (p2/p1)*100;
               
                if((int)p1==0)
                {
                 listModel.addElement("Sua porcentagem de acerto em questoes faceis é:Voce nao fez nenhuma questão nessa dificuldade" );
                listModel.addElement("\n");   
                }
                else if ((int)p3==0)
                {
                    listModel.addElement("Sua porcentagem de acerto em questoes faceis é:" + "0.00%" );
                listModel.addElement("\n");
                }
                else {
                    listModel.addElement("Sua porcentagem de acerto em questoes faceis é:" + df.format(p3)+"%" );
                listModel.addElement("\n");
                }
                
                p3 = (p5/p4)*100;
                if((int)p4==0)
                {
                 listModel.addElement("Sua porcentagem de acerto em questoes medias é:Voce nao fez nenhuma questão nessa dificuldade" );
                listModel.addElement("\n");   
                }
                else if ((int)p3==0)
                {
                    listModel.addElement("Sua porcentagem de acerto em questoes medias é:" + "0.00%" );
                listModel.addElement("\n");
                }
                
                else {
                    listModel.addElement("Sua porcentagem de acerto em questoes medias é:" + df.format(p3)+"%" );
                listModel.addElement("\n");
                }
                p3 = (p7/p6)*100;
                if((int)p6==0)
                {
                 listModel.addElement("Sua porcentagem de acerto em questoes dificeis é:Voce nao fez nenhuma questão nessa dificuldade" );
                listModel.addElement("\n");   
                }
                else if ((int)p3==0)
                {
                    listModel.addElement("Sua porcentagem de acerto em questoes dificeis é:" + "0.00%" );
                listModel.addElement("\n");
                }
              
                else {
                    listModel.addElement("Sua porcentagem de acerto em questoes dificeis é:" + df.format(p3)+"%" );
                listModel.addElement("\n");
                }
                p3 = ((p7+p5+p2)/(p1+p4+p6))*100;
                if ((int)p3==0)
                {
                    listModel.addElement("Sua porcentagem de acerto total é:" + "0.00%" );
                listModel.addElement("\n");
                }
                else {
                    listModel.addElement("Sua porcentagem de acerto total é:" + df.format(p3)+"%" );
                listModel.addElement("\n");
                }
            }
 
        

        listMat.setModel(listModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listMat = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listMat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        listMat.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listMat);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Resultado da Disciplina por Tópico");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Voltar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("Ir para Desempenho por Matéria");
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(125, 125, 125))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jLabel1)
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1)
                .addGap(102, 102, 102)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        OverAluno2 mc = new OverAluno2(loginn);
        mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mc.setVisible(true);           // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        OverAluno1 mc = new OverAluno1(loginn);
        mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mc.setVisible(true);
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
            java.util.logging.Logger.getLogger(OverAluno3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OverAluno3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OverAluno3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OverAluno3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OverAluno3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listMat;
    // End of variables declaration//GEN-END:variables
}
