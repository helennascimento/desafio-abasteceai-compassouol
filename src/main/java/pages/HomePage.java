package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import beans.Evidence;

public class HomePage extends BasePage {
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	Evidence evidencia = new Evidence();
	
	@FindBy(id = "search_query_top")
	private WebElement search;
	
	@FindBy(xpath = "//button[@class ='btn btn-default button-search']")
	private WebElement btnBuscar;
	
	@FindBy(className = "login")
	private WebElement btnSignIn;
	
	@FindBy(xpath="//div[@id='layer_cart']//h2[contains(., 'Product successfully added to your shopping cart')]")
	private WebElement lblProdutoAdicionado;
	
	@FindBy(xpath = "//span[ends-with(text(), 'Proceed to checkout')]")
	private WebElement btnProceedCheckout;
	
	
	public void validarAcesso() {
		debug("====== HOME- MY STORE ======");
		assertEquals("My Store", driver.getTitle());
	}
	
	public void pesquisarProduto (String procuraDeProduto) throws DocumentException {
		search.sendKeys(procuraDeProduto);
		evidencia.evidenciaPorPagina("Pesquisa do produto ");
		sleep(2000);
		debug("=== Pesquisa do Produto "+procuraDeProduto+" ====");
				
	}
	
	public void botaoBuscar() {
		btnBuscar.click();
	}
	
	public void acessarLogin() {
		btnSignIn.click();
	}

	public void clickBtnAddToCart(String nomeDoProduto) throws DocumentException{
		evidencia.evidenciaPorPagina("Adicionando produto no card ");
		WebElement nomeProduto = driver.findElement(By.xpath("(//h5[@itemprop='name']//a[contains(text(), '"+nomeDoProduto+"')])[1]"));
		debug("==== Adicionando Card "+nomeDoProduto+" ====");
		nomeProduto.click();
	}
	
	public void validarItemAdicionadoCarrinho(){
		assertTrue(lblProdutoAdicionado.isDisplayed());
	}
	
	public void clickProcederCheckout(){
		btnProceedCheckout.click();
	}

}
