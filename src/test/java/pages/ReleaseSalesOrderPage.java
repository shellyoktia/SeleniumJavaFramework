package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReleaseSalesOrderPage {
WebDriver driver = null;
	
	By menu_releasesalesorder = By.linkText("Release Sales Order");
	By menu_approvalreleasesalesorder = By.linkText("Release Sales Order - Approval");
	By filter = By.className("ant-collapse-header-text");
	By no_salesOrder = By.name("noSalesorder");
	By submit_filter = By.name("submit");
	By releaseSalesOrder = By.linkText("Release");
	By yaReleaseSalesOrder = By.name("save-btn");
	By assign = By.name("save-btn");
	By yaAssign = By.cssSelector(".ant-col > .ant-btn-primary");
	By notifBox = By.className("ant-notification-notice-message");
	By message = By.className("ant-notification-notice-description");
	By approveReleaseSalesOrder = By.xpath("(//a[contains(text(), 'Approve')])[1]");
	By yaApproveReleaseSalesOrder = By.name("save-btn");
	By waitApprove = By.className("ant-notification-notice-description");
	private WebDriverWait wait;
	private Actions actions;
	
	public ReleaseSalesOrderPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		actions = new Actions(driver);
	}
	public void click_menuReleaseSalesOrder() {
		driver.findElement(menu_releasesalesorder).click();
	}
	public void click_menuApprovaReleaseSalesOrder() {
		driver.findElement(menu_approvalreleasesalesorder).click();
	}
	public void click_filter() {
		wait.until(ExpectedConditions.elementToBeClickable(releaseSalesOrder));
		driver.findElement(filter).click();
	}
	public void filter_byNoSalesOrder(String noSalesOrder) {
		driver.findElement(no_salesOrder).sendKeys(noSalesOrder);
	}
	public void click_submitFilter() {
		driver.findElement(submit_filter).click();
	}
	public void click_releaseSalesOrder() {
		WebElement button_release = driver.findElement(releaseSalesOrder);
	    actions.moveToElement(button_release).click().build().perform();
	
	}
	public void click_YaReleaseSalesOrder() {
		driver.findElement(yaReleaseSalesOrder).click();
	}
	public void click_assign() {
		wait.until(ExpectedConditions.elementToBeClickable(assign));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.findElement(assign).click();
	}
	public void click_YaAssign() {
		wait.until(ExpectedConditions.elementToBeClickable(yaAssign));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		driver.findElement(yaAssign).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		
	}
	public String getMessage() {
		wait.until(ExpectedConditions.presenceOfElementLocated(notifBox));
		return driver.findElement(message).getText();	
	}
	public void click_approveReleaseSalesOrder() {
		driver.findElement(approveReleaseSalesOrder).click();
	}
	public void click_YaApproveReleaseSalesOrder() {
		driver.findElement(yaApproveReleaseSalesOrder).click();
	}
	public void wait_approve() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(waitApprove));
		Thread.sleep(2000);
	}
}
