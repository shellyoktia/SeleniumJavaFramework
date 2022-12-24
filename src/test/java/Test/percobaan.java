package Test;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class percobaan {

	private static WebDriver driver = null;

	@BeforeTest
	public void setUpTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		//ke url
		LoginPage.url_localhost(driver);
		
		//maximize window
		driver.manage().window().maximize();
		
		//login
		LoginPage.username_textbox(driver);
		LoginPage.password_textbox(driver);
		LoginPage.button_login(driver).click();
		
		String outkrts, corr, beratbox, beratorder, berattrim, BeratSubstance1, BeratSubstance2, BeratSubstance3, BeratSubstance4, BeratSubstance5;
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));

		driver.findElement(By.id("rootmenu-production")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Work Order (SPK)")));
		driver.findElement(By.linkText("Work Order (SPK)")).click();
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("noWorkorder")).sendKeys("2210.0123.SPK");
		driver.findElement(By.name("submit")).click();
		Thread.sleep(100);
	    driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]/a[1]")).click();
		
		String noSPKset = driver.findElement(By.name("noWorkorderGrouped")).getAttribute("value");
		outkrts = driver.findElement(By.cssSelector(".wo_out input")).getAttribute("value");
		corr = driver.findElement(By.cssSelector(".wo_corr input")).getAttribute("value");
		beratbox = driver.findElement(By.cssSelector(".wo_weightperkg input")).getAttribute("value");
		beratorder = driver.findElement(By.cssSelector(".wo_weight input")).getAttribute("value");
		berattrim = driver.findElement(By.cssSelector(".wo_trimweight input")).getAttribute("value");
		BeratSubstance1 = driver.findElement(By.cssSelector(".wo_gsmweight1 input")).getAttribute("value");
		BeratSubstance2 = driver.findElement(By.cssSelector(".wo_gsmweight2 input")).getAttribute("value");
		BeratSubstance3 = driver.findElement(By.cssSelector(".wo_gsmweight3 input")).getAttribute("value");
		BeratSubstance4 = driver.findElement(By.cssSelector(".wo_gsmweight4 input")).getAttribute("value");
		BeratSubstance5 = driver.findElement(By.cssSelector(".wo_gsmweight5 input")).getAttribute("value");
		driver.findElement(By.xpath("//button[@name='back-button']")).click();
		
		System.out.println("no spk set" + noSPKset);
		System.out.println("out kertas " + outkrts);
		System.out.println("corr " + corr);
		System.out.println("berat box" + beratbox);
		System.out.println("berat order" + beratorder);
		System.out.println("berat trim" + berattrim);
		System.out.println("berat substance 1 " + BeratSubstance1);
		System.out.println("berat substance 2 " + BeratSubstance2);
		System.out.println("berat substance 3 " + BeratSubstance3);
		System.out.println("berat substance 4 " + BeratSubstance4);
		System.out.println("berat substance 5 " + BeratSubstance5);
		    		
		}

			
	
	
	@Test
	public void firstTest() throws InterruptedException {
		assertEquals(4, 4);
	    	}
	
	
	@AfterTest
	public void tearDownTest() {    
		driver.quit();
	}

}
