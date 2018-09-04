package org.gariem.samples.page;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

/**
 * Created by gariem on 9.4.2018.
 */
public class Settings extends BasePage {

    public Settings(AppiumDriver driver) {
        super(driver);
    }

    private By returnButton = By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]");

    @Step("Click on the return button")
    public void clickOnReturnButton() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(returnButton)).click();
    }
}
