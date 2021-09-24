package runner;

import org.junit.Before;

import beans.Evidence;
import config.Driver;
import cucumber.api.java.After;
import pages.HomePage;

public class Hooks extends Driver {
	
	HomePage home = new HomePage();
	
	
	@Before
	public void setup () {
		getDriver();
		home.acessarUrl();
		
	}
	
	@After
	public void tearDown() {
		finish();
	}
	
	
}
