package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SalesOrderPage {
	WebDriver driver = null;
	By tab_sales = By.cssSelector(".rootmenu-sales");
	By menu_salesorder = By.linkText("Sales Order");
	By clickAddButton = By.name("add");
	By no_salesOrder = By.name("noSalesorder");
	By sales_person = By.cssSelector(".so-salesperson .ant-select-selection-search-input");
	By input_customer = By.cssSelector(".so-customer .ant-select-selection-search-input");
	By billingAddress = By.cssSelector(".so-bilingaddress .ant-select-selection-search-input");
	By shippingAddress = By.cssSelector(".so-shippingaddress .ant-select-selection-search-input");
	By buttonAddSalesOrderLine = By.cssSelector(".fa-plus-circle");
	By tipeSalesOrderLine = By.cssSelector(".sol-type .ant-select-selection-search-input");
	By mastercard = By.cssSelector(".sol-mastercard .ant-select-selection-search-input");
	By quantity = By.cssSelector(".sol-quantity .ant-input-number-input-wrap");
	By unitSalesOrderLine = By.cssSelector(".sol-unitmeasurement .ant-select-selection-search-input");
	By harga = By.cssSelector(".sol-unitprice .ant-input-number-input-wrap");
	By ongkosPrinting = By.cssSelector(".sol-unitserviceprice .ant-input-number-input-wrap");
	By komisiCustomer = By.cssSelector(".sol-customercomission .ant-input-number-input-wrap");
	By tanggal = By.cssSelector(".sol-deliverydate .ant-picker");
	By tanggalHariIni = By.cssSelector(".ant-picker-cell-today > .ant-picker-cell-inner");
	By saveSalesOrderLine = By.xpath("(//button[@name='save-btn'])[2]");		
	By subtotal = By.cssSelector(".so-subtotal .ant-input-number-input");
	By ppn = By.cssSelector(".so-ppn .ant-input-number-input");
	By jumlahTotal = By.cssSelector("div.container main.layout section.content:nth-child(3) div.canvas div.canvas--content.add-edit-view-data form.ant-form.ant-form-vertical.data-form div.ant-row.ant-row-start.ant-row-top:nth-child(7) > div.ant-col.ant-col-7");
	By totalHarga = By.cssSelector(".sol-totalamount .ant-input-number-input");
	By totalKomisi = By.cssSelector(".sol-total-comission .ant-input-number-input");
	By editSalesOrder_2ndLine = By.xpath("(//a[contains(text(),'Edit')])[2]");
	By editSalesOrder_3rdLine = By.xpath("(//a[contains(text(),'Edit')])[3]");
	By tipeSalesOrderLine_Box = By.xpath("//div[contains(text(),'Box')]");
	By unitSalesOrderLine_pcs = By.xpath("//div[contains(text(),'pcs')]");
	By saveSalesOrder = By.name("save-btn");
	By kawasanBerikatCheckBox = By.cssSelector(".so-checkbox-iskawasan .ant-checkbox-input");
	private Actions actions;
	
	public SalesOrderPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
	}
	public void click_menuSalesOrder() {
		driver.findElement(menu_salesorder).click();
	}
	public void click_addSalesOrder() {
		driver.findElement(clickAddButton).click();
	}
	public void input_noSalesOrder(String noSalesOrder) {
		driver.findElement(no_salesOrder).sendKeys(noSalesOrder);
	}
	public void input_salesPerson(String salesPerson) {
		// Actions action = new Actions(driver);
		WebElement salesperson = driver.findElement(sales_person); 
		actions.moveToElement(salesperson).click().sendKeys(salesPerson).build().perform();
	}
	public void input_customer(String customerName) {
		// Actions action = new Actions(driver);
		WebElement customer = driver.findElement(input_customer);
		actions.moveToElement(customer).click().sendKeys(customerName).build().perform();
	}
	public void click_billingAddress() {
		driver.findElement(billingAddress).click();
	}
	public void click_shippingAddress() {
		driver.findElement(shippingAddress).click();
	}
	public void checkIfkawasanBerikat_isTicked() {
		WebElement kwsn = driver.findElement(kawasanBerikatCheckBox);
		boolean isSelected = kwsn.isSelected();
	}
	public void click_addSalesOrderLine() {
		driver.findElement(buttonAddSalesOrderLine).click();
	}
	public void click_tipeSalesOrderLine() {
		driver.findElement(tipeSalesOrderLine).click();
	}
	public void click_mastercard() {
		driver.findElement(mastercard).click();
	}
	public void input_quantity(String qty) {
		//Actions action = new Actions(driver);
		WebElement input_quantity = driver.findElement(quantity);
	    actions.moveToElement(input_quantity).click().sendKeys(qty).build().perform();
	}
	public void click_unitSalesOrderLine() {
		driver.findElement(unitSalesOrderLine).click();
	}
	public void input_harga(String price) {
		//Actions action = new Actions(driver);
		WebElement input_harga = driver.findElement(harga);
	    actions.moveToElement(input_harga).click().sendKeys(price).build().perform();
	}
	public void input_ongkosPrinting(String ongkos_printing) {
		//Actions action = new Actions(driver);
		WebElement input_ongkosPrinting = driver.findElement(ongkosPrinting);
	    actions.moveToElement(input_ongkosPrinting).click().sendKeys(ongkos_printing).build().perform();
	}
	public void input_komisiCustomer(String komisi_customer) {
		//Actions action = new Actions(driver);
		WebElement input_komisiCustomer = driver.findElement(komisiCustomer);
	    actions.moveToElement(input_komisiCustomer).click().sendKeys(komisi_customer).build().perform();
	}
	public void click_calendar() {
		driver.findElement(tanggal).click();
	}
	public void click_tanggalHariIni() {
		driver.findElement(tanggalHariIni).click();
	}
	public void click_saveSalesOrderLine() {
		driver.findElement(saveSalesOrderLine).click();
	}
	public String getSubtotalValue(){
        return driver.findElement(subtotal).getAttribute("value");
    }
	public String getPPNValue(){
        return driver.findElement(ppn).getAttribute("value");
    }
	public String getJumlahTotalValue(){
        return driver.findElement(jumlahTotal).getText();
    }
	public String getTotalHargaValue(){
        return driver.findElement(totalHarga).getAttribute("value");
    }
	public String getTotalKomisiValue(){
        return driver.findElement(totalKomisi).getAttribute("value");
    }
	public void edit_2ndSalesOrderLine() {
		driver.findElement(editSalesOrder_2ndLine).click();
	}
	public void edit_3rdSalesOrderLine() {
		driver.findElement(editSalesOrder_3rdLine).click();
	}
	public void click_saveSalesOrder() {
		driver.findElement(saveSalesOrder).click();
	}
	//tipe
	public void clickTipeSalesOrderLine_Box() {
		driver.findElement(tipeSalesOrderLine_Box).click();
	}
	//unit
	public void clickUnitSalesOrderLine_pcs() {
		driver.findElement(unitSalesOrderLine_pcs).click();
	}
}
