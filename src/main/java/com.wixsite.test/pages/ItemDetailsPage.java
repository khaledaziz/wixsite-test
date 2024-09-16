package com.wixsite.test.pages;

import com.wixsite.test.base.BasePage;
import org.openqa.selenium.By;

public class ItemDetailsPage extends BasePage {

    protected By whiteColor = By.xpath("(//div[contains(@style,'background-color')])[2]");
    protected By quantityText = By.xpath("//input[@type='number']");
    protected By arrowUp = By.xpath("//span[@data-hook='number-input-spinner-up-arrow']");
    protected By showMoreButton =  By.xpath("//button[@data-hook='show-more-or-less']");
    protected By addToCartButton =  By.xpath("//button[@data-hook='add-to-cart']");
    protected By viewCartButton =  By.id("widget-view-cart-button");
    protected By cartFrame =  By.xpath("//iframe[contains(@name,'tpapopup')]");

    public ItemDetailsPage selectWhiteColor() throws Exception {
        waitForPresenceOfElement(showMoreButton);
        jsClickElement(whiteColor);
        takeSnapShot(System.getProperty("user.dir") + "/screens/selectWhiteColor.png");
        return new ItemDetailsPage();
    }

    public ItemDetailsPage increaseItems() throws Exception {
        hoverElement(quantityText);
        hoverElement(arrowUp);
        clickOnElement(arrowUp);
        takeSnapShot(System.getProperty("user.dir") + "/screens/increaseItems.png");
        return new ItemDetailsPage();
    }

    public ItemDetailsPage addToCart() throws Exception {
        clickOnElement(addToCartButton);
        takeSnapShot(System.getProperty("user.dir") + "/screens/addToCart.png");
        return new ItemDetailsPage();
    }

    public CartPage viewCart() {
        switchToFrame(cartFrame);
        clickOnElement(viewCartButton);
        driver.switchTo().defaultContent();
        return new CartPage();
    }

}
