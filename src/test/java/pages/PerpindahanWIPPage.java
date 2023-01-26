package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PerpindahanWIPPage {
	WebDriver driver = null;

	By menu_perpindahanwip = By.linkText("Perpindahan WIP");
	By add = By.name("add");
	By tanggal = By.cssSelector(".goodsissue-transactiondate .ant-picker");
	By tanggalHariIni = By.cssSelector(".ant-picker-cell-today > .ant-picker-cell-inner");
	By input_noSPKSet = By.cssSelector(".goodsissue-searchwo .ant-form-item-control-input .ant-select-selection-search-input");
	By next = By.name("next-btn");
	By noSPKItem = By.cssSelector(".goodsissue-nospk .ant-select-selection-overflow");
	By rawMaterial = By.cssSelector(".goodsissue-item .ant-select-selection-search-input");
	By stockBalance_rawMaterial = By.xpath("(//input[contains(@class, 'ant-checkbox-input')])[2]");
	By bagiSisaBalance = By.cssSelector(".goodsissue-bagisisa");
	By assign = By.cssSelector(".goodsissue-assign");
	By submit = By.xpath("(//button[contains(@name, 'save-btn')])[2]");
	By yes_submit = By.xpath("//button[contains(@class, 'ant-btn ant-btn-primary') and contains(span, 'Yes')]");
	private Actions actions;
	private WebDriverWait wait;
	
	public PerpindahanWIPPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	public void click_menuPerpindahanWIP() {
		wait.until(ExpectedConditions.elementToBeClickable(menu_perpindahanwip));
		driver.findElement(menu_perpindahanwip).click();
	}
	public void click_addPerpindahanWIP() {
		driver.findElement(add).click();
	}
	public void click_calendar() {
		driver.findElement(tanggal).click();
	}
	public void click_tanggalHariIni() {
		driver.findElement(tanggalHariIni).click();
	}
	public void input_noSPKSet(String noSPKSet) throws InterruptedException {
		WebElement searchwo = driver.findElement(input_noSPKSet);
	    actions.moveToElement(searchwo).click().sendKeys(noSPKSet).build().perform();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), '"+noSPKSet+"')]")).click();
	}
	public void click_noSPKItem() {
		driver.findElement(noSPKItem).click();
	}
	public void click_next() {
		driver.findElement(next).click();
	}
	public void click_rawMaterial() {
		driver.findElement(rawMaterial).click();
	}
	public void click_stockBalance_rawMaterial() {
		driver.findElement(stockBalance_rawMaterial).click();
	}
	public void click_bagiSisaBalance() {
		driver.findElement(bagiSisaBalance).click();
	}
	public void click_assign() {
		driver.findElement(assign).click();
	}
	public void click_submit() {
		driver.findElement(submit).click();
	}
	public void click_yesSubmit() {
		wait.until(ExpectedConditions.elementToBeClickable(yes_submit));
		driver.findElement(yes_submit).click();
	}
}
