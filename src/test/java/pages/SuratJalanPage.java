package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuratJalanPage {
	WebDriver driver = null;
	private WebDriverWait wait;
	private Actions actions;
	By menu_SuratJalanDeliveryOrder = By.linkText("Surat Jalan / Delivery Order");
	By add = By.name("add");
	By filter = By.className("ant-collapse-header-text");
	By submit_filter = By.name("submit");
	By no_workOrderGrouped = By.name("noWorkorderGrouped");
	By view_sj = By.xpath("(//a[contains(text(), 'View')])[1]");
	By more = By.xpath("(//a[contains(text(), 'More')])[1]");
	By checkbox_sjn = By.xpath("(//input[contains(@class, 'ant-checkbox-input')])[2]");
	By button_editSJN = By.xpath("//button[contains(@name,'update')]");
	By searchStatusSJN = By.xpath("(//input[contains(@class,'ant-select-selection-search-input')])[5]");
	By statusSJN_completed = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(),'Completed')]");
	By save_editStatus = By.name("save-btn");
	By get_noSJN = By.xpath("(//td[contains(@class, 'ant-table-cell')])[5]");
	By get_noSJR = By.xpath("(//td[contains(@class, 'ant-table-cell')])[5]");
	By tipe = By.cssSelector(".do_type .ant-select-selection-search-input");
	By tipe_normal = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'Normal')]");
	By tipe_returSusulan = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'Susulan')]");
	By input_noSPK = By.cssSelector(".do_spkset .ant-select-selection-search-input");
	By input_noSJN = By.cssSelector(".do_nodeliveryordernormal .ant-select-selection-search-input");
	By angkutan = By.cssSelector(".do_angkutan .ant-select-selection-search-input");
	By salesorderline_1 = By.xpath("(//input[contains(@class, 'ant-checkbox-input')])[2]");
	By salesorderline_2 = By.xpath("(//input[contains(@class, 'ant-checkbox-input')])[3]");
	By addSOL = By.xpath("(//button[contains(@name, 'save-btn')])[1]");
	By qtySJN = By.cssSelector(".do_deliveryqty .ant-input-number-input");
	By save_jumlahPengiriman = By.xpath("(//button[contains(@name, 'save-btn')])[3]");
	By pilih_sb = By.cssSelector(".sb_checkbox .ant-checkbox-input");
	By save_sb = By.xpath("(//button[contains(@name, 'save-btn')])[3]");
	By theoritical_weight = By.cssSelector(".do_theoriticalweight .ant-input-number-input");
	By actual_weight = By.cssSelector(".do_actualweight .ant-input-number-input");
	By save_beratTeoritis = By.xpath("(//button[contains(@name, 'save-btn')])[3]");
	By next = By.name("next-btn");
	By beratKg = By.cssSelector(".do_weight .ant-input-number-input");
	By tarifPerKg = By.cssSelector(".do_tarifperkg .ant-input-number-input");
	By subtotal = By.cssSelector(".do_subtotal .ant-input-number-input");
	By save_sj = By.xpath("(//button[contains(@name, 'save-btn')])[2]");
	
	public SuratJalanPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	public void click_menuSuratJalanDeliveryOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(menu_SuratJalanDeliveryOrder));
		driver.findElement(menu_SuratJalanDeliveryOrder).click();
	}
	public void click_addSuratJalan() {
		driver.findElement(add).click();
	}
	public void click_filter() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(view_sj));
		Thread.sleep(5000);
		driver.findElement(filter).click();
	}
	public void filter_byNoWorkOrderGrouped(String no_wo_grouped) {
		driver.findElement(no_workOrderGrouped).sendKeys(no_wo_grouped);
	}
	public void click_submitFilter() throws InterruptedException {
		driver.findElement(submit_filter).click();
		wait.until(ExpectedConditions.elementToBeClickable(view_sj));
		Thread.sleep(5000);
	}
	public void click_more() {
		driver.findElement(more).click();
	}
	public void edit_statusSJN() {
		driver.findElement(checkbox_sjn).click();
		driver.findElement(button_editSJN).click();
		wait.until(ExpectedConditions.elementToBeClickable(searchStatusSJN));
		driver.findElement(searchStatusSJN).click();
	}
	public void clickStatusSJN_completed() {
		driver.findElement(statusSJN_completed).click();
	}
	public void clickSave_editStatus() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(save_editStatus));
		driver.findElement(save_editStatus).click();
		Thread.sleep(3000);
	}
	public void click_tipeSJ() {
		driver.findElement(tipe).click();
	}
	public void click_tipeNormal() {
		driver.findElement(tipe_normal).click();
	}
	public void click_tipeReturSusulan() {
		driver.findElement(tipe_returSusulan).click();
	}
	public void input_noSPKSet(String noSPKSet) throws InterruptedException {
		WebElement inputSPKSet = driver.findElement(input_noSPK);
		actions.moveToElement(inputSPKSet).click().sendKeys(noSPKSet).build().perform();
		Thread.sleep(3000);
		WebElement spk = driver.findElement(By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), '"+noSPKSet+"')]"));
		wait.until(ExpectedConditions.elementToBeClickable(spk));
		spk.click();
	}
	public void input_NoSJN(String no_sjn) throws InterruptedException {
		WebElement inputNoSJN = driver.findElement(input_noSJN);
		actions.moveToElement(inputNoSJN).click().sendKeys(no_sjn).build().perform();
		Thread.sleep(3000);
		WebElement sjn = driver.findElement(By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), '"+no_sjn+"')]"));
		wait.until(ExpectedConditions.elementToBeClickable(sjn));
		sjn.click();
	}
	public void click_angkutan() {
		driver.findElement(angkutan).click();
	}
	public void clickCheckbox_sol1() {
		driver.findElement(salesorderline_1).click();
	}
	public void clickCheckbox_sol2() {
		driver.findElement(salesorderline_2).click();
	}
	public void clickAdd_salesOrderLine() {
		driver.findElement(addSOL).click();
	}
	public void input_quantitySJN(String qty_SJN) {
	    String selectAll = Keys.chord(Keys.CONTROL, "a");
	    driver.findElement(qtySJN).sendKeys(selectAll);
	    driver.findElement(qtySJN).sendKeys(Keys.BACK_SPACE);
	    driver.findElement(qtySJN).sendKeys(qty_SJN);
	}
	public void clickSave_jumlahPengiriman() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(save_jumlahPengiriman));
		driver.findElement(save_jumlahPengiriman).click();
		Thread.sleep(2000);
	}
	public void click_stockBalance() throws InterruptedException {
	    driver.findElement(pilih_sb).click();
	    Thread.sleep(2000);
	}
	public void clickSave_stockBalance() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(save_sb));
		driver.findElement(save_sb).click();
		Thread.sleep(2000);
	}
	public String getTheoritivalWeightValue() {
		return driver.findElement(theoritical_weight).getAttribute("value");
	}
	public void input_actualWeight(String actualWeight) {
		driver.findElement(actual_weight).sendKeys(actualWeight);
	}
	public void clickSave_beratTeoritis() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(save_beratTeoritis));
		driver.findElement(save_beratTeoritis).click();
		Thread.sleep(2000);
	}
	public void click_next() throws InterruptedException {
		driver.findElement(next).click();
		Thread.sleep(2000);
	}
	public String getBeratKGValue() {
		return driver.findElement(beratKg).getAttribute("value");
	}
	public String getTarifPerKGValue() {
		return driver.findElement(tarifPerKg).getAttribute("value");
	}
	public String getSubtotalValue() {
		return driver.findElement(subtotal).getAttribute("value");
	}
	public String getNoSJN() {
		return driver.findElement(get_noSJN).getText();
	}
	public String getNoSJR() {
		return driver.findElement(get_noSJR).getText();
	}
	public void clickSave_suratJalan() throws InterruptedException{
		wait.until(ExpectedConditions.elementToBeClickable(save_sj));
		driver.findElement(save_sj).click();
		wait.until(ExpectedConditions.elementToBeClickable(add));
		Thread.sleep(3000);
	}
}
