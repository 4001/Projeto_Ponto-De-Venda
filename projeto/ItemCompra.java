import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ItemCompra {
	
	private int id;
	private int idCompra;
	private int idMercadoria;
	private int quantidade;
	private MysqlConnect db;
	private Connection conn;
	private int quantidadeTotal = 0;
	private double precoTotal = 0.0d;
	
	
	public ItemCompra(int idCompra, int idMercadoria, int quantidade) {
		connect();
		this.idCompra = idCompra;
		this.idMercadoria = idMercadoria;
		this.quantidade = quantidade;
	}
	
	public ItemCompra(){
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
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public int getIdMercadoria() {
		return idMercadoria;
	}
	public void setIdMercadoria(int idMercadoria) {
		this.idMercadoria = idMercadoria;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public void incluir() {

		String sql = "INSERT INTO itemcompra (idcompra,idmercadoria,quantidade) VALUES (?,?,?)";
		PreparedStatement st;
		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, idCompra);
			st.setInt(2, idMercadoria);
			st.setInt(3, quantidade);
			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.closeConnection();

	}
	
	public boolean excluirUltimaOcorrenciaDoItem(int idMercadoria, int idCompra){
	
		boolean sucesso = false;
		String sql1 = "SELECT * FROM itemcompra WHERE idmercadoria = ? AND idcompra = ? ORDER BY id DESC";
		String sql2 = "DELETE FROM itemcompra WHERE id = ?";
		PreparedStatement st;
		PreparedStatement st2;
		ResultSet rs;
		try {

			st = conn.prepareStatement(sql1);
			st.setInt(1, idMercadoria);
			st.setInt(2, idCompra);
			rs = st.executeQuery();
			
			if(rs.next()){
				setId(rs.getInt("id"));
				setIdMercadoria(rs.getInt("idmercadoria"));
				setIdCompra(rs.getInt("idcompra"));
				setQuantidade(rs.getInt("quantidade"));
				st2 = conn.prepareStatement(sql2);
				st2.setInt(1,rs.getInt("id"));
				st2.executeUpdate();
				sucesso = true;
				st2.close();
			}
			st.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sucesso;
	}
	
	public void fecharCompra(int idCompra){
		
	      String sql = "SELECT sum(itemcompra.quantidade) AS soma, sum(itemcompra.quantidade * mercadoria.preco) AS total FROM itemcompra INNER JOIN mercadoria ON itemcompra.idmercadoria = mercadoria.id WHERE itemcompra.idcompra=?";
	      PreparedStatement st;
	      ResultSet rs;	   
	      try{
	      
	         st = conn.prepareStatement(sql);
	         st.setInt(1, idCompra);
	         rs=st.executeQuery();
	      
	         if(rs.next()){
	            quantidadeTotal = rs.getInt("soma");
	            precoTotal = rs.getDouble("total");
	         }
	      
	      }
	      catch(SQLException e){
	         e.printStackTrace();
	      }
		
	}
	
	public void excluirComprasPorIdCompra(int idCompra){
		
		String sql = "DELETE FROM itemcompra WHERE idcompra = ?";
		PreparedStatement st;
		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, idCompra);
			st.executeUpdate();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		db.closeConnection();
		
	}
	
	public ItemCompra[] getMaisVendidos(){
		
		 ItemCompra[] maisVendidos = new ItemCompra[10];
		 ItemCompra item = null;
		 int contador = 0;
	      String sql = "SELECT idmercadoria, SUM(quantidade) AS total FROM itemcompra GROUP BY idmercadoria ORDER BY total DESC LIMIT 10";
	      PreparedStatement st;
	      ResultSet rs;	   
	      try{
	      
	         st = conn.prepareStatement(sql);
	         rs=st.executeQuery();
	      
	         while(rs.next()){
	        	 item = new ItemCompra();
	        	 item.setIdMercadoria(rs.getInt("idmercadoria"));
	        	 item.setQuantidade(rs.getInt("total"));
	        	 maisVendidos[contador]= item;
	        	 contador++;
	         }
	      
	      }
	      catch(SQLException e){
	         e.printStackTrace();
	      }
		
		return maisVendidos;
	}
	

}
