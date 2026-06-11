package com.abhinay.automation.pages;

import com.abhinay.automation.base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProvidersPage extends BasePage {

    public ProvidersPage(Page page) {
        super(page);
    }

    private final String staffMenu = "button:has-text('Staff')";
    private final String providersLink = "a[href='#/staff/providers']";

    private final String addProviderButton = "button:has(i.bi-plus-lg)";
    private final String popupAddProvider = "p:has-text('Add Provider')";
    private final String nextButton = "button:has-text('Next')";
    private final String npiField = "input[name='npi']";
    private final String addButton = "button.primarynextebtn[type='submit']";

    private final String inProcessTab = "#nav-process-tab";
    private final String searchBox =
            "input[placeholder='Search by Name, NPI, Email, Phone']";

    private final String continueOnboardingIcon =
            "i.bi-person-fill-up"; // UPDATE if UI differs

    // ---------------- NAVIGATION ----------------

    public void openProviders() {
        page.locator(staffMenu).hover();
        page.locator(providersLink).click();
    }

    public void verifyProvidersPageLoaded() {
        page.waitForURL("**/staff/providers");
    }

    public void selectLocation() {
        page.locator(".LctnSelection").click();

        page.locator("div.hdrLctListCard")
                .filter(new Locator.FilterOptions()
                        .setHasText("Dark Knight Oso Inc"))
                .click();

        page.locator(inProcessTab).waitFor();
    }

    // ---------------- SEARCH (FIXED - NO RELOAD ISSUES FROM TEST SIDE) ----------------

    public boolean searchProvider(String npi) {

        page.locator(inProcessTab).click();

        page.waitForTimeout(10000);

        Locator search = page.locator(searchBox);

        search.click();
        search.fill("");        // clear safely
        search.fill(npi);       // IMPORTANT: no type(), no Enter

        // wait for table to stabilize (safe UI sync)
        page.waitForLoadState();

        Locator row = page.locator("tbody tr")
                .filter(new Locator.FilterOptions().setHasText(npi));

        return row.count() > 0;
    }

    // ---------------- EXISTING PROVIDER FLOW ----------------

    public Page clickContinueOnboarding(String npi) {

        Locator row = page.locator("tbody tr")
                .filter(new Locator.FilterOptions().setHasText(npi));

        return page.waitForPopup(() -> {
            row.locator(continueOnboardingIcon).click();
        });
    }

    // ---------------- ADD PROVIDER FLOW ----------------

    public void clickAddProvider() {
        page.locator(addProviderButton).click();
    }

    public void clickPopupAddProvider() {
        page.locator(popupAddProvider).click();
    }

    public void clickNext() {
        page.locator(nextButton).click();
    }

    public void enterNpi(String npi) {
        page.locator(npiField).fill(npi);
    }

    

    public Page clickAddAndCapturePopup() {
        return page.waitForPopup(() ->
                page.locator(addButton).click()
        );
    }
}