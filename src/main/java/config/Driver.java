package config;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

	private static WebDriver driver;
	private static final String CHROME_DRIVER_DIRECTORY = "webdriver.chrome.driver";
	private static final String FIREFOX_DRIVER_DIRECTORY = "webdriver.gecko.driver";

	
	//TODO - Execução via Headless ir em Propriedades e direcionar o Browser 
	public static WebDriver getDriver() {
		
		if (driver == null) {
			switch (Propriedades.browser) {
			case CHROME:
				System.setProperty(CHROME_DRIVER_DIRECTORY, "src/test/resources/drivers/chromedriver.exe");
				driver = new ChromeDriver();				
				break;
				
			case FIREFOX:
				System.setProperty(FIREFOX_DRIVER_DIRECTORY, "src/test/resources/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				break;
				
			case HEADLESS:
				ChromeOptions chromeOptions = new ChromeOptions();
				System.setProperty(CHROME_DRIVER_DIRECTORY,"src/test/resources/drivers/chromedriver.exe");
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
				
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		}
		
		return driver;
	}

	

	public static void finish() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
