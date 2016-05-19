import java.util.Date;
import java.util.Random;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class PontoDeVenda {
	
	private String texto;
	private boolean compraEmAndamento;
	private double totalCompra;
	private static final String DIVISOR = "\n---------------------------------------------------------\n";
	private int idCompra;
	private Compra compra;
	private Mercadoria mercadoria;
	private ItemCompra itemCompra;
	
	public String inicializaPDV(){
		
		texto = "Mercado USJT - Caixa Aberto - "+getDataHoje()+DIVISOR;
		compraEmAndamento = false;
		totalCompra = 0.0d;
		idCompra = 0;
		return texto;
		
	}
	
	public String novaCompra(){
		
		String local = "";
		if(compraEmAndamento){
			local="Atenção: existe uma compra em andamento.\nFeche ou Cancele a compra atual\nantes de iniciar nova compra."+DIVISOR;
		}else{
			local ="Nova compra iniciada - "+getDataHoje()+DIVISOR;
			compraEmAndamento = true;
			totalCompra = 0.0d;
			incluirCompra();
		}
		texto += local;
		return local;
	}
	
	
	private String getDataHoje(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return sdf.format(date);
	}
	
	public String getTotalCompra(){
		return formatarReal(totalCompra);
	}
	
	public boolean isCompraEmAndamento(){
		return compraEmAndamento;
	}
	
	private String formatarReal(double valor){
		DecimalFormat df = new DecimalFormat("#,###,##0.00");
       return df.format(valor);
	}
	
	private String spc(String texto, int espacos){
		return String.format("%-"+espacos+"s", texto);
	}
	
	private void incluirCompra(){
		compra = new Compra(getDataHoje());
		idCompra = compra.incluir();
	}
	
	public String adicionarItemNaCompra(int idMercadoria, int quantidade){
		String local = "";
		
		mercadoria = new Mercadoria(idMercadoria);
		if(mercadoria.getId()!=0){
			itemCompra = new ItemCompra(idCompra,idMercadoria,quantidade);
			itemCompra.incluir();
			local = spc(mercadoria.getDescricao(),35) + " qtd: " + quantidade + " x R$ "+ formatarReal(mercadoria.getPreco())+"/un = R$ "+ formatarReal((quantidade * mercadoria.getPreco() ))+"\n";
			totalCompra += quantidade * mercadoria.getPreco() ;
		}else{
			local = "Atenção: Código de mercadoria "+idMercadoria+" não localizado !";
		}
		texto += local;
		return local;
	}
	
	public String cancelarCompra(){
		
		ItemCompra ic = new ItemCompra();
		ic.setIdCompra(idCompra);
		ic.excluirComprasPorIdCompra(idCompra);
		compra = new Compra();
		compra.excluir(idCompra);
		compraEmAndamento=false;
		return DIVISOR +"Compra cancelada com sucesso"+DIVISOR;
	}
	
	public String cancelarItemCompra(int idMercadoria){
		
		itemCompra = new ItemCompra();
		String local = "";
		boolean sucesso = itemCompra.excluirUltimaOcorrenciaDoItem(idMercadoria,idCompra);
		if(sucesso){
			mercadoria = new Mercadoria(idMercadoria);
			local = spc(mercadoria.getDescricao(),35) + " qtd: " + itemCompra.getQuantidade() + " x R$ "+ formatarReal(mercadoria.getPreco())+"/un = R$ "+ formatarReal((-1 * itemCompra.getQuantidade() * mercadoria.getPreco() ))+"->CANCELADO\n";
			totalCompra += (-1 * itemCompra.getQuantidade() * mercadoria.getPreco());
		}else{
			local = "Atenção: Item não localizado nesta compra!";
		}
		
		texto += local;
		return local;
		
	}
	
	public String fecharCompra(){
		
		itemCompra = new ItemCompra();
		itemCompra.fecharCompra(idCompra);
		String resultado = DIVISOR + "F E C H A M E N T O   D A   C O M P R A" +DIVISOR + 
				"UNIDADES: "+itemCompra.getQuantidadeTotal()+ "   TOTAL R$ "+formatarReal(itemCompra.getPrecoTotal())+
				DIVISOR;
		return resultado;
	}
	
	public String pagarComCartao(String numeroCartao, String validade){
		PagamentoCartao pc = new PagamentoCartao(0,getDataHoje(),totalCompra,numeroCartao,validade,idCompra);
		pc.incluir();
		compraEmAndamento=false;
		
		return DIVISOR +"Pagamento no cartão aprovado pela operadora.\nReceibo do Cartão impresso aqui..."+DIVISOR; 
	

	}
	
	public String pagarEmDinheiro(double valorRecebido){
		
		double troco = valorRecebido - totalCompra;
		String retorno = "";
		
		if(troco < 0){
			PagamentoDinheiro pd = new PagamentoDinheiro(0,getDataHoje(),totalCompra,idCompra,valorRecebido, troco);
			pd.incluir();
			retorno = DIVISOR +"Valor insuficiante, favor verificar."+DIVISOR;
		}else{
			compraEmAndamento=false;
			retorno = DIVISOR +"Troco para o cliente: R$ "+formatarReal(troco)+DIVISOR;
		}
		
		return retorno;
	}
	
	public String selecionaMaisVendidos(){
		
		ItemCompra ic = new ItemCompra();
		ItemCompra[] itens = ic.getMaisVendidos();
		Mercadoria mercadoria = new Mercadoria();
		ItemCompra item = null;
		String retorno ="Lista de Mercadorias mais vendidas:\n\n";
		
		for(int i=0;i<10;i++){
			item = itens[i];
			mercadoria.getMercadoriaById(item.getIdMercadoria());
			retorno += ""+(i+1)+") "+mercadoria.getDescricao()+" - Cod: "+mercadoria.getId()+" - Qtd vendida: "+item.getQuantidade()+" unidades\n";
		}
		
		
		return retorno;
	}

}


