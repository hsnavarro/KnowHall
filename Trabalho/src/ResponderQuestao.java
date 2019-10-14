
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ResponderQuestao extends javax.swing.JFrame {
    Timer timer = new Timer();
    int fechar = 1;
    double qc;
    double qe;
    int counter1;
    int counter2;
    int counter3;
    int qc1,qf1;
    String materia;
    String submateria;
    String dificuldade;
        String p = null;
        String r;
        int x = 0;
    String login;
    int quest;
    int num;
    int num_quest;
    Connection conexao = trabalho.MinhaConexao.getInstance().sqlConnection;
    JFrame father;
    public ResponderQuestao(String login1, int quest1, int num1, int num_quest1,double qc1,double qe1) {
         initComponents();
        login = login1;
        num = num1;
        quest = quest1;
        num_quest = num_quest1;
        qc = qc1;
        qe = qe1;
        atualizaQuestao();
        atualizaDados();
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    private void atualizaQuestao() {   
            jLabel2.setText(Integer.toString( num_quest ));
    }
    public void atualizaDados() {
        ArrayList <Questoes> questao = new ArrayList();
        ArrayList <Asserts> asserts = new ArrayList();
        String query = "SELECT segundo,minuto,qc,qf,pergunta,questaoID,r,materia,submateria,dificuldade FROM questoes1 WHERE question_id = ('"+ quest +"')";
        Statement stmt = null;
        try {
            stmt = conexao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String pergunta = rs.getString("pergunta");
                materia = rs.getString("materia");
                submateria = rs.getString("submateria");
                dificuldade = rs.getString("dificuldade");
                int questaoID = rs.getInt("questaoID");
                counter1 = rs.getInt("segundo");
                counter2 = rs.getInt("minuto");
                counter3 = counter2*60+counter1;
                String resposta = rs.getString("r");
                qc1 = rs.getInt("qc");
                qf1 = rs.getInt("qf");
                questao.add(new Questoes(pergunta, questaoID, resposta,materia, submateria, dificuldade,qc1,qf1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        int i=0;
        for (Questoes c : questao) {
            if(i==num_quest-1)
            {
                materia = c.getMateria();
                submateria = c.getSubmateria();
                dificuldade = c.getDificuldade();
                x = c.getQuestaoID();
                p = c.getPergunta();
                r = c.getResposta();
            }
            i++;
        }
        String query1 = "SELECT assertiv,peso FROM assertivas WHERE questao_id = ('"+ x +"')";
        Statement stmt1 = null;
        try {
            stmt1 = conexao.createStatement();
            ResultSet rs1 = stmt1.executeQuery(query1);
            while (rs1.next()) {
                String assertiva = rs1.getString("assertiv");
                String peso = rs1.getString("peso");
                asserts.add(new Asserts(assertiva,peso));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       // questao.get(num_quest-1);
        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement(new String(p));
        for (Asserts c : asserts) {
            listModel.addElement(new String(c.getPergunta() + " ------- " + c.getPeso()));
        }
         //new timer
       
        TimerTask task = new TimerTask() {         
            public void run() {                
                timeLeft.setText(Integer.toString(counter3)); //the timer lable to counter.
                counter3--;
                if (counter3 == -1){
                    timer.cancel();
                    fechartela();
            
                }
            }   
        };
    timer.scheduleAtFixedRate(task, 1000, 1000);
    
    

        listQuest.setModel(listModel);
    }
    public void fechartela()
    {
        num_quest++;
        String query = "SELECT nome_materia,questoesf,questoesc FROM overaluno1 WHERE user_aluno = ('"+ login +"')";
            Statement stmt = null;
                   try {
                       stmt = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            ResultSet rs = null;
                   try {
                       rs = stmt.executeQuery(query);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            int cond = 0;
                   try {
                       while(rs.next())
                       {
                           
                      
                               String nome = rs.getString("nome_materia");
                           int qf = rs.getInt("questoesf");
                           int qc = rs.getInt("questoesc");
                           
                           if(nome.equals(materia))
                           {
                               cond = 1;
                               String query2 = "UPDATE overaluno1 SET questoesf = '"+(qf+1)+"' WHERE nome_materia = '"+materia+"' AND user_aluno = '"+login+"'";
                               Statement stmt2 = conexao.createStatement();
                stmt2.executeUpdate(query2);
                
                           }
                       }      } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            if (cond == 0)
            {
                String query3;
                query3 = "INSERT INTO overaluno1(user_aluno, nome_materia, questoesf, questoesc) VALUES ('" + login + "','"+materia+"','"+ (int)1 +"','"+ (int)0 +"')";
                Statement stmt5 = null;
                try {
                    stmt5 = conexao.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    stmt5.executeUpdate(query3);
                } catch (SQLException ex) {
                    Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } //Fim overview materia
            
            //overview Submateria
            String query1 = "SELECT nome_submateria,questoesf,questoesc,questoesf1,questoesc1,questoesf2,questoesc2 FROM overaluno2 WHERE user_aluno = ('"+ login +"')";
            Statement stmt1 = null;
                   try {
                       stmt1 = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            ResultSet rs1 = null;
                   try {
                       rs1 = stmt1.executeQuery(query1);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            int cond1 = 0;
                   try {
                       while(rs1.next())
                       {
                           
                    
                               String nome = rs1.getString("nome_submateria");
                           int qf = rs1.getInt("questoesf");
                           int qc = rs1.getInt("questoesc");
                           int qf1 = rs1.getInt("questoesf1");
                           int qc1 = rs1.getInt("questoesc1");
                           int qf2 = rs1.getInt("questoesf2");
                           int qc2 = rs1.getInt("questoesc2");
                           
                           
                           if(nome.equals(submateria))
                           {
                               
                               cond1 = 1;
                               String query4 = "UPDATE overaluno2 SET questoesf = '"+(qf+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                 
                               String query6 = "UPDATE overaluno2 SET questoesf1 = '"+(qf1+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                               
                               String query8 = "UPDATE overaluno2 SET questoesf2 = '"+(qf2+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                              
                               if(dificuldade.equals("Facil"))
                               {
                                   Statement stmt4 = conexao.createStatement();
                stmt4.executeUpdate(query4);
                
                               }
                               else if(dificuldade.equals("Medio"))
                               {
                                   Statement stmt6 = conexao.createStatement();
                stmt6.executeUpdate(query6);
                
                               }
                               else if(dificuldade.equals("Dificil"))
                               {
                                   Statement stmt8 = conexao.createStatement();
                stmt8.executeUpdate(query8);
                
                               }
                               
                           }
                       }      } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       if (cond1 == 0)
            {
           
                String query4 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)1 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"')";
                String query6 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)1 +"','"+ (int)0 +"')";
                String query5 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)1 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"')";             
                 
                if(dificuldade.equals("Facil"))
                               {
              
                        Statement stmt4 = null;
                    Object ex = null;
                    try {
                        stmt4 = conexao.createStatement();
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt4.executeUpdate(query4);
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
                               }
                               else if(dificuldade.equals("Medio"))
                               {
                     Statement stmt5 = null;
                    Object ex = null;
                    try {
                        stmt5 = conexao.createStatement();
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt5.executeUpdate(query5);
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                               }
                               else if(dificuldade.equals("Dificil"))
                               {
                                   
                     Statement stmt6 = null;
                    Object ex = null;
                    try {
                        stmt6 = conexao.createStatement();
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt6.executeUpdate(query6);
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                               }
                               
                           
                     
            }
            //fim overview submateria
                       
                        // overview professor
                     String query4 = "UPDATE questoes1 SET qf = '"+(qf1+1)+"' WHERE questaoID = '"+x+"'";
                     Statement stmt4 = null;
                     if (qf1+1>=10)
                     {
                        
                         String query7;
                         Statement stmt10 = null;
                         if (((double)(qc1)/(qf1+1))>0.6)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Facil"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                         else if (((double)(qc1)/(qf1+1))<=0.4)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Dificil"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                         else if ((double)(qc1)/(qf1+1)<=0.6 && (double)(qc1+1)/(qf1+1)>0.4)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Medio"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                     }
                   try {
                       stmt4 = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   try {
                       stmt4.executeUpdate(query4);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
                     
                   // fim overview professor
            qe++;
             this.setVisible(false);
             Errou mc = new Errou(login,quest,num,num_quest,qc,qe);
              mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             mc.setVisible(true); 
    }
    public ResponderQuestao() {
        initComponents();
      
    }
    /**
     * Creates new form InsiraPin
     */
     public ResponderQuestao(JFrame father) {
        initComponents();
        this.father = father;
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        listQuest = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtResp = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        timeLeft = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        listQuest.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        listQuest.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listQuest);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Responda a questao");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("jLabel2");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Enviar Resposta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtResp);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Insira a resposta");

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("Abandonar Questionário");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        timeLeft.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        timeLeft.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(timeLeft, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addGap(368, 368, 368)
                .addComponent(jButton1)
                .addGap(135, 135, 135))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(104, 104, 104))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(152, 152, 152))))
            .addGroup(layout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(timeLeft))
                .addGap(67, 67, 67)
                .addComponent(jButton2)
                .addGap(113, 113, 113))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String resp = txtResp.getText();
        if (txtResp.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "A resposta nao pode estar em branco!", "Erro ao computar resposta!", JOptionPane.ERROR_MESSAGE);
        }
        else {
               num_quest++;
               timer.cancel();
        if(resp.equals(r))
        {
            // Overview Materia
            String query = "SELECT nome_materia,questoesf,questoesc FROM overaluno1 WHERE user_aluno = ('"+ login +"')";
            Statement stmt = null;
                   try {
                       stmt = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            ResultSet rs = null;
                   try {
                       rs = stmt.executeQuery(query);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            int cond = 0;
                   try {
                       while(rs.next())
                       {
                           
                      
                               String nome = rs.getString("nome_materia");
                           int qf = rs.getInt("questoesf");
                           int qc = rs.getInt("questoesc");
                           
                           if(nome.equals(materia))
                           {
                               cond = 1;
                               String query2 = "UPDATE overaluno1 SET questoesf = '"+(qf+1)+"' WHERE nome_materia = '"+materia+"' AND user_aluno = '"+login+"'";
                               String query3 = "UPDATE overaluno1 SET questoesc = '"+(qc+1)+"' WHERE nome_materia = '"+materia+"' AND user_aluno = '"+login+"'";
                               Statement stmt2 = conexao.createStatement();
                stmt2.executeUpdate(query2);
                Statement stmt3 = conexao.createStatement();
                stmt3.executeUpdate(query3);
                           }
                       }      } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            if (cond == 0)
            {
                String query3;
                query3 = "INSERT INTO overaluno1(user_aluno, nome_materia, questoesf, questoesc) VALUES ('" + login + "','"+materia+"','"+ (int)1 +"','"+ (int)1 +"')";
                Statement stmt5 = null;
                try {
                    stmt5 = conexao.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    stmt5.executeUpdate(query3);
                } catch (SQLException ex) {
                    Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }//Fim overview materia
            
            
            // Overview Submateria
            String query1 = "SELECT nome_submateria,questoesf,questoesc,questoesf1,questoesc1,questoesf2,questoesc2 FROM overaluno2 WHERE user_aluno = ('"+ login +"')";
            Statement stmt1 = null;
                   try {
                       stmt1 = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            ResultSet rs1 = null;
                   try {
                       rs1 = stmt1.executeQuery(query1);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            int cond1 = 0;
                   try {
                       while(rs1.next())
                       {
                           
                    
                               String nome = rs1.getString("nome_submateria");
                           int qf = rs1.getInt("questoesf");
                           int qc = rs1.getInt("questoesc");
                           int qf1 = rs1.getInt("questoesf1");
                           int qc1 = rs1.getInt("questoesc1");
                           int qf2 = rs1.getInt("questoesf2");
                           int qc2 = rs1.getInt("questoesc2");
                           
                           
                           if(nome.equals(submateria))
                           {
                               
                               cond1 = 1;
                               String query4 = "UPDATE overaluno2 SET questoesf = '"+(qf+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                               String query5 = "UPDATE overaluno2 SET questoesc = '"+(qc+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                               String query6 = "UPDATE overaluno2 SET questoesf1 = '"+(qf1+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                               String query7 = "UPDATE overaluno2 SET questoesc1 = '"+(qc1+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                               String query8 = "UPDATE overaluno2 SET questoesf2 = '"+(qf2+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                               String query9 = "UPDATE overaluno2 SET questoesc2 = '"+(qc2+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                               if(dificuldade.equals("Facil"))
                               {
                                   Statement stmt4 = conexao.createStatement();
                stmt4.executeUpdate(query4);
                Statement stmt5 = conexao.createStatement();
                stmt5.executeUpdate(query5);
                               }
                               else if(dificuldade.equals("Medio"))
                               {
                                   Statement stmt6 = conexao.createStatement();
                stmt6.executeUpdate(query6);
                Statement stmt7 = conexao.createStatement();
                stmt7.executeUpdate(query7);
                               }
                               else if(dificuldade.equals("Dificil"))
                               {
                                   Statement stmt8 = conexao.createStatement();
                stmt8.executeUpdate(query8);
                Statement stmt9 = conexao.createStatement();
                stmt9.executeUpdate(query9);
                               }
                               
                           }
                       }      } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
           
                   
                   if (cond1 == 0)
            {
           
                String query4 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)1 +"','"+ (int)1 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"')";
                String query6 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)1 +"','"+ (int)1 +"')";
                String query5 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)1 +"','"+ (int)1 +"','"+ (int)0 +"','"+ (int)0 +"')";             
                 
                if(dificuldade.equals("Facil"))
                               {
              
                        Statement stmt4 = null;
                    try {
                        stmt4 = conexao.createStatement();
                    } catch (SQLException ex) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt4.executeUpdate(query4);
                    } catch (SQLException ex) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
                               }
                               else if(dificuldade.equals("Medio"))
                               {
                     Statement stmt5 = null;
                    try {
                        stmt5 = conexao.createStatement();
                    } catch (SQLException ex) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt5.executeUpdate(query5);
                    } catch (SQLException ex) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                               }
                               else if(dificuldade.equals("Dificil"))
                               {
                                   
                     Statement stmt6 = null;
                    try {
                        stmt6 = conexao.createStatement();
                    } catch (SQLException ex) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt6.executeUpdate(query6);
                    } catch (SQLException ex) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                               }
                               
                           
                     
            }//Fim overview submateria
            // overview professor
                     String query10 = "UPDATE questoes1 SET qf = '"+(qf1+1)+"' WHERE questaoID = '"+x+"'";
                     String query11 = "UPDATE questoes1 SET qc = '"+(qc1+1)+"' WHERE questaoID = '"+x+"'";
                     Statement stmt4 = null;
                     if (qf1+1>=10)
                     {
                         String query7;
                         Statement stmt10 = null;
                         if (((double)(qc1+1)/(qf1+1))>0.6)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Facil"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                         else if (((double)(qc1+1)/(qf1+1))<=0.4)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Dificil"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                         else if (((double)(qc1+1)/(qf1+1))<=0.6 && ((qc1+1)/(qf1+1))>0.4)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Medio"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                     }
                   try {
                       stmt4 = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   try {
                       stmt4.executeUpdate(query10);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
                     Statement stmt5 = null;
                   try {
                       stmt5 = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   try {
                       stmt5.executeUpdate(query11);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   // fim overview professor
            qc++;
            this.setVisible(false);
             Acertou mc = new Acertou(login,quest,num,num_quest,qc,qe);
              mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             mc.setVisible(true);    
        }
        
        
        
        
        
        else
        {
            // overview materia
         String query = "SELECT nome_materia,questoesf,questoesc FROM overaluno1 WHERE user_aluno = ('"+ login +"')";
            Statement stmt = null;
                   try {
                       stmt = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            ResultSet rs = null;
                   try {
                       rs = stmt.executeQuery(query);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            int cond = 0;
                   try {
                       while(rs.next())
                       {
                           
                      
                               String nome = rs.getString("nome_materia");
                           int qf = rs.getInt("questoesf");
                           int qc = rs.getInt("questoesc");
                           
                           if(nome.equals(materia))
                           {
                               cond = 1;
                               String query2 = "UPDATE overaluno1 SET questoesf = '"+(qf+1)+"' WHERE nome_materia = '"+materia+"' AND user_aluno = '"+login+"'";
                               Statement stmt2 = conexao.createStatement();
                stmt2.executeUpdate(query2);
                
                           }
                       }      } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            if (cond == 0)
            {
                String query3;
                query3 = "INSERT INTO overaluno1(user_aluno, nome_materia, questoesf, questoesc) VALUES ('" + login + "','"+materia+"','"+ (int)1 +"','"+ (int)0 +"')";
                Statement stmt5 = null;
                try {
                    stmt5 = conexao.createStatement();
                } catch (SQLException ex) {
                    Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    stmt5.executeUpdate(query3);
                } catch (SQLException ex) {
                    Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                }
            } //Fim overview materia
            
            //overview Submateria
            String query1 = "SELECT nome_submateria,questoesf,questoesc,questoesf1,questoesc1,questoesf2,questoesc2 FROM overaluno2 WHERE user_aluno = ('"+ login +"')";
            Statement stmt1 = null;
                   try {
                       stmt1 = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            ResultSet rs1 = null;
                   try {
                       rs1 = stmt1.executeQuery(query1);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
            int cond1 = 0;
                   try {
                       while(rs1.next())
                       {
                           
                    
                               String nome = rs1.getString("nome_submateria");
                           int qf = rs1.getInt("questoesf");
                           int qc = rs1.getInt("questoesc");
                           int qf1 = rs1.getInt("questoesf1");
                           int qc1 = rs1.getInt("questoesc1");
                           int qf2 = rs1.getInt("questoesf2");
                           int qc2 = rs1.getInt("questoesc2");
                           
                           
                           if(nome.equals(submateria))
                           {
                               
                               cond1 = 1;
                               String query4 = "UPDATE overaluno2 SET questoesf = '"+(qf+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                 
                               String query6 = "UPDATE overaluno2 SET questoesf1 = '"+(qf1+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                               
                               String query8 = "UPDATE overaluno2 SET questoesf2 = '"+(qf2+1)+"' WHERE nome_submateria = '"+submateria+"' AND user_aluno = '"+login+"'";
                              
                               if(dificuldade.equals("Facil"))
                               {
                                   Statement stmt4 = conexao.createStatement();
                stmt4.executeUpdate(query4);
                
                               }
                               else if(dificuldade.equals("Medio"))
                               {
                                   Statement stmt6 = conexao.createStatement();
                stmt6.executeUpdate(query6);
                
                               }
                               else if(dificuldade.equals("Dificil"))
                               {
                                   Statement stmt8 = conexao.createStatement();
                stmt8.executeUpdate(query8);
                
                               }
                               
                           }
                       }      } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       if (cond1 == 0)
            {
           
                String query4 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)1 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"')";
                String query6 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)1 +"','"+ (int)0 +"')";
                String query5 = "INSERT INTO overaluno2 (user_aluno, nome_submateria, questoesf, questoesc,questoesf1,questoesc1,questoesf2,questoesc2) VALUES ('" + login + "','"+submateria+"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)1 +"','"+ (int)0 +"','"+ (int)0 +"','"+ (int)0 +"')";             
                 
                if(dificuldade.equals("Facil"))
                               {
              
                        Statement stmt4 = null;
                    Object ex = null;
                    try {
                        stmt4 = conexao.createStatement();
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt4.executeUpdate(query4);
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
               
                               }
                               else if(dificuldade.equals("Medio"))
                               {
                     Statement stmt5 = null;
                    Object ex = null;
                    try {
                        stmt5 = conexao.createStatement();
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt5.executeUpdate(query5);
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                               }
                               else if(dificuldade.equals("Dificil"))
                               {
                                   
                     Statement stmt6 = null;
                    Object ex = null;
                    try {
                        stmt6 = conexao.createStatement();
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        stmt6.executeUpdate(query6);
                    } catch (SQLException ex1) {
                        Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                               }
                               
                           
                     
            }
            //fim overview submateria
                       
                        // overview professor
                     String query4 = "UPDATE questoes1 SET qf = '"+(qf1+1)+"' WHERE questaoID = '"+x+"'";
                     Statement stmt4 = null;
                     if (qf1+1>=10)
                     {
                        
                         String query7;
                         Statement stmt10 = null;
                         if (((double)(qc1)/(qf1+1))>0.6)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Facil"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                         else if (((double)(qc1)/(qf1+1))<=0.4)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Dificil"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                         else if ((double)(qc1)/(qf1+1)<=0.6 && (double)(qc1+1)/(qf1+1)>0.4)
                         {
                             query7 = "UPDATE questoes1 SET dificuldade = '"+"Medio"+"' WHERE questaoID = '"+x+"'";
                             try {
                                 stmt10 = conexao.createStatement();
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                             try {
                                 stmt10.executeUpdate(query7);
                             } catch (SQLException ex) {
                                 Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                             }
                         }
                     }
                   try {
                       stmt4 = conexao.createStatement();
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   try {
                       stmt4.executeUpdate(query4);
                   } catch (SQLException ex) {
                       Logger.getLogger(ResponderQuestao.class.getName()).log(Level.SEVERE, null, ex);
                   }
                     
                   // fim overview professor
            qe++;
            this.setVisible(false);
             Errou mc = new Errou(login,quest,num,num_quest,qc,qe);
              mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             mc.setVisible(true);    
             
        }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         timer.cancel();
        this.setVisible(false);
             TelaAluno1 mc = new TelaAluno1(login);
              mc.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
             mc.setVisible(true); 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
     // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(ResponderQuestao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResponderQuestao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResponderQuestao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResponderQuestao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResponderQuestao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listQuest;
    private javax.swing.JLabel timeLeft;
    private javax.swing.JTextPane txtResp;
    // End of variables declaration//GEN-END:variables
}
