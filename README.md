# SeleniumFramework
Selenium Framework with POM, testNG,Extent reporting, CI/CD, Maven features along with E2E test for retail application
# SeleniumFramework
# 🚀 Selenium Automation Framework

> A scalable, CI-ready Selenium WebDriver framework built using Java, TestNG, and Maven — designed with clean architecture and enterprise-level best practices.

---

## 👨‍💻 About This Project

This project demonstrates a robust Selenium automation framework built using:

- 🔹 Java
- 🔹 Selenium WebDriver
- 🔹 TestNG
- 🔹 Maven
- 🔹 WebDriverManager
- 🔹 Page Object Model (POM)
- 🔹 Headless Execution Support
- 🔹 Jenkins CI Integration

The goal of this framework is to showcase clean automation design, reusability, and CI/CD readiness — aligned with real-world QA automation standards.

---

## 🏗️ Framework Design Philosophy

This framework follows:

- ✅ Page Object Model (POM)
- ✅ Separation of Concerns
- ✅ Centralized Driver Management
- ✅ Externalized Configuration
- ✅ Scalable & Maintainable Structure
- ✅ CI/CD Compatible Architecture

---

## 📂 Project Structure
SeleniumFramework/
│
├── src/main/java
│   ├── pageObjects/        → Page classes
│   ├── utilities/          → Reusable helpers
│   └── resources/          → Config & properties
│
├── src/test/java
│   ├── base/               → Base test setup
│   └── testCases/          → Test classes
│
├── testng.xml
├── pom.xml
└── README.md

---

## ⚙️ Key Features

- 🔹 Cross-browser ready (Chrome configured)
- 🔹 Headless execution toggle
- 🔹 Maven profile support
- 🔹 Screenshot capture on failure
- 🔹 TestNG grouping & parallel execution
- 🔹 Jenkins pipeline ready
- 🔹 Command-line execution support
- 🔹 Clean driver lifecycle management

---

## ▶️ How to Run Tests

### Run Full Suite
```bash
mvn test -PFull_Suite
mvn test -PFull_Suite -Dheadless=true
mvn -Dtest=LoginTest test
-Dheadless=true

boolean isHeadless = Boolean.parseBoolean(
    System.getProperty("headless", prop.getProperty("headless"))
);

if (isHeadless) {
    chromeOptions.addArguments("--headless=new");
    chromeOptions.addArguments("--window-size=1920,1080");
}
CI/CD Integration

This framework is Jenkins-ready.

In Jenkins Maven build step:

test -PFull_Suite -Dheadless=true

Supports:
	•	Scheduled builds
	•	Parameterized builds
	•	Headless CI execution
	•	Surefire reporting

### Reporting
Extent Report

This project demonstrates:
	•	Framework design capability
	•	Automation architecture thinking
	•	CI/CD integration understanding
	•	Clean and scalable coding standards
	•	Real-world test automation practices

It reflects production-grade automation principles rather than basic Selenium scripting.