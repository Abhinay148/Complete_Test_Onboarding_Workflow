package com.abhinay.automation.pages;

import com.abhinay.automation.base.BasePage;
import com.microsoft.playwright.Page;

public class DashboardPage extends BasePage {

    public DashboardPage(Page page) {
        super(page);
    }

    private final String staffMenu =
            "button:has-text('Staff')";

    public boolean isDashboardDisplayed() {

        try {

            page.locator(staffMenu)
                    .waitFor();

            return page.locator(staffMenu)
                    .isVisible();

        } catch (Exception e) {

            return false;
        }
    }

    public void verifyDashboardLoaded() {

        page.locator(staffMenu)
                .waitFor();

        System.out.println("Dashboard loaded successfully");
    }
}