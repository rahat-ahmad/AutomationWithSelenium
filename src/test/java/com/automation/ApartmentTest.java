package com.automation;

import com.automation.pages.ApartmentListModel;
import com.automation.pages.HomePageInfo;
import com.automation.services.ApartmentDetailInfo;
import com.automation.services.ApartmentInfoFromHomePage;
import com.automation.util.WebDriverInitialization;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ApartmentTest {
    WebDriver driver;
    WebDriverWait wait;
    ApartmentListModel apartmentList;
    JavascriptExecutor js;

    @BeforeMethod()
    public void beforeClass() throws FileNotFoundException {
        driver = new WebDriverInitialization().getChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://bestrentnj.com/all-nj-apartments/");
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @Test
    public void loginWithValidCredentials() throws InterruptedException {
        ApartmentInfoFromHomePage apartmentInfoFromHomePage = new ApartmentInfoFromHomePage(wait);
        List<HomePageInfo> homePageInfoList = new ArrayList<HomePageInfo>();
        homePageInfoList = apartmentInfoFromHomePage.getAparmentInfoFromHomePage();
        for(int i = 0;i < homePageInfoList.size();i++){
            driver.get(homePageInfoList.get(i).url);
            js.executeScript("window.scrollBy(0,1000)");
            ApartmentDetailInfo apartmentDetailInfo = new ApartmentDetailInfo(wait);
            System.out.println("Print the minimum cost size of Detail page: " + apartmentDetailInfo.getAllMinRoomCostFromDetail().size());
            System.out.println("Print the minimum cost size of home page: " + homePageInfoList.get(i).minPricePerBed.size());
            System.out.println("Is Homepage min and detail page min equal? " + apartmentDetailInfo.getAllMinRoomCostFromDetail().equals(homePageInfoList.get(i).minPricePerBed));

        }

    }

}
