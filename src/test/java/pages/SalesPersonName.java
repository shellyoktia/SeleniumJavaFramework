package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SalesPersonName {
	WebDriver driver = null;
	By salesPerson_Sujadi = By.xpath("//div[contains(text(),'Sujadi')]");
	By salesPerson_Kuswadi = By.xpath("//div[contains(text(),'Kuswadi')]");
	
	public SalesPersonName(WebDriver driver) {
		this.driver = driver;
	}
	public void clickSalesPerson_Sujadi() {
		driver.findElement(salesPerson_Sujadi).click(); 
	}
	public void clickSalesPerson_Kuswadi() {
		driver.findElement(salesPerson_Kuswadi).click(); 
	}

}
