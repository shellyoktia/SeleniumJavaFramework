package quality_check;

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
import pages.ItemMastercard;
import pages.QCBeratTimbang;
import pages.VariableGlobalMain;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.PerpindahanWIPPage;

public class QCBeratTimbangBoxPartisi {

	private static WebDriver driver = null;
	String noSPKSet;
	//String noSPKSet = "23001625";
	String berat_corr_box = "10";
	String berat_flexo_box = "10";	
	String berat_corr_partisi = "10";
	String berat_flexo_partisi = "10";
	Home objHome;
	ItemMastercard objItemMastercard;
	QCBeratTimbang objQCBeratTimbang;
	WebDriverWait wait;
	
	
			
	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		objHome = new Home(driver);
		objQCBeratTimbang = new QCBeratTimbang(driver);
		objItemMastercard = new ItemMastercard(driver);
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
	
	@Test (priority = 1)
	public void firstTest() throws InterruptedException {
		Actions action = new Actions(driver);
	    //ke halaman qc berat timbang
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		
		objHome.waitCardToBeClickable();
		objHome.click_rootmenuQualityCheck();
		Thread.sleep(1000);
		objQCBeratTimbang.click_menuQCBeratTimbang();
		objQCBeratTimbang.click_addQCBeratTimbang();
		objQCBeratTimbang.input_noSPKSet(noSPKSet);
		objQCBeratTimbang.click_noSPKItem();
		objItemMastercard.clickItemMastercard_PTJayamasMedica_box();
		objQCBeratTimbang.input_beratFlexo(berat_flexo_box);
		objQCBeratTimbang.input_beratTimbang(berat_corr_box);
		objQCBeratTimbang.click_save();
	}
	@Test (priority = 2)
	public void secondTest() throws InterruptedException {
		objQCBeratTimbang.click_addQCBeratTimbang();
		objQCBeratTimbang.input_noSPKSet(noSPKSet);
		objQCBeratTimbang.click_noSPKItem();
		objItemMastercard.clickItemMastercard_PTJayamasMedica_partisi();
		objQCBeratTimbang.input_beratFlexo(berat_flexo_partisi);
		objQCBeratTimbang.input_beratTimbang(berat_corr_partisi);
		objQCBeratTimbang.click_save();
	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}


}
