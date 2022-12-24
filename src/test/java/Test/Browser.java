package Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {
	public static void main(String[] args) throws IOException {
		
		//setup webdriver
		//System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		//ke url
		//driver.get("http://google.com");
		driver.navigate().to("http://localhost:3001/");
		
		//maximize window
		driver.manage().window().maximize();
		
		//screenshot & save
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./image.png"));
		
//		driver.get("http://google.com");
//		WebElement element = driver.findElement(By.cssSelector("./lnXdpd"));
//		File scrFile1 = element.getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(scrFile1, new File("./image1.png"));
		
		//print current url and title of the page
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//click button pake js
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement button = driver.findElement(By.name("btnI"));
		js.executeScript("arguments[0].click();", button);
		js.executeScript("console.log('Hello ..')");
		
		//driver.navigate().refresh();
		//driver.close();
	}
}
