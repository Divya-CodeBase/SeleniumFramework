package org.test;

import org.pageObjects.*;
import org.comp.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class E2E_Test extends BaseTest {
    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String,String> mp) {
        //Login Application
        ProductCatalogue productCatalogue = landingPage.loginToApplication(mp.get("email"), mp.get("password"));

        //add product to cart
        productCatalogue.addProductToCart(mp.get("productName"));
        CartPage cartPage = productCatalogue.navigateToCart();

        //verify cart and proceed to checkout
        Assert.assertTrue(cartPage.verifyItemInCart( mp.get("productName")));
        PaymentPage paymentPage = cartPage.proceedPayment();

        //enter coupon country and submit
        Assert.assertTrue(paymentPage.enterPaymentAndCouponDetails(mp.get("cvv"), mp.get("coupon")));
        OrderPage orderPage = paymentPage.enterCountryDetailSubmit(mp.get("country"));

        //Confirm Order
        Assert.assertTrue(orderPage.verifyOrderSubmission( mp.get("productName"), "THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods ="submitOrder",dataProvider = "getData")
    public void OrderHistory(HashMap<String,String> data){
        ProductCatalogue productCatalogue =  landingPage.loginToApplication(data.get("email"), data.get("password"));
        OrderHistoryPage orderHistoryPage = productCatalogue.clickOrderHistory();
        Assert.assertTrue(orderHistoryPage.getProduct(data.get("productName")));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data= getTestData(new File(System.getProperty("user.dir")+"/src/test/java/org/resources/testData.json"));


         return new Object[][]{{data.get(0)},{data.get(1)}};
    }
}
