package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import pages.LoginPage;
import pages.VariableGlobalMain;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Variableglobal {
	WebDriver driver = null;
	String usn = "rcti oke";
	
	@BeforeTest
	public void setUpTest() {
		//System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@Test
	public void mainTest() {		
		driver.get("https://instagram.com");
		
		VariableGlobalMain.globalVariable = usn;
		//driver.findElement(By.name("q")).sendKeys(usn);
	}
	
	@AfterTest
	public void tearDownTest() {
		driver.close();
	}

}
