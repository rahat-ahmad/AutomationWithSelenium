package com.automation.pages;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ApartmentDetailPage {

    public Integer numberOfBedRoom;
    public List<Integer> listOfDiffAmountForBedRoom = new ArrayList<>();

    public void setnumberOfBedRoom(WebElement element){
        numberOfBedRoom = Integer.parseInt(element.getText().replace(" Bedroom","").trim());
    }

    public void setListOfDiffAmountForBedRoom(List<WebElement> elements){

        for(int i = 0;i<elements.size();i++){
            listOfDiffAmountForBedRoom.add(Integer.parseInt(elements.get(i).getText().replace(" from $","").trim().replace(",","").trim()));
        }

    }
}
