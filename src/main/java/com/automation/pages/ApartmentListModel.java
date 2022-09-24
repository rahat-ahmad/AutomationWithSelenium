package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class ApartmentListModel {

    public Map<String,String> apartmentList = new HashMap<>();
    public WebDriver driver;
    public WebDriverWait wait;
    public WebElement apartment;

    public ApartmentListModel() {
    }

    public ApartmentListModel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.setApartmentList();
    }

    public void setApartmentList(){
        apartment = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//section[@class='search-column']/ul/li"))).get(1); //Getting all the apartment list
        String apartmentName = apartment.findElement(By.tagName("a")).getAttribute("title");
        String apartmentURL = apartment.findElement(By.tagName("a")).getAttribute("href");
        apartmentList.put(apartmentName,apartmentURL);
    }

    public Map<String,String> getApartmentList(){
        return this.apartmentList;
    }

}
