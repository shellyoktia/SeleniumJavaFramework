package release_sales_order;


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
import pages.VariableGlobalMain;

public class ReleaseSalesOrderBoxPartisi {

	private static WebDriver driver = null;
	//String noSalesOrder = VariableGlobalMain.noSalesOrderBoxPartisi;
	String noSalesOrder = "11nov2022";
	String whitelist = "Tidak bisa release so line, karena ada pembayaran telat";
	String approval = "Request telah berhasil dibuat. Silahkan tunggu approval";
	String expense = "expense per kg < benchmark, pls ask selling price appro";

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
	    //ke halaman release sales order
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
		driver.findElement(By.id("rootmenu-sales")).click();
		driver.findElement(By.linkText("Release Sales Order")).click();
		
		//filter by no sales order
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("noSalesorder")).sendKeys(noSalesOrder);
		driver.findElement(By.name("submit")).click();
	
		//release sales order
		driver.findElement(By.linkText("Release")).click();
		driver.findElement(By.name("save-btn")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("save-btn")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.findElement(By.name("save-btn")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ant-col > .ant-btn-primary")));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.findElement(By.cssSelector(".ant-col > .ant-btn-primary")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		
		//ambil message
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ant-notification-notice-message")));
		String alert = driver.findElement(By.cssSelector("div.ant-notification.ant-notification-topRight span:nth-child(1) div.ant-notification-notice.ant-notification-notice-closable div.ant-notification-notice-content div.ant-notification-notice-with-icon > div.ant-notification-notice-description")).getText();
		System.out.println("alert" + alert);
		
		//approval (!!!kurang if else sesuai alert!!!)
		Thread.sleep(5000);
		driver.findElement(By.id("rootmenu-sales")).click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("Whitelist Release SPK (bypass payment delay) - Approval")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]/a[1]")).click();
		driver.findElement(By.name("save-btn")).click();
		
//		driver.findElement(By.linkText("Release Sales Order - Approval")).click();
//		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]/a[1]")).click();
//		driver.findElement(By.name("save-btn")).click();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ant-notification-notice-description")));
		
	}
	
	
	@AfterTest
	public void tearDownTest() {    
		driver.quit();
	}

}
