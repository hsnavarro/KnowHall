
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author silve
 */
public class CadastraQuestao extends javax.swing.JFrame {
    
private String login1;
private int question1;
private int qtd1;
Connection conexao = MinhaConexao.getInstance().sqlConnection;
JFrame father;
    /**
     * Creates new form CadastraQuestao
     */
    public CadastraQuestao() {
        initComponents();
        question1=0;
    }
    public CadastraQuestao(JFrame father) {
        initComponents();
        this.father = father;
        question1=0;
// TODO add your handling code here:
    }
    public CadastraQuestao(String nome,int qtd) {
        initComponents();
        login1 = nome;
        qtd1 = qtd;
        question1=0;
    }
    
public CadastraQuestao(String nome,int question, int qtd) {
        initComponents();
        login1 = nome;
        question1 = question;
        qtd1=qtd;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtQuestao = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        sel1 = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        sel2 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtNumAssert = new javax.swing.JTextPane();
        jLabel15 = new javax.swing.JLabel();
        dific = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        minuto = new javax.swing.JSpinner();
        segundo = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        Cadastra = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Cadastra_Topico = new javax.swing.JTextPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("Insira a questão");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Insira a pergunta :");

        jScrollPane1.setViewportView(txtQuestao);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Adicionar Questão");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Cancelar Questionário");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel12.setText("Insira a matéria da pergunta :");

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

            jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel13.setText("Insira a sub-matéria da pergunta :");

