import java.text.DecimalFormat;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mercadoria{


   private int id;
   private String descricao;
   private double preco;
   private MysqlConnect db;
   private Connection conn;
   private ArrayList<String> lista;

   public Mercadoria(){
	   connect();
   }
   
   public Mercadoria(int id){
	   connect();
	   getMercadoriaById(id);
   }
   
   private void connect(){
	      db = new MysqlConnect();
	      conn = db.getConnection(); 
   }
   
   public void setId(int id){
      this.id=id;
   }
   
   public void setDescricao(String descricao){
      this.descricao=descricao;
   }

   public void setPreco(double preco){
      this.preco=preco;
   }
   
   public int getId(){
      return id;
   }

   public String getDescricao(){
      return descricao;
   }

   public double getPreco(){
      return preco;
   }
   
   public void verificaMercadorias(){
   
      String sql = "SELECT count(*) as qtd FROM mercadoria";
      PreparedStatement st;
      ResultSet rs;
      int qtd = 0;
   
      try{
      
         st = conn.prepareStatement(sql);
         rs=st.executeQuery();
      
         if(rs.next()){
            qtd = rs.getInt("qtd");
         }
      
         if(qtd <= 0){
            inserirMercadorias();
         }
      
      }
      catch(SQLException e){
         e.printStackTrace();
      }
   
   }


   public void inserirMercadorias(){
   
      lista = new ArrayList<String>();
   
      lista.add("insert into mercadoria (id,descricao,preco) values (1,'Sabão em pó 1kg', 8.70)");
      lista.add("insert into mercadoria (id,descricao,preco) values (2,'Amaciante 500ml', 12.10)");
      lista.add("insert into mercadoria (id,descricao,preco) values (3,'Detergente 500ml', 3.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (4,'Água sanitária 5L', 9.94)");
      lista.add("insert into mercadoria (id,descricao,preco) values (5,'Esponja de aço', 1.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (6,'Buchinha de pia', 1.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (7,'Sabão em pedra', 2.00)");
      lista.add("insert into mercadoria (id,descricao,preco) values (8,'Sabonete', 1.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (9,'Shampo 250ml', 13.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (10,'Condicionador 250 ml', 14.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (11,'Desinfetante 1L', 3.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (12,'Lustra móveis 150ml', 15.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (13,'Tira manchas 150ml', 6.80)");
      lista.add("insert into mercadoria (id,descricao,preco) values (14,'Limpa vidros 150ml', 8.90)");
      lista.add("insert into mercadoria (id,descricao,preco) values (15,'Alcool 1L', 4.5)");
      lista.add("insert into mercadoria (id,descricao,preco) values (16,'Saco de lixo 30l', 8.97)");
      lista.add("insert into mercadoria (id,descricao,preco) values (17,'Saco de lixo 50l', 12.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (18,'Refrigerante 2l', 4.5)");
      lista.add("insert into mercadoria (id,descricao,preco) values (19,'Suco garrafa 1l', 5.67)");
      lista.add("insert into mercadoria (id,descricao,preco) values (20,'Suco caixinha 500ml', 2.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (21,'Suco sachê', 0.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (22,'Leite integral 1L', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (23,'Leite desnatado 1L', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (24,'Arroz 5kg', 18.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (25,'Feijão 2kg', 19.40)");
      lista.add("insert into mercadoria (id,descricao,preco) values (26,'Macarrão 500g', 8.70)");
      lista.add("insert into mercadoria (id,descricao,preco) values (27,'Extrato de tomate 350g', 12.10)");
      lista.add("insert into mercadoria (id,descricao,preco) values (28,'Molho de tomate 350g', 3.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (29,'Sal 500g', 9.94)");
      lista.add("insert into mercadoria (id,descricao,preco) values (30,'Açucar 1kg', 1.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (31,'Achocolatado 500g', 1.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (32,'Bolacha 200g', 2.00)");
      lista.add("insert into mercadoria (id,descricao,preco) values (33,'Café 500g', 1.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (34,'Farofa pronta 500g', 13.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (35,'Fubá 500g', 14.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (36,'Farinha de trigo 1kg', 3.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (37,'Farinha de milho 500g', 15.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (38,'Farinha de mandioca 500g', 6.80)");
      lista.add("insert into mercadoria (id,descricao,preco) values (39,'Sardinha 250g', 8.90)");
      lista.add("insert into mercadoria (id,descricao,preco) values (40,'Atum 250g', 4.5)");
      lista.add("insert into mercadoria (id,descricao,preco) values (41,'Maionese 250g', 8.97)");
      lista.add("insert into mercadoria (id,descricao,preco) values (42,'Molho de pimenta 100g', 12.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (43,'Ervilha 350g', 4.5)");
      lista.add("insert into mercadoria (id,descricao,preco) values (44,'Milho verde 350g', 5.67)");
      lista.add("insert into mercadoria (id,descricao,preco) values (45,'Seleta  350g', 2.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (46,'Doce de leite 200g', 0.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (47,'Goiabada 300g', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (48,'Milho de pipoca 300g', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (49,'Óleo de cozinha 1L', 18.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (50,'Leite em pó 500g', 19.40)");
      lista.add("insert into mercadoria (id,descricao,preco) values (51,'Creme de leite 350g', 8.70)");
      lista.add("insert into mercadoria (id,descricao,preco) values (52,'Leite condensado 350g', 12.10)");
      lista.add("insert into mercadoria (id,descricao,preco) values (53,'Pão de forma 400g', 3.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (54,'Alface un', 9.94)");
      lista.add("insert into mercadoria (id,descricao,preco) values (55,'Couve un', 1.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (56,'Batata 1kg', 1.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (57,'Tomate 1kg', 2.00)");
      lista.add("insert into mercadoria (id,descricao,preco) values (58,'Cenoura 500g', 1.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (59,'Beterraba 500g', 13.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (60,'Chicória un', 14.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (61,'Mandioca 1kg', 3.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (62,'Chuchu 500g', 15.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (63,'Espinafre un', 6.80)");
      lista.add("insert into mercadoria (id,descricao,preco) values (64,'Banana 500g', 8.90)");
      lista.add("insert into mercadoria (id,descricao,preco) values (65,'Ovos dz', 4.5)");
      lista.add("insert into mercadoria (id,descricao,preco) values (66,'Uva 1kg', 8.97)");
      lista.add("insert into mercadoria (id,descricao,preco) values (67,'Abacate un', 12.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (68,'Mamão un', 4.5)");
      lista.add("insert into mercadoria (id,descricao,preco) values (69,'Melancia un', 5.67)");
      lista.add("insert into mercadoria (id,descricao,preco) values (70,'Melão un', 2.34)");
      lista.add("insert into mercadoria (id,descricao,preco) values (71,'Jiló 350g', 0.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (72,'Quiabo 300g', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (73,'Salsa un', 3.89)");
      lista.add("insert into mercadoria (id,descricao,preco) values (74,'Cheiro verde un', 18.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (75,'Cebola 1kg', 19.40)");
      lista.add("insert into mercadoria (id,descricao,preco) values (76,'Queijo Minas 400g', 8.70)");
      lista.add("insert into mercadoria (id,descricao,preco) values (77,'Queijo Mussarela 300g', 12.10)");
      lista.add("insert into mercadoria (id,descricao,preco) values (78,'Queijo outros 300g', 3.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (79,'Manteiga 250g', 9.94)");
      lista.add("insert into mercadoria (id,descricao,preco) values (80,'Margarina 250g', 1.20)");
      lista.add("insert into mercadoria (id,descricao,preco) values (81,'Iogurte 500ml', 1.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (82,'Presunto 300g', 2.00)");
      lista.add("insert into mercadoria (id,descricao,preco) values (83,'Peixe 350g', 1.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (84,'Frango 1kg', 13.45)");
      lista.add("insert into mercadoria (id,descricao,preco) values (85,'Carne vermelha 1kg', 14.13)");
      lista.add("insert into mercadoria (id,descricao,preco) values (86,'Carne seca 500g', 3.50)");
      lista.add("insert into mercadoria (id,descricao,preco) values (87,'Salsicha 500g', 15.89)");
   
      PreparedStatement st = null;
      try{
         for(String sql: lista){
            st = conn.prepareStatement(sql);
            st.executeUpdate();
         }
         st.close();
      }
      catch(SQLException e){
         e.printStackTrace();
      }
      finally{
         db.closeConnection();
      }
   
   }


   public void getMercadoriaById(int id){
	   
		String sql = "SELECT * FROM mercadoria WHERE id = ?";
		PreparedStatement st;
		ResultSet rs;
		try {

			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()){
				setId(rs.getInt("id"));
				setDescricao(rs.getString("descricao"));
				setPreco(rs.getDouble("preco"));
			}else{
				setId(0);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   
   public String getAllMercadoria(){
	
		String sql = "SELECT * FROM mercadoria ORDER BY descricao";
		PreparedStatement st;
		ResultSet rs;
		String resultado = "";
		try {

			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while(rs.next()){
				resultado += "Cód: "+rs.getInt("id")+"\t"+rs.getString("descricao")+"/R$ "+formatarReal(rs.getDouble("preco"))+"\n";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	   return resultado;
   }
   
	private String formatarReal(double valor){
		DecimalFormat df = new DecimalFormat("#,###,##0.00");
       return df.format(valor);
	}
	

}