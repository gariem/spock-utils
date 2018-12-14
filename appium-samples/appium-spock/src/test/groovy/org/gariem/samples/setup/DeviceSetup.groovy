package org.gariem.samples.setup

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities

class DeviceSetup extends BaseTest {

    static AppiumDriver prepareDevice() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities()
        capabilities.setCapability("deviceName", "Samsung Galaxy S8");
        capabilities.setCapability("udid", "33001cf7a8cab38b"); //DeviceId from "adb devices" command
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("skipUnlock", "true");
        capabilities.setCapability("appPackage", "com.google.android.apps.maps");
        capabilities.setCapability("appActivity", "com.google.android.maps.MapsActivity");
        capabilities.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities)
        return driver
    }

}
