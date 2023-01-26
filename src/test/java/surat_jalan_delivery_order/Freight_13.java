package surat_jalan_delivery_order;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
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
import pages.Home;
import pages.StockBalancePage;
import pages.SalesOrderPage;
import pages.WorkOrderPage;
import pages.SuratJalanPage;
import pages.Angkutan;
import pages.NotaPenjualanPage;


public class Freight_13 {

	private static WebDriver driver = null;
	String noSPKset;
	//String noSPKset = "23001694";
	String qtySTBBox;
	//String qtySTBBox = "100";
	int qtySTBHasilBox;
	String qtySTBPartisi1;
	//String qtySTBPartisi1 = "200";
	int qtySTBHasilPartisi1;
	String qtySTBPartisi2;
	//String qtySTBPartisi2 = "300";
	int qtySTBHasilPartisi2;
	String qty_SJN= "20";
	int qtySJN = Integer.parseInt(qty_SJN);
	String lot_sjn = "FG 1";
	String actualWeight1_value, actualWeight2_value, actual_BeratKg, actual_Subtotal,actual_tarifKg;
	float actual_weight1_value, actual_weight2_value;
	String actual_status_wo, actual_status_sol, actual_outstanding_sol;
	String expectedStatusWO = "partial-completed";
	String expectedStatusSOL = "partial-delivery";
	String qty_order;
	String noSalesOrder;
	//String noSalesOrder = "2023225";
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
		noSPKset = VariableGlobalMain.noSpkSetBoxPartisi;
		qtySTBBox = VariableGlobalMain.qtySTBHasilbox;
		qtySTBHasilBox = Integer.parseInt(qtySTBBox);
		qtySTBPartisi1 = VariableGlobalMain.qtySTBHasilPartisi1;
		qtySTBHasilPartisi1 = Integer.parseInt(qtySTBPartisi1);
		qtySTBPartisi2 = VariableGlobalMain.qtySTBHasilPartisi2;
		qtySTBHasilPartisi2 = Integer.parseInt(qtySTBPartisi2);
		qty_order = VariableGlobalMain.qtySalesOrderBoxPartisi;
		qtyOrder = Integer.parseInt(qty_order);
		noSalesOrder = VariableGlobalMain.noSalesOrderBoxPartisi;
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
	public void createSJN() throws InterruptedException {
		Actions action = new Actions(driver);
	    //ke halaman surat jalan / delivery order
		objHome.click_rootmenuFreight();
		Thread.sleep(1000);
		objSuratJalanPage.click_menuSuratJalanDeliveryOrder();
		objSuratJalanPage.click_addSuratJalan();
		//tipe sj
		objSuratJalanPage.click_tipeSJ();
		objSuratJalanPage.click_tipeNormal();
		
		//no spk set
		objSuratJalanPage.input_noSPKSet(noSPKset);
	    
		//angkutan
	    objSuratJalanPage.click_angkutan();
	    objAngkutan.clickAngkutan_Purwantoro();
	    
	    //add sales order line 1
	    objSuratJalanPage.clickCheckbox_sol1();
	    objSuratJalanPage.clickAdd_salesOrderLine();
	    
	    //qty sjn
	    objSuratJalanPage.input_quantitySJN(qty_SJN);
	    VariableGlobalMain.qtySJNBoxPartis = qty_SJN;
	    objSuratJalanPage.clickSave_jumlahPengiriman();
	    
	    //pilih stock balance
	    objSuratJalanPage.click_stockBalance();
	    objSuratJalanPage.clickSave_stockBalance();

	    //simpan nilai theoritical weight yang ada di ui lalu dipakai untuk input actual weight
	    String theoriticalWeight1 = objSuratJalanPage.getTheoritivalWeightValue();
	    System.out.println(theoriticalWeight1);
	    float theoritical_weight1 = Float.parseFloat(theoriticalWeight1);
	    actual_weight1_value = (float) (theoritical_weight1 - (0.01));
	    actualWeight1_value = String.format("%.2f", actual_weight1_value);
	    System.out.println("actual weight 1 : " + actualWeight1_value);
	    objSuratJalanPage.input_actualWeight(actualWeight1_value);
	    objSuratJalanPage.clickSave_beratTeoritis();
	   
	    //add sales order line 2
	    objSuratJalanPage.clickCheckbox_sol2();
	    objSuratJalanPage.clickAdd_salesOrderLine();
	    
	    //qty sjn
	    objSuratJalanPage.input_quantitySJN(qty_SJN);
	    VariableGlobalMain.qtySJNBoxPartis = qty_SJN;
	    objSuratJalanPage.clickSave_jumlahPengiriman();
	    
	    //pilih stock balance partisi 1
	    objSuratJalanPage.click_stockBalance();
	    objSuratJalanPage.clickSave_stockBalance();
	    
	    //pilih stock balance partisi 2
	    objSuratJalanPage.click_stockBalance();
	    objSuratJalanPage.clickSave_stockBalance();

	    //simpan nilai theoritical weight yang ada di ui lalu dipakai untuk input actual weight
	    String theoriticalWeight2 = objSuratJalanPage.getTheoritivalWeightValue();
	    System.out.println(theoriticalWeight2);
	    float theoritical_weight2 = Float.parseFloat(theoriticalWeight2);
	    actual_weight2_value = (float) (theoritical_weight2 - (0.01));
	    actualWeight2_value = String.format("%.2f", actual_weight2_value);
	    System.out.println("actual weight 2 : " + actualWeight2_value);
	    objSuratJalanPage.input_actualWeight(actualWeight2_value);
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
	public void verifyBeratKg() throws InterruptedException {
		float expectedBeratKg = actual_weight1_value + actual_weight2_value;
		String expected_BeratKg = Float.toString(expectedBeratKg);
		System.out.println("expected berat kg: " + expected_BeratKg);
		assertEquals(actual_BeratKg, expected_BeratKg);
	}
	@Test (priority = 3)
	public void verifySubtotal() throws InterruptedException {
		float beratKg = Float.parseFloat(actual_BeratKg);
		float tarifKg = Float.parseFloat(actual_tarifKg);
		float subtotal = beratKg * tarifKg;
		String expected_Subtotal = Float.toString(subtotal);
		System.out.println("expected subtotal: " + expected_Subtotal);
		assertEquals(actual_Subtotal, expected_Subtotal);
	}
	@Test(priority = 4)
	public void saveSJN() throws InterruptedException {
		objSuratJalanPage.clickSave_suratJalan();
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
		int outstandingSOL = qtyOrder - qtySJN;
	    String expectedOutstandingSOL = Integer.toString(outstandingSOL);
	    String actualOutstandingSOL  = objSalesOrderPage.get_outstandingQuantitySOLBox();
	    System.out.println("actual outstanding sol box : " + actualOutstandingSOL);
	    System.out.println("expected outstanding sol box : " + expectedOutstandingSOL);
	    assertEquals(actualOutstandingSOL, expectedOutstandingSOL);
	    
	    Thread.sleep(1000);
	
	}
	@Test (priority = 9)
	public void verifySOLStatus_partisi() throws InterruptedException {
		//status so partisi
		String actualStatusSOL = objSalesOrderPage.get_statusSOLPartisi();
	    System.out.println("actual status sol partisi : " + actualStatusSOL);
	    assertEquals(actualStatusSOL, expectedStatusSOL);
	    Thread.sleep(1000);
	    
	}
	@Test (priority = 10)
	public void verifySOLOutstanding_partisi() throws InterruptedException {
		int outstandingSOL = qtyOrder - qtySJN;
	    String expectedOutstandingSOL = Integer.toString(outstandingSOL);
	    String actualOutstandingSOL  = objSalesOrderPage.get_outstandingQuantitySOLPartisi();
	    System.out.println("actual outstanding sol partisi : " + actualOutstandingSOL);
	    System.out.println("expected outstanding sol partisi : " + expectedOutstandingSOL);
	    assertEquals(actualOutstandingSOL, expectedOutstandingSOL);
	    Thread.sleep(1000);
		
	}
	@Test (priority = 11)
	public void verifyNotaPenjualan() throws InterruptedException {
		//verify nota penjualan is created
		objHome.click_rootmenuSales();
		objNotaPenjualanPage.click_menuNotaPenjualan();
		
		objNotaPenjualanPage.click_filter();
		objNotaPenjualanPage.filter_byNoSalesOrder(noSalesOrder);
		objNotaPenjualanPage.click_submitFilter();
	    String value_noSO = objNotaPenjualanPage.getNoSO_inNotaPenjualan();
	    System.out.println(value_noSO);
	    assertEquals(value_noSO, noSalesOrder);
	}
	@Test (priority = 12)
	public void verifyStockBalance_box() throws InterruptedException {
		//verify stock balance box
		objHome.click_rootmenuWarehouse();
		Thread.sleep(1000);
		String menu = "Warehouse > Laporan Stok Level (Stock Balance) > Finished";
		objHome.click_rootmenuHome();
		objHome.click_searchMenu(menu);
		
		objStockBalancePage.click_filter();
		objStockBalancePage.filter_bySKU(noSPKset);
		objStockBalancePage.filter_byLot(lot_sjn);
		objStockBalancePage.click_submitFilter();
		
		int expectedBalanceBox = qtySTBHasilBox - qtySJN; 
		String expected_balance_box = Integer.toString(expectedBalanceBox);
		System.out.println("expected_balance_box : " + expected_balance_box);

		String actual_balance_box = objStockBalancePage.get_balanceBox();
		System.out.println("actual_balance_box : " + actual_balance_box);
		assertEquals(actual_balance_box, expected_balance_box);
	}
	@Test (priority = 13)
	public void verifyStockBalance_partisi1() throws InterruptedException {
	//verify stock balance partisi 1
		int expectedBalancePartisi1 = qtySTBHasilPartisi1 - (qtySJN * qty_per_set_partisi1); 
		String expected_balance_partisi1 = Integer.toString(expectedBalancePartisi1);
		System.out.println("expected_balance_partisi1 : " + expected_balance_partisi1);
		
		String actual_balance_partisi1 = objStockBalancePage.get_balancePartisi1();
		System.out.println("actual_balance_partisi1 : " + actual_balance_partisi1);
		assertEquals(actual_balance_partisi1, expected_balance_partisi1);
	}
	@Test (priority = 14)
	public void verifyStockBalance_partisi2() throws InterruptedException {
	//verify stock balance partisi 2
		int expectedBalancePartisi2 = qtySTBHasilPartisi2 - (qtySJN * qty_per_set_partisi2); 
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
