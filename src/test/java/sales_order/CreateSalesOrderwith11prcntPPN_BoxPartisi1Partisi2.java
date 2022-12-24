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
import pages.ObjectRepository_SalesOrder;
import pages.VariableGlobalMain;

public class CreateSalesOrderwith11prcntPPN_BoxPartisi1Partisi2 {

	private static WebDriver driver = null;
    String noSalesOrder = "11nov2022";
	String quantity = "150";
    String price_box = "9880";
    String price_partisi = "9800";
    int expectedSubtotal = 0;
    int expectedPPN = 0;
    String qty_per_set_partisi1 = "2";
    String qty_per_set_partisi2 = "3";
    
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
		
		
		VariableGlobalMain.qtypersetBoxPartisi_partisi1 = qty_per_set_partisi1;
		VariableGlobalMain.qtypersetBoxPartisi_partisi2 = qty_per_set_partisi2;
	
	    //ke halaman sales order
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
		driver.findElement(By.id("rootmenu-sales")).click();
		driver.findElement(By.linkText("Sales Order")).click();

	    //add new sales order
	    driver.findElement(By.name("add")).click();
	    
	    //input no sales order
	    driver.findElement(By.name("noSalesorder")).sendKeys(noSalesOrder);
	    VariableGlobalMain.noSalesOrderBoxPartisi = noSalesOrder;
	    
	    //input sales person
	    WebElement salesPerson = driver.findElement(By.xpath("//div[contains(@class, 'so-salesperson')]")); 
	    action.moveToElement(salesPerson).click().sendKeys("sujadi").build().perform();
	    driver.findElement(By.xpath("//li[contains(text(),'303 - Sujadi')]")).click(); 
	    
	    
	    //input customer
	    WebElement customer = driver.findElement(By.cssSelector(".so-customer .ant-select-selection__placeholder"));
	    action.moveToElement(customer).click().sendKeys("jayamas").build().perform();
	    driver.findElement(By.xpath("//li[contains(text(),'JA186 - PT JAYAMAS MEDICA INDUSTRI')]")).click(); 
	      
	    //input billing address
	    driver.findElement(By.cssSelector(".so-bilingaddress .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'01 KRIAN')]")).click();
	    
	    //input shipping address
	    driver.findElement(By.cssSelector(".so-shippingaddress .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'SIDOMOJO RT 05 RW 01')]")).click();
	    
	    //add sales order line
	    driver.findElement(By.cssSelector(".fa-plus-circle")).click();
	    
	    //pilih tipe
	    driver.findElement(By.cssSelector(".sol-type .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'Box')]")).click();
	    
	    //pilih mc
	    driver.findElement(By.cssSelector(".sol-mastercard .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'1402 - 001076 - BOX USG 250cc (K0173) - WK150/M125')]")).click();
	    
	    //quantity
	    WebElement qty = driver.findElement(By.cssSelector(".sol-quantity .ant-input-number-input-wrap"));
	    action.moveToElement(qty).click().sendKeys(quantity).build().perform();
	    VariableGlobalMain.qtySalesOrderBoxPartisi = quantity;
	    
	    //unit
	    driver.findElement(By.cssSelector(".sol-unitmeasurement .ant-select-selection__rendered")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'pcs')]")).click();
	    
	    //harga
	    //WebElement harga = driver.findElement(By.cssSelector(".sol-unitprice .ant-input-number-input"));
	    //action.moveToElement(harga).click().sendKeys(price_box).build().perform();
	    ObjectRepository_SalesOrder.harga(driver).click();
	    ObjectRepository_SalesOrder.harga(driver).sendKeys(price_box);
	    
	    //tanggal
	    driver.findElement(By.cssSelector(".sol-deliverydate .ant-calendar-picker-input")).click();
	    driver.findElement(By.cssSelector(".ant-calendar-today > .ant-calendar-date")).click();
	    
	    //save sales order line
	    driver.findElement(By.cssSelector(".ant-row-flex > .ant-btn")).click();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	    
	    //edit price so line partisi
	    driver.findElement(By.xpath("//tbody/tr[2]/td[1]/span[1]/a[1]")).click();
	    
	    //input harga partisi
	    ObjectRepository_SalesOrder.harga(driver).click();
	    ObjectRepository_SalesOrder.harga(driver).sendKeys(price_partisi);
	    
	    //save sales order line partisi
	    driver.findElement(By.cssSelector(".ant-row-flex > .ant-btn")).click();
	}
	
	@Test (priority = 1)
	public void firstTest() {
	    //ambil dan verify nilai subtotal
	    String actual_subtotal = driver.findElement(By.cssSelector(".so-subtotal .ant-input-number-input")).getAttribute("value");
	    System.out.println("actual subtotal : " + actual_subtotal);
	    
	    int qty_order = Integer.parseInt(quantity);
	    int harga_item_box = Integer.parseInt(price_box);
	    int harga_item_partisi = Integer.parseInt(price_partisi);
	    int subtotalBox = qty_order * harga_item_box;
	    int subtotalPartisi = qty_order * harga_item_partisi;
	    expectedSubtotal = subtotalBox + subtotalPartisi;
	    String expected_subtotal = Integer.toString(expectedSubtotal);
	    System.out.println("expected subtotal : " + expected_subtotal);
	    assertEquals(actual_subtotal, expected_subtotal);
	}
	@Test (priority = 2)
	public void secondTest() {
	    
	    //ambil dan verify nilai ppn
	    String actual_ppn = driver.findElement(By.cssSelector(".so-ppn .ant-input-number-input")).getAttribute("value");
	    System.out.println("actual ppn : " + actual_ppn);
	    
	    expectedPPN = (expectedSubtotal * 11) / 100;
	    String expected_ppn = Integer.toString(expectedPPN);
	    System.out.println("expected ppn : " + expected_ppn);
	    assertEquals(actual_ppn, expected_ppn);
	    
	}
	
	@Test (priority = 3)
	public void thirdTest() {
		
		//ambil dan verify nilai jumlah total
		String jumlahTotal = driver.findElement(By.cssSelector("div.container main.layout section.content:nth-child(3) div.canvas div.canvas--content.add-edit-view-data form.data-form div.ant-row-flex.ant-row-flex-start.ant-row-flex-top:nth-child(7) > div.ant-col.ant-col-7")).getText();
		String jumlah = jumlahTotal.replace(".", "");
	    String actual_jumlahTotal = jumlah.substring(15);
		System.out.println("actual jumlah total : " + actual_jumlahTotal);
		
		int expectedJumlahTotal = expectedSubtotal + expectedPPN;
		String expected_jumlahtotal = Integer.toString(expectedJumlahTotal);
		System.out.println("expected jumlah total : " + expected_jumlahtotal);
		assertEquals(actual_jumlahTotal, expected_jumlahtotal);
		driver.findElement(By.name("save-btn")).click();
		
	}
	
	@AfterTest
	public void tearDownTest() {    
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.elementToBeClickable(By.name("add")));
		driver.quit();
	}

}
