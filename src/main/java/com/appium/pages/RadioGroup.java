package com.appium.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RadioGroup extends BaseActions{

    public RadioGroup(AndroidDriver<WebElement> driver, WebDriverWait wait) {
        super(driver, wait);
    }
}
