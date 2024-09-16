package com.wixsite.test.pages;

import com.wixsite.test.base.BasePage;
import org.openqa.selenium.By;

public class CartPage extends BasePage {
    protected By totalAmountText =  By.id("total-sum");

    public String getTotalCost() {
        return getElementText(totalAmountText);
    }
}
