package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuratJalanReturApprovalPage {
	WebDriver driver = null;
	private WebDriverWait wait;
	private Actions actions;
	By menu_SuratJalanReturApproval = By.linkText("Surat Jalan / Delivery Order - Retur - Approval");
	By filter = By.className("ant-collapse-header-text");
	By submit_filter = By.name("submit");
	By no_workOrderGrouped = By.name("noWorkorderGrouped");
	By approve = By.xpath("(//a[contains(text(), 'Approve')])[1]");
	By confirm = By.name("confirm");
	
	public SuratJalanReturApprovalPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	public void click_menuSuratJalanReturApproval() {
		wait.until(ExpectedConditions.elementToBeClickable(menu_SuratJalanReturApproval));
		driver.findElement(menu_SuratJalanReturApproval).click();
	}
	public void click_filter() throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(approve));
		driver.findElement(filter).click();
	}
	public void filter_byNoWorkOrderGrouped(String no_wo_grouped) {
		driver.findElement(no_workOrderGrouped).sendKeys(no_wo_grouped);
	}
	public void click_submitFilter() throws InterruptedException {
		driver.findElement(submit_filter).click();
		wait.until(ExpectedConditions.elementToBeClickable(approve));
		Thread.sleep(5000);
	}
	public void approve_suratJalanretur() {
		wait.until(ExpectedConditions.elementToBeClickable(approve));
		driver.findElement(approve).click();
	}
	public void clickConfrim_approveSuratJalanretur() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(confirm));
		driver.findElement(confirm).click();
		Thread.sleep(3000);
	}
}
