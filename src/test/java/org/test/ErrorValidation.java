package org.test;

import org.comp.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest {
    @Test
    public void loginError() {

        String username = "testuser001@mail.com";
        String password = "Learn@13";

        landingPage.loginToApplication(username, password);
        Assert.assertEquals("Incorrect email o password.", landingPage.getErrorText());
    }
}
