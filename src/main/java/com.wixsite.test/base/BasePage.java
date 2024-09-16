package com.wixsite.test.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class BasePage {

    protected WebDriver driver;
    private final int defaultImplicitlyWaitTime = 20;

    public BasePage() {
        driver = BaseTest.getDriver();
    }

    public void typeTextToField(String text, By field) {
        waitForPresenceOfElement(field);
        findElement(field).sendKeys(text);
    }

    public void waitForPresenceOfElement(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public WebElement findElement(By element) {
        return driver.findElement(element);
    }

    protected void clickOnElement(By element) {
        waitElementToBeClickable(element);
        findElement(element).click();
    }

    protected void jsClickElement(By element) {
        waitElementToBeClickable(element);
        WebElement webElement = findElement(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    protected void waitElementToBeClickable(By element) {
        wait.until(elementToBeClickable(element));
    }

    protected List<WebElement> findElements(By element) {
        return driver.findElements(element);
    }

    FluentWait<WebDriver> wait = new FluentWait<>(BaseTest.getDriver())
            .withTimeout(Duration.ofSeconds(defaultImplicitlyWaitTime))
            .pollingEvery(Duration.ofMillis(200))
            .ignoring(NoSuchElementException.class);

    protected String getElementText(By element) {
        waitForPresenceOfElement(element);
        String text = findElement(element).getText();
        return text;
    }

    protected void hoverElement(By element) {
        waitForPresenceOfElement(element);
        Actions action = new Actions(BaseTest.getDriver());
        action.moveToElement(findElement(element)).perform();
    }

    protected void scrollToElement(By locator) {
        waitForPresenceOfElement(locator);
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    protected void switchToFrame(By element) {
        driver.switchTo().frame(findElement(element));
    }

    protected String getScreenShotBase64() {
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        return base64Screenshot;
    }

    public void takeSnapShot(String fileWithPath) throws Exception {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }
}
