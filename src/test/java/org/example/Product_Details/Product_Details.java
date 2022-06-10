package org.example.Product_Details;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;



    public class Product_Details {
        WebDriver driver;
        private static ExtentHtmlReporter htmlReporter;
        private static ExtentReports extent;
        private static ExtentTest test;
        @BeforeSuite
        public void setUp()
        {
                // hello world

            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/Product_Details_tests.html");
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
        public void test_addSpecifiedItem() throws IOException {

            test = extent.createTest("test_addSpecifiedItem","Test case passed");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img")).click();
            driver.findElement(By.name("Blue")).click();
            Select dropdown = new Select(driver.findElement(By.id("group_1")));
            dropdown.selectByIndex(1);
            driver.findElement(By.className("icon-plus")).click();
            driver.findElement(By.className("icon-plus")).click();
            driver.findElement(By.name("Submit")).click();
            Assert.assertTrue(driver.findElements(By.className("icon-ok")).size() >= 1);
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_addSpecifiedItem.png"));;
            driver.close();

        }
        @Test(priority = 2)
        public void test_productDetails() throws IOException {


            test = extent.createTest("test_productDetails","Test case passed");

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            // driver.findElement(By.className("replace-2x img-responsive")).click();
            driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img")).click();
            // driver.findElement(By.name("Submit")).click();
            //  WebElement Expected = (WebElement) ExpectedConditions.visibilityOfElementLocated(By.name("Submit"));
            Assert.assertNotNull(ExpectedConditions.visibilityOfElementLocated(By.name("Submit")));
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_productDetails.png"));;
            driver.close();
        }
        @Test (priority = 3)
        public void test_quantityAdjustment() throws IOException {

            test = extent.createTest("test_quantityAdjustment","Test case passed");

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img")).click();
            driver.findElement(By.className("icon-plus")).click();
            driver.findElement(By.className("icon-plus")).click();
            String qtyCheck= driver.findElement(By.name("qty")).getAttribute("value");
            File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_quantityAdjustment.png"));;

            Assert.assertEquals("3",qtyCheck);
            driver.findElement(By.className("icon-minus")).click();
            driver.findElement(By.className("icon-minus")).click();
            String qtyCheck1= driver.findElement(By.name("qty")).getAttribute("value");

            Assert.assertEquals("1",qtyCheck1);
            driver.close();
        }
        @Test(priority = 4)
        public void test_sizeAndColor() throws IOException {

            test = extent.createTest("test_sizeAndColor", "Test case passed");
            //driver.findElement(By.className("replace-2x img-responsive")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.findElement(By.xpath("//*[@id=\"homefeatured\"]/li[1]/div/div[1]/div/a[1]/img")).click();
            driver.findElement(By.name("Blue")).click();
            Select dropdown = new Select(driver.findElement(By.id("group_1")));
            dropdown.selectByIndex(1);
            Assert.assertFalse(driver.findElement(By.name("Orange")).isSelected());
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("src/test/resources/screenshots/test_sizeAndColor.png"));
            ;
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

