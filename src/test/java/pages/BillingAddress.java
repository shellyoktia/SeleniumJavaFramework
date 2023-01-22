package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BillingAddress {
	WebDriver driver = null;
	By billingAddress_PTBeautyKasatama = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'RAYA DARMO')]");
	By billingAddress_PTjayamasMedica = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'BY PASS')]");
	By billingAddress_PTPinrangPantaiIndah = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'GURU GANTANGAN')]");
	By billingAddress_PTGeloraDjaja = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'BUNTARAN')]");
	By billingAddress_PTSukajadiSawitMekar = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'SPRING TOWER')]");
			
	public BillingAddress(WebDriver driver) {
		this.driver = driver;
	}
	public void clickBillingAddress_PTBeautyKasatama() {
		driver.findElement(billingAddress_PTBeautyKasatama).click();
	}
	public void clickBillingAddress_PTJayamasMedica() {
		driver.findElement(billingAddress_PTjayamasMedica).click();
	}
	public void clickBillingAddress_PTPinrangPantaiIndah() {
		driver.findElement(billingAddress_PTPinrangPantaiIndah).click();
	}
	public void clickBillingAddress_PTGeloraDjaja() {
		driver.findElement(billingAddress_PTPinrangPantaiIndah).click();
	}
	public void clickBillingAddress_PTSukajadiSawitMekar() {
		driver.findElement(billingAddress_PTSukajadiSawitMekar).click();
	}
}
