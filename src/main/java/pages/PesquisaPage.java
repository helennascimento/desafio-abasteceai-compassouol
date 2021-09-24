package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import beans.Evidence;

public class PesquisaPage extends BasePage {
	
	public PesquisaPage () {
		PageFactory.initElements(driver, this);
	}
	
	Evidence evidencia = new Evidence();
	
	public void validacaoDePesquisa (String produto) throws DocumentException {
		evidencia.evidenciaPorPagina("Validação da Pesquisa ");
		String pesquisa = driver.findElement(By.xpath("//h5[@itemprop = 'name']//a[@class='product-name'][contains(.,'"+produto+"')]")).getText();
		Assert.assertEquals(pesquisa, produto);
		debug("====== Validação da Pesquisa ======");
	}
}
