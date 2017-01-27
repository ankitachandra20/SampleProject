package com.nemo.test;

import com.nemo.pageobjects.DataEnum;
import com.nemo.pageobjects.PageObjectFactory;
import omelet.testng.support.SAssert;
import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by chan20 on 1/3/17.
 */
public class CountrySwitch {
    SAssert sassert = new SAssert();


    @Test(description = "Checking country swtich", dataProviderClass = omelet.data.DataProvider.class, dataProvider = "XmlData", groups = {
            "CountrySwitch", "All", "SmokeTest"})
    public void countrySwitchCheck(IBrowserConf browserConf,
                                   IProperty prop) throws ParseException {
        System.out.println("entered method");

        PageObjectFactory pof = new PageObjectFactory(browserConf, prop);
        pof.homePage().load(prop.getValue(DataEnum.HomePage_URL));
        pof.homePage().isLoaded();

        List<WebElement> countryList = new ArrayList<WebElement>();

        countryList=pof.homePage().getCountryList();

       for(int i=0;i<countryList.size();i++) {
         
           if (prop.getValue(DataEnum.HomePage_Country).equalsIgnoreCase("United States")) {
        	   sassert.assertEquals(countryList.get(i).getText(), prop.getValue(DataEnum.HomePage_Country), "Verify Country displayed in service navigation bar");
               getHeaderMenuItems(browserConf, prop);
               pof.homePage().getMenuItems().get(0).click();
               sassert.assertEquals(pof.newsAndExpertOpinions().dateFormat(), prop.getValue(DataEnum.NewsAndExpertOpinions_Date), "Validate date for US has been displayed or not?");
               break;
           }
           
           
           else if (prop.getValue(DataEnum.HomePage_Country).equalsIgnoreCase("United Kingdom")) {
        	   pof.homePage().getNextCountry();
        	   sassert.assertEquals(countryList.get(i).getText(), prop.getValue(DataEnum.HomePage_Country), "Verify Country displayed in service navigation bar");
               getHeaderMenuItems(browserConf, prop);
               pof.homePage().getMenuItems().get(0).click();
              // sassert.assertEquals(pof.newsAndExpertOpinions().dateFormat(), prop.getValue(DataEnum.NewsAndExpertOpinions_Date), "Validate date for Uk has been displayed or not?");
               break;
           }
           
           else if (prop.getValue(DataEnum.HomePage_Country).equalsIgnoreCase("India")) {
        	   pof.homePage().getNextCountry();
        	   sassert.assertEquals(countryList.get(i).getText(), prop.getValue(DataEnum.HomePage_Country), "Verify Country displayed in service navigation bar");
               getHeaderMenuItems(browserConf, prop);
               pof.homePage().getMenuItems().get(0).click();
             //  sassert.assertEquals(pof.newsAndExpertOpinions().dateFormat(), prop.getValue(DataEnum.NewsAndExpertOpinions_Date), "Validate date for Uk has been displayed or not?");
               break;
           }
           sassert.assertAll();
       }

    }

    public void getHeaderMenuItems(IBrowserConf browserConf, IProperty prop){
        PageObjectFactory pof = new PageObjectFactory(browserConf, prop);

        List<String> expectedMenuItems = new ArrayList<String>();
        String menuItems = prop.getValue(DataEnum.HomePage_MenuItems);
        expectedMenuItems = Arrays.asList(menuItems.split(","));

        for (int i = 0; i < expectedMenuItems.size(); i++) {

            sassert.assertEquals(pof.homePage().getHeaderLinks().get(i).toString().trim(), expectedMenuItems.get(i).toString().trim(), "Verify menu items as per country selected");
          
        }
    }
}
