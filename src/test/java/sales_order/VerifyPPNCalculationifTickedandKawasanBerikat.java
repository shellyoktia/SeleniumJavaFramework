package sales_order;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class VerifyPPNCalculationifTickedandKawasanBerikat {
	
	private static WebDriver driver = null;
	SalesOrderPage objSalesOrder;
    SalesPersonName objSalesPerson;
    CustomerName objCustomer;
    Mastercard objMastercard;
    BillingAddress objBillingAddress;
    ShippingAddress objShippingAddress;
    Home objHome;
    String noSalesOrder = "kawasan berikat";
    String quantity = "59";
    String price = "190000";
    String expected_ppn = "0";
	
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
		}
		
		@Test
		public void firstTest() {
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
			objSalesOrder.input_salesPerson("sujadi");	    
		  	objSalesPerson.clickSalesPerson_Sujadi(); 
		    
		    //input customer
		  	objSalesOrder.input_customer("sukajadi sawit");
		    objCustomer.clickCustomer_PTSukajadiSawitMekar();
			  
		    //input billing address
		    objSalesOrder.click_billingAddress();
		    objBillingAddress.clickBillingAddress_PTSukajadiSawitMekar();
		    
		    //input shipping address
		    objSalesOrder.click_shippingAddress();
		    objShippingAddress.clickShippingAddress_PTSukajadiSawitMekar();
		    
		    //add sales order line
		    objSalesOrder.click_addSalesOrderLine();
		    
		    //pilih tipe
		    objSalesOrder.click_tipeSalesOrderLine();
		    objSalesOrder.clickTipeSalesOrderLine_Box();
		    
		    //pilih mc
		    objSalesOrder.click_mastercard();
		    objMastercard.clickMastercard_PTSukajadiSawitMekar_006087_R1();
		    
		    //quantity
		    objSalesOrder.input_quantity(quantity);
		    
		    //unit
		    objSalesOrder.click_unitSalesOrderLine();
		    objSalesOrder.clickUnitSalesOrderLine_pcs();
		    
		    //harga
		    objSalesOrder.input_harga(price);
		    
		    //tanggal
		    objSalesOrder.click_calendar();
		    objSalesOrder.click_tanggalHariIni();
		    
		    //save sales order line
		    objSalesOrder.click_saveSalesOrderLine();
		    
		    //verify if kawasan berikat is ticked
		    WebElement kwsn = driver.findElement(By.cssSelector(".so-checkbox-iskawasan .ant-checkbox-input"));
		    
		    //ambil nilai ppn
		    String actual_ppn = objSalesOrder.getPPNValue();
		    System.out.println("actual ppn : " + actual_ppn);
		    System.out.println("expected ppn : " + expected_ppn);
		    
			//verify kawasan berikat is ticked and verify ppn value
			if(!kwsn.isSelected()) {
				  System.out.println("ini tidak dicentang");
				  assertNotEquals(actual_ppn, expected_ppn);
			}else if(kwsn.isSelected()){
				  assertEquals(actual_ppn, expected_ppn);
				  System.out.println("ini dicentang");
			}
		}
		
		@AfterTest
		public void tearDownTest() {
			driver.close();
		}
	
}
