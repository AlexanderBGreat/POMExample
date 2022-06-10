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
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class test_existingLogin {
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

    @Test (priority = 3)
    public void test_existingLogin() throws IOException {
        // System.setProperty("webdriver.chrome.driver", "C:\\Users\\ALEXANDER\\Documents\\TestNGExample\\Alexander_ecom_sba\\chromedriver_win32\\chromedriver.exe");
        // driver = new ChromeDriver();
        // driver.get("http://automationpractice.com/index.php");
        test = extent.createTest("test_formFill", "Test case passed");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // driver.findElement(By.className("product-container")).click();
        // driver.findElement(By.name("Submit")).click();
        // driver.findElement(By.linkText("Proceed to checkout")).click();
        // driver.findElement(By.linkText("Proceed to checkout")).click();


        // driver.findElement(By.xpath("//*[@id=\"create-account_form\"]/div/div[2]/label")).click();
        // driver.findElement(By.linkText("Email address")).click();
        driver.findElement(By.className("login")).click();
        driver.findElement(By.name("email")).sendKeys("Sampleaccount@test.com");
        driver.findElement(By.name("passwd")).click();
        driver.findElement(By.name("passwd")).sendKeys("Test1234");
        driver.findElement(By.name("SubmitLogin")).click();
        WebElement myAccount = driver.findElement(By.id("my-account"));
        Assert.assertEquals(true,myAccount.isDisplayed());
        System.out.println("My account is displayed – Assert passed");

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_existingLogin.png"));;
        //  driver.findElement(By.id("id_gender1")).click();
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

