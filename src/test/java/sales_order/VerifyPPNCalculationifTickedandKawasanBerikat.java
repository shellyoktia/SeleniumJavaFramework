package sales_order;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class VerifyPPNCalculationifTickedandKawasanBerikat {
	
	private static WebDriver driver = null;
	
		@BeforeTest
		public void setUpTest() {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		@Test
		public void firstTest() {
			Actions action = new Actions(driver);
			//ke url
			LoginPage.url_localhost(driver);
			
			//maximize window
			driver.manage().window().maximize();
			
			//login
			LoginPage.username_textbox(driver);
			LoginPage.password_textbox(driver);
			LoginPage.button_login(driver).click();
		
		    //ke halaman sales order
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
			driver.findElement(By.id("rootmenu-sales")).click();
			driver.findElement(By.linkText("Sales Order")).click();
	
		    //add new sales order
		    driver.findElement(By.name("add")).click();
		    
		    //input no sales order
		    driver.findElement(By.name("noSalesorder")).sendKeys("kawasan berikat");
		    
		    //input sales person
		    WebElement salesPerson = driver.findElement(By.xpath("//div[contains(@class, 'so-salesperson')]")); 
		    action.moveToElement(salesPerson).click().sendKeys("sujadi").build().perform();
		    driver.findElement(By.xpath("//li[contains(text(),'303 - Sujadi')]")).click(); 
		    
		    
		    //input customer
		    WebElement customer = driver.findElement(By.cssSelector(".so-customer .ant-select-selection__placeholder"));
		    action.moveToElement(customer).click().sendKeys("sukajadi").build().perform();
		    driver.findElement(By.xpath("//li[contains(text(),'SU566 - PT SUKAJADI SAWIT MEKAR')]")).click(); 
			  
		    //input billing address
		    driver.findElement(By.cssSelector(".so-bilingaddress .ant-select-selection__placeholder")).click();
		    driver.findElement(By.xpath("//li[contains(text(),'SPRING TOWER')]")).click();
		    
		    //input shipping address
		    driver.findElement(By.cssSelector(".so-shippingaddress .ant-select-selection__placeholder")).click();
		    driver.findElement(By.xpath("//li[contains(text(),'JALAN H.M. ARSYAD')]")).click();
		    
		    //add sales order line
		    driver.findElement(By.cssSelector(".fa-plus-circle")).click();
		    
		    //pilih tipe
		    driver.findElement(By.cssSelector(".sol-type .ant-select-selection__placeholder")).click();
		    driver.findElement(By.xpath("//li[contains(text(),'Box')]")).click();
		    
		    //pilih mc
		    driver.findElement(By.cssSelector(".sol-mastercard .ant-select-selection__placeholder")).click();
		    driver.findElement(By.xpath("//li[contains(text(),'9102 - 006087-R1 - CARTON M & M 1L (SNI-VITAMIN A)')]")).click();
		    
		    //quantity
		    String quantity = "59";
		    WebElement qty = driver.findElement(By.cssSelector(".sol-quantity .ant-input-number-input-wrap"));
		    action.moveToElement(qty).click().sendKeys(quantity).build().perform();
		    
		    //unit
		    driver.findElement(By.cssSelector(".sol-unitmeasurement .ant-select-selection__rendered")).click();
		    driver.findElement(By.xpath("//li[contains(text(),'pcs')]")).click();
		    
		    //harga
		    String price = "190000";
		    WebElement harga = driver.findElement(By.cssSelector(".sol-unitprice .ant-input-number-input-wrap"));
		    action.moveToElement(harga).click().sendKeys(price).build().perform();
		    
		    //tanggal
		    driver.findElement(By.cssSelector(".sol-deliverydate .ant-calendar-picker-input")).click();
		    driver.findElement(By.cssSelector(".ant-calendar-today > .ant-calendar-date")).click();
		    
		    //save sales order line
		    driver.findElement(By.cssSelector(".ant-row-flex > .ant-btn")).click();
		    
		    //verify if kawasan berikat is ticked
		    WebElement kwsn = driver.findElement(By.cssSelector(".so-checkbox-iskawasan .ant-checkbox-input"));
		    boolean isSelected = kwsn.isSelected();
		    
		    //ambil nilai ppn
		    String actual_ppn = driver.findElement(By.cssSelector(".so-ppn .ant-input-number-input")).getAttribute("value");
		    System.out.println("actual ppn : " + actual_ppn);
		    
		    String expected_ppn = "0";
		    
			//performing click operation if element is not checked
			if(isSelected == false) {
				  System.out.println("ini tidak dicentang");
			}else if(isSelected == true){
				  assertEquals(actual_ppn, expected_ppn);
				  System.out.println("ini dicentang");
			}
		}
		
		@AfterTest
		public void tearDownTest() {
			driver.close();
		}
	
}
