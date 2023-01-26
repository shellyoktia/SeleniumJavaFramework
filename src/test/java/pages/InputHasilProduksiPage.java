package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InputHasilProduksiPage {
	WebDriver driver = null;
	By menu_inputHasilProduksi = By.partialLinkText("Input Hasil Produksi");
	By add = By.name("add");
	By tanggal = By.xpath("(//div[contains(@class,'ant-picker-input')])[1]");
	By tanggalHariIni = By.cssSelector(".ant-picker-cell-today > .ant-picker-cell-inner");
	By input_noSPK = By.xpath("(//input[contains(@class, 'ant-select-selection-search-input')])[1]");
	By input_noSPKItem = By.xpath("(//input[contains(@class, 'ant-select-selection-search-input')])[2]");
	By corrBaik = By.cssSelector(".ihp-corrgoodamount .ant-input-number-input-wrap .ant-input-number-input");
	By scrap = By.cssSelector(".ihp-corrscrap .ant-input-number-input-wrap .ant-input-number-input");
	By flexo = By.cssSelector(".ihp-flexoout .ant-input-number-input-wrap .ant-input-number-input");
	By save = By.name("save-btn");
	By ya_save = By.xpath("(//button[contains(@class, 'ant-btn ant-btn-primary')])[2]");
	private WebDriverWait wait;
	private Actions actions;
	
	public InputHasilProduksiPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	public void click_menuInputHasilProduksi() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(menu_inputHasilProduksi));
		driver.findElement(menu_inputHasilProduksi).click();
	}
	public void click_addInputHasilProduksi() {
		driver.findElement(add).click();
	}
	public void input_noSPKSet(String noSPKSet) throws InterruptedException {
		WebElement inputSPKSet = driver.findElement(input_noSPK);
		actions.moveToElement(inputSPKSet).click().sendKeys(noSPKSet).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), '"+noSPKSet+"')]")).click();
	}
	public void click_noSPKItem() {
		driver.findElement(input_noSPKItem).click();
	}
	public void click_calendar() {
		driver.findElement(tanggal).click();
	}
	public void click_tanggalHariIni() {
		driver.findElement(tanggalHariIni).click();
	}
	public void input_corrBaik(String corr_baik) {
		driver.findElement(corrBaik).sendKeys(corr_baik);
	}
	public void input_scrap(String input_scrap) {
		driver.findElement(scrap).sendKeys(input_scrap);
	}
	public void input_flexo(String input_flexo) {
		driver.findElement(flexo).sendKeys(input_flexo);
	}
	public void click_save(){
		driver.findElement(save).click();
		wait.until(ExpectedConditions.elementToBeClickable(ya_save));
	}
	public void click_yaSave() {
		driver.findElement(ya_save).click();
		wait.until(ExpectedConditions.elementToBeClickable(add));
	}
}
