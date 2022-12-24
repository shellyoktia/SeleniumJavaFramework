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

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.VariableGlobalMain;

public class PerpindahanWIPBoxPartisi {

	private static WebDriver driver = null;
	//String noSPKset = VariableGlobalMain.noSpkSetBoxPartisi;
	String noSPKset = "22020051";
			
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
	
	@Test
	public void firstTest() throws InterruptedException {
		Actions action = new Actions(driver);
	    //ke halaman perpindahan wip
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
		driver.findElement(By.id("rootmenu-production")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Perpindahan WIP")).click();
		driver.findElement(By.name("add")).click();
		driver.findElement(By.cssSelector(".ant-calendar-picker-input")).click();
	    driver.findElement(By.cssSelector(".ant-calendar-today > .ant-calendar-date")).click();
	    WebElement searchwo = driver.findElement(By.className("goodsissue-searchwo"));
	    action.moveToElement(searchwo).click().sendKeys(noSPKset).build().perform();
	    Thread.sleep(10000);
	    driver.findElement(By.xpath("//li[contains(text(),'"+ noSPKset +"')]")).click();
		driver.findElement(By.name("next-btn")).click();
		driver.findElement(By.cssSelector(".goodsissue-nospk .ant-select-selection__rendered")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[contains(text(),'BOX - BOX USG 250CC (K0173)')]")).click();
		driver.findElement(By.xpath("//li[contains(text(),'PARTISI - BOX USG 250CC (K0173)')]")).click();
		driver.findElement(By.cssSelector(".goodsissue-item .ant-select-selection__placeholder")).click();
		driver.findElement(By.xpath("//li[contains(text(),'8 - M150 - A - PA69 - MPKA')]")).click();
		driver.findElement(By.cssSelector(".ant-table-row:nth-child(1) .ant-checkbox-input")).click();
	    driver.findElement(By.cssSelector(".goodsissue-bagisisa")).click();
	    driver.findElement(By.className("goodsissue-assign")).click();
	    Thread.sleep(100);
	    driver.findElement(By.cssSelector(".data-form__actions-row:nth-child(16) > .ant-btn")).click();
	    driver.findElement(By.cssSelector(".ant-btn:nth-child(2)")).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.name("add")));
		
	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}

}
