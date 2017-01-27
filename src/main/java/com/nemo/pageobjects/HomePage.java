package com.nemo.pageobjects;

import omelet.common.ExpectedConditionExtended;
import omelet.data.IProperty;
import omelet.driver.DriverUtility;
import omelet.exception.FrameworkException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chan20 on 12/30/16.
 */
public class HomePage {
    private WebDriver driver;
    private IProperty prop;
    public int pageTimeOut = 60;

     @FindBys(@FindBy(css = "[class*='service-navigation__item__country']"))
    private List<WebElement> countryNameList;


    @FindBy(css=".service-navigation__dropdown>ul>li>a>span:not(.arrow)")
    private WebElement arrow;

    @FindBy(css=".service-navigation__item__country-gb")
    private WebElement ukCountry;

    @FindBy(css=".service-navigation__item__country-in")
    private WebElement inCountry;

    @FindBys(@FindBy(css = ".navigation.cf.list--is-unstyled>li>a>span"))
    private List<WebElement> menuItems;

    public HomePage(WebDriver driver, IProperty prop) {
        this.driver = driver;
        this.prop = prop;
        PageFactory.initElements(driver, this);
    }

    public HomePage load(String url) {
        driver.get(url);
        return this;
    }

    public HomePage isLoaded() {
        if (null == DriverUtility.waitFor(ExpectedConditionExtended.elementsToBeClickable(arrow), driver, pageTimeOut)) {
            throw new FrameworkException("HomePage is not loaded in " + pageTimeOut);
        }

        return this;
    }

    public List<WebElement> getCountryList() {
        return countryNameList;
    }

    public void getNextCountry() {
    	Actions MouseHover= new Actions(driver);
    	for (int i=0;i<countryNameList.size();i++){
    		if(prop.getValue(DataEnum.HomePage_Country).equals("Unite States")){
    			break;
    		}
    		else if(prop.getValue(DataEnum.HomePage_Country).equals("United Kingdom")){
    		
        	MouseHover.moveToElement(countryNameList.get(i)).click().perform();
        	MouseHover.moveToElement(ukCountry).click().build().perform();
        	break;
    		}
    		else if(prop.getValue(DataEnum.HomePage_Country).equals("India")){
    		MouseHover.moveToElement(countryNameList.get(i)).click().perform();
        	MouseHover.moveToElement(inCountry).click().build().perform();
        	break;
    		}
    			
    	}
    }
    	
        public List<String> getHeaderLinks() {
        List<String> headerStrings = new ArrayList<String>();
        for (WebElement headerTabs : menuItems) {
            headerStrings.add(headerTabs.getText());
        }
        return headerStrings;
    }

    public List<WebElement> getMenuItems() {

        return menuItems;
    }

}
