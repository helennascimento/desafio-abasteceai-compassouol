package pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.itextpdf.text.DocumentException;

import beans.Evidence;

public class LoginPage extends BasePage {
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	 Evidence evidence = new Evidence();
	
	@FindBy(xpath="//h1[text()='Authentication']")
	private WebElement titleAuthentication;
	
	@FindBy(id="email")
	private WebElement inputEmail;
	
	@FindBy(name="passwd")
	private WebElement inputSenha;
	
	@FindBy(id="SubmitLogin")
	private WebElement btnSignIn;
	
	public void validarSignInPage() {
		assertTrue(titleAuthentication.isDisplayed());
	}
	
	@SuppressWarnings("static-access")
	public void loginComDadosValidos(String email, String senha) throws DocumentException, InterruptedException{
		System.out.println("=====\n Inicialização de login \n=====");
		evidence.evidenciaPorPagina("Login ");
		inputEmail.sendKeys(email);
		evidence.evidenciaPorPagina("Inserindo dados para acesso");
		inputSenha.sendKeys(senha);
		evidence.evidenciaPorPagina("Dados inserido com sucesso");
		btnSignIn.click();
		
		
	}	
	
	
}
