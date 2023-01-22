package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShippingAddress {
	WebDriver driver = null;
	By shippingAddress_PTBeautyKasatama = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'PERGUDANGAN ROMOKALISARI BLOK')]");
	By shippingAddress_PTjayamasMedica = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'PERGUDANGAN')]");
	By shippingAddress_DiambilSendiri = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'DIAMBIL SENDIRI')]");
	By shippingAddress_PTGeloraDjaja = By.xpath("(//label[contains(text(),'Default Shipping Address')]/following::div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'BUNTARAN')])[2]");
	By shippingAddress_PTSukajadiSawitMekar = By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), 'JALAN H.M. ARSYAD')]");
	
	public ShippingAddress(WebDriver driver) {
		this.driver = driver;
	}
	public void clickShippingAddress_PTBeautyKasatama() {
		driver.findElement(shippingAddress_PTBeautyKasatama).click();
	}
	public void clickShippingAddress_PTJayamasMedica() {
		driver.findElement(shippingAddress_PTjayamasMedica).click();
	}
	public void clickShippingAddress_DiambilSendiri() {
		driver.findElement(shippingAddress_DiambilSendiri).click();
	}
	public void clickShippingAddress_PTGeloraDjaja() {
		driver.findElement(shippingAddress_PTGeloraDjaja).click();
	}
	public void clickShippingAddress_PTSukajadiSawitMekar() {
		driver.findElement(shippingAddress_PTSukajadiSawitMekar).click();
	}
}
