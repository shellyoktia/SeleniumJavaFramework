package surat_jalan_delivery_order;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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

public class CreateSuratJalanReturSusulan_BoxPartisi {
	private static WebDriver driver = null;
	//String noSPKset = VariableGlobalMain.noSpkSetBoxPartisi;
	String noSPKset = "22020063";
	//String qtySTBBox = VariableGlobalMain.qtySTBHasilbox;
	String qtySTBBox = "100";
	int qtySTBHasilBox = Integer.parseInt(qtySTBBox);
	//String qtySTBPartisi1 = VariableGlobalMain.qtySTBHasilPartisi1;
	String qtySTBPartisi1 = "200";
	int qtySTBHasilPartisi1 = Integer.parseInt(qtySTBPartisi1);
	//String qtySTBPartisi2 = VariableGlobalMain.qtySTBHasilPartisi2;
	String qtySTBPartisi2 = "300";
	int qtySTBHasilPartisi2 = Integer.parseInt(qtySTBPartisi2);
	String qty_SJN= "20";
	int qtySJN = Integer.parseInt(qty_SJN);
	String qty_SJR = "10";
	int qtySJR = Integer.parseInt(qty_SJR);
	String noSJN, actualWeight1_value, actualWeight2_value, actual_BeratKg, actual_Subtotal,actual_tarifKg;
	float actual_weight1_value, actual_weight2_value;
	private static ArrayList<String> statusworkorder = new ArrayList<String>();
	private static ArrayList<String> statussalesorderline = new ArrayList<String>();
	private static ArrayList<String> outstandingsalesorderline = new ArrayList<String>();
	String actual_status_wo, actual_status_sol, actual_outstanding_sol;
	String expectedStatusWO = "completed";
	String expectedStatusSOL = "partial-delivery";
	//String qty_order = VariableGlobalMain.qtySalesOrderBoxPartisi;
	//String noSalesOrder = VariableGlobalMain.noSalesOrderBoxPartisi;
	String noSalesOrder = "test sb";
	String qty_order = "100";
	int qtyOrder = Integer.parseInt(qty_order);
	//String qtypersetpartisi1 = VariableGlobalMain.qtypersetBoxPartisi_partisi1;
	//int qty_per_set_partisi1 = Integer.parseInt(qtypersetpartisi1);
	int qty_per_set_partisi1 = 2;
	//String qtypersetpartisi2 = VariableGlobalMain.qtypersetBoxPartisi_partisi2;
	//int qty_per_set_partisi2 = Integer.parseInt(qtypersetpartisi2);
	int qty_per_set_partisi2 = 3;

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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));

	}
	@Test (priority = 1)
	public void createSJRandVerifySubtotal() throws InterruptedException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.id("rootmenu-freight")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Surat Jalan / Delivery Order")).click();
		//ubah status menjadi completed
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("noWorkorderGrouped")).sendKeys(noSPKset);
		driver.findElement(By.name("submit")).click();
		Thread.sleep(2000);
		WebElement more_button = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]/a[2]"));
		action.moveToElement(more_button).perform();
		WebElement edit_button = driver.findElement(By.xpath("//li[contains(text(),'Edit')]"));
		action.moveToElement(edit_button).perform();
		edit_button.click();
		Thread.sleep(5000);
		noSJN = driver.findElement(By.cssSelector(".do_nodeliveryorder")).getAttribute("value");
		//noSJN = "2210.0034.SJN";
		System.out.println(noSJN);
		VariableGlobalMain.noSJNBoxPartisi = noSJN;
		driver.findElement(By.cssSelector(".do_status .ant-select-selection__rendered .ant-select-selection-selected-value")).click();
		driver.findElement(By.xpath("//li[contains(text(),'Completed')]")).click();
		driver.findElement(By.name("save-btn")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add")));
		
		//create sjr
		driver.findElement(By.name("add")).click();
		driver.findElement(By.cssSelector(".do_type .ant-select-selection__rendered")).click();
		driver.findElement(By.xpath("//li[contains(text(),'Retur Susulan')]")).click();
		WebElement inputNoSJN = driver.findElement(By.cssSelector(".do_nodeliveryordernormal .ant-input"));
		action.moveToElement(inputNoSJN).click().sendKeys(noSJN).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'"+ noSJN +"')]")));
		driver.findElement(By.xpath("//li[contains(text(),'"+ noSJN +"')]")).click();
		driver.findElement(By.cssSelector(".ant-table-body-inner .ant-table-row:nth-child(1) .ant-checkbox-input")).click();
	    driver.findElement(By.name("save-btn")).click();
	    String selectAll = Keys.chord(Keys.CONTROL, "a");
	    driver.findElement(By.cssSelector(".do_deliveryqty .ant-input-number-input")).sendKeys(selectAll);
	    driver.findElement(By.cssSelector(".do_deliveryqty .ant-input-number-input")).sendKeys(Keys.BACK_SPACE);
	    driver.findElement(By.cssSelector(".do_deliveryqty .ant-input-number-input")).sendKeys(qty_SJR);
	    VariableGlobalMain.qtySJRBoxPartis = qty_SJR;
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector(".ant-row-flex > .ant-btn")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector(".ant-col > .ant-btn")).click();
	    
	    driver.findElement(By.cssSelector(".ant-table-body-inner .ant-table-row:nth-child(2) .ant-checkbox-input")).click();
	    driver.findElement(By.name("save-btn")).click();
	    driver.findElement(By.cssSelector(".do_deliveryqty .ant-input-number-input")).sendKeys(selectAll);
	    driver.findElement(By.cssSelector(".do_deliveryqty .ant-input-number-input")).sendKeys(Keys.BACK_SPACE);
	    driver.findElement(By.cssSelector(".do_deliveryqty .ant-input-number-input")).sendKeys(qty_SJR);
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector(".ant-row-flex > .ant-btn")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.cssSelector(".ant-col > .ant-btn")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.name("next-btn")).click();
	    
		
		actual_BeratKg = driver.findElement(By.cssSelector(".do_weight .ant-input-number-input")).getAttribute("value");
	    actual_tarifKg = driver.findElement(By.cssSelector(".do_tarifperkg .ant-input-number-input")).getAttribute("value");
	    actual_Subtotal = driver.findElement(By.cssSelector(".do_subtotal .ant-input-number-input")).getAttribute("value");
	    System.out.println("actual_BeratKg : " + actual_BeratKg);
	    System.out.println("actual_tarifKg : " + actual_tarifKg);
	    System.out.println("actual_Subtotal : " + actual_Subtotal);
	    float BeratKg = Float.parseFloat(actual_BeratKg);
	    float tarifKg = Float.parseFloat(actual_tarifKg);
	    float expectedSubtotal = BeratKg * tarifKg;
	    String expected_Subtotal = Float.toString(expectedSubtotal);
	    System.out.println("expected_Subtotal: " + expected_Subtotal);
	    assertEquals(actual_Subtotal, expected_Subtotal);
	    
	    driver.findElement(By.xpath("(//button[@name='save-btn'])[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add")));
	}
	@Test (priority = 2)
	public void approvalSJR() throws InterruptedException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.id("rootmenu-freight")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Surat Jalan / Delivery Order - Retur - Approval")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div/main/section/div/div[2]/div[3]/div/div/div/div/div[2]/div[2]/div/table/tbody/tr[1]/td/span/a[1]")).click();
		driver.findElement(By.name("confirm")).click();
		Thread.sleep(2000);
	}

	@Test (priority = 3)
	public void verifyWorkOrderStatus_Box() throws InterruptedException {
		driver.findElement(By.id("rootmenu-production")).click();
		driver.findElement(By.linkText("Work Order (SPK)")).click();
	    driver.findElement(By.className("filter-block__title")).click();
	    driver.findElement(By.name("noWorkorderGrouped")).sendKeys(noSPKset);
	    driver.findElement(By.name("submit")).click();
		
	    //ambil status dari semua workorder
	    WebElement Table = driver.findElement(By.xpath("/html/body/div[1]/div/main/section/div/div[2]/div[2]/div[4]/div/div/div/div/div[1]/div[2]/table"));
	    List<WebElement> rows_table = Table.findElements(By.tagName("tr"));
	    int rows_count = rows_table.size();
	    
	    
	    // 'Loop will execute for all the rows of the table'
	    for (int row = 0; row < rows_count; row++) {
	        //'To locate columns(cells) of that specific row'
	        List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
	        //'status ada dikolom ke 9'
	        int column_status = 9;
	        actual_status_wo = Columns_row.get(column_status).getText();
	        statusworkorder.add(actual_status_wo);
	    }
	    String actualStatusWO = statusworkorder.get(0);
	    System.out.println("actual status wo box : " + actualStatusWO);
	    assertEquals(actualStatusWO, expectedStatusWO);
	}
	@Test (priority = 4)
	public void verifyWorkOrderStatus_Partisi() throws InterruptedException {
		 String actualStatusWO = statusworkorder.get(1);
		 System.out.println("actual status wo partisi : " + actualStatusWO);
		 assertEquals(actualStatusWO, expectedStatusWO);
		 Thread.sleep(100);
		
	}
	@Test (priority = 5)
	public void verifySOLStatus_box() throws InterruptedException {
		//status so dan outstanding box
		driver.findElement(By.id("rootmenu-sales")).click();
		driver.findElement(By.linkText("Sales Order")).click();
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("noSalesorder")).sendKeys(noSalesOrder);
		driver.findElement(By.name("submit")).click();
		driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]/a[1]")).click();
		Thread.sleep(10000);
		
		//'To locate table'
		WebElement Table1 = driver.findElement(By.xpath("/html/body/div[1]/div/main/section/div/div[2]/form/div[4]/div[1]/div/div/div/div/div[1]/div[2]/table/tbody"));
		List<WebElement> rows_table1 = Table1.findElements(By.tagName("tr"));
		int rows_count1 = rows_table1.size();
		//'Loop will execute for all the rows of the table'
		for (int row1 = 0; row1 < rows_count1; row1++) {
		    //'To locate columns(cells) of that specific row'
		    List<WebElement> Columns_row1 = rows_table1.get(row1).findElements(By.tagName("td"));
		    //'status ada dikolom ke 1'
		    int column_status1 = 1;
		    actual_status_sol = Columns_row1.get(column_status1).getText();
		    statussalesorderline.add(actual_status_sol);
		    //'outstanding ada di kolom ke 24'
		    int column_outstanding = 24;
		    actual_outstanding_sol = Columns_row1.get(column_outstanding).getText();
		    outstandingsalesorderline.add(actual_outstanding_sol);
		}
		String actualStatusSOL = statussalesorderline.get(0);
	    System.out.println("actual status sol box : " + actualStatusSOL);
	    assertEquals(actualStatusSOL, expectedStatusSOL);
	    
		
	}
	@Test (priority = 6)
	public void verifySOLOutstanding_box() throws InterruptedException {
		int outstandingSOL = qtyOrder - qtySJN + qtySJR;
	    String expectedOutstandingSOL = Integer.toString(outstandingSOL);
	    String actualOutstandingSOL  = outstandingsalesorderline.get(0);
	    System.out.println("actual outstanding sol box : " + actualOutstandingSOL);
	    System.out.println("expected outstanding sol box : " + expectedOutstandingSOL);
	    assertEquals(actualOutstandingSOL, expectedOutstandingSOL);
	    
	    Thread.sleep(1000);
	
	}
	@Test (priority = 7)
	public void verifySOLStatus_partisi() throws InterruptedException {
		//status so dan outstanding partisi
		String actualStatusSOL = statussalesorderline.get(1);
	    System.out.println("actual status sol partisi : " + actualStatusSOL);
	    assertEquals(actualStatusSOL, expectedStatusSOL);
	    Thread.sleep(1000);
	    
	}
	@Test (priority = 8)
	public void verifySOLOutstanding_partisi() throws InterruptedException {
		int outstandingSOL = qtyOrder - qtySJN + qtySJR;
	    String expectedOutstandingSOL = Integer.toString(outstandingSOL);
	    String actualOutstandingSOL  = outstandingsalesorderline.get(1);
	    System.out.println("actual outstanding sol partisi : " + actualOutstandingSOL);
	    System.out.println("expected outstanding sol partisi : " + expectedOutstandingSOL);
	    assertEquals(actualOutstandingSOL, expectedOutstandingSOL);
	    Thread.sleep(1000);
		
	}
	@Test (priority = 9)
	public void verifyNotaReturPenjualan() throws InterruptedException {
		//ambil no sjr di halaman surat jalan
		driver.findElement(By.id("rootmenu-freight")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Surat Jalan / Delivery Order")).click();
		//ubah status menjadi completed
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("noWorkorderGrouped")).sendKeys(noSPKset);
		driver.findElement(By.name("submit")).click();
		Thread.sleep(2000);
		String NoSJR = driver.findElement(By.xpath("/html/body/div[1]/div/main/section/div/div[2]/div[2]/div[2]/div/div/div/div/div[1]/div[2]/table/tbody/tr/td[5]")).getText();
		System.out.println("No SJR : " + NoSJR);
		
		//verify di halaan nota retur penjualan
		driver.findElement(By.id("rootmenu-sales")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".ant-menu-submenu:nth-child(20) > .ant-menu-submenu-title")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Nota Retur Penjualan")).click();
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("noSj")).sendKeys(NoSJR);
		driver.findElement(By.name("submit")).click();
		Thread.sleep(3000);
		String value_NoSJR = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[6]")).getText();
		System.out.println("value No SJR : " + value_NoSJR);
		
		assertEquals(value_NoSJR, NoSJR);
	}
	@Test (priority = 10)
	public void verifyStockBalance_box() throws InterruptedException {
	//verify stock balance box
		driver.findElement(By.id("rootmenu-warehouse")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@id='Warehouse$Menu']/li[12]/div")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@id='Warehouse-menu-item_11$Menu']/li[3]/a")).click();
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("sku")).sendKeys(noSPKset);
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Search'])[1]/following::*[name()='svg'][1]")).click();
		driver.findElement(By.xpath("//li[contains(text(),'Lot Retur Barang Jadi')]")).click();
		driver.findElement(By.name("submit")).click();
		Thread.sleep(3000);
		
		int expectedBalanceBox = qtySJR; 
		String expected_balance_box = Integer.toString(expectedBalanceBox);
		System.out.println("expected_balance_box : " + expected_balance_box);
		
		String actual_balance_box = driver.findElement(By.xpath("//tbody//td[5][contains(text(),'0')]//following::td[2]")).getText();
		System.out.println("actual_balance_box : " + actual_balance_box);
		assertEquals(actual_balance_box, expected_balance_box);
	}
	@Test (priority = 11)
	public void verifyStockBalance_partisi1() throws InterruptedException {
	//verify stock balance partisi 1
		int expectedBalancePartisi1 = qtySJR * qty_per_set_partisi1; 
		String expected_balance_partisi1 = Integer.toString(expectedBalancePartisi1);
		System.out.println("expected_balance_partisi1 : " + expected_balance_partisi1);
		
		String actual_balance_partisi1 = driver.findElement(By.xpath("//tbody//td[5][contains(text(),'1')]//following::td[2]")).getText();
		System.out.println("actual_balance_partisi1 :" + actual_balance_partisi1);
		assertEquals(actual_balance_partisi1, expected_balance_partisi1);
	}
	@Test (priority = 12)
	public void verifyStockBalance_partisi2() throws InterruptedException {
	//verify stock balance partisi 2
		int expectedBalancePartisi2 = qtySJR * qty_per_set_partisi2; 
		String expected_balance_partisi2 = Integer.toString(expectedBalancePartisi2);
		System.out.println("expected_balance_partisi2 : " + expected_balance_partisi2);
		
		String actual_balance_partisi2 = driver.findElement(By.xpath("//tbody//td[5][contains(text(),'2')]//following::td[2]")).getText();
		System.out.println("actual_balance_partisi2 : " + actual_balance_partisi2);
		assertEquals(actual_balance_partisi2, expected_balance_partisi2);
	
	}
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}
	

}
