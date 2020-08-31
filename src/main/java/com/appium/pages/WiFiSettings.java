package com.appium.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WiFiSettings extends BaseActions{

    public WiFiSettings(AndroidDriver<WebElement> driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void selectAndTypeWiFiSettings(String text){
        clickWiFiSettings();
        setEditTextWidget().sendKeys(text);
    }

    public WebElement setEditTextWidget(){
       return driver.findElementByClassName("android.widget.EditText");
    }

    public WebElement setWiFiCheckBox(){
       return driver.findElementByXPath("//android.widget.CheckBox");
    }

    public void clickWiFiSettings(){
        driver.findElementsByXPath("//android.widget.RelativeLayout").get(1).click();
    }


    public void selectWiFi(){
            driver.findElementByXPath("//android.widget.CheckBox").click();
        }
    }

