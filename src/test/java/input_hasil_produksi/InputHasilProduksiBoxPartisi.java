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

public class InputHasilProduksiBoxPartisi {

	private static WebDriver driver = null;
	//String noSPKset = VariableGlobalMain.noSpkSetBoxPartisi;
	String noSPKset = "22020060";
	//String corrbaik = VariableGlobalMain.qtySalesOrderBoxPartisi;
	String corrbaik = "100";
	String scrap = "2";
			
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
	    //ke halaman input hasil produksi
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
		driver.findElement(By.id("rootmenu-production")).click();
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Input Hasil Produksi")).click();
		driver.findElement(By.name("add")).click();
		WebElement inputSPKset = driver.findElement(By.cssSelector(".ant-select-enabled .ant-select-selection__rendered"));
		action.moveToElement(inputSPKset).click().sendKeys(noSPKset).build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'"+ noSPKset +"')]")).click();
		driver.findElement(By.cssSelector(".ant-row:nth-child(2) .ant-select-selection__placeholder:nth-child(1)")).click();
		driver.findElement(By.xpath("//li[contains(text(),'BOX - BOX')]")).click();
		String corrout = driver.findElement(By.cssSelector(".ihp-corrout .ant-input-number-input-wrap .ant-input-number-input")).getAttribute("value");
		System.out.println(corrout);
		driver.findElement(By.cssSelector(".ihp-flexoout .ant-input-number-input-wrap .ant-input-number-input")).sendKeys(corrout);
		driver.findElement(By.cssSelector(".ihp-corrgoodamount .ant-input-number-input-wrap .ant-input-number-input")).sendKeys(corrbaik);
		driver.findElement(By.cssSelector(".ihp-corrscrap .ant-input-number-input-wrap .ant-input-number-input")).sendKeys(scrap);
		driver.findElement(By.name("save-btn")).click();
		Thread.sleep(5000);
		
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
		String corrout = driver.findElement(By.cssSelector(".ihp-corrout .ant-input-number-input-wrap .ant-input-number-input")).getAttribute("value");
		System.out.println(corrout);
		driver.findElement(By.cssSelector(".ihp-flexoout .ant-input-number-input-wrap .ant-input-number-input")).sendKeys(corrout);
		driver.findElement(By.cssSelector(".ihp-corrgoodamount .ant-input-number-input-wrap .ant-input-number-input")).sendKeys(corrbaik);
		driver.findElement(By.cssSelector(".ihp-corrscrap .ant-input-number-input-wrap .ant-input-number-input")).sendKeys(scrap);
		driver.findElement(By.name("save-btn")).click();
		Thread.sleep(5000);
		
	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}

}
