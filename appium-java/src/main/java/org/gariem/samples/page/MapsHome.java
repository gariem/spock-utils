package org.gariem.samples.page;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

/**
 * Created by gariem on 9.4.2018.
 */
public class MapsHome extends BasePage {

    private By menuButton = By.id("com.google.android.apps.maps:id/search_omnibox_menu_button");

    public MapsHome(AppiumDriver driver) {
        super(driver);
    }

    @Step("Click on the menu button")
    public void clickOnMenuButton() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(menuButton)).click();
    }

}
