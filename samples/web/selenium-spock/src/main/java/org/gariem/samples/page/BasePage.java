package org.gariem.samples.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by gariem on 1.14.2019.
 */
class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;


    public BasePage(ChromeDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    void click(By element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).click();
    }

    String getText(By element) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        return driver.findElement(element).getText();
    }
}
