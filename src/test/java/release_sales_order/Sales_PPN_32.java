package release_sales_order;


import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
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

public class Sales_PPN_32 {

	private static WebDriver driver = null;
	String noSalesOrder;
	//String noSalesOrder = "tesnotif4";
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
		String message = objReleaseSalesOrderPage.getMessage();
		System.out.println("message full : " + message);
		message = message.substring(0,55);
		message = message.trim();
		System.out.println("message : " + message);
		Thread.sleep(5000);
		
		if(message.equals(approval)) {
			System.out.println("bisa");
			System.out.println("approval : " + approval);
			System.out.println("message : " + message);
			//approval
			objHome.click_rootmenuSales();
			Thread.sleep(3000);
			objReleaseSalesOrderPage.click_menuApprovaReleaseSalesOrder();
			Thread.sleep(3000);
			objReleaseSalesOrderPage.click_approveReleaseSalesOrder();
			Thread.sleep(3000);
			objReleaseSalesOrderPage.click_YaApproveReleaseSalesOrder();
			Thread.sleep(3000);
			objReleaseSalesOrderPage.wait_approve();
		}
		if(message.equals(whitelist)) {
			System.out.println("bisa");
			System.out.println("whitelist : " + whitelist);
			System.out.println("message : " + message);
			//approval
			objHome.click_rootmenuSales();
			Thread.sleep(3000);
			objReleaseSalesOrderPage.click_menuWhitelistReleaseSalesOrder();
			Thread.sleep(3000);
			objReleaseSalesOrderPage.click_approveReleaseSalesOrder();
			Thread.sleep(3000);
			objReleaseSalesOrderPage.click_YaApproveReleaseSalesOrder();
			objReleaseSalesOrderPage.wait_approve();
//			Thread.sleep(3000);
//			objReleaseSalesOrderPage.click_approveReleaseSalesOrder();
//			Thread.sleep(3000);
//			objReleaseSalesOrderPage.click_YaApproveReleaseSalesOrder();
//			Thread.sleep(3000);
//			objReleaseSalesOrderPage.wait_approve();
		}
		
	}
	
	
	@AfterTest
	public void tearDownTest() {    
		driver.quit();
	}

}
