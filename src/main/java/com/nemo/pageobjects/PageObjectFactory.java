package com.nemo.pageobjects;

import omelet.data.IProperty;
import omelet.data.driverconf.IBrowserConf;
import omelet.driver.Driver;
import org.openqa.selenium.WebDriver;

/**
 * Created by chan20 on 12/29/16.
 */
public class PageObjectFactory {
    private WebDriver driver;
    private IProperty prop;

    private HomePage homePage;
    private NewsAndExpertOpinions newsAndExpertOpinions;

    public PageObjectFactory(IBrowserConf browserConf, IProperty prop) {
        this.prop = prop;
        driver = Driver.getDriver(browserConf);
        driver.manage().window().maximize();
    }


    public HomePage homePage() {
        if (homePage == null)
            homePage = new HomePage(driver, prop);

        return homePage;

    }

    public NewsAndExpertOpinions newsAndExpertOpinions() {
        if (newsAndExpertOpinions == null)
            newsAndExpertOpinions = new NewsAndExpertOpinions(driver, prop);

        return newsAndExpertOpinions;

    }
}
