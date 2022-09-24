package com.automation.services;

import com.automation.pages.HomePageInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ApartmentInfoFromHomePage {

    WebDriverWait wait;

    WebElement elementOfApartmentFromHomePage ;

    public ApartmentInfoFromHomePage() {
    }

    public ApartmentInfoFromHomePage(WebDriverWait wait){
        this.wait = wait;
    }

    public List<HomePageInfo> getAparmentInfoFromHomePage() throws InterruptedException {
        List<HomePageInfo> homePageInfoList = new ArrayList<HomePageInfo>();

        for(int i = 0; i<getTotalApartmentCount();i++){
            HomePageInfo homePageInfo = new HomePageInfo();
            homePageInfo.setApartmentName(getElemntOfApartment(i));
            System.out.println("Apartment Name: "+ homePageInfo.apartmentName);
            homePageInfo.seturl(getElemntOfApartment(i));
            System.out.println("Apartment URL: "+ homePageInfo.url);
            homePageInfo.setBedListPerEachApartmentAndMinPriceBed(getElemntOfApartment(i),i);
            System.out.println("Apartment Min price count: "+ homePageInfo.minPricePerBed.size());
            homePageInfoList.add(homePageInfo);
        }

        return homePageInfoList;
    }

    public int getTotalApartmentCount(){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//section[@class='search-column']/ul/li"))).size();

    }

    public WebElement getElemntOfApartment(int i){
        elementOfApartmentFromHomePage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//section[@class='search-column']/ul/li"))).get(i);
        return elementOfApartmentFromHomePage;
    }



}
