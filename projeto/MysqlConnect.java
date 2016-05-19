import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class MysqlConnect implements Connect {

	// atributo
	public Connection conn;

	// construtor
	public MysqlConnect() {
		conn = null;
		String url = "jdbc:mysql://localhost/"; // localizacao do servidor
		String dbName = "mercado2015"; // nome do banco de dados
		String driver = "com.mysql.jdbc.Driver"; // nome do driver de conexao
		String userName = "alunos"; // nome do usuario do banco
		String password = "usjt@but2010"; // respectiva senha

		try {
			Class.forName(driver);
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no Banco de Dados!\n\n"
					+ "Contate seu Administrador do Sistema!", "Erro...",
					JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}

	// fornece a conexão com o banco de dados Mysql
	public Connection getConnection() {
		return conn;
	}

	// Fecha a conexão com bco de dados
	public void closeConnection() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
