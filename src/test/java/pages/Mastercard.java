package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Mastercard {
	WebDriver driver = null;
	By mastercard_ptbeautykasatama_003944 = By.xpath("//div[contains(text(),'5157 - 003944 - BOX VIO PRINTING BARU (340) - K125')]");
	By mastercard_ptjayamasmedica_001076 = By.xpath("//div[contains(text(),'1402 - 001076 - BOX USG 250cc (K0173) - WK150/M125')]");
	By mastercard_ptpinrangpantaiindah_008420 = By.xpath("//div[contains(text(),'10849 - 008420 - DUS CARMEL MOONCAKE - K150/0/0/M1')]");
	By mastercard_ptgeloradjaja_000638R01 = By.xpath("//div[contains(text(),'897 - 000638R01 - BOX-KBOX WD12 NW 001628. CDI.18')]");
	By mastercard_ptsukajadisawitmekar_006087_R1 =  By.xpath("//div[contains(text(),'9102 - 006087-R1 - CARTON M & M 1L (SNI-VITAMIN A)')]");
	
	public Mastercard(WebDriver driver) {
		this.driver = driver;
	}
	public void clickMastercard_PTBeautyKasatama_003944() {
		driver.findElement(mastercard_ptbeautykasatama_003944).click(); 
	}
	public void clickMastercard_PTJayamasMedica_001076() {
		driver.findElement(mastercard_ptjayamasmedica_001076).click(); 
	}
	public void clickMastercard_PTPinrangPantaiIndah_008420() {
		driver.findElement(mastercard_ptpinrangpantaiindah_008420).click(); 
	}
	public void clickMastercard_PTGeloraDjaja_000638R01() {
		driver.findElement(mastercard_ptgeloradjaja_000638R01).click(); 
	}
	public void clickMastercard_PTSukajadiSawitMekar_006087_R1() {
		driver.findElement(mastercard_ptsukajadisawitmekar_006087_R1).click(); 
	}
}
