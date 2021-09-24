package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import beans.Evidence;
import config.Driver;

public class BasePage {
	
	@FindBy(xpath="//img[contains(@class, 'logo')]")
	private WebElement logo;
	
	public static WebDriver driver = Driver.getDriver();
	
	
	
	public void acessarUrl() {
		
		driver.get("http://automationpractice.com/index.php");
	}

	public void acessarUrl(String url) {
		driver.get(url);
	}
	
	public void selecionarPorValue(WebElement elemento, String valor) {
		Select selectDateDays = new Select(elemento);
		selectDateDays.selectByValue(valor);
	}
	
	public void selecionarPorTexto(WebElement elemento, String texto) {
		Select selectDateDays = new Select(elemento);
		selectDateDays.selectByVisibleText(texto);
	}
	
	public void selecionarPrimeiro(WebElement elemento) {
		Select select = new Select(elemento);
		select.selectByIndex(0);
	}
	
	public void voltarParaHome(){
		logo.click();
	}
	
	public void sleep (int milissegundos) {
		try {
			Thread.sleep(milissegundos);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void descerAteOTexto(String texto) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.partialLinkText(texto)));
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,285)");
    }
	


}
