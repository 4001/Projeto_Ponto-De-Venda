
public class PagamentoCartao extends Pagamento{

	private String numeroCartao;
	private String validade;
	
	public PagamentoCartao(int id, String data, double valor,
			String numeroCartao, String validade, int idCompra) {
		super(id, data, valor, idCompra);
		this.numeroCartao = numeroCartao;
		this.validade = validade;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	
}
