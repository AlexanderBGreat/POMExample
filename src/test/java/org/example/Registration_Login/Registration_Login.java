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

public class Registration_Login {  WebDriver driver;
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
    @BeforeMethod
    public void browserLauncher() {
        //Change browsername if you want to test other browser
        driver = selectBrowser.StartBrowser("Chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test(priority = 1)
    public void test_checkoutAsGuest() throws IOException {

        test = extent.createTest("test_checkoutAsGuest","Test case failed");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.className("product-container")).click();
        driver.findElement(By.name("Submit")).click();
        driver.findElement(By.linkText("Proceed to checkout")).click();
        driver.findElement(By.linkText("Proceed to checkout")).click();
        WebElement ExpectedValue = driver.findElement(By.linkText("Checkout as guest"));
        driver.findElement(By.linkText("Checkout as guest")).click();
        Assert.assertEquals(ExpectedValue,"Checkout as Guest");

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_checkoutAsGuest.png"));;
        driver.close();
    }
    @Test (priority = 2)
    public void test_uniqueRegistration() throws IOException {



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
        driver.close();
    }
    @Test (priority = 3)
    public void test_existingLogin() throws IOException {


        test = extent.createTest("test_existingLogin", "Test case passed");


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
        driver.close();
    }
    @Test(priority = 4)
    public void test_loginAllProducts() throws IOException { test = extent.createTest("test_loginAllProducts", "Test case passed");
        driver.findElement(By.className("login")).click();
        driver.findElement(By.name("email")).sendKeys("Sampleaccount@test.com");
        driver.findElement(By.name("passwd")).click();
        driver.findElement(By.name("passwd")).sendKeys("Test1234");
        driver.findElement(By.name("SubmitLogin")).click();

        WebElement all = driver.findElement(By.linkText("All"));
        Assert.assertEquals(true,all.isDisplayed());
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_loginAllProducts.png"));
        driver.close();

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

