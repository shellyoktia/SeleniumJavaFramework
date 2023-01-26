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
	By menu_whitelistreleasesalesorder = By.linkText("Whitelist Release SPK (bypass payment delay) - Approval");
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
	By loadingPage = By.className("ant-spin-spinning");
	private WebDriverWait wait;
	private Actions actions;
	
	public ReleaseSalesOrderPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		actions = new Actions(driver);
	}
	public void click_menuReleaseSalesOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(menu_releasesalesorder));
		driver.findElement(menu_releasesalesorder).click();
	}
	public void click_menuApprovaReleaseSalesOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(menu_approvalreleasesalesorder));
		driver.findElement(menu_approvalreleasesalesorder).click();
	}
	public void click_menuWhitelistReleaseSalesOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(menu_whitelistreleasesalesorder));
		driver.findElement(menu_whitelistreleasesalesorder).click();
	}
	public void click_filter() {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(loadingPage));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingPage));
		driver.findElement(filter).click();
	}
	public void filter_byNoSalesOrder(String noSalesOrder) {
		driver.findElement(no_salesOrder).sendKeys(noSalesOrder);
	}
	public void click_submitFilter() {
		driver.findElement(submit_filter).click();
	}
	public void click_releaseSalesOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(releaseSalesOrder));
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(message));
		wait.until(ExpectedConditions.presenceOfElementLocated(message));
		return driver.findElement(message).getText();
	}
	public void click_approveReleaseSalesOrder() {
		driver.findElement(approveReleaseSalesOrder).click();
	}
	public void click_YaApproveReleaseSalesOrder() {
		driver.findElement(yaApproveReleaseSalesOrder).click();
	}
	public void wait_approve() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(waitApprove));
		wait.until(ExpectedConditions.presenceOfElementLocated(waitApprove));
		Thread.sleep(2000);
	}
}
