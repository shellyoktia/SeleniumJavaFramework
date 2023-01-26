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

public class Sales_PPN_93 {
	private static WebDriver driver = null;
	String quantity = "5";
    String price = "73000";
    String ongkos_printing = "10000";
    String komisi_cust = "2000";
    String quantity2 = "10";
    String price2 = "7000";
    String ongkos_printing2 = "5000";
    String komisi_cust2 = "3000";
    
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
	    
	    //save sales order line
	    driver.findElement(By.cssSelector(".ant-row-flex > .ant-btn")).click();
	    
	    //tambah item
	    driver.findElement(By.xpath("//span[contains(text(),'tambah item')]")).click();
	    
	    //pilih tipe
	    driver.findElement(By.cssSelector(".sol-type .ant-select-selection__placeholder")).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Box')]")));
	    driver.findElement(By.xpath("//li[contains(text(),'Box')]")).click();
	    
	    //pilih mc
	    driver.findElement(By.cssSelector(".sol-mastercard .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'897 - 000638R01 - BOX-KBOX WD12 NW 001628. CDI.18')]")).click();
	  
	    //quantity
	    WebElement qty2 = driver.findElement(By.cssSelector(".sol-quantity .ant-input-number-input-wrap"));
	    action.moveToElement(qty2).click().sendKeys(quantity2).build().perform();
	    
	    //unit
	    driver.findElement(By.cssSelector(".sol-unitmeasurement .ant-select-selection__rendered")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'pcs')]")).click();
	    
	    //harga
	    WebElement harga2 = driver.findElement(By.cssSelector(".sol-unitprice .ant-input-number-input-wrap"));
	    action.moveToElement(harga2).click().sendKeys(price2).build().perform();
	    
	    //ongkos printing
	    WebElement ongkosPrinting2 = driver.findElement(By.cssSelector(".sol-unitserviceprice .ant-input-number-input"));
	    action.moveToElement(ongkosPrinting2).click().sendKeys(ongkos_printing2).build().perform();
	    
	    //komisi cust.
	    WebElement KomisiCust2 = driver.findElement(By.cssSelector(".sol-customercomission .ant-input-number-input"));
	    action.moveToElement(KomisiCust2).click().sendKeys(komisi_cust2).build().perform();
	    
	    //tanggal
	    driver.findElement(By.cssSelector(".sol-deliverydate .ant-calendar-picker-input")).click();
	    driver.findElement(By.cssSelector(".ant-calendar-today > .ant-calendar-date")).click();
	    
	    //save sales order line
	    driver.findElement(By.cssSelector(".ant-row-flex > .ant-btn")).click();
	    
	    //pph23
	    
	    //verify
	    int qtySOL1 = Integer.parseInt(quantity);
	    int qtySOL2 = Integer.parseInt(quantity2);
	    int ongkosPrintingSOL1 = Integer.parseInt(ongkos_printing);
	    int ongkosPrintingSOL2 = Integer.parseInt(ongkos_printing2);
	    int PPH23 = (int) (((qtySOL1 * ongkosPrintingSOL1) + (qtySOL2 * ongkosPrintingSOL2)) * 0.02);
	    String expectedPPH23 = Integer.toString(PPH23);
	    System.out.println("Expected PPH23 : " + expectedPPH23);
//	    assertEquals(actualTotalHarga, expectedTotalHarga);
	    
	}
	@AfterTest
	public void tearDownTest() {    
		//driver.close();
	}
}
