package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class HomePageInfo {

    public String apartmentName;
    public String url;
    public List<String> bedListPerEachApartment = new ArrayList<String>();
    public List<Integer> minPricePerBed = new ArrayList<Integer>();

    public HomePageInfo() {
    }

    public void setApartmentName(WebElement element) throws InterruptedException {
        Thread.sleep(1000);
        apartmentName = element.findElement(By.tagName("a")).getAttribute("title").trim();  //getting title from home page list; List can be found from other component
    }

    public void seturl(WebElement element) throws InterruptedException {
        Thread.sleep(1000);
        url = element.findElement(By.tagName("a")).getAttribute("href").trim();
    }

    public void setBedListPerEachApartmentAndMinPriceBed(WebElement element){
        String lines[] = element.findElement(By.xpath("//p[@class='beds']")).getText().split("\\r?\\n");

        List<String> bedText = new ArrayList<String>();

        //Getting all the bed and min cost of a Apartment
        for (int i = 0 ; i < lines.length; i ++){
            bedText.addAll(Arrays.asList(lines[i].split(Pattern.quote(" Bedroom from $"))));
        }

        //Separte bedList and min cost in list
        for (int i = 0; i < bedText.size();i=i+2){
            bedListPerEachApartment.add(bedText.get(i));
            Integer minPrice = Integer.parseInt(bedText.get(i+1).replace(",",""));
            minPricePerBed.add(minPrice);
        }


    }

}
