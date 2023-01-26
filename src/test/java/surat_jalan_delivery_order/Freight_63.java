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
import pages.NotaPenjualanPage;
import pages.VariableGlobalMain;
import pages.Home;
import pages.StockBalancePage;
import pages.SalesOrderPage;
import pages.WorkOrderPage;
import pages.SuratJalanPage;
import pages.Angkutan;
import pages.NotaPenjualanPage;
import pages.SuratJalanReturApprovalPage;
import pages.NotaReturPenjualanPage;

public class Freight_63 {
	private static WebDriver driver = null;
	String noSPKset;
	//String noSPKset = "23001701";
	String qtySTBBox;
	//String qtySTBBox = "100";
	int qtySTBHasilBox;
	String qtySTBPartisi1;
	//String qtySTBPartisi1 = "200";
	int qtySTBHasilPartisi1;
	String qtySTBPartisi2;
	//String qtySTBPartisi2 = "300";
	int qtySTBHasilPartisi2;
	String qty_SJN;
	//String qty_SJN= "20";
	int qtySJN;
	String qty_SJR = "10";
	int qtySJR = Integer.parseInt(qty_SJR);
	String lot_sjr = "Lot Retur Barang Jadi";
	String noSJN,noSJR, actualWeight1_value, actualWeight2_value, actual_BeratKg, actual_Subtotal,actual_tarifKg;
	float actual_weight1_value, actual_weight2_value;
	String actual_status_wo, actual_status_sol, actual_outstanding_sol;
	String expectedStatusWO = "partial-completed";
	String expectedStatusSOL = "partial-delivery";
	String qty_order;
	String noSalesOrder;
	//String noSalesOrder = "dhsjhjshb";
	//String qty_order = "100";
	int qtyOrder;
	String qtypersetpartisi1;
	int qty_per_set_partisi1;
	//int qty_per_set_partisi1 = 2;
	String qtypersetpartisi2;
	int qty_per_set_partisi2;
	//int qty_per_set_partisi2 = 3;
	Home objHome;
	StockBalancePage objStockBalancePage;
	SalesOrderPage objSalesOrderPage;
	WorkOrderPage objWorkOrderPage;
	SuratJalanPage objSuratJalanPage;
	Angkutan objAngkutan;
	NotaPenjualanPage objNotaPenjualanPage;
	SuratJalanReturApprovalPage objSuratJalanReturApprovalPage;
	NotaReturPenjualanPage objNotaReturPenjualanPage;

	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		objHome = new Home(driver);
		objStockBalancePage = new StockBalancePage(driver);
		objSalesOrderPage = new SalesOrderPage(driver);
		objWorkOrderPage = new WorkOrderPage(driver);
		objSuratJalanPage = new SuratJalanPage(driver);
		objAngkutan = new Angkutan(driver);
		objNotaPenjualanPage = new NotaPenjualanPage(driver);
		objSuratJalanReturApprovalPage = new SuratJalanReturApprovalPage(driver);
		objNotaReturPenjualanPage = new NotaReturPenjualanPage(driver);
		noSPKset = VariableGlobalMain.noSpkSetBoxPartisi;
		qtySTBBox = VariableGlobalMain.qtySTBHasilbox;
		qtySTBHasilBox = Integer.parseInt(qtySTBBox);
		qtySTBPartisi1 = VariableGlobalMain.qtySTBHasilPartisi1;
		qtySTBHasilPartisi1 = Integer.parseInt(qtySTBPartisi1);
		qtySTBPartisi2 = VariableGlobalMain.qtySTBHasilPartisi2;
		qtySTBHasilPartisi2 = Integer.parseInt(qtySTBPartisi2);
		qty_SJN = VariableGlobalMain.qtySJNBoxPartis;
		qtySJN = Integer.parseInt(qty_SJN);
		qty_order = VariableGlobalMain.qtySalesOrderBoxPartisi;
		noSalesOrder = VariableGlobalMain.noSalesOrderBoxPartisi;
		qtyOrder = Integer.parseInt(qty_order);
		qtypersetpartisi1 = VariableGlobalMain.qtypersetBoxPartisi_partisi1;
		qty_per_set_partisi1 = Integer.parseInt(qtypersetpartisi1);
		qtypersetpartisi2 = VariableGlobalMain.qtypersetBoxPartisi_partisi2;
		qty_per_set_partisi2 = Integer.parseInt(qtypersetpartisi2);
		
		//ke url
		LoginPage.url_localhost(driver);
		
		//maximize window
		driver.manage().window().maximize();
		
		//login
		LoginPage.username_textbox(driver);
		LoginPage.password_textbox(driver);
		LoginPage.button_login(driver).click();
		
