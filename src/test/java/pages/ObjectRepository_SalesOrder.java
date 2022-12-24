package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObjectRepository_SalesOrder {
	private static WebElement element = null;
	
	public static WebElement harga(WebDriver driver) {
		element = driver.findElement(By.cssSelector(".sol-unitprice .ant-input-number-input"));
		return element;
	}
	
	public static WebElement filter_releaseSalesOrder(WebDriver driver) {
		element = driver.findElement(By.className("filter-block__title"));
		return element;
	}

}
