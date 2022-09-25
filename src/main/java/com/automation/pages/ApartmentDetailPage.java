package com.automation.pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ApartmentDetailPage {

    public List<Integer> listOfDiffAmountForBedRoom = new ArrayList<>();

    public void setListOfDiffAmountForBedRoom(List<WebElement> elements){
        for(int i = 0;i<elements.size();i++){
            System.out.println("Amount are: "+elements.get(i).getText());
            System.out.println("Amount after trim: "+elements.get(i).getText().replace("from $","").trim().replace(",","").trim());
            if(elements.get(i).getText().contains("Call for Pricing")){
                listOfDiffAmountForBedRoom.add(0);
            }
            listOfDiffAmountForBedRoom.add(Integer.parseInt(elements.get(i).getText().replace("from $","").trim().replace(",","").trim()));
        }
        System.out.println("List of price: "+ listOfDiffAmountForBedRoom.toString());

    }
}
