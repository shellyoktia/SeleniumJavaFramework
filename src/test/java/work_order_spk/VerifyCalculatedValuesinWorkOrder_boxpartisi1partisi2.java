package work_order_spk;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Array;
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

public class VerifyCalculatedValuesinWorkOrder_boxpartisi1partisi2 {

	private static WebDriver driver = null;
	private static String isiTable, outkrts, corr, beratbox, beratorder, berattrim, BeratSubstance1, BeratSubstance2, BeratSubstance3, BeratSubstance4, BeratSubstance5;
	private static ArrayList<String> allValues = new ArrayList<String>();
	//String noSalesOrder = VariableGlobalMain.noSalesOrderBoxPartisi;
	String noSalesOrder = "qwertfghjyyi";
	//String qtySalesOrder = VariableGlobalMain.qtySalesOrderBoxPartisi;
	String qtySalesOrder = "150";
	float qty = Float.parseFloat(qtySalesOrder);
	float lebarKertasMinimum_box, width_box, outkertas_box,constantDoubleJoint_box,out_box,outKnife_box, corrBox,weightSheetNettoGram_box,weight_box, beratBoxBox;
	float lebarKertasMinimum_partisi, width_partisi, outkertas_partisi, constantDoubleJoint_partisi, out_partisi, outKnife_partisi,corrPartisi, weightSheetNettoGram_partisi,weight_partisi, beratBoxPartisi;

