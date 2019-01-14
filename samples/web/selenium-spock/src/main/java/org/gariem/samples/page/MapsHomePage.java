package org.gariem.samples.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by gariem on 9.4.2018.
 */
public class MapsHomePage extends BasePage {

    private By searchBox = By.id("searchboxinput");
    private By searchBoxButton = By.id("searchbox-searchbutton");

    public MapsHomePage(ChromeDriver driver) {
        super(driver);
    }

    public void enterSearchText(String searchText){
        driver.findElement(searchBox).sendKeys(searchText);
    }

    public void clickSearchButton(){
        driver.findElement(searchBoxButton).click();
    }

}
