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

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class QCBeratTimbangBoxPartisi {

	private static WebDriver driver = null;
	//String noSPKset = VariableGlobalMain.noSpkSetBoxPartisi;
	String noSPKset = "22020060";
	String berat_corr_box = "10";
	String berat_flexo_box = "10";	
	String berat_corr_partisi = "10";
	String berat_flexo_partisi = "10";
	
			
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
	
	@Test (priority = 1)
	public void firstTest() throws InterruptedException {
		Actions action = new Actions(driver);
	    //ke halaman qc berat timbang
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
		driver.findElement(By.id("rootmenu-quality-check")).click();
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("QC Berat Timbang Sample Hasil Produksi")).click();
		driver.findElement(By.name("add")).click();
		WebElement inputSPKset = driver.findElement(By.cssSelector(".ant-select-enabled .ant-select-selection__rendered"));
		action.moveToElement(inputSPKset).click().sendKeys(noSPKset).build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'"+ noSPKset +"')]")).click();
		driver.findElement(By.cssSelector(".ant-row:nth-child(2) .ant-select-selection__placeholder:nth-child(1)")).click();
		driver.findElement(By.xpath("//li[contains(text(),'BOX - BOX')]")).click();
		driver.findElement(By.cssSelector(".qc-corractualweight .ant-input-number-input")).sendKeys(berat_corr_box);
		driver.findElement(By.cssSelector(".qc-actualweight .ant-input-number-input")).sendKeys(berat_flexo_box);
		driver.findElement(By.name("save-btn")).click();
		Thread.sleep(3000);
		
	}
	@Test (priority = 2)
	public void secondTest() throws InterruptedException {
		Actions action = new Actions(driver);
		driver.findElement(By.name("add")).click();
		WebElement inputSPKset = driver.findElement(By.cssSelector(".ant-select-enabled .ant-select-selection__rendered"));
		action.moveToElement(inputSPKset).click().sendKeys(noSPKset).build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'"+ noSPKset +"')]")).click();
		driver.findElement(By.cssSelector(".ant-row:nth-child(2) .ant-select-selection__placeholder:nth-child(1)")).click();
		driver.findElement(By.xpath("//li[contains(text(),'PARTISI - BOX')]")).click();
		driver.findElement(By.cssSelector(".qc-corractualweight .ant-input-number-input")).sendKeys(berat_corr_partisi);
		driver.findElement(By.cssSelector(".qc-actualweight .ant-input-number-input")).sendKeys(berat_flexo_partisi);
		driver.findElement(By.name("save-btn")).click();
		Thread.sleep(3000);
		
	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}


}
