package org.gariem.samples.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UniqueSearchResultPage extends BasePage {

    By placeTitle = By.className("section-hero-header-title");
    By placeAddress = By.className("section-info-line");

    public UniqueSearchResultPage(ChromeDriver driver) {
        super(driver);
    }

    public String getPlaceTitle(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(placeTitle)).getText();
    }

    public String getPlaceAddress(){
        return wait.until(ExpectedConditions.presenceOfElementLocated(placeAddress)).getText().trim();
    }

}
