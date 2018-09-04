package org.gariem.samples.setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by gariem on 9.4.2018.
 */
public class DeviceSetup extends BaseTest {

    static AppiumDriver prepareDevice() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Samsung Galaxy S8");
        caps.setCapability("udid", "ce03171379614c130c"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "7.0");
        caps.setCapability("skipUnlock", "true");
        caps.setCapability("appPackage", "com.google.android.apps.maps");
        caps.setCapability("appActivity", "com.google.android.maps.MapsActivity");
        caps.setCapability("noReset", "false");
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        return driver;
    }
}