		objHome.waitCardToBeClickable();
	}
	@Test (priority = 1)
	public void createSJRandVerifySubtotal() throws InterruptedException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		objHome.click_rootmenuFreight();
		Thread.sleep(1000);
		objSuratJalanPage.click_menuSuratJalanDeliveryOrder();
		
		//ubah status menjadi completed dan simpan no sj
		objSuratJalanPage.click_filter();
		objSuratJalanPage.filter_byNoWorkOrderGrouped(noSPKset);
		objSuratJalanPage.click_submitFilter();
		noSJN = objSuratJalanPage.getNoSJN();
		System.out.println("no sjn" + noSJN);
		objSuratJalanPage.edit_statusSJN();
		objSuratJalanPage.clickStatusSJN_completed();
		objSuratJalanPage.clickSave_editStatus();
		
		//create sjr
		objSuratJalanPage.click_addSuratJalan();
		objSuratJalanPage.click_tipeSJ();
		objSuratJalanPage.click_tipeReturSusulan();
		//input no sjn
		objSuratJalanPage.input_NoSJN(noSJN);
		
		//add sales order line 2
		objSuratJalanPage.clickCheckbox_sol1();
	    objSuratJalanPage.clickAdd_salesOrderLine();
	    //qty sjr
	    objSuratJalanPage.input_quantitySJN(qty_SJR);
	    VariableGlobalMain.qtySJRBoxPartis = qty_SJR;
	    objSuratJalanPage.clickSave_jumlahPengiriman();
	    //save berat teoritis
	    objSuratJalanPage.clickSave_beratTeoritis();
	    
	    //add sales order line 2
	  	objSuratJalanPage.clickCheckbox_sol2();
	  	objSuratJalanPage.clickAdd_salesOrderLine();
	  	//qty sjr
	  	objSuratJalanPage.input_quantitySJN(qty_SJR);
	  	objSuratJalanPage.clickSave_jumlahPengiriman();
	  	//save berat teoritis
	  	objSuratJalanPage.clickSave_beratTeoritis();
	  	objSuratJalanPage.click_next();

	  	//ambil nilai berat kg, tarif kg dan subtotal untuk di verify
	    actual_BeratKg = objSuratJalanPage.getBeratKGValue();
	    actual_tarifKg = objSuratJalanPage.getTarifPerKGValue();
	    actual_Subtotal = objSuratJalanPage.getSubtotalValue();
	    System.out.println("actual berat kg : " + actual_BeratKg);
	    System.out.println("actual subtotal : " + actual_Subtotal);
	  
	    Thread.sleep(2000);
	}
	@Test (priority = 2)
	public void verifySubtotal() throws InterruptedException {
		float BeratKg = Float.parseFloat(actual_BeratKg);
	    float tarifKg = Float.parseFloat(actual_tarifKg);
	    float expectedSubtotal = BeratKg * tarifKg;
	    String expected_Subtotal = Float.toString(expectedSubtotal);
	    System.out.println("expected_Subtotal: " + expected_Subtotal);
	    assertEquals(actual_Subtotal, expected_Subtotal);
	}
	@Test(priority = 3)
	public void saveSJN() throws InterruptedException {
		objSuratJalanPage.clickSave_suratJalan();
	}
	@Test (priority = 4)
	public void approvalSJR() throws InterruptedException {
		objHome.click_rootmenuFreight();
		objSuratJalanReturApprovalPage.click_menuSuratJalanReturApproval();
		objSuratJalanReturApprovalPage.click_filter();
		objSuratJalanReturApprovalPage.filter_byNoWorkOrderGrouped(noSPKset);
		objSuratJalanReturApprovalPage.click_submitFilter();
		objSuratJalanReturApprovalPage.approve_suratJalanretur();
		objSuratJalanReturApprovalPage.clickConfrim_approveSuratJalanretur();
	}

	@Test (priority = 5)
	public void verifyWorkOrderStatus_Box() throws InterruptedException {
		objHome.click_rootmenuProduction();
		objWorkOrderPage.click_menuWorkOrder();
	    objWorkOrderPage.click_filter();
	    objWorkOrderPage.filter_byNoWorkOrderGrouped(noSPKset);
	    objWorkOrderPage.click_submitFilter();
	    String actualStatusWO = objWorkOrderPage.get_statusWOBox();
	    System.out.println("actual status wo box : " + actualStatusWO);
	    assertEquals(actualStatusWO, expectedStatusWO);
	}
	@Test (priority = 6)
	public void verifyWorkOrderStatus_Partisi() throws InterruptedException {
		 String actualStatusWO = objWorkOrderPage.get_statusWOPartisi();
		 System.out.println("actual status wo partisi : " + actualStatusWO);
		 assertEquals(actualStatusWO, expectedStatusWO);
		 Thread.sleep(100);
		
	}
	@Test (priority = 7)
	public void verifySOLStatus_box() throws InterruptedException {
		//status so dan outstanding box
		objHome.click_rootmenuSales();
		objSalesOrderPage.click_menuSalesOrder();
		objSalesOrderPage.click_filter();
		objSalesOrderPage.filter_byNoSalesOrder(noSalesOrder);
		objSalesOrderPage.click_submitFilter();
		objSalesOrderPage.click_viewSalesOrder();
		Thread.sleep(5000);
				
		String actualStatusSOL = objSalesOrderPage.get_statusSOLBox();
	    System.out.println("actual status sol box : " + actualStatusSOL);
	    assertEquals(actualStatusSOL, expectedStatusSOL);
	    
		
	}
	@Test (priority = 8)
	public void verifySOLOutstanding_box() throws InterruptedException {
		int outstandingSOL = qtyOrder - qtySJN + qtySJR;
		String expectedOutstandingSOL = Integer.toString(outstandingSOL);
	    String actualOutstandingSOL  = objSalesOrderPage.get_outstandingQuantitySOLBox();
	    System.out.println("actual outstanding sol box : " + actualOutstandingSOL);
	    System.out.println("expected outstanding sol box : " + expectedOutstandingSOL);
	    assertEquals(actualOutstandingSOL, expectedOutstandingSOL);
	    
	    Thread.sleep(1000);
	
	}
	@Test (priority = 9)
	public void verifySOLStatus_partisi() throws InterruptedException {
		//status so dan outstanding partisi
		String actualStatusSOL = objSalesOrderPage.get_statusSOLPartisi();
	    System.out.println("actual status sol partisi : " + actualStatusSOL);
	    assertEquals(actualStatusSOL, expectedStatusSOL);
	    Thread.sleep(1000);
	    
	}
	@Test (priority = 10)
	public void verifySOLOutstanding_partisi() throws InterruptedException {
		int outstandingSOL = qtyOrder - qtySJN + qtySJR;
	    String expectedOutstandingSOL = Integer.toString(outstandingSOL);
	    String actualOutstandingSOL  = objSalesOrderPage.get_outstandingQuantitySOLPartisi();
	    System.out.println("actual outstanding sol partisi : " + actualOutstandingSOL);
	    System.out.println("expected outstanding sol partisi : " + expectedOutstandingSOL);
	    assertEquals(actualOutstandingSOL, expectedOutstandingSOL);
	    Thread.sleep(1000);
		
	}
	@Test (priority = 11)
	public void verifyNotaReturPenjualan() throws InterruptedException {
		//ambil no sjr di halaman surat jalan
		objHome.click_rootmenuFreight();
		Thread.sleep(1000);
		objSuratJalanPage.click_menuSuratJalanDeliveryOrder();
		objSuratJalanPage.click_filter();
		objSuratJalanPage.filter_byNoWorkOrderGrouped(noSPKset);
		objSuratJalanPage.click_submitFilter();
		noSJR = objSuratJalanPage.getNoSJR();
		System.out.println("no sjr" + noSJR);
		
		//verify di halaan nota retur penjualan
		Thread.sleep(1000);
		String menu = "Sales > Retur > Nota Retur Penjualan";
		objHome.click_rootmenuHome();
		objHome.click_searchMenu(menu);
		objNotaReturPenjualanPage.click_filter();
		objNotaReturPenjualanPage.filter_byNoSJR(noSJR);
		objNotaReturPenjualanPage.click_submitFilter();
	
		String value_NoSJR = objNotaReturPenjualanPage.getNoSJR_inNotaPenjualan();
		System.out.println("value No SJR : " + value_NoSJR);
		
		assertEquals(value_NoSJR, noSJR);
	}
	@Test (priority = 12)
	public void verifyStockBalance_box() throws InterruptedException {
		//verify stock balance box
		Thread.sleep(1000);
		String menu = "Warehouse > Laporan Stok Level (Stock Balance) > Finished";
		objHome.click_rootmenuHome();
		objHome.click_searchMenu(menu);
				
		objStockBalancePage.click_filter();
		objStockBalancePage.filter_bySKU(noSPKset);
		objStockBalancePage.filter_byLot(lot_sjr);
		objStockBalancePage.click_submitFilter();
		
		int expectedBalanceBox = qtySJR; 
		String expected_balance_box = Integer.toString(expectedBalanceBox);
		System.out.println("expected_balance_box : " + expected_balance_box);
		
		String actual_balance_box = objStockBalancePage.get_balanceBox();
		System.out.println("actual_balance_box : " + actual_balance_box);
		assertEquals(actual_balance_box, expected_balance_box);
	}
	@Test (priority = 13)
	public void verifyStockBalance_partisi1() throws InterruptedException {
	//verify stock balance partisi 1
		int expectedBalancePartisi1 = qtySJR * qty_per_set_partisi1; 
		String expected_balance_partisi1 = Integer.toString(expectedBalancePartisi1);
		System.out.println("expected_balance_partisi1 : " + expected_balance_partisi1);
		
		String actual_balance_partisi1 = objStockBalancePage.get_balancePartisi1();
		System.out.println("actual_balance_partisi1 :" + actual_balance_partisi1);
		assertEquals(actual_balance_partisi1, expected_balance_partisi1);
	}
	@Test (priority = 14)
	public void verifyStockBalance_partisi2() throws InterruptedException {
	//verify stock balance partisi 2
		int expectedBalancePartisi2 = qtySJR * qty_per_set_partisi2; 
		String expected_balance_partisi2 = Integer.toString(expectedBalancePartisi2);
		System.out.println("expected_balance_partisi2 : " + expected_balance_partisi2);
		
		String actual_balance_partisi2 = objStockBalancePage.get_balancePartisi2();
		System.out.println("actual_balance_partisi2 : " + actual_balance_partisi2);
		assertEquals(actual_balance_partisi2, expected_balance_partisi2);
	
	}
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}
	

}
