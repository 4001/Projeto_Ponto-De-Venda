import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Compra {

	private int id;
	private String data;
	private MysqlConnect db;
	private Connection conn;
	
	public Compra(String data) {
		connect();
		this.data = data;
	}
	
	public Compra(){
		connect();
	}
	
	private void connect(){
		db = new MysqlConnect();
		conn = db.getConnection();
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

	public int incluir() {

		String sql = "INSERT INTO compra (data) VALUES (?)";
		PreparedStatement st;
		try {

			st = conn.prepareStatement(sql);
			st.setString(1, data);

			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int ultimo = getUltimoId();
		db.closeConnection();
		return ultimo;
	}
	
	private int getUltimoId(){

		int ultimo = 0;
		
		String sql = "SELECT max(id) AS ultimo FROM compra";
		PreparedStatement st;
		ResultSet rs;
		try {

			st = conn.prepareStatement(sql);
			
			rs = st.executeQuery();
			
			if(rs.next()){
				ultimo = rs.getInt("ultimo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ultimo;
	}
	
	public void excluir(int id){
		
		String sql = "DELETE FROM compra WHERE id = ?";
		PreparedStatement st;
		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, id);

			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int ultimo = getUltimoId();
		db.closeConnection();
		
	}

}
