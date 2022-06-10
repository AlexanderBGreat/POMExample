package org.example.Search_Feature;

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
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class test_searchFeature {





        WebDriver driver;
        private static ExtentHtmlReporter htmlReporter;
        private static ExtentReports extent;
        private static ExtentTest test;
    WebElement webElement;
        @BeforeSuite
        public void setUp()
        {

            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/Search_Feature_tests.html");
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

        @Test(priority = 1)
        public void test_searchFeature() throws IOException {
           // System.setProperty("webdriver.chrome.driver" , "C:\\Users\\ALEXANDER\\Documents\\TestNGExample\\Alexander_ecom_sba\\chromedriver_win32\\chromedriver.exe");
           // driver = new ChromeDriver();
           // driver.get("http://automationpractice.com/index.php");
            test = extent.createTest(" test_searchFeature","Test case passed");

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.id("search_query_top")).click();
            driver.findElement(By.id("search_query_top")).sendKeys("Printed Chiffon Dress");
            driver.findElement(By.name("submit_search")).click();

            String Search =  driver.findElement(By.id("search_query_top")).getAttribute("value");
            Assert.assertEquals("Printed Chiffon Dress", Search);
          //  String attribute = webElement.getAttribute("dress");
           // Assert.assertTrue(attribute>0);
                  File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_searchFeature.png"));;
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


