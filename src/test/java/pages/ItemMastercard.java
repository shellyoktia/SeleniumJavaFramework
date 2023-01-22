package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ItemMastercard {
	WebDriver driver = null;
	By item_mastercard_ptbeautykasatama_003944 = By.xpath("//div[contains(text(),'BOX VIO PRINTING BARU (340) - K125')]");
	By item_mastercard_ptjayamasmedica_001076 = By.xpath("//div[contains(text(),'BOX - BOX USG 250CC (K0')]");
	By item_mastercard_ptjayamasmedica_001076P01 = By.xpath("//div[contains(text(),'PARTISI - BOX USG 250CC (K0')]");
	By item_mastercard_ptpinrangpantaiindah_008420 = By.xpath("//div[contains(text(),'DUS CARMEL MOONCAKE - K150/0/0/M1')]");
	By item_mastercard_ptgeloradjaja_000638R01 = By.xpath("//div[contains(text(),'BOX-KBOX WD12 NW 001628. CDI.18')]");
	By item_mastercard_ptsukajadisawitmekar_006087_R1 =  By.xpath("//div[contains(text(),'CARTON M & M 1L (SNI-VITAMIN A)')]");
	
	public ItemMastercard(WebDriver driver) {
		this.driver = driver;
	}
	public void clickItemMastercard_PTJayamasMedica_box() {
		driver.findElement(item_mastercard_ptjayamasmedica_001076).click(); 
	}
	public void clickItemMastercard_PTJayamasMedica_partisi() {
		driver.findElement(item_mastercard_ptjayamasmedica_001076P01).click(); 
	}
}
