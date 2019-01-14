package org.gariem.samples.setup


import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import spock.lang.Specification

class BaseTest extends Specification {

    public static ChromeDriver driver

    def setup() {
        driver = new ChromeDriver()
    }

    def cleanup() {
        if (driver != null) driver.quit()
    }

    WebDriver getDriver() {
        return driver
    }

}
