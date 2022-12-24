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

public class STBHasilProduksiBoxPartisi {

	private static WebDriver driver = null;
	//String noSPKset = VariableGlobalMain.noSpkSetBoxPartisi;
	String noSPKset = "22020064";
	String qty_stb = "50";
	int qty_stb_partisi =  Integer.parseInt(qty_stb);
	String lot_tujuan = "FG 1";
	//String qtypersetpartisi1 = VariableGlobalMain.qtypersetBoxPartisi_partisi1;
	//int qty_per_set_partisi1 = Integer.parseInt(qtypersetpartisi1);
	int qty_per_set_partisi1 = 2;
	//String qty_per_set_partisi2 = VariableGlobalMain.qtypersetBoxPartisi_partisi2;
	//int qty_per_set_partisi2 = Integer.parseInt(qtypersetpartisi2);
	int qty_per_set_partisi2 = 3;
	String qty_stbpartisi1, qty_stbpartisi2;
			
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
	public void inputSTBBox() throws InterruptedException {
		Actions action = new Actions(driver);
	    //ke halaman stb hasil produksi
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
		driver.findElement(By.id("rootmenu-warehouse")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("STB Hasil Produksi")).click();
		driver.findElement(By.name("add")).click();
		WebElement inputSPKset = driver.findElement(By.cssSelector(".goodsreceipt-nospkset .ant-select-search__field__wrap"));
		action.moveToElement(inputSPKset).click().sendKeys(noSPKset).build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'"+ noSPKset +"')]")).click();
		driver.findElement(By.name("next-btn")).click();
		driver.findElement(By.cssSelector(".goodsreceipt-nospk .ant-select-enabled")).click();
		driver.findElement(By.xpath("//li[contains(text(),'BOX - BOX')]")).click();
		driver.findElement(By.cssSelector(".goodsreceipt-qtystb .ant-input-number-input")).sendKeys(qty_stb);
		VariableGlobalMain.qtySTBHasilbox = qty_stb;
		driver.findElement(By.cssSelector(".goodsreceipt-lotto .ant-select-enabled")).click();
		driver.findElement(By.xpath("//li[contains(text(),'"+ lot_tujuan +"')]")).click();
		WebElement input_receiver = driver.findElement(By.cssSelector(".goodsreceipt-receiver .ant-select-search__field__wrap"));
		action.moveToElement(input_receiver).click().sendKeys("sujadi").build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'303 - Sujadi')]")).click();
		WebElement input_penyerah = driver.findElement(By.cssSelector(".goodsreceipt-acknowledger .ant-select-search__field__wrap"));
		action.moveToElement(input_penyerah).click().sendKeys("kuswadi").build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'6 - Kuswadi')]")).click();
		driver.findElement(By.name("save-btn")).click();
		//click button pake js
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement button = driver.findElement(By.className("goodsreceipt-confirmbutton"));
		js.executeScript("arguments[0].click();", button);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add")));
	}
	@Test (priority = 2)
	public void inputSTBPartisi1() throws InterruptedException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.name("add")).click();
		WebElement inputSPKset = driver.findElement(By.cssSelector(".goodsreceipt-nospkset .ant-select-search__field__wrap"));
		action.moveToElement(inputSPKset).click().sendKeys(noSPKset).build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'"+ noSPKset +"')]")).click();
		driver.findElement(By.name("next-btn")).click();
		driver.findElement(By.cssSelector(".goodsreceipt-nospk .ant-select-enabled")).click();
		driver.findElement(By.xpath("//li[contains(text(),'PARTISI - BOX')]")).click();
		int qty_stb_partisi1 =  qty_stb_partisi * qty_per_set_partisi1;
		qty_stbpartisi1 = Integer.toString(qty_stb_partisi1);
		VariableGlobalMain.qtySTBHasilPartisi1 = qty_stbpartisi1;
		driver.findElement(By.cssSelector(".goodsreceipt-qtystb .ant-input-number-input")).sendKeys(qty_stbpartisi1);
		driver.findElement(By.cssSelector(".goodsreceipt-lotto .ant-select-enabled")).click();
		driver.findElement(By.xpath("//li[contains(text(),'"+ lot_tujuan +"')]")).click();
		WebElement input_receiver = driver.findElement(By.cssSelector(".goodsreceipt-receiver .ant-select-search__field__wrap"));
		action.moveToElement(input_receiver).click().sendKeys("sujadi").build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'303 - Sujadi')]")).click();
		WebElement input_penyerah = driver.findElement(By.cssSelector(".goodsreceipt-acknowledger .ant-select-search__field__wrap"));
		action.moveToElement(input_penyerah).click().sendKeys("kuswadi").build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'6 - Kuswadi')]")).click();
		driver.findElement(By.name("save-btn")).click();
		//click button pake js
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement button = driver.findElement(By.className("goodsreceipt-confirmbutton"));
		js.executeScript("arguments[0].click();", button);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add")));
		
	}
	@Test (priority = 3)
	public void inputSTBPartisi2() throws InterruptedException {
		Actions action = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.findElement(By.name("add")).click();
		WebElement inputSPKset = driver.findElement(By.cssSelector(".goodsreceipt-nospkset .ant-select-search__field__wrap"));
		action.moveToElement(inputSPKset).click().sendKeys(noSPKset).build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'"+ noSPKset +"')]")).click();
		driver.findElement(By.name("next-btn")).click();
		driver.findElement(By.cssSelector(".goodsreceipt-nospk .ant-select-enabled")).click();
		driver.findElement(By.xpath("//li[contains(text(),'PARTISI - BOX')]")).click();
		driver.findElement(By.cssSelector(".goodsreceipt-finishedgood .ant-select-selection")).click();
		driver.findElement(By.xpath("//li[contains(text(),'partisi - BOX USG 250CC (K0173) - 2')]")).click();
		int qty_stb_partisi2 =  qty_stb_partisi * qty_per_set_partisi2;
		qty_stbpartisi2 = Integer.toString(qty_stb_partisi2);
		VariableGlobalMain.qtySTBHasilPartisi2 = qty_stbpartisi2;
		driver.findElement(By.cssSelector(".goodsreceipt-qtystb .ant-input-number-input")).sendKeys(qty_stbpartisi2);
		driver.findElement(By.cssSelector(".goodsreceipt-lotto .ant-select-enabled")).click();
		driver.findElement(By.xpath("//li[contains(text(),'"+ lot_tujuan +"')]")).click();
		WebElement input_receiver = driver.findElement(By.cssSelector(".goodsreceipt-receiver .ant-select-search__field__wrap"));
		action.moveToElement(input_receiver).click().sendKeys("sujadi").build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'303 - Sujadi')]")).click();
		WebElement input_penyerah = driver.findElement(By.cssSelector(".goodsreceipt-acknowledger .ant-select-search__field__wrap"));
		action.moveToElement(input_penyerah).click().sendKeys("kuswadi").build().perform();
		driver.findElement(By.xpath("//li[contains(text(),'6 - Kuswadi')]")).click();
		driver.findElement(By.name("save-btn")).click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement button = driver.findElement(By.className("goodsreceipt-confirmbutton"));
		js.executeScript("arguments[0].click();", button);
		wait.until(ExpectedConditions.elementToBeClickable(By.name("add")));
		
	}
	@Test (priority = 4)
	public void VerifyStockBalanceBox() throws InterruptedException {
		Actions action = new Actions(driver);
	    //ke halaman stock balance
		driver.findElement(By.id("rootmenu-warehouse")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@id='Warehouse$Menu']/li[12]/div")).click();
		driver.findElement(By.xpath("//ul[@id='Warehouse-menu-item_11$Menu']/li[3]/a")).click();
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("sku")).sendKeys(noSPKset);
		driver.findElement(By.name("submit")).click();
		String expected_balance_box = qty_stb;
		//String expected_balance_box = "100";
		String actual_balance_box = driver.findElement(By.xpath("//tbody//td[5][contains(text(),'0')]//following::td[2]")).getText();
		System.out.println(actual_balance_box);
		assertEquals(actual_balance_box, expected_balance_box);
	}
	@Test (priority = 5)
	public void VerifyStockBalancePartisi1() throws InterruptedException {
		String expected_balance_partisi1 = qty_stbpartisi1;
		//String expected_balance_partisi1 = "200";
		String actual_balance_partisi1 = driver.findElement(By.xpath("//tbody//td[5][contains(text(),'1')]//following::td[2]")).getText();
		System.out.println(actual_balance_partisi1);
		assertEquals(actual_balance_partisi1, expected_balance_partisi1);
	}
	@Test (priority = 6)
	public void VerifyStockBalancePartisi2() throws InterruptedException {
		String expected_balance_partisi2 = qty_stbpartisi2;
		//String expected_balance_partisi2 = "300";
		String actual_balance_partisi2 = driver.findElement(By.xpath("//tbody//td[5][contains(text(),'2')]//following::td[2]")).getText();
		System.out.println(actual_balance_partisi2);
		assertEquals(actual_balance_partisi2, expected_balance_partisi2);
	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}

}
