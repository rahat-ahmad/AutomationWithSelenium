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
        apartmentName = element.findElement(By.tagName("a")).getAttribute("title").trim();  //getting title from home page list; List can be found from other component
    }

    public void seturl(WebElement element) throws InterruptedException {
        url = element.findElement(By.tagName("a")).getAttribute("href").trim();
    }

    public void setBedListPerEachApartmentAndMinPriceBed(WebElement element , int i){

        if(element.findElements(By.xpath("//p[@class='beds']")).get(i).getText().contains("Call for Pricing")){
            bedListPerEachApartment.add("Call for Pricing");
            minPricePerBed.add(0);
        }

        else{
            String lines[] = element.findElements(By.xpath("//p[@class='beds']")).get(i).getText().split("\\r?\\n");

            List<String> bedText = new ArrayList<String>();

            //Getting all the bed and min cost of a Apartment
            for (int j = 0 ; j < lines.length; j ++){
                bedText.addAll(Arrays.asList(lines[j].split(Pattern.quote(" from $"))));
            }

            int k = 0;
            //Separte bedList and min cost in list
            for (int j = 0; j < bedText.size();j=j+2){
                bedListPerEachApartment.add(bedText.get(j));
                System.out.println("Total Bed: "+ bedListPerEachApartment.get(k));
                Integer minPrice = Integer.parseInt(bedText.get(j+1).replace(",",""));
                minPricePerBed.add(minPrice);
                System.out.println("Min Price of that bed: "+ minPricePerBed.get(k));
                k++;
            }

        }

    }

}
