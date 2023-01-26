package stb_hasil_produksi;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import pages.ItemMastercard;
import pages.STBHasilProduksiPage;
import pages.SalesPersonName;
import pages.StockBalancePage;

public class Warehouse_FG_9 {

	private static WebDriver driver = null;
	String noSPKSet;
	//String noSPKSet = "23001693";
	String qty_stb = "100";
	int qty_stb_partisi =  Integer.parseInt(qty_stb);
	String lot_tujuan = "FG 1";
	
	String qtypersetpartisi1;
	int qty_per_set_partisi1;
	//int qty_per_set_partisi1 = Integer.parseInt(qtypersetpartisi1);
	//int qty_per_set_partisi1 = 2;
	
	String qtypersetpartisi2;
	int qty_per_set_partisi2;
	//int qty_per_set_partisi2 = 3;
	
	String qty_stbpartisi1, qty_stbpartisi2;
	String receiver = "sujadi";
	String penyerah = "kuswadi";
	Home objHome;
	ItemMastercard objItemMastercard;
	STBHasilProduksiPage objSTBHasilProduksiPage;
	SalesPersonName objSalesPersonName;
	StockBalancePage objStockBalancePage;
	WebDriverWait wait;
			
	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		objHome = new Home(driver);
		objItemMastercard = new ItemMastercard(driver);
		objSTBHasilProduksiPage = new STBHasilProduksiPage(driver);
		objSalesPersonName = new SalesPersonName(driver);
		objStockBalancePage = new StockBalancePage(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		noSPKSet = VariableGlobalMain.noSpkSetBoxPartisi;
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
	}
	
