package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentReportsBasic {
	private static WebDriver driver = null;

    public static void main(String[] args) throws ClassNotFoundException {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);

        ExtentTest test1 = extent.createTest("MyFirstTest", "Sample description");
        
        WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		//test1.generateLog(Status.INFO,"Starting Test");
		
		driver.get("http://google.com");
		test1.pass("buka google");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement button = driver.findElement(By.name("btnI"));
		js.executeScript("arguments[0].click();", button);
		test1.pass("klik i'm lucky");
		
		driver.close();
		test1.pass("close browser");
		
		test1.info("completed");
		extent.flush();
		
		
		
//        extent.createTest("ScreenCapture")
//                .addScreenCaptureFromPath("extent.png")
//                .pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());
//
//        extent.createTest("LogLevels")
//                .info("info")
//                .pass("pass")
//                .warning("warn")
//                .skip("skip")
//                .fail("fail");
//
//        extent.createTest("CodeBlock").generateLog(
//                Status.PASS,
//                MarkupHelper.createCodeBlock(CODE1, CODE2));
//
//        extent.createTest("ParentWithChild")
//                .createNode("Child")
//                .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");
//
//        extent.createTest("Tags")
//                .assignCategory("MyTag")
//                .pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");
//
//        extent.createTest("Authors")
//                .assignAuthor("TheAuthor")
//                .pass("This test 'Authors' was assigned by a special kind of author tag.");
//
//        extent.createTest("Devices")
//                .assignDevice("TheDevice")
//                .pass("This test 'Devices' was assigned by a special kind of devices tag.");
//
//        extent.createTest("Exception! <i class='fa fa-frown-o'></i>")
//                .fail(new RuntimeException("A runtime exception occurred!"));
//
//        extent.flush();
    }

}
