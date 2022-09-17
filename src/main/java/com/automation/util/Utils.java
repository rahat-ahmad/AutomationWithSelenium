package com.automation.util;

import org.openqa.selenium.WebDriver;

public class Utils {

    public static void goPreviousPage(WebDriver driver){
        driver.navigate().back();
    }
}
