package pages;

import static config.Driver.getDriver;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import beans.Evidence;
import beans.ValuesHelper;

public class CompraPage extends BasePage {
	
	public CompraPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	Evidence evidencia = new Evidence();
	
	static ValuesHelper helper = new ValuesHelper();
	
	@FindBy(xpath = "//h1[@itemprop='name']")
	private WebElement tituloProduto;
	
	@FindBy(id = "our_price_display")
	private WebElement valorUnitario;
	
	@FindBy(id = "quantity_wanted")
	private WebElement inputQuantity;
	
	@FindBy(id = "group_1")
	private WebElement selectTamanho;
	
	@FindBy(name = "Submit")
	private WebElement btnAddToCart;
	
	@FindBy(xpath = "(//h2)[1]")
	private WebElement mensagemSucessoCarrinho;
	
	@FindBy(id = "layer_cart_product_title")
	private WebElement nomeProduto;
	
	@FindBy(id = "layer_cart_product_price")
	private WebElement precoUnitarioCarrinho;
	
	@FindBy(xpath = "//span[@class='ajax_cart_shipping_cost']")
	private WebElement valorFreteCarrinho;
	
	@FindBy(xpath = "//span[@class='ajax_block_cart_total']")
	private WebElement valorTotalCarrinho;
	
	@FindBy(xpath = "//a[@title='Proceed to checkout']")
	private WebElement btnProceedToCheckout;
	
	public void validarInformacoesProduto(String nomeProduto) throws Exception {	
		Assert.assertEquals(nomeProduto, tituloProduto.getText());
		helper.setValorProduto(valorUnitario.getText().replace("$", ""));
		
	}
	
	public void inserirInformacoesDeCompra(String qtd, String tamanho) throws DocumentException {
		inputQuantity.clear();
		inputQuantity.sendKeys(qtd);
		evidencia.evidenciaPorPagina("Informações de Compra ");
		selecionarPorTexto(selectTamanho, tamanho);		
		WebElement cor = getDriver().findElement(By.xpath("//a[@name='White']"));	
		evidencia.evidenciaPorPagina("Informação de cor do produto ");
		cor.click();
		debug("==== Informações da compra do produto: "+qtd+", "+tamanho+" ====");
		btnAddToCart.click();
	}

	//SOBRECARGA
	public void inserirInformacoesDeCompra(int quantidade, String cor, String tamanho) throws DocumentException {
		inputQuantity.clear();
		inputQuantity.sendKeys(String.valueOf(quantidade));
		evidencia.evidenciaPorPagina("Inserindo informações de compra ");
		selecionarPorTexto(selectTamanho, tamanho);
		WebElement corElement = getDriver().findElement(By.xpath("//a[@name='"+cor+"']"));
		corElement.click();
		btnAddToCart.click();
		evidencia.evidenciaPorPagina("Após a compra ");
	}
	
	public void validarProdutoAdicionadoAoCarrinho(String nomeProduto) throws DocumentException {
		sleep(2000);
		Assert.assertEquals(nomeProduto, this.nomeProduto.getText());
		Assert.assertEquals(helper.getValorProduto(), precoUnitarioCarrinho.getText().replace("$", ""));
		helper.setValorFrete(valorFreteCarrinho.getText().replace("$", ""));
		float valorTotal = Float.parseFloat(valorTotalCarrinho.getText().replace("$", ""));
		helper.setValorTotal(String.valueOf(String.format("%.2f", valorTotal)));
		Assert.assertEquals(String.valueOf(helper.somarValorTotal(helper.getValorProduto(), helper.getValorFrete())), String.valueOf(valorTotal));
		evidencia.evidenciaPorPagina("Adicionando produto ");
		
		debug("===== "+nomeProduto+ "=====");
		
		btnProceedToCheckout.click();
	}

}