	@Test (priority = 1)
	public void inputSTBBox() throws InterruptedException {
		Actions action = new Actions(driver);
	    //ke halaman stb hasil produksi
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		objHome.waitCardToBeClickable();
		objHome.click_rootmenuWarehouse();
		Thread.sleep(1000);
		objSTBHasilProduksiPage.click_menuSTBHasilProduksi();
		
		//add stb hasil produksi
		objSTBHasilProduksiPage.click_addSTBHasilProduksi();
		
		//input no spk set
		objSTBHasilProduksiPage.input_noSPKSet(noSPKSet);
		objSTBHasilProduksiPage.click_next();
		
		//input spk item
		objSTBHasilProduksiPage.click_noSPKItem();
		objItemMastercard.clickItemMastercard_PTJayamasMedica_box();
		
		//input qty
		objSTBHasilProduksiPage.input_quantity(qty_stb);
		VariableGlobalMain.qtySTBHasilbox = qty_stb;
		
		//input lot
		objSTBHasilProduksiPage.input_lot(lot_tujuan);
		
		//input penerima
		objSTBHasilProduksiPage.input_receiver(receiver);
		objSalesPersonName.clickSalesPerson_Sujadi();
		
		//input penyerah
		objSTBHasilProduksiPage.input_penyerah(penyerah);
		objSalesPersonName.clickSalesPerson_Kuswadi();
		
		//save
		objSTBHasilProduksiPage.click_save();
		//objSTBHasilProduksiPage.click_yaSave();
	}
	@Test (priority = 2)
	public void inputSTBPartisi1() throws InterruptedException {
		//add stb hasil produksi
		objSTBHasilProduksiPage.click_addSTBHasilProduksi();
				
		//input no spk set
		objSTBHasilProduksiPage.input_noSPKSet(noSPKSet);
		objSTBHasilProduksiPage.click_next();
				
		//input spk item
		objSTBHasilProduksiPage.click_noSPKItem();
		objItemMastercard.clickItemMastercard_PTJayamasMedica_partisi();
				
		//input qty
		int qty = qty_stb_partisi * qty_per_set_partisi1;
		qty_stbpartisi1 = Integer.toString(qty);
		objSTBHasilProduksiPage.input_quantity(qty_stbpartisi1);
		VariableGlobalMain.qtySTBHasilPartisi1 = qty_stbpartisi1;
				
		//input lot
		objSTBHasilProduksiPage.input_lot(lot_tujuan);
				
		//input penerima
		objSTBHasilProduksiPage.input_receiver(receiver);
		objSalesPersonName.clickSalesPerson_Sujadi();
				
		//input penyerah
		objSTBHasilProduksiPage.input_penyerah(penyerah);
		objSalesPersonName.clickSalesPerson_Kuswadi();
			
		//save
		objSTBHasilProduksiPage.click_save();
		//objSTBHasilProduksiPage.click_yaSave();
	}
	@Test (priority = 3)
	public void inputSTBPartisi2() throws InterruptedException {
		//add stb hasil produksi
		objSTBHasilProduksiPage.click_addSTBHasilProduksi();
						
		//input no spk set
		objSTBHasilProduksiPage.input_noSPKSet(noSPKSet);
		objSTBHasilProduksiPage.click_next();
						
		//input spk item
		objSTBHasilProduksiPage.click_noSPKItem();
		objItemMastercard.clickItemMastercard_PTJayamasMedica_partisi();
		
		//pilih partisi 2
		String fg = "partisi - BOX USG 250CC (K0173) - 2";
		objSTBHasilProduksiPage.click_pilihFinishedgood(fg);
		driver.findElement(By.xpath("//div[contains(text(),'"+fg+"')]")).click();
						
		//input qty
		int qty = qty_stb_partisi * qty_per_set_partisi2;
		qty_stbpartisi2 = Integer.toString(qty);
		objSTBHasilProduksiPage.input_quantity(qty_stbpartisi2);
		VariableGlobalMain.qtySTBHasilPartisi2 = qty_stbpartisi2;
						
		//input lot
		objSTBHasilProduksiPage.input_lot(lot_tujuan);
						
		//input penerima
		objSTBHasilProduksiPage.input_receiver(receiver);
		objSalesPersonName.clickSalesPerson_Sujadi();
						
		//input penyerah
		objSTBHasilProduksiPage.input_penyerah(penyerah);
		objSalesPersonName.clickSalesPerson_Kuswadi();
					
		//save
		objSTBHasilProduksiPage.click_save();
		//objSTBHasilProduksiPage.click_yaSave();
	}
	@Test (priority = 4)
	public void VerifyStockBalanceBox() throws InterruptedException {
	    //ke halaman stock balance
		objHome.click_rootmenuWarehouse();
		Thread.sleep(1000);
		String menu = "Warehouse > Laporan Stok Level (Stock Balance) > Finished";
		objHome.click_rootmenuHome();
		objHome.click_searchMenu(menu);
		
		objStockBalancePage.click_filter();
		objStockBalancePage.filter_bySKU(noSPKSet);
		objStockBalancePage.click_submitFilter();
		
		String expected_balance_box = qty_stb;
//		//String expected_balance_box = "100";
		String actual_balance_box = objStockBalancePage.get_balanceBox();
		System.out.println(actual_balance_box);
		assertEquals(actual_balance_box, expected_balance_box);
	}
	@Test (priority = 5)
	public void VerifyStockBalancePartisi1() throws InterruptedException {
		String expected_balance_partisi1 = qty_stbpartisi1;
		//String expected_balance_partisi1 = "200";
		String actual_balance_partisi1 = objStockBalancePage.get_balancePartisi1();
		System.out.println(actual_balance_partisi1);
		assertEquals(actual_balance_partisi1, expected_balance_partisi1);
	}
	@Test (priority = 6)
	public void VerifyStockBalancePartisi2() throws InterruptedException {
		String expected_balance_partisi2 = qty_stbpartisi2;
		//String expected_balance_partisi2 = "300";
		String actual_balance_partisi2 = objStockBalancePage.get_balancePartisi2();
		System.out.println(actual_balance_partisi2);
		assertEquals(actual_balance_partisi2, expected_balance_partisi2);
	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}

}
