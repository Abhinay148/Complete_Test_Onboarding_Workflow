package com.abhinay.automation.tests;

import com.abhinay.automation.pages.*;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

import org.testng.annotations.Test;

public class AddProviderTest extends BaseTest {

    @Test
    public void verifyProviderCanBeAdded() {

        String npi = "1962819813";

        LoginPage loginPage = new LoginPage(page);
        DashboardPage dashboardPage = new DashboardPage(page);
        ProvidersPage providersPage = new ProvidersPage(page);

        loginPage.login(
                "mishraabhinay814@gmail.com",
                "Abhinay@00"
        );

        dashboardPage.verifyDashboardLoaded();

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/after_login.png"))
                .setFullPage(true));

        providersPage.openProviders();
        providersPage.verifyProvidersPageLoaded();
        providersPage.selectLocation();

        boolean exists = providersPage.searchProvider(npi);

        Page providerPage;

        if (exists) {

            System.out.println(
                    "Provider exists → continuing onboarding"
            );

            providerPage =
                    providersPage.clickContinueOnboarding(npi);

        } else {

            System.out.println(
                    "Provider not found → creating new"
            );

            providersPage.clickAddProvider();
            providersPage.clickPopupAddProvider();
            providersPage.clickNext();
            providersPage.enterNpi(npi);
            page.waitForTimeout(5000);
            providerPage =
                    providersPage.clickAddAndCapturePopup();
        }

        providerPage.waitForLoadState();

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/add_existing_user.png"))
                .setFullPage(true));

        BasicInformationPage basic =
                new BasicInformationPage(providerPage);

        basic.verifyBasicInformationPageLoaded();

        if (exists) {

            providerPage.waitForTimeout(10000);

            Locator disclosureQuestionnaire = providerPage.locator(
                    "div#nav-tab button:has-text('Disclosure Questionnaire - I')"
            );

            if (disclosureQuestionnaire.isVisible()) {

                basic.clickAttestationSaveAndNext();

            } else {

                basic.enterSsn("123456789");
                basic.enterEmail("test@test.com");
                basic.enterDob("2008-01-01");

                basic.clickSaveAndNext();
                basic.clickSaveAndNext();
                basic.clickSaveAndNext();

                basic.clickAttestationSaveAndNext();
            }

        } else {

                page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/onboarding_process.png"))
                .setFullPage(true));
            basic.enterSsn("123456789");
            basic.enterEmail("test@test.com");
            basic.enterDob("2008-01-01");

            basic.clickSaveAndNext();
            basic.clickSaveAndNext();
            basic.clickSaveAndNext();

            basic.clickAttestationSaveAndNext();
        }

        basic.clickSignNow();
        page.waitForTimeout(5000);

        providerPage.locator("#expand_form_button").click();

        basic.clickTypeSignature();
        basic.enterSignature("abhinay");

         page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("screenshots/onboarding_completed.png"))
                .setFullPage(true));
    }
}