	@BeforeTest
	public void setUpTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'SPK Rework Request Approval')]")));
		driver.findElement(By.id("rootmenu-sales")).click();
		driver.findElement(By.linkText("Release Sales Order")).click();
				
		//filter by no sales order
		driver.findElement(By.className("filter-block__title")).click();
		driver.findElement(By.name("noSalesorder")).sendKeys(noSalesOrder);
		driver.findElement(By.name("submit")).click();
	}
	
	@Test (priority = 1)
	 public static void firstTes() throws InterruptedException {
				//ambil no spk per item
				WebElement Table = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/main[1]/section[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/table[1]/tbody[1]"));
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
				for (int arr = 0; arr < array_count; arr++) {
				    String out = noSPKperItem.get(arr);
				    
				    driver.findElement(By.id("rootmenu-production")).click();
					driver.findElement(By.linkText("Work Order (SPK)")).click();
				    driver.findElement(By.className("filter-block__title")).click();
				    driver.findElement(By.name("noWorkorder")).sendKeys(out);
				    driver.findElement(By.name("submit")).click();
				    driver.findElement(By.xpath("//tbody/tr[1]/td[1]/span[1]/a[1]")).click();
				    
				    Thread.sleep(7000);
				    String noSPKset = driver.findElement(By.name("noWorkorderGrouped")).getAttribute("value");
				    outkrts = driver.findElement(By.cssSelector(".wo_out input")).getAttribute("value");
					corr = driver.findElement(By.cssSelector(".wo_corr input")).getAttribute("value");
					beratbox = driver.findElement(By.cssSelector(".wo_weightperkg input")).getAttribute("value");
					beratorder = driver.findElement(By.cssSelector(".wo_weight input")).getAttribute("value");
					berattrim = driver.findElement(By.cssSelector(".wo_trimweight input")).getAttribute("value");
					BeratSubstance1 = driver.findElement(By.cssSelector(".wo_gsmweight1 input")).getAttribute("value");
					BeratSubstance2 = driver.findElement(By.cssSelector(".wo_gsmweight2 input")).getAttribute("value");
					BeratSubstance3 = driver.findElement(By.cssSelector(".wo_gsmweight3 input")).getAttribute("value");
					BeratSubstance4 = driver.findElement(By.cssSelector(".wo_gsmweight4 input")).getAttribute("value");
					BeratSubstance5 = driver.findElement(By.cssSelector(".wo_gsmweight5 input")).getAttribute("value");
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
				    driver.findElement(By.xpath("//button[@name='back-button']")).click();
				    
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
				//out dengan nilai lebarkertasminumim terbesar (dataase mcsheet)
				outkertas_box = 4;

				String expected_outkrts_box = Float.toString(outkertas_box);
				String actual_outkrts_box = allValues.get(0);
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
				corrBox = (float) (constantDoubleJoint_box * Math.floor((qty / out_box) / outKnife_box));
				String expected_corr_box = Float.toString(corrBox);
				String actual_corr_box = allValues.get(1);
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
				String expected_beratbox_box = Float.toString(beratBoxBox);
				String actual_beratbox_box = allValues.get(2);
				assertEquals(actual_beratbox_box, expected_beratbox_box);
				System.out.println("actual_beratbox_box " + actual_beratbox_box);
				System.out.println("expected_beratbox_box " + expected_beratbox_box);
	}
	@Test (priority = 5)
	public void calculate_beratOrder_box() {
	
				//calculate berat order box
				float beratOrderBox = (float) (Math.ceil(weight_box * 100) / 100);
				String expected_beratorder_box = Float.toString(beratOrderBox);
				String actual_beratorder_box = allValues.get(3);
				assertEquals(actual_beratorder_box, expected_beratorder_box);
				System.out.println("actual_beratorder_box " + actual_beratorder_box);
				System.out.println("expected_beratorder_box " + expected_beratorder_box);
	}
	@Test (priority = 6)
	public void calculate_beratTrim_box() {
				//calculate berat trim box
				float trimWasteGram_box = 13.09f;
				float beratTrimBox = ((qty / out_box) * trimWasteGram_box) / 1000;
				String expected_berattrim_box = Float.toString(beratTrimBox);
				String actual_berattrim_box = allValues.get(4);
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
				String expected_beratsubstance1_box = String.format("%.2f", beratSubstanceBox1);
				String actual_beratsubstance1_box = allValues.get(5);
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
				String expected_beratsubstance2_box = String.format("%.2f", beratSubstanceBox2);
				String actual_beratsubstance2_box = allValues.get(6);
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
				String expected_beratsubstance3_box = String.format("%.2f", beratSubstanceBox3);
				String actual_beratsubstance3_box = allValues.get(7);
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
				String expected_beratsubstance4_box = String.format("%.2f", beratSubstanceBox4);
				String actual_beratsubstance4_box = allValues.get(8);
				assertEquals(actual_beratsubstance4_box, expected_beratsubstance4_box);
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
				String expected_beratsubstance5_box = String.format("%.2f", beratSubstanceBox5);
				String actual_beratsubstance5_box = allValues.get(9);
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
		String expected_outkrts_partisi = Float.toString(outkertas_partisi);
		String actual_outkrts_partisi = allValues.get(10);
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
				corrPartisi = (float) (constantDoubleJoint_partisi * Math.floor((qty / out_partisi) / outKnife_partisi));
				String expected_corr_partisi = Float.toString(corrPartisi);
				String actual_corr_partisi = allValues.get(11);
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
				String expected_beratbox_partisi = Float.toString(beratBoxPartisi);
				String actual_beratbox_partisi = allValues.get(12);
				assertEquals(actual_beratbox_partisi, expected_beratbox_partisi);
				System.out.println("actual_beratbox_partisi " + actual_beratbox_partisi);
				System.out.println("expected_beratbox_partisi " + expected_beratbox_partisi);
	}
	@Test (priority = 15)
	public void calculate_beratOrder_partisi() {
	
				//calculate berat order partisi
				float beratOrderPartisi = (float) (Math.ceil(weight_partisi * 100) / 100);
				String expected_beratorder_partisi = Float.toString(beratOrderPartisi);
				String actual_beratorder_partisi = allValues.get(13);
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

				String expected_berattrim_partisi = Float.toString(beratTrimPartisi);
				String actual_berattrim_partisi = allValues.get(14);
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
				String expected_beratsubstance1_partisi = String.format("%.2f", beratSubstancePartisi1);
				String actual_beratsubstance1_partisi = allValues.get(15);
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
				String expected_beratsubstance2_partisi = String.format("%.2f", beratSubstancePartisi2);
				String actual_beratsubstance2_partisi = allValues.get(16);
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
				String expected_beratsubstance3_partisi = String.format("%.2f", beratSubstancePartisi3);
				String actual_beratsubstance3_partisi = allValues.get(17);
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
				String expected_beratsubstance4_partisi = String.format("%.2f", beratSubstancePartisi4);
				String actual_beratsubstance4_partisi = allValues.get(18);
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
				String expected_beratsubstance5_partisi = String.format("%.2f", beratSubstancePartisi5);
				String actual_beratsubstance5_partisi = allValues.get(19);
				assertEquals(actual_beratsubstance5_partisi, expected_beratsubstance5_partisi);
				System.out.println("actual_beratsubstance5_partisi " + actual_beratsubstance5_partisi);
				System.out.println("expected_beratsubstance5_partisi " + expected_beratsubstance5_partisi);
	}
	@AfterTest
	public void tearDownTest() {    
		driver.quit();
	}

}
