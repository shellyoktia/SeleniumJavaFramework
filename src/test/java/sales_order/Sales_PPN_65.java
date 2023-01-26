package sales_order;

import static org.testng.Assert.assertEquals;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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

public class Sales_PPN_65 {
	private static WebDriver driver = null;
	String quantity = "5";
    String incusive_ppn = "70000";
    double expected_harga;
    double expectedSubtotal;
    int expectedPPN;
    private static final DecimalFormat decfor = new DecimalFormat("0.00");  

	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
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
	    driver.findElement(By.name("noSalesorder")).sendKeys("tes satu");
	    
	    //input sales person
	    WebElement salesPerson = driver.findElement(By.xpath("//div[contains(@class, 'so-salesperson')]")); 
	    action.moveToElement(salesPerson).click().sendKeys("sujadi").build().perform();
	    driver.findElement(By.xpath("//li[contains(text(),'303 - Sujadi')]")).click(); 
	    
	    
	    //input customer
	    WebElement customer = driver.findElement(By.cssSelector(".so-customer .ant-select-selection__placeholder"));
	    action.moveToElement(customer).click().sendKeys("beauty").build().perform();
	    driver.findElement(By.xpath("//li[contains(text(),'BE631 - PT BEAUTY KASATAMA INDONESIA')]")).click(); 
	      
	    //input billing address
	    driver.findElement(By.cssSelector(".so-bilingaddress .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'RAYA DARMO')]")).click();
	    
	    //input shipping address
	    driver.findElement(By.cssSelector(".so-shippingaddress .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'PERGUDANGAN ROMOKALISARI BLOK A-5')]")).click();
	    
	    //tick "inclusive ppn"
	    driver.findElement(By.xpath("//input[@name = 'isPpnInclusive' and @value = 'true']")).click();
	    
	    //add sales order line
	    driver.findElement(By.cssSelector(".fa-plus-circle")).click();
	    
	    //pilih tipe
	    driver.findElement(By.cssSelector(".sol-type .ant-select-selection__placeholder")).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Box')]")));
	    driver.findElement(By.xpath("//li[contains(text(),'Box')]")).click();
	    
	    //pilih mc
	    driver.findElement(By.cssSelector(".sol-mastercard .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'5157 - 003944 - BOX VIO PRINTING BARU (340) - K125')]")).click();
	    
	    //harga (inclusive ppn)
	    WebElement inclusivePpn = driver.findElement(By.cssSelector(".sol-inclusiveprice .ant-input-number-input-wrap"));
	    action.moveToElement(inclusivePpn).click().sendKeys(incusive_ppn).build().perform();
	    
	    //quantity
	    WebElement qty = driver.findElement(By.cssSelector(".sol-quantity .ant-input-number-input-wrap"));
	    action.moveToElement(qty).click().sendKeys(quantity).build().perform();
	    
	    //unit
	    driver.findElement(By.cssSelector(".sol-unitmeasurement .ant-select-selection__rendered")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'pcs')]")).click();
	    
	   
	    
	    //tanggal
	    driver.findElement(By.cssSelector(".sol-deliverydate .ant-calendar-picker-input")).click();
	    driver.findElement(By.cssSelector(".ant-calendar-today > .ant-calendar-date")).click();
	    
	    
	}
	
	@Test (priority = 1)
	public void firstTest() {
		//ambil dan verify nilai "harga"
	    String actualHarga = driver.findElement(By.cssSelector(".sol-unitprice .ant-input-number-input")).getAttribute("value");
	    System.out.println("Actual Harga : " +  actualHarga);
	    double inclusivePPN = Double.parseDouble(incusive_ppn);
	    expected_harga = inclusivePPN / 1.11;
	    String ExpectedHarga = Double.toString(expected_harga);
	    System.out.println("Expected Harga : " + ExpectedHarga);
	    
	    assertEquals(actualHarga, ExpectedHarga);
	    
	}
	@Test (priority = 2)
	public void secondTest() {
		//ambil dan verify "total harga"
	    String actualTotalHarga = driver.findElement(By.cssSelector(".sol-totalamount .ant-input-number-input")).getAttribute("value");
	    System.out.println("Actual Total Harga : " + actualTotalHarga);
	    int qty = Integer.parseInt(quantity);
	    double expected_totalHarga = qty * expected_harga;
	    String ExpectedTotalHarga = Double.toString(expected_totalHarga);
	    System.out.println("Expected Total Harga : " + ExpectedTotalHarga);
	    
	    assertEquals(actualTotalHarga, ExpectedTotalHarga);
	    
		//save sales order line
	    driver.findElement(By.cssSelector(".ant-row-flex > .ant-btn")).click();
		
	}
	@Test (priority = 3)
	public void thirdTest() {
	    //ambil dan verify nilai subtotal
	    String actual_subtotal = driver.findElement(By.cssSelector(".so-subtotal .ant-input-number-input")).getAttribute("value");
	    System.out.println("actual subtotal : " + actual_subtotal);
	    
	    int qty_order = Integer.parseInt(quantity);
	    expectedSubtotal = qty_order * expected_harga;
	    String expected_subtotal = Double.toString(expectedSubtotal);
	    System.out.println("expected subtotal : " + expected_subtotal);
	    assertEquals(actual_subtotal, expected_subtotal);
	}
	
	@Test (priority = 4)
	public void fourthTest() {
		//ambil dan verify nilai ppn
	    String actual_ppn = driver.findElement(By.cssSelector(".so-ppn .ant-input-number-input")).getAttribute("value");
	    System.out.println("actual ppn : " + actual_ppn);
	    
	    expectedPPN = (int) ((expectedSubtotal * 11) / 100);
	    //expectedPPN = decfor.setRoundingMode(expectedPPN.UP);  
	    String expected_ppn = Integer.toString(expectedPPN);
	    System.out.println("expected ppn : " + expected_ppn);
	    //assertEquals(actual_ppn, expected_ppn);
	}
	@Test (priority = 5)
	public void fifthTest() {
		//ambil dan verify nilai jumlah total
		String jumlahTotal = driver.findElement(By.cssSelector("div.container main.layout section.content:nth-child(3) div.canvas div.canvas--content.add-edit-view-data form.data-form div.ant-row-flex.ant-row-flex-start.ant-row-flex-top:nth-child(7) > div.ant-col.ant-col-7")).getText();
		String jumlah = jumlahTotal.replace(".", "");
		jumlah = jumlah.replace(",", ".");
	    String actual_jumlahTotal = jumlah.substring(15);
		System.out.println("actual jumlah total : " + actual_jumlahTotal);
		
		float expectedJumlahTotal = (float) (expectedSubtotal + expectedPPN);
		String expected_jumlahtotal = Float.toString(expectedJumlahTotal);
		//expected_jumlahtotal = decfor.format(expected_jumlahtotal);
		System.out.println("expected jumlah total : " + expected_jumlahtotal);
		//assertEquals(actual_jumlahTotal, expected_jumlahtotal);
	}
	

	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}
}
