package org.gariem.samples.page;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

/**
 * Created by gariem on 9.4.2018.
 */
public class SideMenu extends BasePage {

    public SideMenu(AppiumDriver driver) {
        super(driver);
    }

    private By settingsOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]");

    @Step("Click on the settings option")
    public void clickOnSettingsOption() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        wait.until(ExpectedConditions.elementToBeClickable(settingsOption)).click();
    }

}