            sel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            sel2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    sel2ActionPerformed(evt);
                }
            });

            jLabel14.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel14.setText("Insira o número de assertivas :");

            jScrollPane11.setViewportView(txtNumAssert);

            jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel15.setText("Insira a dificuldade da pergunta :");

            dific.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            dific.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Facil", "Medio", "Dificil" }));
            dific.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    dificActionPerformed(evt);
                }
            });

            jLabel16.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
            jLabel16.setText("Insira o tempo da pergunta :");

            minuto.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

            segundo.setModel(new javax.swing.SpinnerNumberModel(0, 0, 59, 1));

            jScrollPane3.setViewportView(Cadastra);

            jLabel3.setText("Minutos");

            jLabel4.setText("Segundos");

            jScrollPane2.setViewportView(Cadastra_Topico);

            jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel5.setText("Outro:");

            jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
            jLabel6.setText("Outro:");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(322, 322, 322))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(sel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(sel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5))
                            .addGap(44, 44, 44)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGap(225, 225, 225))
                .addGroup(layout.createSequentialGroup()
                    .addGap(294, 294, 294)
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2)
                    .addGap(251, 251, 251))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dific, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(minuto, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addComponent(jLabel3)
                    .addGap(27, 27, 27)
                    .addComponent(segundo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel4)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(jLabel1)
                    .addGap(58, 58, 58)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dific, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(minuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(segundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(68, 68, 68)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addGap(26, 26, 26))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 // TODString nome = txtNome.getText();
        Object materia = sel1.getSelectedItem();
        Object submateria = sel2.getSelectedItem();
        Object dificuldade = dific.getSelectedItem();
        int minuto1 = (int) minuto.getValue();
        int segundo1 = (int) segundo.getValue();
        String pergunta = txtQuestao.getText();
        String Cadastra1 = Cadastra.getText();
        String Cadastra2 = Cadastra_Topico.getText();
        String num_assert = txtNumAssert.getText();
        String query2;
        query2 = "INSERT INTO questionario (user_prof) VALUES ('"+login1+"')";
        try {
            Statement stmt = conexao.createStatement();
            Statement stmt1 = conexao.createStatement();
            Statement stmt2 = conexao.createStatement();
            PreparedStatement stmt3 = conexao.prepareStatement("SELECT max(question_id) as max_id FROM questionario");
            PreparedStatement stmt4 = conexao.prepareStatement("SELECT max(questaoID) as max1_id FROM questoes1");
            ResultSet rs = stmt3.executeQuery();
            ResultSet rs1 = stmt4.executeQuery();
            if (question1==0 && rs.next())
            {
                stmt2.executeUpdate(query2);
                question1 = rs.getInt("max_id");
                question1++;
                System.out.println(question1);
            }
            int i=0;
            if (rs1.next())
            {
              
                i = rs1.getInt("max1_id");
                i++;
            }
            String query1;
            int cond = 0;
            if (txtQuestao.getText().equals("") || txtNumAssert.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nenhum campo pode estar em branco.", "Erro ao efetuar o Cadastro!", JOptionPane.ERROR_MESSAGE);
            cond=1;
                txtQuestao.setText("");
                txtNumAssert.setText("");
         
        }
            if (cond==0)
            {
                String a,b;
                a = Integer.toString( minuto1 );
                b = Integer.toString( segundo1 );
            if (materia=="Selecionar Outro")
            {
                String query3;
                query3 = "INSERT INTO materia(nome_materia) VALUES ('"+Cadastra1+"')";
                Statement stmt5 = conexao.createStatement();
                stmt5.executeUpdate(query3);
            }
            if (submateria=="Selecionar Outro")
            {
                if (materia=="Selecionar Outro")
                {
                String query4;
                query4 = "INSERT INTO topic(nome_materia, nome) VALUES ('"+Cadastra1+"','"+Cadastra2+"')";
                 Statement stmt6 = conexao.createStatement();
                stmt6.executeUpdate(query4);
                String query;
        query = "INSERT INTO questoes1 (pergunta, assert,materia,submateria,question_id, minuto,segundo,dificuldade,qc,qf) VALUES ('" + pergunta + "','"+num_assert+"','"+ Cadastra1 +"','"+Cadastra2+"','"+question1+"','"+a+"','"+b+"','"+dificuldade+"','"+(int)0+"','"+(int)0+"')";
            stmt.executeUpdate(query);
                }
                else 
                {
                    String query5;
                query5 = "INSERT INTO topic(nome_materia, nome) VALUES ('"+materia+"','"+Cadastra2+"')";
                 Statement stmt7 = conexao.createStatement();
                stmt7.executeUpdate(query5);
                String query;
        query = "INSERT INTO questoes1 (pergunta, assert,materia,submateria,question_id, minuto,segundo,dificuldade,qc,qf) VALUES ('" + pergunta + "','"+num_assert+"','"+ materia +"','"+Cadastra2+"','"+question1+"','"+a+"','"+b+"','"+dificuldade+"','"+(int)0+"','"+(int)0+"')";
            stmt.executeUpdate(query);
                }
            }
            else 
            {
                String query;
        query = "INSERT INTO questoes1 (pergunta, assert,materia,submateria,question_id, minuto,segundo,dificuldade,qc,qf) VALUES ('" + pergunta + "','"+num_assert+"','"+ materia +"','"+submateria+"','"+question1+"','"+a+"','"+b+"','"+dificuldade+"','"+(int)0+"','"+(int)0+"')";
            stmt.executeUpdate(query);
            }
            conexao.close();
            this.setVisible(false);
        CadastraAssert mc = new CadastraAssert(login1, question1,num_assert,i,qtd1);
        mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mc.setVisible(true); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
this.setVisible(false);
        TelaProfessor1 mc = new TelaProfessor1(login1);
        mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mc.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
     strList1.add("Selecionar Outro"); 
 DefaultComboBoxModel model1 = new DefaultComboBoxModel(strList1.toArray());
 sel2.setModel(model1);

//String Matematica[] = {"Algebra", "Geometria", "Trigonometria"};
//String Fisica[] = {"Gravitacao","Velocidade","Fisica Quantica"};
//ComboBoxModel model;
    //if(sel1.getSelectedItem().toString().equals("Matematica")) {
       // model = new DefaultComboBoxModel(Matematica);
       // sel2.setModel(model);
        // se vc não usa o NetBeans e ainda não tiver instanciado 
        // o JcomboBox, então faça da seguinte forma (ñ precisando de criar model):
        // jcbCidades = new JComboBox(cidadesMG);
    //}
   // if(sel1.getSelectedItem().toString().equals("Fisica")) {
   //     model = new DefaultComboBoxModel(Fisica);
    //    sel2.setModel(model);
   // }
// TODO add your handling code here:
    }//GEN-LAST:event_sel1ActionPerformed

    private void sel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sel2ActionPerformed
        sel2.setSelectedItem(this);
    }//GEN-LAST:event_sel2ActionPerformed

    private void dificActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dificActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dificActionPerformed

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
            java.util.logging.Logger.getLogger(CadastraQuestao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastraQuestao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastraQuestao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastraQuestao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastraQuestao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane Cadastra;
    private javax.swing.JTextPane Cadastra_Topico;
    private javax.swing.JComboBox dific;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner minuto;
    private javax.swing.JSpinner segundo;
    private javax.swing.JComboBox sel1;
    private javax.swing.JComboBox sel2;
    private javax.swing.JTextPane txtNumAssert;
    private javax.swing.JTextPane txtQuestao;
    // End of variables declaration//GEN-END:variables

    private static class CadastraTopico {

        public CadastraTopico() {
        }
    }
}