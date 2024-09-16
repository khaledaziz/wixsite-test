package com.wixsite.test.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

public class BaseTest {
    private static final ThreadLocal<WebDriver> WEB_DRIVER_CONTAINER = new ThreadLocal<>();
    private WebDriver driver;
    public AppConfig appConfig;
    public String browserName;
    public String appUrl;

    public static ExtentSparkReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest extentTest;

    public static WebDriver getDriver() {
        return WEB_DRIVER_CONTAINER.get();
    }

    @BeforeTest
    public void init() {
        initReport();
    }

    @BeforeMethod
    public void runOn() {
        initConfig();
        Reporter.log("STARTING BROWSER -" + browserName);
        //Config launching app
        createDriver(browserName); //launch browser using webdriver manager
        getDriver().get(appUrl);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
            WEB_DRIVER_CONTAINER.remove();
        }
    }

    public void createDriver(String browserName) {
        switch (browserName) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--lang=en");
                options.addArguments("--window-size=2000,1500");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                WEB_DRIVER_CONTAINER.set(driver);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                WEB_DRIVER_CONTAINER.set(driver);

            default:
                System.out.print("Failed to setup browser");
        }
    }

    public void initConfig() {
        appConfig = ConfigFactory.create(AppConfig.class);
        browserName = appConfig.browserName();
        appUrl = appConfig.appUrl();
    }

    public void initReport() {
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\reports\\extentReport.html");
        //create ExtentReports and attach reporter(s)
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setDocumentTitle("Test results");
        // Name of the report
        htmlReporter.config().setReportName("sauce demo test result");
        // Dark Theme
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().thumbnailForBase64(true);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws Exception {
        BasePage basePage = new BasePage();
        extentTest = extent.createTest(result.getName());
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            extentTest.log(Status.FAIL,
                    MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            String screenshotPath = basePage.getScreenShotBase64();
            extentTest.fail("Test Case Failed Snapshot is below " + extentTest.addScreenCaptureFromBase64String(screenshotPath));
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP,
                    MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS,
                    MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        }

    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }
}
