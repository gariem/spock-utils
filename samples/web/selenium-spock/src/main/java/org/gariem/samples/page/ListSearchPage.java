package org.gariem.samples.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ListSearchPage extends BasePage {

    By ListSearch = By.className("section-result");


    public ListSearchPage(ChromeDriver driver) {
        super(driver);
    }

    public void click(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ListSearch));
        
        driver.findElements(ListSearch).get(0).click();

    }
}
