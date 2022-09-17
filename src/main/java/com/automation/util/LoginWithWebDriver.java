package com.automation.util;

import org.openqa.selenium.WebDriver;

public class LoginWithWebDriver {
    public void facebookUI() throws InterruptedException {

        WebDriverInitialization webDriverInitialization = new WebDriverInitialization();
        WebDriver driver = webDriverInitialization.getChromeDriver();
        driver.get("https://facebook.com");
        Thread.sleep(10);
        driver.quit();

    }

    public static void main(String[] args) throws InterruptedException {
        LoginWithWebDriver loginWithWebDriver = new LoginWithWebDriver();
        loginWithWebDriver.facebookUI();


    }
}
