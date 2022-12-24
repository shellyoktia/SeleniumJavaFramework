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

public class VerifyValueOfExpenseperkg {
	private static WebDriver driver = null;
	String quantity = "100";
    String price = "9250";
    
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
	    action.moveToElement(customer).click().sendKeys("beauty").build().perform();
	    driver.findElement(By.xpath("//li[contains(text(),'BE631 - PT BEAUTY KASATAMA INDONESIA')]")).click(); 
	      
	    //input billing address
	    driver.findElement(By.cssSelector(".so-bilingaddress .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'RAYA DARMO')]")).click();
	    
	    //input shipping address
	    driver.findElement(By.cssSelector(".so-shippingaddress .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'PERGUDANGAN ROMOKALISARI BLOK A-5')]")).click();
	    
	    //add sales order line
	    driver.findElement(By.cssSelector(".fa-plus-circle")).click();
	    
	    //pilih tipe
	    driver.findElement(By.cssSelector(".sol-type .ant-select-selection__placeholder")).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Box')]")));
	    driver.findElement(By.xpath("//li[contains(text(),'Box')]")).click();
	    
	    //pilih mc
	    driver.findElement(By.cssSelector(".sol-mastercard .ant-select-selection__placeholder")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'5157 - 003944 - BOX VIO PRINTING BARU (340) - K125')]")).click();
	    
	    //quantity
	    WebElement qty = driver.findElement(By.cssSelector(".sol-quantity .ant-input-number-input-wrap"));
	    action.moveToElement(qty).click().sendKeys(quantity).build().perform();
	    
	    //unit
	    driver.findElement(By.cssSelector(".sol-unitmeasurement .ant-select-selection__rendered")).click();
	    driver.findElement(By.xpath("//li[contains(text(),'pcs')]")).click();
	    
	    //harga
	    WebElement harga = driver.findElement(By.cssSelector(".sol-unitprice .ant-input-number-input-wrap"));
	    action.moveToElement(harga).click().sendKeys(price).build().perform();
	    
	    //tanggal
	    driver.findElement(By.cssSelector(".sol-deliverydate .ant-calendar-picker-input")).click();
	    driver.findElement(By.cssSelector(".ant-calendar-today > .ant-calendar-date")).click();
	    
	    //cost of paper kg
	    String cost_of_paper = driver.findElement(By.cssSelector(".sol-cop  .ant-input-number-input")).getAttribute("value");
	    System.out.println("Cost of Paper : " + cost_of_paper);
	    
	    //selling price kg
	    String selling_price = driver.findElement(By.cssSelector(".sol-sellingpricekg .ant-input-number-input")).getAttribute("value");
	    System.out.println("Selling Price : " + selling_price);
	    
	    //expense per kg
	    String actualExpensePerKg = driver.findElement(By.cssSelector(".sol-expensekg .ant-input-number-input")).getAttribute("value");
	    System.out.println("Actual Expense per Kg : " + actualExpensePerKg);
	    
	    //verify
	    float costOfPaper = Float.parseFloat(cost_of_paper);
	    float sellingPrice = Float.parseFloat(selling_price);
	    float expensePerKg = sellingPrice - costOfPaper;
	    String expectedExpensePerKg = Float.toString(expensePerKg);
	    System.out.println("Expected Expense per Kg : " + expectedExpensePerKg);
	    assertEquals(actualExpensePerKg, expectedExpensePerKg);
	}
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}
}
