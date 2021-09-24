package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import beans.Evidence;

public class MinhaContaPage extends BasePage {
	
	public MinhaContaPage() {
		PageFactory.initElements(driver, this);
	}
	Evidence evidencia = new Evidence();
	
	@FindBy(xpath="//h1[text()='My account']")
	private WebElement titleMyAccount;
	
	@FindBy(className="info-account")
	private WebElement lblWelcomeAccount;
	
	public void validarMinhaConta() throws DocumentException{
		System.out.println("=====\n Validação de minha conta \n=====");
		evidencia.evidenciaPorPagina("Minha Conta ");
		assertTrue(titleMyAccount.isDisplayed());
	}
	
	public void validarMensagemWelcome() throws DocumentException{
		System.out.println("=====\n Welcome \n=====");
		evidencia.evidenciaPorPagina("Welcome ");
		assertEquals(lblWelcomeAccount.getText(), "Welcome to your account. Here you can manage all of your personal information and orders.");
	}
}

