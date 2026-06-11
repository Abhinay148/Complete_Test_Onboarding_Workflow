package com.abhinay.automation.tests;

import com.abhinay.automation.factory.PlaywrightFactory;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.NavigateOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected Page page;

    @BeforeMethod
    public void setUp() {

        page = PlaywrightFactory.initBrowser();

        // Increase timeouts
        page.setDefaultTimeout(120000);              // 2 minutes
        page.setDefaultNavigationTimeout(120000);    // 2 minutes

        page.navigate(
                "http://mumbai.staging-provider-passport.s3-website.ap-south-1.amazonaws.com/#/auth/login",
                new NavigateOptions()
                        .setTimeout(120000)
        );
    }

    @AfterMethod
    public void tearDown() {

        PlaywrightFactory.closeBrowser();
    }
}