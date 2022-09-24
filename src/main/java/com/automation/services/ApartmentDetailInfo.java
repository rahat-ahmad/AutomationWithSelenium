package com.automation.services;

import com.automation.pages.ApartmentDetailPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApartmentDetailInfo {

    public WebDriverWait wait;

    public ApartmentDetailInfo() {
    }

    public ApartmentDetailInfo(WebDriverWait wait){
        this.wait = wait;
    }

    public List<Integer> getAllMinRoomCostFromDetail(){
        List<Integer> minCostOfEachRoom = new ArrayList<>();
        for(int i = 0;i<getRoomCount();i++){
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='tableList']/li"))).get(i+1).click();
            minCostOfEachRoom.add(this.getMinimumAmountFromList(this.getAllThePriceFromARoom()));
        }
        return minCostOfEachRoom;
    }

    public int getRoomCount(){

        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='tableList']/li"))).size()-1;
    }

    public List<Integer> getAllThePriceFromARoom(){
        ApartmentDetailPage apartmentDetailPage = new ApartmentDetailPage();
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//table[@id='listings']/tbody/tr/td[3]")));
        apartmentDetailPage.setListOfDiffAmountForBedRoom(elements);
        return apartmentDetailPage.listOfDiffAmountForBedRoom;

    }

    public Integer getMinimumAmountFromList(List<Integer> list){

        return list.indexOf(Collections.min(list));
    }


}
