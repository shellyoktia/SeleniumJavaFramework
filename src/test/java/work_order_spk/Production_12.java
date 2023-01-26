package work_order_spk;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.VariableGlobalMain;
import pages.Home;
import pages.ReleaseSalesOrderPage;
import pages.WorkOrderPage;

public class Production_12 {

	private static WebDriver driver = null;
	private static String isiTable, outkrts, corr, beratbox, beratorder, berattrim, BeratSubstance1, BeratSubstance2, BeratSubstance3, BeratSubstance4, BeratSubstance5;
	private static ArrayList<String> allValues = new ArrayList<String>();
	
	String noSalesOrder;
	//String noSalesOrder = "2023 63";
	String qtySalesOrder;
	//String qtySalesOrder = "150";
	int qty;
	float lebarKertasMinimum_box, width_box,weightSheetNettoGram_box,weight_box, beratBoxBox;
	int outkertas_box, constantDoubleJoint_box,out_box,outKnife_box,corrBox;
	float lebarKertasMinimum_partisi, width_partisi, weightSheetNettoGram_partisi,weight_partisi, beratBoxPartisi;
	int outkertas_partisi, constantDoubleJoint_partisi, out_partisi, outKnife_partisi,corrPartisi;
	Home objHome;
	ReleaseSalesOrderPage objReleaseSalesOrderPage;
	WorkOrderPage objWorkOrderPage;
	
	
	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		objHome = new Home(driver);
		objReleaseSalesOrderPage = new ReleaseSalesOrderPage(driver);
		objWorkOrderPage = new WorkOrderPage(driver);
		noSalesOrder = VariableGlobalMain.noSalesOrderBoxPartisi;
		qtySalesOrder = VariableGlobalMain.qtySalesOrderBoxPartisi;
		qty = Integer.parseInt(qtySalesOrder);
		
		//ke url
		LoginPage.url_localhost(driver);
		
		//maximize window
		driver.manage().window().maximize();
		
		//login
		LoginPage.username_textbox(driver);
		LoginPage.password_textbox(driver);
		LoginPage.button_login(driver).click();
		
