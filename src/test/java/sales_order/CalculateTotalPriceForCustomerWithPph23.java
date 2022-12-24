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

public class CalculateTotalPriceForCustomerWithPph23 {
	private static WebDriver driver = null;
	String quantity = "5";
    String price = "73000";
    String ongkos_printing = "10000";
    String komisi_cust = "2000";
    
	@BeforeTest
	public void setUpTest() {
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
	}
	@Test
	public void firstTest() throws InterruptedException {
		Actions action = new Actions(driver);
		
	    //ke halaman sales order
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
		driver.findElement(By.id("rootmenu-sales")).click();
		driver.findElement(By.linkText("Sales Order")).click();

	    //add new sales order
	    driver.findElement(By.name("add")).click();
	    
	    //input no sales order
	    driver.findElement(By.name("noSalesorder")).sendKeys("tes satu");
	    
	    //input sales person
	    WebElement salesPerson = driver.findElement(By.xpath("//div[contains(@class, 'so-salesperson')]")); 
	    action.moveToElement(salesPerson).click().sendKeys("sujadi").build().perform();
	    driver.findElement(By.xpath("//li[contains(text(),'303 - Sujadi')]")).click(); 
	    
	    //input customer
	    WebElement customer = driver.findElement(By.cssSelector(".so-customer .ant-select-selection__placeholder"));
	    action.moveToElement(customer).click().sendKeys("gelora").build().perform();
	    driver.findElement(By.xpath("//li[contains(text(),'PT GELORA DJAJA')]")).click(); 
	      
	    //input billing address
	    driver.findElement(By.cssSelector(".so-bilingaddress .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'BUNTARAN')]")).click();
	    
	    //input shipping address
	    driver.findElement(By.cssSelector(".so-shippingaddress .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//label[contains(text(),'Default Shipping Address')]/following::li[contains(text(),'BUNTARAN')][2]")).click();
	    
	    //add sales order line
	    driver.findElement(By.cssSelector(".fa-plus-circle")).click();
	    
	    //pilih tipe
	    driver.findElement(By.cssSelector(".sol-type .ant-select-selection__placeholder")).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Box')]")));
	    driver.findElement(By.xpath("//li[contains(text(),'Box')]")).click();
	    
	    //pilih mc
	    driver.findElement(By.cssSelector(".sol-mastercard .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'897 - 000638R01 - BOX-KBOX WD12 NW 001628. CDI.18')]")).click();
	  
	    //quantity
	    WebElement qty = driver.findElement(By.cssSelector(".sol-quantity .ant-input-number-input-wrap"));
	    action.moveToElement(qty).click().sendKeys(quantity).build().perform();
	    
	    //unit
	    driver.findElement(By.cssSelector(".sol-unitmeasurement .ant-select-selection__rendered")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'pcs')]")).click();
	    
	    //harga
	    WebElement harga = driver.findElement(By.cssSelector(".sol-unitprice .ant-input-number-input-wrap"));
	    action.moveToElement(harga).click().sendKeys(price).build().perform();
	    
	    //ongkos printing
	    WebElement ongkosPrinting = driver.findElement(By.cssSelector(".sol-unitserviceprice .ant-input-number-input"));
	    action.moveToElement(ongkosPrinting).click().sendKeys(ongkos_printing).build().perform();
	    
	    //komisi cust.
	    WebElement KomisiCust = driver.findElement(By.cssSelector(".sol-customercomission .ant-input-number-input"));
	    action.moveToElement(KomisiCust).click().sendKeys(komisi_cust).build().perform();
	    
	    //tanggal
	    driver.findElement(By.cssSelector(".sol-deliverydate .ant-calendar-picker-input")).click();
	    driver.findElement(By.cssSelector(".ant-calendar-today > .ant-calendar-date")).click();
	    
	    //total harga
	    String actualTotalHarga = driver.findElement(By.cssSelector(".sol-totalamount .ant-input-number-input")).getAttribute("value");
	    System.out.println("Actual Total Harga : " + actualTotalHarga);
	    
	    //verify
	    int qtySOL = Integer.parseInt(quantity);
	    int hargaSOL = Integer.parseInt(price);
	    int ongkosPrintingSOL = Integer.parseInt(ongkos_printing);
	    int totalHargaSOL = (hargaSOL * qtySOL) + (ongkosPrintingSOL * qtySOL);
	    String expectedTotalHarga = Integer.toString(totalHargaSOL);
	    System.out.println("Expected Total Harga : " + expectedTotalHarga);
	    assertEquals(actualTotalHarga, expectedTotalHarga);
	    
	}
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}
}
