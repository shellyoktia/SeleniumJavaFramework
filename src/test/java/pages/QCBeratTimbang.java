package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QCBeratTimbang {
	WebDriver driver = null;
	By menu_qcBeratTimbang = By.partialLinkText("QC Berat Timbang Sample Hasil Produksi");
	By add = By.name("add");
	By input_noSPK = By.xpath("(//input[contains(@class, 'ant-select-selection-search-input')])[1]");
	By input_noSPKItem = By.xpath("(//input[contains(@class, 'ant-select-selection-search-input')])[2]");
	By beratTimbang = By.cssSelector(".qc-corractualweight .ant-input-number-input");
	By flexo = By.cssSelector(".qc-actualweight .ant-input-number-input");
	By save = By.name("save-btn");
	private WebDriverWait wait;
	private Actions actions;
	
	public QCBeratTimbang(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	public void click_menuQCBeratTimbang() {
		driver.findElement(menu_qcBeratTimbang).click();
	}
	public void click_addQCBeratTimbang() {
		driver.findElement(add).click();
	}
	public void input_noSPKSet(String noSPKSet) throws InterruptedException {
		WebElement inputSPKset = driver.findElement(input_noSPK);
		actions.moveToElement(inputSPKset).click().sendKeys(noSPKSet).build().perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), '"+noSPKSet+"')]")).click();
	}
	public void click_noSPKItem() {
		driver.findElement(input_noSPKItem).click();
	}
	public void input_beratTimbang(String berat_corr) {
		driver.findElement(beratTimbang).sendKeys(berat_corr);
	}
	public void input_beratFlexo(String berat_flexo) {
		driver.findElement(flexo).sendKeys(berat_flexo);
	}
	public void click_save() {
		driver.findElement(save).click();
		wait.until(ExpectedConditions.elementToBeClickable(add));
	}
}
