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

public class Sales_PPN_114 {

	private static WebDriver driver = null;
	String noSalesOrder = "tes box layer";
	String quantity = "600";
    String price_box = "10500";
    String price_layer1 = "11500";
    String price_layer2 = "9500";
    int expectedSubtotal = 0;
    int expectedPPN = 0;
    String salesPerson ="sujadi";
    String customer = "pinrang";
    SalesOrderPage objSalesOrder;
    SalesPersonName objSalesPerson;
    CustomerName objCustomer;
    Mastercard objMastercard;
    BillingAddress objBillingAddress;
    ShippingAddress objShippingAddress;
    Home objHome;

	@BeforeTest
	public void setUpTest() throws InterruptedException {
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
		objSalesOrder.input_salesPerson(salesPerson);	    
	  	objSalesPerson.clickSalesPerson_Sujadi();
	    
	    //input customer
	  	objSalesOrder.input_customer(customer);
	    objCustomer.clickCustomer_PTPinrangPantaiIndah();
	      
	    //input billing address
	    objSalesOrder.click_billingAddress();
	    objBillingAddress.clickBillingAddress_PTPinrangPantaiIndah();
	    
	    //input shipping address
	    objSalesOrder.click_shippingAddress();
	    objShippingAddress.clickShippingAddress_DiambilSendiri();
	    
	    //add sales order line
	    objSalesOrder.click_addSalesOrderLine();
	    
	    //pilih tipe
	    objSalesOrder.click_tipeSalesOrderLine();
	    objSalesOrder.clickTipeSalesOrderLine_Box();
	    
	    //pilih mc
	    objSalesOrder.click_mastercard();
	    objMastercard.clickMastercard_PTPinrangPantaiIndah_008420();
	    
	    //quantity
	    objSalesOrder.input_quantity(quantity);
	    
	    //unit
	    objSalesOrder.click_unitSalesOrderLine();
	    objSalesOrder.clickUnitSalesOrderLine_pcs();
	    
	    //harga
	    objSalesOrder.input_harga(price_box);
	    
	    //tanggal
	    objSalesOrder.click_calendar();
	    objSalesOrder.click_tanggalHariIni();
	    
	    //save sales order line
	    objSalesOrder.click_saveSalesOrderLine();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
	    
	    //edit price so line layer 1
	    objSalesOrder.edit_2ndSalesOrderLine();
	    Thread.sleep(1000);
	    
	    //input harga layer 1
	    objSalesOrder.input_harga(price_layer1);
	    
	    //save sales order line layer 1
	    objSalesOrder.click_saveSalesOrderLine();
	    
	    //edit price so line layer 2
	    objSalesOrder.edit_3rdSalesOrderLine();
	    Thread.sleep(1000);
	    
	    //input harga layer 2
	    objSalesOrder.input_harga(price_layer2);
	    
	    //save sales order line layer 2
	    objSalesOrder.click_saveSalesOrderLine();
	}
	
	@Test (priority = 1)
	public void firstTest() {
	    //ambil dan verify nilai subtotal
	    String actual_subtotal = objSalesOrder.getSubtotalValue();
	    System.out.println("actual subtotal : " + actual_subtotal);
	    
	    int qty_order = Integer.parseInt(quantity);
	    int harga_item_box = Integer.parseInt(price_box);
	    int harga_item_layer1 = Integer.parseInt(price_layer1);
	    int harga_item_layer2 = Integer.parseInt(price_layer2);
	    int subtotalBox = qty_order * harga_item_box;
	    int subtotalLayer1 = qty_order * harga_item_layer1;
	    int subtotalLayer2 = qty_order * harga_item_layer2;
	    expectedSubtotal = subtotalBox + subtotalLayer1 + subtotalLayer2;
	    String expected_subtotal = Integer.toString(expectedSubtotal);
	    System.out.println("expected subtotal : " + expected_subtotal);
	    assertEquals(actual_subtotal, expected_subtotal);
	}
	@Test (priority = 2)
	public void secondTest() {
	    
	    //ambil dan verify nilai ppn
	    String actual_ppn = objSalesOrder.getPPNValue();
	    System.out.println("actual ppn : " + actual_ppn);
	    
	    expectedPPN = (expectedSubtotal * 11) / 100;
	    String expected_ppn = Integer.toString(expectedPPN);
	    System.out.println("expected ppn : " + expected_ppn);
	    assertEquals(actual_ppn, expected_ppn);
	    
	}
	
	@Test (priority = 3)
	public void thirdTest() {
		
		//ambil dan verify nilai jumlah total
		String jumlahTotal = objSalesOrder.getJumlahTotalValue();
		String jumlah = jumlahTotal.replace(".", "");
	    String actual_jumlahTotal = jumlah.substring(15);
		System.out.println("actual jumlah total : " + actual_jumlahTotal);
		
		int expectedJumlahTotal = expectedSubtotal + expectedPPN;
		String expected_jumlahtotal = Integer.toString(expectedJumlahTotal);
		System.out.println("expected jumlah total : " + expected_jumlahtotal);
		assertEquals(actual_jumlahTotal, expected_jumlahtotal);
	}
	
	@AfterTest
	public void tearDownTest() {    
		driver.close();
	}


}
