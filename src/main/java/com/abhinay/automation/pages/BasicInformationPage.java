package com.abhinay.automation.pages;

import com.abhinay.automation.base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BasicInformationPage extends BasePage {

    public BasicInformationPage(Page page) {
        super(page);
    }

    private final String basicInformationHeading =
            "h3:has-text('Basic Information')";

    private final String ssnField =
            "input[name='ssn']";

    private final String emailField =
            "input[name='email']";

    private final String dobField =
            "input[name='dob']";
    private final String saveNextBtn = "text=Save & Next";

    public void verifyBasicInformationPageLoaded() {

        assertThat(
                page.locator(basicInformationHeading)
        ).isVisible();

        System.out.println("Basic Information Page Loaded");
    }

    public void enterSsn(String ssn) {

        System.out.println("Entering SSN");

        page.locator(ssnField)
                .fill(ssn);
    }

    

    public void enterEmail(String email) {

        System.out.println("Entering Email");

        page.locator(emailField)
                .fill(email);
    }

    public void enterDob(String dob) {

        System.out.println("Entering DOB");

        page.locator(dobField)
                .fill(dob);
    }

        public void clickSaveAndNext() {
        page.locator(saveNextBtn).click();
        page.waitForLoadState();
        page.waitForTimeout(5000);
        }

        public void clickAttestationSaveAndNext() {

        page.locator("#complete")
                .click();

        page.waitForLoadState();
        page.waitForTimeout(5000);
        }

    public void selectAllYes() {

        Locator yesLabels = page.locator(
                "label[for^='questionRadioBtn'][for$='0']"
        );

        int count = yesLabels.count();

        System.out.println("Found " + count + " Yes radio buttons");

        for (int i = 0; i < count; i++) {

            Locator label = yesLabels.nth(i);

            label.scrollIntoViewIfNeeded();

            label.click();

            System.out.println("Clicked Yes button " + (i + 1));
        }
    }

    public void clickSignNow() {

        page.locator("button:has-text('Sign Now')")
                .click();
    }

        public void clickTypeSignature() {

        page.getByRole(
                com.microsoft.playwright.options.AriaRole.BUTTON,
                new Page.GetByRoleOptions()
                        .setName("TYPE")
        ).click();

        System.out.println("Clicked TYPE");
        }

    public void enterSignature(String signature) {

        page.locator("#signature_text_input")
                .fill(signature);
    }

    public void clickSignAndComplete() {

        page.locator("#submit_form_button")
                .click();
    }
}