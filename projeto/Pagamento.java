import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pagamento {
	
	private int id;
	private String data;
	private double valor;
	private int idCompra;
	private MysqlConnect db;
	private Connection conn;
	
	public Pagamento(int id, String data, double valor, int idCompra) {
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.idCompra = idCompra;
		connect();
	}
	
	private void connect(){
		db = new MysqlConnect();
		conn = db.getConnection();
	}
	
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public void incluir() {

		String sql = "INSERT INTO pagamento (data,valor,idcompra) VALUES (?,?,?)";
		PreparedStatement st;
		try {

			st = conn.prepareStatement(sql);
			st.setString(1, getData());
			st.setDouble(2, getValor());
			st.setInt(3, getIdCompra());
			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.closeConnection();

	}
	

}
