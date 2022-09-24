package com.automation;

import com.automation.pages.HomePageInfo;
import com.automation.services.ApartmentDetailInfo;
import com.automation.services.ApartmentInfoFromHomePage;
import com.automation.util.CSVFileWrite;
import com.automation.util.WebDriverInitialization;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApartmentTest {
    WebDriver driver;
    WebDriverWait wait;
    static JavascriptExecutor js;

    @BeforeMethod()
    public void beforeClass() {
        driver = new WebDriverInitialization().getChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://bestrentnj.com/all-nj-apartments/");
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @Test
//    @Ignore
    public void loginWithValidCredentials() throws InterruptedException, IOException {
        ApartmentInfoFromHomePage apartmentInfoFromHomePage = new ApartmentInfoFromHomePage(wait);
        List<HomePageInfo> homePageInfoList = new ArrayList<HomePageInfo>();
        homePageInfoList = apartmentInfoFromHomePage.getAparmentInfoFromHomePage();
        List<String> listOfNameOfMismatchedApartmentCost = new ArrayList<>();
        for (int i = 0; i < homePageInfoList.size(); i++) {

            System.out.println("Detail of Apartment: " + homePageInfoList.get(i).apartmentName);

            try {
                driver.get(homePageInfoList.get(i).url);
                js.executeScript("window.scrollBy(0,500)");
                Thread.sleep(3000);
                ApartmentDetailInfo apartmentDetailInfo = new ApartmentDetailInfo(wait);
                boolean priceMatchResult = apartmentDetailInfo.getAllMinRoomCostFromDetail().equals(homePageInfoList.get(i).minPricePerBed);
                if (!priceMatchResult) {
                    listOfNameOfMismatchedApartmentCost.add(homePageInfoList.get(i).apartmentName);
                }
                System.out.println("Is Homepage min and detail page min equal? " + priceMatchResult);
            } catch (Exception e) {
                listOfNameOfMismatchedApartmentCost.add(homePageInfoList.get(i).apartmentName);
                continue;
            }

        }
        CSVFileWrite csvFileWrite = new CSVFileWrite();
        csvFileWrite.resultInFile(listOfNameOfMismatchedApartmentCost); //Store the failed result into CSV file

    }

    @Test
    @Ignore
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(9);
        list.add(3);
        list.add(1);
        System.out.println("Getting minimum amount: " + Collections.min(list));
    }

}
