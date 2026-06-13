# Provider Onboarding Automation

Automated end-to-end testing framework built using Java, Playwright, Maven, and TestNG.

## Overview

This project automates the Provider Onboarding workflow, including:

- Login to the application
- Navigate to Providers
- Search for an existing provider using NPI
- Continue onboarding if the provider already exists
- Add a new provider if not found
- Complete Basic Information
- Handle conditional onboarding flows
- Complete Attestation
- Initiate Sign Now workflow
- Capture screenshots during execution

---

## Tech Stack

- Java
- Playwright
- Maven
- TestNG

---

## Project Structure

```
src
├── main
│   └── java
│       └── com.abhinay.automation.pages
│           ├── LoginPage.java
│           ├── DashboardPage.java
│           ├── ProvidersPage.java
│           └── BasicInformationPage.java
│
└── test
    └── java
        └── com.abhinay.automation.tests
            ├── BaseTest.java
            └── AddProviderTest.java
```

---

## Features

### Existing Provider Flow

- Search provider by NPI
- Click Continue Onboarding
- Wait for onboarding screen
- Detect whether Basic Information is already completed
- If Disclosure Questionnaire is displayed:
    - Skip Basic Information
    - Continue directly to Attestation

### New Provider Flow

- Add Provider
- Enter NPI
- Complete Basic Information
- Continue onboarding

---

## Screenshots Captured

The framework captures screenshots at key checkpoints:

| Screenshot | Description |
|------------|-------------|
| after_login.png | After successful login |
| add_existing_user.png | After opening onboarding |
| onboarding_process.png | Before signature process |

Screenshots are stored in:

```
screenshots/
```

---

## Prerequisites

Install:

- Java 17 or above
- Maven
- Git
- IntelliJ IDEA (recommended)

Verify installation:

```bash
java -version
mvn -version
git --version
```

---

## Installation

Clone the repository:

```bash
git clone https://github.com/<your-username>/<repository-name>.git
```

Move into the project:

```bash
cd <repository-name>
```

Install dependencies:

```bash
mvn clean install
```

---

## Running Tests

Execute all tests:

```bash
mvn test
```

Execute a specific test:

```bash
mvn test -Dtest=AddProviderTest
```

---

## Test Scenario

### Verify Provider Can Be Added

1. Login to the application
2. Open Providers
3. Select Location
4. Search using NPI
5. If provider exists:
    - Continue Onboarding
    - Check onboarding state
6. Else:
    - Add Provider
    - Complete onboarding
7. Complete Attestation
8. Sign the document

---

## Conditional Logic Implemented

If the onboarding resumes at the Disclosure Questionnaire stage:

The following steps are skipped:

```java
basic.enterSsn(...);
basic.enterEmail(...);
basic.enterDob(...);

basic.clickSaveAndNext();
basic.clickSaveAndNext();
basic.clickSaveAndNext();
```

Execution continues directly with:

```java
basic.clickAttestationSaveAndNext();
```

---

## Notes

- Uses Page Object Model (POM).
- Uses Playwright's popup handling.
- Includes screenshot support.
- Supports both new and existing provider onboarding scenarios.

---

## Author

Abhinay Mishra

Automation Engineer
