package com.abhinay.automation.factory;

import com.microsoft.playwright.*;

public class PlaywrightFactory {

    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static Page initBrowser() {

        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
        );

        context = browser.newContext();

        page = context.newPage();

        return page;
    }

    public static void closeBrowser() {

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}