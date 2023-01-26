package sales_order;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.SalesOrderPage;
import pages.SalesPersonName;
import pages.CustomerName;
import pages.Mastercard;
import pages.BillingAddress;
import pages.ShippingAddress;
import pages.Home;

public class Sales_PPN_64 {
	private static WebDriver driver = null;
	String noSalesOrder = "tes total komisi";
	String quantity = "5";
    String price = "73000";
    String ongkos_printing = "10000";
    String komisi_cust = "2000";
    SalesOrderPage objSalesOrder;
    SalesPersonName objSalesPerson;
    CustomerName objCustomer;
    Mastercard objMastercard;
    BillingAddress objBillingAddress;
    ShippingAddress objShippingAddress;
    Home objHome;
    
	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		objSalesOrder = new SalesOrderPage(driver);
		objSalesPerson = new SalesPersonName(driver);
		objCustomer = new CustomerName(driver);
		objMastercard = new Mastercard(driver);
		objBillingAddress = new BillingAddress(driver);
		objShippingAddress = new ShippingAddress(driver);
		objHome = new Home(driver);
		
		//ke url
		LoginPage.url_localhost(driver);
		
		//maximize window
		driver.manage().window().maximize();
		
		//login
		LoginPage.username_textbox(driver);
		LoginPage.password_textbox(driver);
		LoginPage.button_login(driver).click();
	}
	@Test
	public void firstTest() throws InterruptedException {
		
	    //ke halaman sales order
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		objHome.waitCardToBeClickable();
		objHome.click_rootmenuSales();
		objSalesOrder.click_menuSalesOrder();

	    //add new sales order
		objSalesOrder.click_addSalesOrder();
	    
	    //input no sales order
		objSalesOrder.input_noSalesOrder(noSalesOrder);
	    
	    //input sales person
		objSalesOrder.input_salesPerson("sujadi");	    
	  	objSalesPerson.clickSalesPerson_Sujadi(); 
	    
	    //input customer
	  	objSalesOrder.input_customer("gelora");
	    objCustomer.clickCustomer_PTGeloraDjaja(); 
	      
	    //input billing address
	    objSalesOrder.click_billingAddress();
	    objBillingAddress.clickBillingAddress_PTGeloraDjaja();
	    
	    //input shipping address
	    objSalesOrder.click_shippingAddress();
	    objShippingAddress.clickShippingAddress_PTGeloraDjaja();
	    
	    //add sales order line
	    objSalesOrder.click_addSalesOrderLine();
	    
	    //pilih tipe
	    objSalesOrder.click_tipeSalesOrderLine();
	    objSalesOrder.clickTipeSalesOrderLine_Box();
	    
	    //pilih mc
	    objSalesOrder.click_mastercard();
	    objMastercard.clickMastercard_PTGeloraDjaja_000638R01();
	    
	    //quantity
	    objSalesOrder.input_quantity(quantity);
	    
	    //unit
	    objSalesOrder.click_unitSalesOrderLine();
	    objSalesOrder.clickUnitSalesOrderLine_pcs();
	    
	    //harga
	    objSalesOrder.input_harga(price);
	    
	    //ongkos printing
	    objSalesOrder.input_ongkosPrinting(ongkos_printing);
	    
	    //komisi cust.
	    objSalesOrder.input_komisiCustomer(komisi_cust);
	    
	    //tanggal
	    objSalesOrder.click_calendar();
	    objSalesOrder.click_tanggalHariIni();
	    
	    //total komisi
	    String actualTotalKomisi = objSalesOrder.getTotalKomisiValue();
	    System.out.println("Actual Total Komisi : " + actualTotalKomisi);
	    
	    //verify
	    int qtySOL = Integer.parseInt(quantity);
	    int komisiCustSOL = Integer.parseInt(komisi_cust);
	    int totalKomisiSOL = komisiCustSOL * qtySOL;
	    String expectedTotalKomisi = Integer.toString(totalKomisiSOL);
	    System.out.println("Expected Total Komisi : " + expectedTotalKomisi);
	    assertEquals(actualTotalKomisi, expectedTotalKomisi);
	    
	}
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}
}
