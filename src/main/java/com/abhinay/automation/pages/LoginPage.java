package com.abhinay.automation.pages;

import com.abhinay.automation.base.BasePage;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    // Locators
    private final String welcomeText = "text=Welcome";

    private final String emailField = "input[type='email']";

    private final String passwordButton =
            "button:has-text('Password')";

    private final String passwordField =
            "input[type='password']";

    private final String signInButton =
            "button:has-text('Sign In')";

    public void verifyLoginPageLoaded() {

        assertThat(page.locator(welcomeText))
                .isVisible();
    }

    public void enterEmail(String email) {

        page.locator(emailField)
                .fill(email);
    }

    public void clickPasswordButton() {

        page.locator(passwordButton)
                .click();
    }

    public void enterPassword(String password) {

        page.locator(passwordField)
                .fill(password);
    }

    public void clickSignIn() {

        page.locator(signInButton)
                .click();
    }

    public void login(String email, String password) {

        verifyLoginPageLoaded();

        page.locator(emailField).waitFor();

        enterEmail(email);

        clickPasswordButton();

        page.locator(passwordField).waitFor();

        enterPassword(password);

        clickSignIn();
    }
}