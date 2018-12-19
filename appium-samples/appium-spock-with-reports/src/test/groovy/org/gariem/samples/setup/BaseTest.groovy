package org.gariem.samples.setup

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebDriver
import spock.lang.Specification

class BaseTest extends Specification {

    public static AppiumDriver driver

    def setup() {
        DeviceSetup.prepareDevice()
    }

    def cleanup() {
        if (driver != null) driver.quit()
    }

    WebDriver getDriver() {
        return driver
    }

}
