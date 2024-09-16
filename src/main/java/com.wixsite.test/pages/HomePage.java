package com.wixsite.test.pages;

import com.wixsite.test.base.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    protected By shopTab = By.id("comp-iy4cwgmq1label");
    protected By bestSellerItem = By.xpath("//div[contains(@aria-label,'a product. Best Seller gallery')]//p");
    protected By bestSellerQuickViewBtn = By.xpath("//div[text()= 'Best Seller']/../following-sibling::div//button");

    public HomePage openShopTab() throws Exception {
        clickOnElement(shopTab);
        takeSnapShot(System.getProperty("user.dir") + "/screens/openShopTab.png");
        return new HomePage();
    }

    public ItemDetailsPage openBestSellerDetails() throws Exception {
        scrollToElement(bestSellerItem);
        jsClickElement(bestSellerItem);
        takeSnapShot(System.getProperty("user.dir") + "/screens/openBestSellerItem.png");
        return new ItemDetailsPage();
    }

}
