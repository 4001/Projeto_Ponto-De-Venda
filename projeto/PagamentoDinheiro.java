
public class PagamentoDinheiro extends Pagamento{
	
	private double valorPago;
	private double troco;
	
	public PagamentoDinheiro(int id, String data, double valor, int idCompra,
			double valorPago, double troco) {
		super(id, data, valor, idCompra);
		this.valorPago = valorPago;
		this.troco = troco;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
	public double getTroco() {
		return troco;
	}
	public void setTroco(double troco) {
		this.troco = troco;
	}


}
