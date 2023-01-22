package release_sales_order;


import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.VariableGlobalMain;
import pages.Home;
import pages.ReleaseSalesOrderPage;

public class ReleaseSalesOrderBoxPartisi {

	private static WebDriver driver = null;
	String noSalesOrder;
	String whitelist = "Tidak bisa release so line, karena ada pembayaran telat";
	String approval = "Request telah berhasil dibuat. Silahkan tunggu approval";
	String expense = "expense per kg < benchmark, pls ask selling price appro";
	Home objHome;
	ReleaseSalesOrderPage objReleaseSalesOrderPage;
	

	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		objHome = new Home(driver);
		objReleaseSalesOrderPage = new ReleaseSalesOrderPage(driver);
		noSalesOrder = VariableGlobalMain.noSalesOrderBoxPartisi;
		
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
		objHome.waitCardToBeClickable();
		objHome.click_rootmenuSales();
		objReleaseSalesOrderPage.click_menuReleaseSalesOrder();
		
		//filter by no sales order
		objReleaseSalesOrderPage.click_filter();
		objReleaseSalesOrderPage.filter_byNoSalesOrder(noSalesOrder);
		objReleaseSalesOrderPage.click_submitFilter();
	
		//release sales order
		objReleaseSalesOrderPage.click_releaseSalesOrder();
		Thread.sleep(2000);
		objReleaseSalesOrderPage.click_YaReleaseSalesOrder();
		objReleaseSalesOrderPage.click_assign();
		Thread.sleep(2000);
		objReleaseSalesOrderPage.click_YaAssign();
		
		//ambil message
		String alert = objReleaseSalesOrderPage.getMessage();
		System.out.println("alert" + alert);
		
		//approval (!!!kurang if else sesuai alert!!!)
		Thread.sleep(5000);
		objHome.click_rootmenuSales();
		
		//whitelist
		//Thread.sleep(5000);
		//driver.findElement(By.linkText("Whitelist Release SPK (bypass payment delay) - Approval")).click();
		//driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]/a[1]")).click();
		//driver.findElement(By.name("save-btn")).click();
		
		//approval
		Thread.sleep(3000);
		objReleaseSalesOrderPage.click_menuApprovaReleaseSalesOrder();
		Thread.sleep(3000);
		objReleaseSalesOrderPage.click_approveReleaseSalesOrder();
		Thread.sleep(3000);
		objReleaseSalesOrderPage.click_YaApproveReleaseSalesOrder();
		objReleaseSalesOrderPage.wait_approve();
		
		//price
		
	}
	
	
	@AfterTest
	public void tearDownTest() {    
		driver.quit();
	}

}
