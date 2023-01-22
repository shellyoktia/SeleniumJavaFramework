package perpindahan_wip;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

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
import pages.Home;
import pages.PerpindahanWIPPage;
import pages.ItemMastercard;
import pages.RawMaterial;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.VariableGlobalMain;

public class PerpindahanWIPBoxPartisi {

	private static WebDriver driver = null;
	String noSPKSet;
	//String noSPKSet = "23001305";
	Home objHome;
	PerpindahanWIPPage objPerpindahanWIPPage;
	ItemMastercard objItemMastercard;
	RawMaterial objRawMaterial;
	WebDriverWait wait;
			
	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		objHome = new Home(driver);
		objPerpindahanWIPPage = new PerpindahanWIPPage(driver);
		objItemMastercard = new ItemMastercard(driver);
		objRawMaterial = new RawMaterial(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		noSPKSet = VariableGlobalMain.noSpkSetBoxPartisi;
		
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
	    //ke halaman perpindahan wip
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		
		objHome.waitCardToBeClickable();
		objHome.click_rootmenuProduction();
		Thread.sleep(1000);
		
		
		objPerpindahanWIPPage.click_menuPerpindahanWIP();
		objPerpindahanWIPPage.click_addPerpindahanWIP();
		objPerpindahanWIPPage.click_calendar();
		objPerpindahanWIPPage.click_tanggalHariIni();
		
	    objPerpindahanWIPPage.input_noSPKSet(noSPKSet);
	    objPerpindahanWIPPage.click_next();
	    objPerpindahanWIPPage.click_noSPKItem();
	    Thread.sleep(3000);
	    objItemMastercard.clickItemMastercard_PTJayamasMedica_box();
	    objItemMastercard.clickItemMastercard_PTJayamasMedica_partisi();
	    objPerpindahanWIPPage.click_rawMaterial();
	    objRawMaterial.clickrawMaterial_8_M150();
	    objPerpindahanWIPPage.click_stockBalance_rawMaterial();
	    objPerpindahanWIPPage.click_bagiSisaBalance();
		objPerpindahanWIPPage.click_assign();
	    Thread.sleep(100);
	    objPerpindahanWIPPage.click_submit();
	    objPerpindahanWIPPage.click_yesSubmit();
	    wait.until(ExpectedConditions.elementToBeClickable(By.name("add")));

	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}

}
