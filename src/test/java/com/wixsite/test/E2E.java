package com.wixsite.test;

import com.wixsite.test.base.BaseTest;
import com.wixsite.test.pages.CartPage;
import com.wixsite.test.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2E extends BaseTest {

    @Test(testName = "Verify user can add item and navigate to checkout")
    public void checkoutAfterAddingItems() throws Exception {
        HomePage homePageObj = new HomePage();
        CartPage cartPageObj = new CartPage();

        homePageObj.openShopTab()
                .openBestSellerDetails()
                .selectWhiteColor()
                .increaseItems()
                .increaseItems()
                .addToCart()
                .viewCart();

        Assert.assertEquals(cartPageObj.getTotalCost(), "C$54.00");
    }
}
