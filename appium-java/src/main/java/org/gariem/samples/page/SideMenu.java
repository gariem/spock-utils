package org.gariem.samples.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.util.concurrent.TimeUnit;

/**
 * Created by gariem on 9.4.2018.
 */
public class SideMenu extends BasePage {

    public SideMenu(AppiumDriver driver) {
        super(driver);
    }

    //private By settingsOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]");
    private By settingsOption = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.TextView[3]");

    @Step("Click on the settings option")
    public void clickOnSettingsOption() throws InterruptedException {

        boolean isFoundElement = driver.findElements(settingsOption).size() > 0;
        while (isFoundElement == false) {
            swipeVertical((AppiumDriver) driver, 0.9, 0.1, 0.5, 2000);
            isFoundElement = driver.findElements(settingsOption).size() > 0;
        }
        TimeUnit.SECONDS.sleep(2);
        driver.findElement(settingsOption).click();
    }

    //Needed to scroll-down the side menu options
    public static void swipeVertical(AppiumDriver driver, double startPercentage, double finalPercentage, double anchorPercentage, int duration) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * finalPercentage);
        new TouchAction(driver).press(anchor, startPoint).waitAction(duration).moveTo(anchor, endPoint).release().perform();
    }

}
