package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomerName {
	WebDriver driver = null;
	
	By customer_PTBeautyKasatama = By.xpath("//div[contains(text(),'PT BEAUTY KASATAMA INDONESIA')]");
	By customer_PTJayamasMedica = By.xpath("//div[contains(text(),'JA186 - PT JAYAMAS MEDICA INDUSTRI TBK')]");
	By customer_PTPinrangPantaiIndah = By.xpath("//div[contains(text(),'PI685 - PT PINRANG PANTAI INDAH')]");
	By customer_PTGeloraDjaja = By.xpath("//div[contains(text(),'PT GELORA DJAJA')]");
	By customer_PTSukajadiSawitMekar = By.xpath("//div[contains(text(),'SU566 - PT SUKAJADI SAWIT MEKAR')]");
	
	public CustomerName(WebDriver driver) {
		this.driver = driver;
	}
	public void clickCustomer_PTBeautyKasatama() {
		driver.findElement(customer_PTBeautyKasatama).click(); 
	}
	public void clickCustomer_PTJayamasMedica() {
		driver.findElement(customer_PTJayamasMedica).click(); 
	}
	public void clickCustomer_PTPinrangPantaiIndah() {
		driver.findElement(customer_PTPinrangPantaiIndah).click(); 
	}
	public void clickCustomer_PTGeloraDjaja() {
		driver.findElement(customer_PTGeloraDjaja).click(); 
	}
	public void clickCustomer_PTSukajadiSawitMekar() {
		driver.findElement(customer_PTSukajadiSawitMekar).click(); 
	}
}
