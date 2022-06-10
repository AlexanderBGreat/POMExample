package org.example.Registration_Login;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.example.library.selectBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class test_uniqueRegistration {
    WebDriver driver;
    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;
    @BeforeSuite
    public void setUp()
    {

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/Registration_Login_tests.html");
        extent = new ExtentReports();

        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "DESKTOP-5G64RED");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Alexander");
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting Demo Report");
        htmlReporter.config().setReportName("My Own Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }
    @BeforeClass
    public void browserLauncher() {
        driver = selectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test (priority = 2)
    public void test_uniqueRegistration() throws IOException {
        // System.setProperty("webdriver.chrome.driver" , "C:\\Users\\ALEXANDER\\Documents\\TestNGExample\\Alexander_ecom_sba\\chromedriver_win32\\chromedriver.exe");
        // driver = new ChromeDriver();
        // driver.get("http://automationpractice.com/index.php");
        test = extent.createTest("test_UniqueRegistration","Test case passed");


        driver.findElement(By.className("login")).click();


        driver.findElement(By.name("email_create")).click();
        // driver.findElement(By.linkText("Email address")).click();
        driver.findElement(By.name("email_create")).sendKeys("Timmysmith21@gmail.com" );
        driver.findElement(By.name("SubmitCreate")).click();
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("customer_firstname")).click();
        driver.findElement(By.id("customer_firstname")).sendKeys("Timmy");
        driver.findElement(By.id("customer_lastname")).click();
        driver.findElement(By.id("customer_lastname")).sendKeys("Smith");
        driver.findElement(By.id("passwd")).click();
        driver.findElement(By.id("passwd")).sendKeys("Test1234");
        Select dropdown = new Select(driver.findElement(By.id("days")));
        dropdown.selectByIndex(15);
        Select dropdown1 = new Select(driver.findElement(By.id("months")));
        dropdown1.selectByIndex(5);
        Select dropdown2 = new Select(driver.findElement(By.id("years")));
        dropdown2.selectByIndex(23);
        driver.findElement(By.id("address1")).click();
        driver.findElement(By.id("address1")).sendKeys("123 Main St");
        driver.findElement(By.id("city")).click();
        driver.findElement(By.id("city")).sendKeys("PleasantVille");
        Select dropdown3 = new Select(driver.findElement(By.id("id_state")));
        dropdown3.selectByIndex(5);
        driver.findElement((By.id("postcode"))).click();
        driver.findElement(By.id("postcode")).sendKeys("12345");
        driver.findElement(By.id("submitAccount")).click();
        //Assert.assertNull(driver.findElement(By.name("phone_mobile")));

        Assert.assertNotNull(driver.findElement(By.name("email")));

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_UniqueRegistration.png"));;
    }
    @AfterMethod
    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}

