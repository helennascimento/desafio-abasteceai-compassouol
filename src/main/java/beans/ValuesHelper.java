package beans;

public class ValuesHelper {
	
	private static String valorProduto;
	private static String valorFrete;
	private static String valorTotal;
	private static String nomeEnderecoEntrega;

	public String getValorProduto() {
		return valorProduto;
	}
	
	@SuppressWarnings("static-access")
	public void setValorProduto(String valorProduto) {
		this.valorProduto = valorProduto;
	}
	
	public String getValorFrete() {
		return valorFrete;
	}
	
	@SuppressWarnings("static-access")
	public void setValorFrete(String valorFrete) {
		this.valorFrete = valorFrete;
	}
	
	public String getValorTotal() {
		return valorTotal;
	}
	
	@SuppressWarnings("static-access")
	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public String getNomeEnderecoEntrega() {
		return nomeEnderecoEntrega;
	}
	
	@SuppressWarnings("static-access")
	public void setNomeEnderecoEntrega(String nomeEndereco) {
		this.nomeEnderecoEntrega = nomeEndereco;
	}
	
	// Métodos matemáticos
	
	public Float somarValorTotal(String valorProduto, String valorFrete) {
		return Float.parseFloat(valorProduto) + Float.parseFloat(valorFrete);
	}
	
	
	
	
}
