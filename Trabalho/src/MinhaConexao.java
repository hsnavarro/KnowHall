package trabalho;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *
 * @author Pinhata
 */
public class MinhaConexao {
 
 //objeto estático para guardar uma instância de minha conexão
 public static MinhaConexao conexao = null;
 
 //método estático para criar uma instância do objeto
 //MinhaConexao
 public static MinhaConexao getInstance(){
   try{
     //verifica se ja existe uma conexão. Isso é feito verificando
     //se há algum objeto atribuido à conexao ou se a conexao sql
     //atribuída à ele está fechada.
     if(conexao==null || conexao.sqlConnection.isClosed()){
       conexao = new MinhaConexao(); //cria uma nova conexão caso não
                                     //exista uma.
     }
   }catch(Exception e){
     e.printStackTrace();
   }
   return conexao;
 }
 
 //cria um objeto Connection chamado sqlConnection
 public Connection sqlConnection;
 
 //construtor para inicializar a conexão
 private MinhaConexao()
 {
   try{
 
//cria uma nova instancia utilizando o driver que foi adicionado
     //à biblioteca atraves do arquivo .jar
     Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//define a string de conexão com o banco de dados MySQL.
     //Lembrando que meuUsuario e minhaSenha devem ser substituídos
     //pelo usuário e senha utilizados para conectar com o banco de dados.
     String textoConexao = "jdbc:mysql://localhost/provisorio1?user=root&password=Ric25091999";
//adquire a conexão
     sqlConnection = DriverManager.getConnection(textoConexao);
}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e){
       e.printStackTrace();
    }
  }

    

}