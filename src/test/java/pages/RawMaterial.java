package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RawMaterial {
	WebDriver driver = null;
	By rawMaterial_8_M150 = By.xpath("//div[contains(text(),'8 - M150 - A - PA69 - MPKA')]");
	
	public RawMaterial(WebDriver driver) {
		this.driver = driver;
	}
	public void clickrawMaterial_8_M150() {
		driver.findElement(rawMaterial_8_M150).click();
	}

}
