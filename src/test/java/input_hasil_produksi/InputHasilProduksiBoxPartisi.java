package input_hasil_produksi;

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
import pages.Home;
import pages.InputHasilProduksiPage;
import pages.ItemMastercard;

public class InputHasilProduksiBoxPartisi {

	private static WebDriver driver = null;
	String noSPKSet;
	//String noSPKSet = "23001625";
	String corr_baik;
	//String corr_baik = "150";
	String scrap = "2";
	String out = "1";
	String flexo = "1";
	Home objHome;
	ItemMastercard objItemMastercard;
	InputHasilProduksiPage objInputHasilProduksiPage;
			
	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		objHome = new Home(driver);
		objInputHasilProduksiPage = new InputHasilProduksiPage(driver);
		objItemMastercard = new ItemMastercard(driver);
		noSPKSet = VariableGlobalMain.noSpkSetBoxPartisi;
		corr_baik = VariableGlobalMain.qtySalesOrderBoxPartisi;
		
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
	public void firstTest() throws InterruptedException {
		Actions action = new Actions(driver);
	    //ke halaman input hasil produksi
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		objHome.waitCardToBeClickable();
		objHome.click_rootmenuProduction();
		objInputHasilProduksiPage.click_menuInputHasilProduksi();
		
		//add input hasil produksi
		objInputHasilProduksiPage.click_addInputHasilProduksi();
		
		//input no spk
		objInputHasilProduksiPage.input_noSPKSet(noSPKSet);
		objInputHasilProduksiPage.click_noSPKItem();
		objItemMastercard.clickItemMastercard_PTJayamasMedica_box();
		
		//tanggal
		objInputHasilProduksiPage.click_calendar();
		objInputHasilProduksiPage.click_tanggalHariIni();
		
		//input corr baik
		objInputHasilProduksiPage.input_corrBaik(corr_baik);
		
		//input scrap
		objInputHasilProduksiPage.input_scrap(scrap);
		
		//iinput flexo out
		objInputHasilProduksiPage.input_flexo(flexo);

		//save
		objInputHasilProduksiPage.click_save();
		objInputHasilProduksiPage.click_yaSave();
		
	}
	@Test (priority = 2)
	public void secondTest() throws InterruptedException {
		//add input hasil produksi
		objInputHasilProduksiPage.click_addInputHasilProduksi();
				
		//input no spk
		objInputHasilProduksiPage.input_noSPKSet(noSPKSet);
		objInputHasilProduksiPage.click_noSPKItem();
		objItemMastercard.clickItemMastercard_PTJayamasMedica_partisi();
		
		//tanggal
		objInputHasilProduksiPage.click_calendar();
		objInputHasilProduksiPage.click_tanggalHariIni();
				
		//input corr baik
		objInputHasilProduksiPage.input_corrBaik(corr_baik);
				
		//input scrap
		objInputHasilProduksiPage.input_scrap(scrap);
				
		//iinput flexo out
		objInputHasilProduksiPage.input_flexo(flexo);

		//save
		objInputHasilProduksiPage.click_save();
		objInputHasilProduksiPage.click_yaSave();
		
	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}

}
