package org.gariem.samples.tests;

import io.qameta.allure.*;
import org.gariem.samples.listener.TestListener;
import org.gariem.samples.page.MapsHome;
import org.gariem.samples.page.Settings;
import org.gariem.samples.page.SideMenu;
import org.gariem.samples.setup.BaseTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by gariem on 9.4.2018.
 */
@Listeners({TestListener.class})
@Epic("Sample Tests")
@Feature("Google Maps demo")


public class BasicNavigationTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Description("Test Description: Loads course list and asserts first course")
    @Story("Courses page")
    public void navigationTest() throws InterruptedException {
        //Courses courses = new Courses(driver);
        MapsHome home = new MapsHome(driver);
        Settings settings = new Settings(driver);
        SideMenu menu = new SideMenu(driver);

        home.clickOnMenuButton();
        menu.clickOnSettingsOption();
        settings.clickOnReturnButton();

    }


}
