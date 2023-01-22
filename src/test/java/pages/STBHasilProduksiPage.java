package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class STBHasilProduksiPage {
	WebDriver driver = null;
	By menu_STBHasilProduksi = By.linkText("STB Hasil Produksi");
	By add = By.name("add");
	By input_noSPK = By.cssSelector(".goodsreceipt-nospkset .ant-select-selection-search-input");
	By next = By.name("next-btn");
	By input_noSPKItem = By.cssSelector(".goodsreceipt-nospk .ant-select-selection-search-input");
	By quantity = By.cssSelector(".goodsreceipt-qtystb .ant-input-number-input");
	By lotTujuan = By.cssSelector(".goodsreceipt-lotto .ant-select-selection-search-input");
	By input_receiver = By.cssSelector(".goodsreceipt-receiver .ant-select-selection-search-input");
	By save = By.name("save-btn");
	By ya_save = By.xpath("//button[contains(@class, 'ant-btn ant-btn-primary goodsreceipt-confirmbutton')]");
	private WebDriverWait wait;
	private Actions actions;
	
	public STBHasilProduksiPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	public void click_menuSTBHasilProduksi() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(menu_STBHasilProduksi).click();
	}
	public void click_addSTBHasilProduksi() {
		driver.findElement(add).click();
	}
	public void input_noSPKSet(String noSPKSet) throws InterruptedException {
		WebElement inputSPKSet = driver.findElement(input_noSPK);
		actions.moveToElement(inputSPKSet).click().sendKeys(noSPKSet).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), '"+noSPKSet+"')]")).click();
	}
	public void click_next() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(next).click();
	}
	public void click_noSPKItem() {
		driver.findElement(input_noSPKItem).click();
	}
	public void input_quantity(String qty_stb) {
		driver.findElement(quantity).sendKeys(qty_stb);
	}
	public void input_lot(String lot_tujuan) {
		driver.findElement(lotTujuan).click();
		driver.findElement(By.xpath("//div[contains(text(),'"+ lot_tujuan +"')]")).click();
	}
	public void input_receiver(String receiver) {
		WebElement receiverperson = driver.findElement(input_receiver); 
		actions.moveToElement(receiverperson).click().sendKeys(receiver).build().perform();
	}
	public void click_save() {
		driver.findElement(save).click();
	}
	public void click_yaSave() {
		driver.findElement(ya_save).click();
		wait.until(ExpectedConditions.elementToBeClickable(add));
	}
}
