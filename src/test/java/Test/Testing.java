package Test;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;

public class Testing {
	private static WebDriver driver = null;
	
	public Testing() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@BeforeTest
	public void setUpTest() {
		//ke url
		driver.navigate().to("https://www.idntimes.com/");
		
		//maximize window
		driver.manage().window().maximize();
	}
	
	@Test (priority = 1)
	public void firstTest() {
		List <WebElement> berita = (List<WebElement>) driver.findElement(By.cssSelector(".box-headline"));
		int jumlah = berita.size();
		System.out.println("jumlah : " + jumlah);
		int expectedJumlah = 5;
		assertEquals(jumlah, expectedJumlah);
	}
	@Test (priority = 2)
	public void secondTest() {
		List <WebElement> berita = (List<WebElement>) driver.findElement(By.className("box-latest box-list "));
		int jumlah = berita.size();
		System.out.println("jumlah : " + jumlah);
		int expectedJumlah = 18;
		assertEquals(jumlah, expectedJumlah);
	}
	@Test (priority = 3)
	public void thirdTest() {
		List <WebElement> berita = (List<WebElement>) driver.findElement(By.cssSelector(".box-trending"));
		int jumlah = berita.size();
		System.out.println("jumlah : " + jumlah);
		int expectedJumlah = 10;
		assertEquals(jumlah, expectedJumlah);
	}
	@Test (priority = 4)
	public void fourthTest() {
		List <WebElement> berita = (List<WebElement>) driver.findElement(By.cssSelector(".latest-post .section-title"));
		int jumlah = berita.size();
		System.out.println("jumlah : " + jumlah);
		int expectedJumlah = 5;
		assertEquals(jumlah, expectedJumlah);
	}
	@Test (priority = 5)
	public void fifthTest() {
		String judul = driver.findElement(By.xpath("//body[1]/div[5]/div[1]/div[3]/div[1]/section[3]/h2[1]/span[1]")).getText();
		System.out.println("Judul : " + judul);
		String expectedJudul = "News";
		assertEquals(judul, expectedJudul);
	}
}
