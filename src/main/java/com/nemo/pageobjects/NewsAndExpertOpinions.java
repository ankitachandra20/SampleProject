package com.nemo.pageobjects;

import omelet.data.IProperty;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chan20 on 1/19/17.
 */
public class NewsAndExpertOpinions {
    private WebDriver driver;
    private IProperty prop;
    public int pageTimeOut = 60;

    @FindBys(@FindBy(css = ".tag-line--default"))
    private List<WebElement> articleDate;

    public NewsAndExpertOpinions(WebDriver driver, IProperty prop) {
        this.driver = driver;
        this.prop = prop;
        PageFactory.initElements(driver, this);
    }

    public String dateFormat() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");

        String date = articleDate.get(0).getText();
        Date d1 = sdf.parse(date);

        return sdf.format(d1);
    }

}
