package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorkOrderPage {
	WebDriver driver = null;
	By menu_workorder = By.linkText("Work Order (SPK)");
	By filter = By.className("ant-collapse-header-text");
	By submit_filter = By.name("submit");
	By no_workOrder = By.name("noWorkorder");
	By view_workorder = By.xpath("(//a[contains(text(), 'View')])[1]");
	By back = By.xpath("//button[@name='back-button']");
	By noSPKSet = By.name("noWorkorderGrouped");
	By outKertas = By.cssSelector(".wo_out input");
	By corr = By.cssSelector(".wo_corr input");
	By beratbox = By.cssSelector(".wo_weightperkg input");
	By beratorder = By.cssSelector(".wo_weight input");
	By berattrim = By.cssSelector(".wo_trimweight input");
	By BeratSubstance1 = By.cssSelector(".wo_gsmweight1 input");
	By BeratSubstance2 = By.cssSelector(".wo_gsmweight2 input");
	By BeratSubstance3 = By.cssSelector(".wo_gsmweight3 input");
	By BeratSubstance4 = By.cssSelector(".wo_gsmweight4 input");
	By BeratSubstance5 = By.cssSelector(".wo_gsmweight5 input");
	private WebDriverWait wait;
	private Actions actions;
	
	public WorkOrderPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		actions = new Actions(driver);
	}
	public void click_menuReleaseSalesOrder() {
		driver.findElement(menu_workorder).click();
	}
	public void click_filter() {
		//wait.until(ExpectedConditions.elementToBeClickable(releaseSalesOrder));
		driver.findElement(filter).click();
	}
	public void filter_byNoWorkOrder(String out) {
		driver.findElement(no_workOrder).sendKeys(out);
	}
	public void click_submitFilter() {
		driver.findElement(submit_filter).click();
	}
	public void click_viewWorkorder() {
		driver.findElement(view_workorder).click();
	}
	public void click_back() {
		driver.findElement(back).click();
	}
	public String get_noSPKSet() {
		return driver.findElement(noSPKSet).getAttribute("value");
	}
	public String get_outKertas() {
		return driver.findElement(outKertas).getAttribute("value");
	}
	public String get_corr() {
		return driver.findElement(corr).getAttribute("value");
	}
	public String get_beratBox() {
		return driver.findElement(beratbox).getAttribute("value");
	}
	public String get_beratOrder() {
		return driver.findElement(beratorder).getAttribute("value");
	}
	public String get_beratTrim() {
		return driver.findElement(berattrim).getAttribute("value");
	}
	public String get_beratSubstance1() {
		return driver.findElement(BeratSubstance1).getAttribute("value");
	}
	public String get_beratSubstance2() {
		return driver.findElement(BeratSubstance2).getAttribute("value");
	}
	public String get_beratSubstance3() {
		return driver.findElement(BeratSubstance3).getAttribute("value");
	}
	public String get_beratSubstance4() {
		return driver.findElement(BeratSubstance4).getAttribute("value");
	}
	public String get_beratSubstance5() {
		return driver.findElement(BeratSubstance5).getAttribute("value");
	}
}