		//ke halaman release sales order buat ambil no spk
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
		objHome.waitCardToBeClickable();
		objHome.click_rootmenuSales();
		objReleaseSalesOrderPage.click_menuReleaseSalesOrder();
				
		
		
	}
	
	@Test (priority = 1)
	 public void firstTes() throws InterruptedException {
				//filter by no sales order
				objReleaseSalesOrderPage.click_filter();
				objReleaseSalesOrderPage.filter_byNoSalesOrder(noSalesOrder);
				objReleaseSalesOrderPage.click_submitFilter();
				Thread.sleep(5000);
				//ambil no spk per item
				WebElement Table = driver.findElement(By.xpath("//tbody[@class='ant-table-tbody']"));
				List<WebElement> rows_table = Table.findElements(By.tagName("tr"));
				int rows_count = rows_table.size();
				ArrayList<String> noSPKperItem = new ArrayList<String>();
				
				//'Loop will execute for all the rows of the table'
				for (int row = 0; row < rows_count; row++) {
				    //'To locate columns(cells) of that specific row'
				    List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));

				    //'no spk item ada dikolom ke 4'
				    int column_no_spk_item = 4;

				    isiTable = Columns_row.get(column_no_spk_item).getText();

				    System.out.println(isiTable);
				    noSPKperItem.add(isiTable);
				}
				//filter by no spk set di halaman work order
				int array_count = noSPKperItem.size();
				System.out.print(array_count);
				for (int arr = 1; arr < array_count; arr++) {
				    String out = noSPKperItem.get(arr);
				    
				    //ke halaman work order
				    objHome.click_rootmenuProduction();
				    objWorkOrderPage.click_menuWorkOrder();
				    //filter
				    objWorkOrderPage.click_filter();
				    objWorkOrderPage.filter_byNoWorkOrder(out);
				    objWorkOrderPage.click_submitFilter();
				    objWorkOrderPage.click_viewWorkorder();
				    
				    Thread.sleep(7000);
				    String noSPKset = objWorkOrderPage.get_noSPKSet();
				    outkrts = objWorkOrderPage.get_outKertas();
					corr = objWorkOrderPage.get_corr();
					beratbox = objWorkOrderPage.get_beratBox();
					beratorder = objWorkOrderPage.get_beratOrder();
					berattrim = objWorkOrderPage.get_beratTrim();
					BeratSubstance1 = objWorkOrderPage.get_beratSubstance1();
					BeratSubstance2 = objWorkOrderPage.get_beratSubstance2();
					BeratSubstance3 = objWorkOrderPage.get_beratSubstance3();
					BeratSubstance4 = objWorkOrderPage.get_beratSubstance4();
					BeratSubstance5 = objWorkOrderPage.get_beratSubstance5();
					System.out.println("no spk set " + noSPKset);
					VariableGlobalMain.noSpkSetBoxPartisi = noSPKset;
					System.out.println("out kertas " + outkrts);
					System.out.println("corr " + corr);
					System.out.println("berat box " + beratbox);
					System.out.println("berat order " + beratorder);
					System.out.println("berat trim " + berattrim);
					System.out.println("berat substance 1 " + BeratSubstance1);
					System.out.println("berat substance 2 " + BeratSubstance2);
					System.out.println("berat substance 3 " + BeratSubstance3);
					System.out.println("berat substance 4 " + BeratSubstance4);
					System.out.println("berat substance 5 " + BeratSubstance5);
				    objWorkOrderPage.click_back();
				    
				    allValues.add(outkrts);
				    allValues.add(corr);
				    allValues.add(beratbox);
				    allValues.add(beratorder);
				    allValues.add(berattrim);
				    allValues.add(BeratSubstance1);
				    allValues.add(BeratSubstance2);
				    allValues.add(BeratSubstance3);
				    allValues.add(BeratSubstance4);
				    allValues.add(BeratSubstance5);
				    
				}
	}
	
	@Test (priority = 2)
	public void calculate_outKertas_box() {
				//calculate out kertas box
				lebarKertasMinimum_box = 1500;
				width_box = 366;

				//int outkertas_box = Math.floor(lebarKertasMinimum_box / width_box)
				//out dengan nilai lebarkertasminumim terbesar (database mcsheet)
				outkertas_box = 4;

				int expected_outkrts_box = outkertas_box;
				String outkrts_box = allValues.get(0);
				int actual_outkrts_box = Integer.parseInt(outkrts_box);
				assertEquals(actual_outkrts_box, expected_outkrts_box);
				
				System.out.println("actual_otkrts_box " + actual_outkrts_box);
				System.out.println("expected_outkrts_box " + expected_outkrts_box);
	}
	@Test (priority = 3)
	public void calculate_corr_box() {
				//calculate corr box
				constantDoubleJoint_box = 1;
				out_box = outkertas_box;
				outKnife_box = 1;
				
				corrBox = (int) (constantDoubleJoint_box * Math.floor((qty / out_box) / outKnife_box));
				int  expected_corr_box = corrBox;
				String corr_box = allValues.get(1);
				int actual_corr_box = Integer.parseInt(corr_box);
				assertEquals(actual_corr_box, expected_corr_box);
				
				System.out.println("actual_corr_box " + actual_corr_box);
				System.out.println("expected_corr_box " + expected_corr_box);
	}
	@Test (priority = 4)
	public void calculate_beratBox_box() {

				//calculate weight box
				weightSheetNettoGram_box = 133.08f;
				weight_box = (constantDoubleJoint_box * (weightSheetNettoGram_box * qty)) / 1000;
				
				//calculate berat box box
				beratBoxBox = (float) (Math.ceil(((weight_box / qty) * 1000) * 100) / 100);
				
				Float expected_beratbox_box = beratBoxBox;
				String beratbox_box = allValues.get(2);
				Float actual_beratbox_box = Float.parseFloat(beratbox_box); 
				assertEquals(actual_beratbox_box, expected_beratbox_box);
				
				System.out.println("actual_beratbox_box " + actual_beratbox_box);
				System.out.println("expected_beratbox_box " + expected_beratbox_box);
	}
	@Test (priority = 5)
	public void calculate_beratOrder_box() {
	
				//calculate berat order box
				float beratOrderBox = (float) (Math.ceil(weight_box * 100) / 100);
				
				Float expected_beratorder_box = beratOrderBox;
				String beratorder_box = allValues.get(3);
				Float actual_beratorder_box = Float.parseFloat(beratorder_box);
				assertEquals(actual_beratorder_box, expected_beratorder_box);
				
				System.out.println("actual_beratorder_box " + actual_beratorder_box);
				System.out.println("expected_beratorder_box " + expected_beratorder_box);
	}
	@Test (priority = 6)
	public void calculate_beratTrim_box() {
				//calculate berat trim box
				float trimWasteGram_box = 13.09f;
				float beratTrimBox = ((qty / out_box) * trimWasteGram_box) / 1000;
				
				Float expected_berattrim_box = beratTrimBox;
				String berattrim_box = allValues.get(4);
				Float actual_berattrim_box = Float.parseFloat(berattrim_box);
				assertEquals(actual_berattrim_box, expected_berattrim_box);
				
				System.out.println("actual_berattrim_box " + actual_berattrim_box);
				System.out.println("expected_berattrim_box " + expected_berattrim_box);
	}
	@Test (priority = 7)
	public void calculate_beratSubstanceBox1() {
				//calculate substance 1 box
				float qValuebox1 = 150;
				float sheetLengthBox1 = 824;
				float minLebarMmSheetBox1 = 1500;
				float identifierBox1 = 1;
				float beratSubstanceBox1 = ((((((qValuebox1 / 1000) * sheetLengthBox1) / 1000) * minLebarMmSheetBox1) / 1000) * identifierBox1) * corrBox;
				
				BigDecimal beratSubstanceBox_1 = new BigDecimal(beratSubstanceBox1);
				beratSubstanceBox_1 = beratSubstanceBox_1.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance1_box = beratSubstanceBox_1.floatValue();
				
				String actual_beratsubstance1box = allValues.get(5);
				Float actual_beratsubstance1_box =  Float.parseFloat(actual_beratsubstance1box);
				
				assertEquals(actual_beratsubstance1_box, expected_beratsubstance1_box);
				System.out.println("actual_beratsubstance1_box " + actual_beratsubstance1_box);
				System.out.println("expected_beratsubstance1_box " + expected_beratsubstance1_box);
	}
	@Test (priority = 8)
	public void calculate_beratSubstanceBox2() {
				//calculate substance 2 box
				float qValuebox2 = 125;
				float sheetLengthBox2 = 824;
				float minLebarMmSheetBox2 = 1500;
				float identifierBox2 = 1.33f;
				float beratSubstanceBox2 = ((((((qValuebox2 / 1000) * sheetLengthBox2) / 1000) * minLebarMmSheetBox2) / 1000) * identifierBox2) * 
				corrBox;
				
				BigDecimal beratSubstanceBox_2 = new BigDecimal(beratSubstanceBox2);
				beratSubstanceBox_2 = beratSubstanceBox_2.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance2_box = beratSubstanceBox_2.floatValue();
				
				String actual_beratsubstance2box = allValues.get(6);
				Float actual_beratsubstance2_box =  Float.parseFloat(actual_beratsubstance2box);
				
				assertEquals(actual_beratsubstance2_box, expected_beratsubstance2_box);
				System.out.println("actual_beratsubstance2_box " + actual_beratsubstance2_box);
				System.out.println("expected_beratsubstance2_box " + expected_beratsubstance2_box);
	}
	@Test (priority = 9)
	public void calculate_beratSubstanceBox3() {
				//calculate substance 3 box
				float qValuebox3 = 125;
				float sheetLengthBox3 = 824;
				float minLebarMmSheetBox3 = 1500;
				float identifierBox3 = 1;
				float beratSubstanceBox3 = ((((((qValuebox3 / 1000) * sheetLengthBox3) / 1000) * minLebarMmSheetBox3) / 1000) * identifierBox3) * 
				corrBox;
				
				BigDecimal beratSubstanceBox_3 = new BigDecimal(beratSubstanceBox3);
				beratSubstanceBox_3 = beratSubstanceBox_3.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance3_box = beratSubstanceBox_3.floatValue();
				
				String actual_beratsubstance3box = allValues.get(7);
				Float actual_beratsubstance3_box =  Float.parseFloat(actual_beratsubstance3box);
				
				assertEquals(actual_beratsubstance3_box, expected_beratsubstance3_box);
				System.out.println("actual_beratsubstance3_box " + actual_beratsubstance3_box);
				System.out.println("expected_beratsubstance3_box " + expected_beratsubstance3_box);
	}
	@Test (priority = 10)
	public void calculate_beratSubstanceBox4() {
				//calculate substance 4 box
				float qValuebox4 = 0;
				float sheetLengthBox4 = 824;
				float minLebarMmSheetBox4 = 1500;
				float identifierBox4 = 1.42f;
				float beratSubstanceBox4 = ((((((qValuebox4 / 1000) * sheetLengthBox4) / 1000) * minLebarMmSheetBox4) / 1000) * identifierBox4) * corrBox;
				
				BigDecimal beratSubstanceBox_4 = new BigDecimal(beratSubstanceBox4);
				beratSubstanceBox_4 = beratSubstanceBox_4.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance4_box = beratSubstanceBox_4.floatValue();
				
				String actual_beratsubstance4box = allValues.get(8);
				Float actual_beratsubstance4_box =  Float.parseFloat(actual_beratsubstance4box);

				assertEquals(actual_beratsubstance4_box, expected_beratsubstance4_box);
				//assertEquals(actual_beratsubstance4_box, expected_beratsubstance4_box);
				//assertEquals(actual_beratsubstance4_box, expected_beratsubstance4_box);
				System.out.println("actual_beratsubstance4_box " + actual_beratsubstance4_box);
				System.out.println("expected_beratsubstance4_box " + expected_beratsubstance4_box);
	}
	@Test (priority = 11)
	public void calculate_beratSubstanceBox5() {
				//calculate substance 5 box
				float qValuebox5 = 0;
				float sheetLengthBox5 = 824;
				float minLebarMmSheetBox5 = 1500;
				float identifierBox5 = 1;
				float beratSubstanceBox5 = ((((((qValuebox5 / 1000) * sheetLengthBox5) / 1000) * minLebarMmSheetBox5) / 1000) * identifierBox5) * 
				corrBox;
				
				BigDecimal beratSubstanceBox_5 = new BigDecimal(beratSubstanceBox5);
				beratSubstanceBox_5 = beratSubstanceBox_5.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance5_box = beratSubstanceBox_5.floatValue();
				
				String actual_beratsubstance5box = allValues.get(9);
				Float actual_beratsubstance5_box =  Float.parseFloat(actual_beratsubstance5box);
				
				assertEquals(actual_beratsubstance5_box, expected_beratsubstance5_box);
				System.out.println("actual_beratsubstance5_box " + actual_beratsubstance5_box);
				System.out.println("expected_beratsubstance5_box " + expected_beratsubstance5_box);
	}
	@Test (priority = 12)
	public void calculate_outKertas_partisi() {
		//calculate out kertas partisi
		lebarKertasMinimum_partisi = 1150;
		width_partisi = 140;
		//int outkertas_partisi = Math.floor(lebarKertasMinimum_partisi / width_partisi)
		//out dengan nilai lebarkertasminumim terbesar (dataase mcsheet)
		outkertas_partisi = 5;
		
		int expected_outkrts_partisi= outkertas_partisi;
		String outkrts_partisi= allValues.get(10);
		int actual_outkrts_partisi = Integer.parseInt(outkrts_partisi);
		assertEquals(actual_outkrts_partisi, expected_outkrts_partisi);
		
		System.out.println("actual_otkrts_partisi " + actual_outkrts_partisi);
		System.out.println("expected_outkrts_partisi " + expected_outkrts_partisi);
	}
	@Test (priority = 13)
	public void calculate_corr_partisi() {
				//calculate corr partisi
				constantDoubleJoint_partisi = 1;
				out_partisi = outkertas_partisi;
				outKnife_partisi = 1;
				corrPartisi = (int) (constantDoubleJoint_partisi * Math.floor((qty / out_partisi) / outKnife_partisi));
				
				int expected_corr_partisi = corrPartisi;
				String corr_partisi = allValues.get(11);
				int actual_corr_partisi = Integer.parseInt(corr_partisi);
				assertEquals(actual_corr_partisi, expected_corr_partisi);
				
				System.out.println("actual_corr_partisi " + actual_corr_partisi);
				System.out.println("expected_corr_partisi " + expected_corr_partisi);
	}
	@Test (priority = 14)
	public void calculate_beratBox_partisi() {

				//calculate weight partisi
				weightSheetNettoGram_partisi = 53.5f;
				weight_partisi = (constantDoubleJoint_partisi * (weightSheetNettoGram_partisi * qty)) / 1000;

				//calculate berat box partisi
				beratBoxPartisi = (float)(Math.ceil(((weight_partisi / qty) * 1000) * 100) / 100);
				
				Float expected_beratbox_partisi = beratBoxPartisi;
				String beratbox_partisi = allValues.get(12);
				Float actual_beratbox_partisi = Float.parseFloat(beratbox_partisi); 
				assertEquals(actual_beratbox_partisi, expected_beratbox_partisi);
				
				System.out.println("actual_beratbox_partisi " + actual_beratbox_partisi);
				System.out.println("expected_beratbox_partisi " + expected_beratbox_partisi);
	}
	@Test (priority = 15)
	public void calculate_beratOrder_partisi() {
	
				//calculate berat order partisi
				float beratOrderPartisi = (float) (Math.ceil(weight_partisi * 100) / 100);
				
				Float expected_beratorder_partisi = beratOrderPartisi;
				String beratorder_partisi = allValues.get(13);
				Float actual_beratorder_partisi = Float.parseFloat(beratorder_partisi);
				assertEquals(actual_beratorder_partisi, expected_beratorder_partisi);
				
				System.out.println("actual_beratorder_partisi " + actual_beratorder_partisi);
				System.out.println("expected_beratorder_partisi " + expected_beratorder_partisi);
	}
	@Test (priority = 16)
	public void calculate_beratTrim_partisi() {
				//calculate berat trim partisi
				float trimWasteGram_partisi = 18.91f;
				float lengthPartisi = 918;
				float lebarKertasPartisi = 50;
				//float gsmPartisi = (125 + (125 * 1.36)) + 125
				float gsmPartisi = 416.25f ;
				//float beratTrimPartisi = qty / out_partisi * trimWasteGram_partisi / 1000
				float beratTrimPartisi = (float) (Math.ceil((corrPartisi * lengthPartisi / 1000 * lebarKertasPartisi / 1000 * gsmPartisi / 1000) * 100)/100);

				Float expected_berattrim_partisi = beratTrimPartisi;
				String berattrim_partisi = allValues.get(14);
				Float actual_berattrim_partisi = Float.parseFloat(berattrim_partisi);
				assertEquals(actual_berattrim_partisi, expected_berattrim_partisi);
				
				System.out.println("actual_berattrim_partisi " + actual_berattrim_partisi);
				System.out.println("expected_berattrim_partisi " + expected_berattrim_partisi);
	}
	@Test (priority = 17)
	public void calculate_beratSubstancePartisi1() {
				//calculate substance 1 partisi
				float qValuepartisi1 = 125;
				float sheetLengthPartisi1 = 918;
				float minLebarMmSheetPartisi1 = 750;
				float identifierPartisi1 = 1;
				float beratSubstancePartisi1 = ((((((qValuepartisi1 / 1000) * sheetLengthPartisi1) / 1000) * minLebarMmSheetPartisi1) / 
				1000) * identifierPartisi1) * corrPartisi;
				
				BigDecimal beratSubstancePartisi_1 = new BigDecimal(beratSubstancePartisi1);
				beratSubstancePartisi_1 = beratSubstancePartisi_1.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance1_partisi = beratSubstancePartisi_1.floatValue();
				
				String actual_beratsubstance1Partisi = allValues.get(15);
				Float actual_beratsubstance1_partisi =  Float.parseFloat(actual_beratsubstance1Partisi);
				
				assertEquals(actual_beratsubstance1_partisi, expected_beratsubstance1_partisi);
				
				System.out.println("actual_beratsubstance1_partisi " + actual_beratsubstance1_partisi);
				System.out.println("expected_beratsubstance1_partisi " + expected_beratsubstance1_partisi);
	}
	@Test (priority = 18)
	public void calculate_beratSubstancePartisi2() {
				//calculate substance 2 partisi
				float qValuepartisi2 = 125;
				float sheetLengthPartisi2 = 918;
				float minLebarMmSheetPartisi2 = 750;
				float identifierPartisi2 = 1.33f;
				float beratSubstancePartisi2 = ((((((qValuepartisi2 / 1000) * sheetLengthPartisi2) / 1000) * minLebarMmSheetPartisi2) / 
				1000) * identifierPartisi2) * corrPartisi;
				
				BigDecimal beratSubstancePartisi_2 = new BigDecimal(beratSubstancePartisi2);
				beratSubstancePartisi_2 = beratSubstancePartisi_2.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance2_partisi = beratSubstancePartisi_2.floatValue();
				
				String actual_beratsubstance2Partisi = allValues.get(16);
				Float actual_beratsubstance2_partisi =  Float.parseFloat(actual_beratsubstance2Partisi);
				
				assertEquals(actual_beratsubstance2_partisi, expected_beratsubstance2_partisi);
				
				System.out.println("actual_beratsubstance2_partisi " + actual_beratsubstance2_partisi);
				System.out.println("expected_beratsubstance2_partisi " + expected_beratsubstance2_partisi);
	}
	@Test (priority = 19)
	public void calculate_beratSubstancePartisi3() {
				//calculate substance 3 partisi
				float qValuepartisi3 = 125;
				float sheetLengthPartisi3 = 918;
				float minLebarMmSheetPartisi3 = 750;
				float identifierPartisi3 = 1;
				float beratSubstancePartisi3 = ((((((qValuepartisi3 / 1000) * sheetLengthPartisi3) / 1000) * minLebarMmSheetPartisi3) / 
				1000) * identifierPartisi3) * corrPartisi;
				
				BigDecimal beratSubstancePartisi_3 = new BigDecimal(beratSubstancePartisi3);
				beratSubstancePartisi_3 = beratSubstancePartisi_3.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance3_partisi = beratSubstancePartisi_3.floatValue();
				
				String actual_beratsubstance3Partisi = allValues.get(17);
				Float actual_beratsubstance3_partisi =  Float.parseFloat(actual_beratsubstance3Partisi);
				
				assertEquals(actual_beratsubstance3_partisi, expected_beratsubstance3_partisi);
				
				System.out.println("actual_beratsubstance3_partisi " + actual_beratsubstance3_partisi);
				System.out.println("expected_beratsubstance3_partisi " + expected_beratsubstance3_partisi);
	}
	@Test (priority = 20)
	public void calculate_beratSubstancePartisi4() {
				//calculate substance 4 partisi
				float qValuepartisi4 = 0;
				float sheetLengthPartisi4 = 918;
				float minLebarMmSheetPartisi4 = 750;
				float identifierPartisi4 = 1.42f;
				float beratSubstancePartisi4 = ((((((qValuepartisi4 / 1000) * sheetLengthPartisi4) / 1000) * minLebarMmSheetPartisi4) / 
				1000) * identifierPartisi4) * corrPartisi;

				BigDecimal beratSubstancePartisi_4 = new BigDecimal(beratSubstancePartisi4);
				beratSubstancePartisi_4 = beratSubstancePartisi_4.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance4_partisi = beratSubstancePartisi_4.floatValue();
				
				String actual_beratsubstance4Partisi = allValues.get(18);
				Float actual_beratsubstance4_partisi =  Float.parseFloat(actual_beratsubstance4Partisi);
				
				assertEquals(actual_beratsubstance4_partisi, expected_beratsubstance4_partisi);
				
				System.out.println("actual_beratsubstance4_partisi " + actual_beratsubstance4_partisi);
				System.out.println("expected_beratsubstance4_partisi " + expected_beratsubstance4_partisi);
	}
	@Test (priority = 21)
	public void calculate_beratSubstancePartisi5() {
				//calculate substance 5 partisi
				float qValuepartisi5 = 0;
				float sheetLengthPartisi5 = 918;
				float minLebarMmSheetPartisi5 = 750;
				float identifierPartisi5 = 1;
				float beratSubstancePartisi5 = ((((((qValuepartisi5 / 1000) * sheetLengthPartisi5) / 1000) * minLebarMmSheetPartisi5) / 
				1000) * identifierPartisi5) * corrPartisi;

				BigDecimal beratSubstancePartisi_5 = new BigDecimal(beratSubstancePartisi5);
				beratSubstancePartisi_5 = beratSubstancePartisi_5.setScale(2, RoundingMode.HALF_UP);
				Float expected_beratsubstance5_partisi = beratSubstancePartisi_5.floatValue();
				
				String actual_beratsubstance5Partisi = allValues.get(19);
				Float actual_beratsubstance5_partisi =  Float.parseFloat(actual_beratsubstance5Partisi);
				
				assertEquals(actual_beratsubstance5_partisi, expected_beratsubstance5_partisi);
				
				System.out.println("actual_beratsubstance5_partisi " + actual_beratsubstance5_partisi);
				System.out.println("expected_beratsubstance5_partisi " + expected_beratsubstance5_partisi);
	}
	@AfterTest
	public void tearDownTest() {    
		driver.quit();	
				
	}

}
