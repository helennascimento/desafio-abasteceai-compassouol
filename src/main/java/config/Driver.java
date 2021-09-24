package config;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

	private static WebDriver driver;
	private static final boolean HEADLESS = true;
	private static final String CHROME_DRIVER_DIRECTORY = "webdriver.chrome.driver";

	
	//TODO - Execu��o via tela - Para executar basta tirar coment�rio da linha 19 � 27 se tiver comentada!
	public static WebDriver getDriver() {
		if (driver == null) {
			System.setProperty(CHROME_DRIVER_DIRECTORY, "src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}

	//TODO - Execu��o Via Headless - Para executar basta tirar coment�rio da linha 30 � 42 se tiver comentada!
//	 public static WebDriver getDriver() {
//		 if (driver == null) {
//			 System.setProperty(CHROME_DRIVER_DIRECTORY,"src/test/resources/drivers/chromedriver.exe");
//			 
//	 }
//		 ChromeOptions chromeOptions = new ChromeOptions();
//		 if (HEADLESS) {
//			 chromeOptions.addArguments("--headless");
//			
//	 }
//		 
//		 return driver = new ChromeDriver(chromeOptions);
//
//	 }

	public static void finish() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
